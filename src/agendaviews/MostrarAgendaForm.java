package agendaviews;

import agenda.Agenda;
import agenda.ContactoFamiliar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            // 🛠️ Forzamos al JTextArea a ajustarse siempre al ancho del panel con este pequeño override:
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
        
        JButton btnAgregar = new JButton("Agregar Contacto");
        btnAgregar.addActionListener(e -> agregarContactoFamiliarDialogo());  

        JPanel panelSur = new JPanel();
        panelSur.add(btnAgregar);
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
}