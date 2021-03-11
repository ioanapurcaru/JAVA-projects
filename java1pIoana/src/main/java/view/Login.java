package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

public class Login extends JFrame {

    public Login() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Login Page");
        setLocationRelativeTo(null);
        setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage("./1.jpg");
        setIconImage(icon);

        JLabel l1 = new JLabel("");
        JLabel title = getLabelTitle();

        JLabel login = getLogin();

        JPanel northPanel = getNorthPanel(l1, title, login);

        JTextField username = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel userLabel = getUserLabel();

        JLabel passwordLabel = getPasswLabel();

        JPanel centerPanel = getCenterPanel(username, passwordField, userLabel, passwordLabel);

        JButton loginButton = getLoginButton(username, passwordField);

        JButton clearButton = getClearButton(username, passwordField);

        JButton registerButton = getRegisterButton();

        JPanel southPanel = getSouthPanel(loginButton, clearButton, registerButton);

        add(centerPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
    }

    private JPanel getSouthPanel(JButton loginButton, JButton clearButton, JButton registerButton) {
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.black);
        southPanel.setLayout(new FlowLayout());
        southPanel.add(clearButton);
        southPanel.add(loginButton);
        southPanel.add(registerButton);
        return southPanel;
    }

    private JButton getRegisterButton() {
        JButton registerButton = new JButton("Inregistrare");
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(Color.BLACK);
        registerButton.setFont(new Font("Broadway", Font.PLAIN, 14));
        registerButton.addActionListener(e -> {
            RegisterPage r = new RegisterPage();
            dispose();
        });
        return registerButton;
    }

    private JButton getClearButton(JTextField username, JPasswordField passwordField) {
        JButton clearButton = new JButton("Resetare");
        clearButton.setForeground(Color.WHITE);
        clearButton.setBackground(Color.black);
        clearButton.setFont(new Font("Broadway", Font.PLAIN, 14));
        clearButton.addActionListener(e -> {
            username.setText("");
            passwordField.setText("");
        });
        return clearButton;
    }

    private JButton getLoginButton(JTextField username, JPasswordField passwordField) {
        JButton loginButton = new JButton("Logare");
        loginButton.setForeground(Color.white);
        loginButton.setBackground(Color.black);
        loginButton.setFont(new Font("Broadway", Font.PLAIN, 14));
        loginButton.addActionListener(e -> {
            if (isValidUser(username.getText())) {
                if (isValidMail(username.getText())) {
                    JOptionPane.showMessageDialog(null, "Contul nu exista!", "Eroare", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (new String(passwordField.getPassword()).equals(UserController.getInstance().findMail(username.getText()).get().getPassw())) {
                        Timestamp t = Timestamp.from(Instant.now());
                        //HistoryActions.actions.put(t, mail.get().getUsername() + " has been logged in");
                        StaticConstants.time.add(t);
                        StaticConstants.messages.add(UserController.getInstance().findUsername(username.getText()).get().getUsername() + " s-a logat.");
                        if (UserController.getInstance().findMail(username.getText()).isPresent()) {
                            StaticConstants.id = UserController.getInstance().findMail(username.getText()).get().getId();
                        }
                        Home home = new Home();
                        StaticConstants.list.add(home);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Contul nu exista!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                if (new String(passwordField.getPassword()).equals(UserController.getInstance().findUsername(username.getText()).get().getPassw())) {
                    Timestamp t = Timestamp.from(Instant.now());
                    StaticConstants.time.add(t);
                    StaticConstants.messages.add(UserController.getInstance().findUsername(username.getText()).get().getUsername() + " s-a logat. ");
                    if (UserController.getInstance().findUsername(username.getText()).isPresent()) {
                        StaticConstants.id = UserController.getInstance().findUsername(username.getText()).get().getId();
                    }
                    Home home = new Home();
                    StaticConstants.list.add(home);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Contul nu exista!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return loginButton;
    }

    private JPanel getCenterPanel(JTextField username, JPasswordField passwordField, JLabel userLabel, JLabel passwordLabel) {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1));
        centerPanel.setBackground(Color.black);
        centerPanel.add(userLabel);
        centerPanel.add(username);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        return centerPanel;
    }

    private JPanel getNorthPanel(JLabel l1, JLabel title, JLabel login) {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(4, 0));
        northPanel.setBackground(Color.black);
        northPanel.add(l1);
        northPanel.add(title);
        northPanel.add(login);
        return northPanel;
    }

    private JLabel getPasswLabel() {
        JLabel passwordLabel = new JLabel("Parola: ");
        passwordLabel.setFont(new Font("Broadway", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.WHITE);
        return passwordLabel;
    }

    private JLabel getUserLabel() {
        JLabel userLabel = new JLabel("Username: ");
        userLabel.setFont(new Font("Broadway", Font.PLAIN, 16));
        userLabel.setForeground(Color.white);
        return userLabel;
    }

    private JLabel getLogin() {
        JLabel login = new JLabel("Log in");
        login.setBorder(new EmptyBorder(0, 140, 0, 0));
        login.setFont(new Font("Broadway", Font.ITALIC, 25));
        login.setForeground(Color.white);
        login.setAlignmentX(0.5f);
        login.setAlignmentY(0.5f);
        return login;
    }

    private JLabel getLabelTitle() {
        JLabel title = new JLabel("American Airlines");
        title.setBorder(new EmptyBorder(0, 80, 0, 0));
        title.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 30));
        title.setForeground(new Color(6, 143, 177, 226));
        title.setAlignmentX(0.5f);
        title.setAlignmentY(0.5f);
        return title;
    }

    public boolean isValidUser(String s) {
        UserController userController = UserController.getInstance();
        Optional<User> user = userController.findUsername(s);

        return user.isEmpty();
    }

    public boolean isValidMail(String s) {
        UserController userController = UserController.getInstance();
        Optional<User> user = userController.findMail(s);

        return user.isEmpty();
    }
}
