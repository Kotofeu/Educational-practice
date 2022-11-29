import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame {

    private JTextField textField;

    public Main() {
        super("Test frame");
        createGUI();
    }

    public void createGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        ActionListener actionListener = new TestActionListener();


        JButton button1 = new JButton("Задание 1");
        button1.setActionCommand("https://replit.com/@PretrovDS/Task-1#main.py");
        panel.add(button1);
        button1.addActionListener(actionListener);

        JButton button2 = new JButton("Задание 2");
        button2.setActionCommand("https://replit.com/@PretrovDS/Task-2#main.py");
        panel.add(button2);
        button2.addActionListener(actionListener);

        JButton button3 = new JButton("Задание 3");
        button3.setActionCommand("https://replit.com/@PretrovDS/Task-3#main.py");
        panel.add(button3);
        button3.addActionListener(actionListener);

        JButton button4 = new JButton("Задание 4");
        button4.setActionCommand("https://replit.com/@PretrovDS/Task-4#Main.java");
        panel.add(button4);
        button4.addActionListener(actionListener);

        JButton button5 = new JButton("Задание 5");
        button5.setActionCommand("https://replit.com/@PretrovDS/Task-5#main.py");
        panel.add(button5);
        button5.addActionListener(actionListener);

        JButton button6 = new JButton("Задание 6");
        button6.setActionCommand("https://replit.com/@PretrovDS/Task-6#main.py");
        panel.add(button6);
        button6.addActionListener(actionListener);

        JButton button7 = new JButton("Задание 7");
        button7.setActionCommand("https://replit.com/@PretrovDS/Task-7#Main.java");
        panel.add(button7);
        button7.addActionListener(actionListener);

        JButton button8 = new JButton("Задание 8");
        button8.setActionCommand("https://replit.com/@PretrovDS/Task-8#Main.java");
        panel.add(button8);
        button8.addActionListener(actionListener);

        getContentPane().add(panel);
        setPreferredSize(new Dimension(320, 200));
    }

    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Desktop.getDesktop().browse(new URL(e.getActionCommand()).toURI());
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                Main frame = new Main();
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

    }
}

