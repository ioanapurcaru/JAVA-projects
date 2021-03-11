package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.regex.Pattern;

public class RegisterPage extends JFrame {

    JTextField username = new JTextField();
    JTextField email = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField confPasswordField = new JPasswordField();

    public RegisterPage() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 350);
        setTitle("Register Page");
        setLocationRelativeTo(null);
        setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage("./1.jpg");
        setIconImage(icon);

        JLabel l1 = new JLabel("");
        JLabel title = getLabelTitle();
        JLabel register = getRegister();

        JPanel northPanel = getNorthPanel(l1, title, register);

        JLabel userLabel = getUserLabel();
        JLabel passwordLabel = getPasswLabel();
        JLabel emailLabel = getEmailLabel();
        JLabel confirmPasswordLabel = getConfPasswLabel();

        JPanel centerPanel = getCenterPanel(userLabel, passwordLabel, emailLabel, confirmPasswordLabel);

        JButton clearButton = getClearButton();
        JButton registerButton = getRegisterButton();

        JPanel southPanel = getSouthPanel(clearButton, registerButton);

        add(centerPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
    }

    private JPanel getNorthPanel(JLabel l1, JLabel title, JLabel register) {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(3, 0));
        northPanel.setBackground(Color.black);
        northPanel.add(l1);
        northPanel.add(title);
        northPanel.add(register);
        return northPanel;
    }

    private JPanel getCenterPanel(JLabel userLabel, JLabel passwordLabel, JLabel emailLabel, JLabel confirmPasswordLabel) {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1));
        centerPanel.setBackground(Color.black);
        centerPanel.add(userLabel);
        centerPanel.add(username);
        centerPanel.add(emailLabel);
        centerPanel.add(email);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        centerPanel.add(confirmPasswordLabel);
        centerPanel.add(confPasswordField);
        return centerPanel;
    }

    private JPanel getSouthPanel(JButton clearButton, JButton registerButton) {
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.black);
        southPanel.setLayout(new FlowLayout());
        southPanel.add(clearButton);
        southPanel.add(registerButton);
        return southPanel;
    }

    private JButton getRegisterButton() {
        JButton registerButton = new JButton("Inregistrare");
        registerButton.setForeground(Color.white);
        registerButton.setBackground(Color.black);
        registerButton.setFont(new Font("Broadway", Font.PLAIN, 14));
        registerButton.addActionListener(e -> {
            String parola = new String(passwordField.getPassword());
            String confParola = new String(confPasswordField.getPassword());
            if (isValid(username.getText(), email.getText(), parola, confParola)) {
                User user = new User(0, username.getText(), email.getText(), parola);
                if (UserController.getInstance().create(user)) {
                    Timestamp t = Timestamp.from(Instant.now());
                    //HistoryActions.actions.put(t, username.getText() + " has been registered");
                    StaticConstants.time.add(t);
                    StaticConstants.messages.add(username.getText() + " s-a delogat. ");
                    Login log = new Login();
                    StaticConstants.list.add(log);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Username-ul sau e-mail-ul sunt deja folosite!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return registerButton;
    }

    private JButton getClearButton() {
        JButton clearButton = new JButton("Resetare");
        clearButton.setForeground(Color.WHITE);
        clearButton.setBackground(Color.black);
        clearButton.setFont(new Font("Broadway", Font.PLAIN, 14));
        clearButton.addActionListener(e -> {
            username.setText("");
            passwordField.setText("");
            confPasswordField.setText("");
            email.setText("");
        });
        return clearButton;
    }

    private JLabel getConfPasswLabel() {
        JLabel confirmPasswordLabel = new JLabel("Confirma parola: ");
        confirmPasswordLabel.setFont(new Font("Broadway", Font.PLAIN, 16));
        confirmPasswordLabel.setForeground(Color.white);
        return confirmPasswordLabel;
    }

    private JLabel getEmailLabel() {
        JLabel emailLabel = new JLabel("E-mail: ");
        emailLabel.setFont(new Font("Broadway", Font.PLAIN, 16));
        emailLabel.setForeground(Color.white);
        return emailLabel;
    }

    private JLabel getPasswLabel() {
        JLabel passwordLabel = new JLabel("Parola: ");
        passwordLabel.setFont(new Font("Broadway", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.white);
        return passwordLabel;
    }

    private JLabel getUserLabel() {
        JLabel userLabel = new JLabel("Username: ");
        userLabel.setFont(new Font("Broadway", Font.PLAIN, 16));
        userLabel.setForeground(Color.white);
        return userLabel;
    }

    private JLabel getRegister() {
        JLabel register = new JLabel("Register");
        register.setBorder(new EmptyBorder(0, 140, 0, 0));
        register.setFont(new Font("Broadway", Font.ITALIC, 25));
        register.setForeground(Color.white);
        register.setAlignmentX(0.5f);
        register.setAlignmentY(0.5f);
        return register;
    }

    public JLabel getLabelTitle() {
        JLabel title = new JLabel("American Airlines");
        title.setBorder(new EmptyBorder(0, 90, 0, 0));
        title.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 30));
        title.setForeground(new Color(6, 143, 177, 226));
        title.setAlignmentX(0.5f);
        title.setAlignmentY(0.5f);
        return title;
    }

    public boolean isValid(String user, String mail, String parola, String confParola) {
        boolean verificare = true;
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        if (user.length() == 0) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Toate campurile trebuie completate!");
        }
        if (specialCharPatten.matcher(user).find() || user.contains(" ")) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Caractere invalide!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }

        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        if (parola.length() == 0) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Toate campurile trebuie completate!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        if (parola.length() < 6) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Parola trebuie sa contina minim 6 caractere!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        if (specialCharPatten.matcher(parola).find()) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Caractere invalide!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        if (!UpperCasePatten.matcher(parola).find() || !lowerCasePatten.matcher(parola).find()
                || !digitCasePatten.matcher(parola).find()) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Parola trebuie sa contina cel putin o litera mica, o litera mare si un numar!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }

        if (!parola.equals(confParola)) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Parolele nu corespund!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }

        int i, poz = 0;
        if (mail.length() == 0) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Toate campurile trebuie completate!", "Eroare",JOptionPane.ERROR_MESSAGE);
        }
        if (!mail.contains("@")) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "E-mail invalid!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        StringBuilder verif = new StringBuilder();
        for (i = 0; i < mail.length(); i++) {
            if (mail.charAt(i) == '@') {
                poz = i;
                break;
            }
            verif.append(mail.charAt(i));
        }
        if (verif.toString().equals("")) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "E-mail invalid!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        verif = new StringBuilder();
        for (i = poz + 1; i < mail.length(); i++) {
            if (mail.charAt(i) == '.') {
                break;
            }
            verif.append(mail.charAt(i));
        }
        if (verif.toString().equals("")) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "E-mail invalid!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        return verificare;
    }
}
