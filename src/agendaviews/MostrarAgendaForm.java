package agendaviews;

import agenda.Agenda;
import agenda.ContactoFamiliar;
import agenda.EventoReunion;
import agenda.EventoSocial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MostrarAgendaForm extends JFrame {

    private JPanel panelPrincipal;
    private JTextArea txtAreaAgenda; // Usamos un área de texto para mostrar toda la info limpia
    private JButton btnVolver;

    private Agenda agenda;

    public MostrarAgendaForm(Agenda agenda) {
        this.agenda = agenda;

        // 1. Inicializa los componentes gráficos
        initComponents();

        // 2. Seguridad por si el panel principal llega a estar nulo
        if (panelPrincipal == null) {
            panelPrincipal = new JPanel(new BorderLayout());
        }

        // 3. Cargar los datos de la agenda en el área de texto usando el toString()
        if (txtAreaAgenda != null && agenda != null) {
            txtAreaAgenda.setText(agenda.toString());
            txtAreaAgenda.setEditable(false); // Para que el usuario no lo pueda editar por accidente
        }

        // 4. Configuración básica de la ventana
        setTitle("Mostrar Agenda - Contenido");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);
        setVisible(true);

        // 5. Acción del botón para regresar al menú principal
        if (btnVolver != null) {
            btnVolver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainForm mainForm = new MainForm(agenda);
                    mainForm.setVisible(true);
                    dispose();
                }
            });
        }
    }

    private void initComponents() {
        if (panelPrincipal == null) {
            panelPrincipal = new JPanel(new BorderLayout());
        }
        
        if (txtAreaAgenda == null) {
            txtAreaAgenda = new JTextArea() {
                @Override
                public boolean getScrollableTracksViewportWidth() {
                    return true;
                }
            };
        }
        
        txtAreaAgenda.setLineWrap(true);       
        txtAreaAgenda.setWrapStyleWord(true);  
        txtAreaAgenda.setEditable(false);      

        if (btnVolver == null) {
            btnVolver = new JButton("Volver");
        }

        panelPrincipal.removeAll();
        panelPrincipal.setLayout(new BorderLayout());
        
        JScrollPane scrollPane = new JScrollPane(txtAreaAgenda);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        
        JButton btnAgregarContacto = new JButton("Agregar Contacto");
        btnAgregarContacto.addActionListener(e -> agregarContactoFamiliarDialogo());

        JButton btnAgregarEvento = new JButton("Agregar Evento");
        btnAgregarEvento.addActionListener(e -> agregarEventoDialogo());

        JPanel panelSur = new JPanel();
        panelSur.add(btnAgregarContacto);
        panelSur.add(btnAgregarEvento);
        panelSur.add(btnVolver);
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);
    }

    // Método para agregar un Contacto Familiar con validación de errores (Puntos 2 y 3)
    private void agregarContactoFamiliarDialogo() {
        try {
            // 1. Nombre
            String nombre = JOptionPane.showInputDialog(this, "Digite el nombre completo del familiar:");
            if (nombre == null) return; // Si le da a cancelar
            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2. Edad (Validación de número con try-catch)
            String edadStr = JOptionPane.showInputDialog(this, "Digite la edad (número entero):");
            if (edadStr == null) return;
            int edad = Integer.parseInt(edadStr.trim()); // Si meten letras, salta al NumberFormatException
            if (edad <= 0) {
                JOptionPane.showMessageDialog(this, "La edad debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Género
            String generoStr = JOptionPane.showInputDialog(this, "Digite el género (ej. 1 para M, 2 para F):");
            if (generoStr == null) return;
            int genero = Integer.parseInt(generoStr.trim());

            // 4. Teléfono
            String telefono = JOptionPane.showInputDialog(this, "Digite el teléfono:");
            if (telefono == null || telefono.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El teléfono no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 5. Cédula (Validación de número y cédula duplicada)
            String cedulaStr = JOptionPane.showInputDialog(this, "Digite la cédula (número entero único):");
            if (cedulaStr == null) return;
            int cedula = Integer.parseInt(cedulaStr.trim());

            // 🔍 Validar si ya existe en la agenda de tu profe
            if (agenda.buscarContacto(cedula) != null) {
                JOptionPane.showMessageDialog(this, "¡Error! Ya existe un contacto registrado con la cédula " + cedula, "Cédula Duplicada", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 6. Parentesco
            String parentesco = JOptionPane.showInputDialog(this, "Digite el parentesco (ej. Padre, Hermana, Prima):");
            if (parentesco == null || parentesco.trim().isEmpty()) {
                parentesco = "Familiar";
            }

            // Si todas las validaciones pasaron, creamos el objeto y lo metemos a la agenda
            ContactoFamiliar nuevoFamiliar = new ContactoFamiliar(nombre, edad, genero, telefono, cedula, parentesco);
            agenda.agregarContacto(nuevoFamiliar);

            // Actualizamos el área de texto para que se vea reflejado de inmediato
            txtAreaAgenda.setText(agenda.toString());
            
            JOptionPane.showMessageDialog(this, "¡Contacto familiar agregado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            // 🛑 Error atrapado si el usuario metió letras en campos numéricos (Edad, Género o Cédula)
            JOptionPane.showMessageDialog(this, "Error de formato: Debe ingresar obligatoriamente un número entero válido en los campos de edad, género y cédula.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void agregarEventoDialogo() {
        try {
            // 1. Preguntar el tipo de evento que desea agregar
            String[] opciones = {"Reunión", "Social"};
            int tipoSeleccionado = JOptionPane.showOptionDialog(
                    this,
                    "Seleccione el tipo de evento que desea agregar:",
                    "Tipo de Evento",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (tipoSeleccionado == JOptionPane.CLOSED_OPTION) return; // Si cierra la ventana

            // 2. Descripción del evento
            String descripcion = JOptionPane.showInputDialog(this, "Digite la descripción del evento:");
            if (descripcion == null) return;
            if (descripcion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "La descripción no puede estar vacía.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Fecha (Validación de formato correcto AAAA-MM-DD)
            String fechaStr = JOptionPane.showInputDialog(this, "Digite la fecha del evento (Formato: AAAA-MM-DD, ej. 2026-12-31):");
            if (fechaStr == null) return;
            
            LocalDate fecha;
            try {
                fecha = LocalDate.parse(fechaStr.trim()); // ⚠️ Validación: Si el formato es incorrecto, salta la excepción
            } catch (DateTimeParseException dtpEx) {
                JOptionPane.showMessageDialog(this, "Error de fecha: Debe usar exactamente el formato AAAA-MM-DD (Año-Mes-Día).", "Formato Inválido", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 4. Dependiendo del tipo de evento, pedimos su dato específico
            if (tipoSeleccionado == 0) { 
                // Es EventoReunion
                String cantStr = JOptionPane.showInputDialog(this, "Digite la cantidad de personas para la reunión (número entero):");
                if (cantStr == null) return;
                int cantidadPersonas = Integer.parseInt(cantStr.trim()); // Validación de número entero

                if (cantidadPersonas <= 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad de personas debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Creamos y agregamos la reunión a la agenda de tu profe
                agenda.agregarEvento(new EventoReunion(fecha, descripcion, cantidadPersonas));
                JOptionPane.showMessageDialog(this, "¡Reunión agregada con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } else { 
                // Es EventoSocial
                String lugar = JOptionPane.showInputDialog(this, "Digite el lugar del evento social:");
                if (lugar == null || lugar.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El lugar no puede estar vacío.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Creamos y agregamos el evento social a la agenda
                agenda.agregarEvento(new EventoSocial(fecha, descripcion, lugar));
                JOptionPane.showMessageDialog(this, "¡Evento social agregado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }

            // Refrescamos de inmediato el área de texto para que aparezca el nuevo evento listado
            txtAreaAgenda.setText(agenda.toString());

        } catch (NumberFormatException ex) {
            // 🛑 Atrapa el error si escriben letras en campos que piden números (como la cantidad de personas)
            JOptionPane.showMessageDialog(this, "Error de formato: Debe ingresar un número entero válido en la cantidad de personas.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }