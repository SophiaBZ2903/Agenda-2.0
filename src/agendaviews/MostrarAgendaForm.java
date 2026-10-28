package agendaviews;

import agenda.Agenda;
import agenda.ElementoAgenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostrarAgendaForm extends JFrame {
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton mostrarButton;

    private Agenda laAgenda;

    public MostrarAgendaForm(Agenda laAgenda) throws HeadlessException {
        this.laAgenda = laAgenda;
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocation(200,200);
        this.pack();

        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText(laAgenda.toString());
            }
        });
    }
}
