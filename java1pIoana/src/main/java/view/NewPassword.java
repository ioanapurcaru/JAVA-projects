package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.regex.Pattern;

public class NewPassword extends JFrame {

    public NewPassword() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 300);
        setTitle("Parola noua");
        setLocationRelativeTo(null);
        setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage("./1.jpg");
        setIconImage(icon);

        JLabel l1 = getL1();
        JLabel l2 = getL2();
        JLabel passwLabel = getPasswLabel();
        JTextField passw = new JPasswordField();
        JLabel confPasswLabel = getConfPasswLabel();
        JPasswordField confPassw = new JPasswordField();

        JLabel l3 = new JLabel();
        l3.setText("");

        JLabel l4 = new JLabel();
        l4.setText("");

        JPanel panel = getPanel(l1, l2, passwLabel, passw, confPasswLabel, confPassw, l3, l4);

        JButton changeButton = getChangeButton((JPasswordField) passw, confPassw);

        JPanel southPanel = getSouthPanel(changeButton);

        add(panel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    private JPanel getSouthPanel(JButton changeButton) {
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLACK);
        southPanel.add(changeButton, BorderLayout.CENTER);
        return southPanel;
    }

    private JButton getChangeButton(JPasswordField passw, JPasswordField confPassw) {
        JButton changeButton = new JButton("Schimba");
        changeButton.setForeground(Color.white);
        changeButton.setBackground(Color.BLACK);
        changeButton.setFont(new Font("Broadway", Font.PLAIN, 15));
        changeButton.addActionListener(e -> {
            boolean verificare = true;
            char[] litere = passw.getPassword();
            String parola = new String(litere);
            char[] litere2 = confPassw.getPassword();
            String confParola = new String(litere2);

            if (isNewPasswordUser(parola, confParola)) {
                Optional<User> usr = UserController.getInstance().findId(StaticConstants.id);
                if (usr.isPresent()) {
                    boolean b = UserController.getInstance().updatePassw(usr.get().getPassw(), parola);
                    if (b) {
                        Optional<User> user = UserController.getInstance().findId(StaticConstants.id);
                        if (user.isPresent()) {
                            Timestamp t = Timestamp.from(Instant.now());
                            StaticConstants.time.add(t);
                            StaticConstants.messages.add(user.get().getUsername() + " a schimbat parola. ");
                        }
                        JOptionPane.showMessageDialog(null, "Schimbare realizata cu succes!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        Login log = new Login();
                        StaticConstants.list.add(log);
                        dispose();
                    }
                }
            }
        });
        return changeButton;
    }

    private JPanel getPanel(JLabel l1, JLabel l2, JLabel passwLabel, JTextField passw, JLabel confPasswLabel, JPasswordField confPassw, JLabel l3, JLabel l4) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.setBackground(Color.black);
        panel.add(l1);
        panel.add(l2);
        panel.add(passwLabel);
        panel.add(passw);
        panel.add(confPasswLabel);
        panel.add(confPassw);
        panel.add(l3);
        panel.add(l4);
        return panel;
    }

    private JLabel getConfPasswLabel() {
        JLabel confPasswLabel = new JLabel("Confirma parola: ");
        confPasswLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        confPasswLabel.setFont(new Font("Elephant", Font.PLAIN, 15));
        confPasswLabel.setForeground(Color.WHITE);
        return confPasswLabel;
    }

    private JLabel getPasswLabel() {
        JLabel passwLabel = new JLabel("Parola noua: ");
        passwLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        passwLabel.setForeground(Color.WHITE);
        passwLabel.setFont(new Font("Elephant", Font.PLAIN, 15));
        return passwLabel;
    }

    private JLabel getL2() {
        JLabel l2 = new JLabel();
        l2.setText("");
        return l2;
    }

    private JLabel getL1() {
        JLabel l1 = new JLabel();
        l1.setText("Parola noua");
        l1.setFont(new Font("Broadway", Font.PLAIN, 18));
        l1.setForeground(Color.WHITE);
        l1.setBorder(new EmptyBorder(0, 90, 0, 0));
        return l1;
    }

    public boolean isNewPasswordUser(String s1, String s2) {
        boolean verificare = true;
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");

        if (s1.length() == 0) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Toate campurile trebuie completate!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        if (s1.length() < 6) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Parola trebuie sa contina minim 6 caractere!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        if (specialCharPatten.matcher(s1).find()) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Caractere invalide!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        if (!UpperCasePatten.matcher(s1).find() || !lowerCasePatten.matcher(s1).find()
                || !digitCasePatten.matcher(s1).find()) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Parola trebuie sa contina minim o litera mica, o litera mare si un numar!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }

        if (!s1.equals(s2)) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Parolele nu corespund!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        return verificare;
    }
}
