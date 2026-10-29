package agendaviews;

import agenda.Agenda;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    
    // Componentes gráficos (Asegúrate de que tus nombres en el diseñador 
    // de gugacarbo coincidan exactamente con estos)
    private JPanel panelPrincipal;
    private JButton btnVerAgenda;
    private JButton btnSalir;
    
    private Agenda agenda;

    public MainForm(Agenda agenda) {
        this.agenda = agenda;

        // 1. Inicializa los componentes visuales generados por la extensión de VS Code
        initComponents();

        // 2. Seguridad por si el panel principal llega a estar nulo
        if (panelPrincipal == null) {
            panelPrincipal = new JPanel();
        }

        // 3. Configuración de la ventana principal
        setTitle("Menú Principal - Agenda");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        // 4. Comandos y acciones de los botones
        if (btnVerAgenda != null) {
            btnVerAgenda.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Abre la ventana secundaria pasando la agenda actual
                    MostrarAgendaForm mostrarForm = new MostrarAgendaForm(agenda);
                    mostrarForm.setVisible(true);
                    dispose(); // Cierra o oculta el menú principal
                }
            });
        }

        if (btnSalir != null) {
            btnSalir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }
    }

    /**
     * Método donde la extensión de Swing GUI Builder coloca la creación 
     * y posicionamiento de los componentes visuales. 
     * (Si la extensión te genera un initComponents propio, puedes reemplazar esta parte con el suyo).
     */
    private void initComponents() {
        // Si tu extensión genera este método automáticamente abajo o en otra sección, 
        // puedes dejarlo aquí o adaptarlo según lo que te cree gugacarbo.
        if (panelPrincipal == null) {
            panelPrincipal = new JPanel();
        }
        if (btnVerAgenda == null) {
            btnVerAgenda = new JButton("Ver Agenda");
        }
        if (btnSalir == null) {
            btnSalir = new JButton("Salir");
        }
        
        // Agregamos los componentes por defecto al panel por si acaso el diseñador los dejó pendientes
        panelPrincipal.add(btnVerAgenda);
        panelPrincipal.add(btnSalir);
    }
}