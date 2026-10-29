package agendaviews;

import agenda.Agenda;
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
        
        JPanel panelSur = new JPanel();
        panelSur.add(btnVolver);
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);
    }
}