package logarlecTheGame.View;

import javax.swing.*;

import logarlecTheGame.Model.*;

import java.awt.*;

public class ActionPanel extends JFrame{

    Player player;
    Board board;

    public ActionPanel() {
        setTitle("ComboBoxok és Gombok");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 3, 10, 10));
        panel.setBounds(100, 400, 500, 160); // A panel pozíciójának és méretének beállítása

        //String[] options = {"Opció 1", "Opció 2", "Opció 3"};
        Font font = new Font("Arial", Font.PLAIN, 20);

        String[] options = new String[player.getLocation().items().size()];
        for (int i = 0; i < player.getLocation().items().size(); i++) {
            options[i] = player.getLocation().items().getClass().getSimpleName();
        }

        JLabel s1 = new JLabel("Szoba tárgyai:");
        s1.setFont(font);
        panel.add(s1);
        JComboBox<String> comboBox1 = new JComboBox<>(options);
        panel.add(comboBox1);
        JButton button1 = new JButton("Tárgyfelvétel");
        panel.add(button1);

        JLabel s2 = new JLabel("Játékos tárgyai:");
        s2.setFont(font);
        panel.add(s2);
        JComboBox<String> comboBox2 = new JComboBox<>(options);
        panel.add(comboBox2);
        JButton button2 = new JButton("Tárgyletétel");
        panel.add(button2);

        JLabel s3 = new JLabel("Játékos tárgyai:");
        s3.setFont(font);
        panel.add(s3);
        JComboBox<String> comboBox3 = new JComboBox<>(options);
        panel.add(comboBox3);
        JButton button3 = new JButton("Párosítás");
        panel.add(button3);

        JLabel s4 = new JLabel("Szoba ajtói:");
        s4.setFont(font);
        panel.add(s4);
        JComboBox<String> comboBox4 = new JComboBox<>(options);
        panel.add(comboBox4);
        JButton button4 = new JButton("Ajtóhasználat");
        panel.add(button4);
        

        mainPanel.add(panel);

        getContentPane().add(mainPanel);
        setSize(300, 200);
        setLocationRelativeTo(null); // Ablak középre helyezése
        setVisible(true);
    }
}
