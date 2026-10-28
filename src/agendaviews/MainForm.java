package agendaviews;

import agenda.Agenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JPanel panel1;
    private JButton btAgregar;
    private JButton btMostrar;

    public MainForm(Agenda laAgenda) throws HeadlessException {
        this.setContentPane(panel1);
        this.setTitle("Mi Agenda");
        this.setLocation(100,100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        btMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new MostrarAgendaForm(laAgenda)).setVisible(true);
            }
        });
    }
}
