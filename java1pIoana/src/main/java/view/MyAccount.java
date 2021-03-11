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

public class MyAccount extends JFrame {

    public MyAccount(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setTitle("Cont");
        setLocationRelativeTo(null);
        setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage("./acc.png");
        setIconImage(icon);

        JButton backButton = getBackButton();

        JMenuBar menuBar = new JMenuBar();
        add(menuBar, BorderLayout.BEFORE_FIRST_LINE);
        setJMenuBar(menuBar);
        menuBar.add(backButton);

        JLabel l1 = new JLabel("");
        JLabel l2 = new JLabel("");

        JButton homeButton = getHomeButton();
        JButton historyButton = getHistoryButton();
        JButton accountButton = getAccountButton();
        JButton logoutButton = getLogoutButton();

        JPanel westPanel = getWestPanel(l1, l2, homeButton, historyButton, accountButton, logoutButton);

        JLabel helloLabel = getHelloLabel();


        JLabel empty1 = new JLabel();
        empty1.setText("");

        JLabel empty2 = new JLabel();
        empty2.setText("");

        JPanel northPanel = getNorthPanel(helloLabel, empty1, empty2);

        JLabel newUserLabel = getNewUserLabel();

        JTextField newUsername = new JTextField();

        JButton changeUserButton = getChangeUserButton(newUsername);

        JLabel newMailLabel = getNewMailLabel();

        JTextField newEmail = new JTextField();

        JButton changeEmailButton = getChangeEmailButton(newEmail);

        JLabel empty3 = new JLabel();
        empty3.setText("");

        JLabel empty4 = new JLabel();
        empty4.setText("");

        JLabel empty5 = new JLabel();
        empty5.setText("");

        JPanel centerPanel = getCenterPanel(newUserLabel, newUsername, changeUserButton, newMailLabel, newEmail, changeEmailButton, empty3, empty4, empty5);

        JButton changePasswButton = getChangePasswButton();
        JPanel southPanel = getSouthPanel(changePasswButton);

        JPanel mainPanel = getMainPanel(northPanel, centerPanel, southPanel);

        add(mainPanel,BorderLayout.CENTER);
        add(westPanel, BorderLayout.WEST);
    }

    private JPanel getMainPanel(JPanel northPanel, JPanel centerPanel, JPanel southPanel) {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.add(northPanel,BorderLayout.NORTH);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(southPanel,BorderLayout.SOUTH);
        return mainPanel;
    }

    private JPanel getSouthPanel(JButton changePasswButton) {
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());
        southPanel.setBackground(Color.white);
        southPanel.add(changePasswButton);
        return southPanel;
    }

    private JButton getChangePasswButton() {
        JButton changePasswButton = new JButton("Schimba parola");
        changePasswButton.setForeground(Color.WHITE);
        changePasswButton.setBackground(Color.BLACK);
        changePasswButton.setFont(new Font("Elephant", Font.PLAIN, 15));
        changePasswButton.addActionListener(e -> {
           new NewPassword();
            dispose();
        });
        return changePasswButton;
    }

    private JPanel getCenterPanel(JLabel newUserLabel, JTextField newUsername, JButton changeUserButton, JLabel newMailLabel, JTextField newEmail, JButton changeEmailButton, JLabel empty3, JLabel empty4, JLabel empty5) {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3,2));
        centerPanel.setBackground(Color.white);
        centerPanel.add(newUserLabel);
        centerPanel.add(newUsername);
        centerPanel.add(changeUserButton);
        centerPanel.add(newMailLabel);
        centerPanel.add(newEmail);
        centerPanel.add(changeEmailButton);
        centerPanel.add(empty3);
        centerPanel.add(empty4);
        centerPanel.add(empty5);
        return centerPanel;
    }

    private JButton getChangeEmailButton(JTextField newEmail) {
        JButton changeEmailButton = new JButton("Schimba e-mail");
        changeEmailButton.setForeground(Color.white);
        changeEmailButton.setBackground(Color.black);
        changeEmailButton.setFont(new Font("Elephant", Font.PLAIN, 15));
        changeEmailButton.addActionListener(e -> {

            String mail = newEmail.getText();
            if (isNewMailValid(mail)) {
                if (UserController.getInstance().findMail(newEmail.getText()).isPresent()) {
                    JOptionPane.showMessageDialog(null, "E-mail este deja utilizat!", "Eroare", JOptionPane.ERROR_MESSAGE);
                } else {
                    Optional<User> usr = UserController.getInstance().findId(StaticConstants.id);
                    if (usr.isPresent()) {
                        boolean b = UserController.getInstance().updateMail(usr.get().getEmail(), newEmail.getText());
                        if (b) {
                            Optional<User> user3 = UserController.getInstance().findId(StaticConstants.id);
                            if (user3.isPresent()) {
                                Timestamp t = Timestamp.from(Instant.now());
                                StaticConstants.time.add(t);
                                StaticConstants.messages.add(user3.get().getUsername() + " a schimbat email-ul ");
                            }
                            JOptionPane.showMessageDialog(null, "Schimbare realizata cu succesc!", "Information", JOptionPane.INFORMATION_MESSAGE);
                            Login log = new Login();
                            StaticConstants.list.add(log);
                            dispose();
                        }
                    }
                }
            }
        });
        return changeEmailButton;
    }

    private JLabel getNewMailLabel() {
        JLabel newMailLabel = new JLabel("E-mail nou: ");
        newMailLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        newMailLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        return newMailLabel;
    }

    private JButton getChangeUserButton(JTextField newUsername) {
        JButton changeUserButton = new JButton("Schimba username");
        changeUserButton.setForeground(Color.white);
        changeUserButton.setBackground(Color.black);
        changeUserButton.setFont(new Font("Elephant", Font.PLAIN, 15));
        changeUserButton.addActionListener(e -> {

            String user = newUsername.getText();
            if (isNewUserValid(user)) {
                Optional<User> usr = UserController.getInstance().findId(StaticConstants.id);
                if (usr.isPresent()) {
                    boolean b = UserController.getInstance().updateUser(usr.get().getUsername(), user);
                    if (b) {
                        Optional<User> user2 = UserController.getInstance().findId(StaticConstants.id);
                        if (user2.isPresent()) {
                            Timestamp t = Timestamp.from(Instant.now());
                            StaticConstants.time.add(t);
                            StaticConstants.messages.add(user2.get().getUsername() + " a schimbat username-ul. ");
                        }
                        JOptionPane.showMessageDialog(null, "Schimbare realizata cu succes!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        Login log = new Login();
                        StaticConstants.list.add(log);
                        dispose();
                    }
                }
            }
        });
        return changeUserButton;
    }

    private JLabel getNewUserLabel() {
        JLabel newUserLabel = new JLabel("Username nou: ");
        newUserLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        newUserLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        return newUserLabel;
    }

    private JPanel getNorthPanel(JLabel helloLabel, JLabel empty1, JLabel empty2) {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(3,0));
        northPanel.setBackground(Color.white);
        northPanel.add(helloLabel);
        northPanel.add(empty1);
        northPanel.add(empty2);
        return northPanel;
    }

    private JLabel getHelloLabel() {
        JLabel helloLabel = new JLabel();
        String s = "";
        Optional<User> u = UserController.getInstance().findId(StaticConstants.id);
        if (u.isPresent()) {
            s = s + "Buna, " + u.get().getUsername() + "! <" + u.get().getEmail() + ">";
        }
        helloLabel.setText(s);
        helloLabel.setFont(new Font("Elephant", Font.PLAIN, 17));
        return helloLabel;
    }

    private JPanel getWestPanel(JLabel l1, JLabel l2, JButton homeButton, JButton historyButton, JButton accountButton, JButton logoutButton) {
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(6, 0));
        westPanel.setBackground(Color.white);
        westPanel.add(l1);
        westPanel.add(homeButton);
        westPanel.add(historyButton);
        westPanel.add(accountButton);
        westPanel.add(logoutButton);
        westPanel.add(l2);
        return westPanel;
    }

    private JButton getLogoutButton() {
        JButton logoutButton = new JButton("LOGOUT");
        logoutButton.setForeground(Color.white);
        logoutButton.setBackground(Color.black);
        logoutButton.setFont(new Font("Broadway", Font.PLAIN, 14));
        logoutButton.addActionListener(e -> {
            Optional<User> user1 = UserController.getInstance().findId(StaticConstants.id);
            if (user1.isPresent()) {
                Timestamp t = Timestamp.from(Instant.now());
                StaticConstants.time.add(t);
                StaticConstants.messages.add(user1.get().getUsername() + " s-a delogat ");
            }
            Login log = new Login();
            StaticConstants.list.add(log);
            dispose();
        });
        return logoutButton;
    }

    private JButton getAccountButton() {
        JButton accountButton = new JButton("MY ACCOUNT");
        accountButton.setForeground(Color.white);
        accountButton.setBackground(Color.black);
        accountButton.setFont(new Font("Broadway", Font.PLAIN, 14));
        accountButton.addActionListener(e -> {
            MyAccount acc = new MyAccount();
            StaticConstants.list.add(acc);
            dispose();
        });
        return accountButton;
    }

    private JButton getHistoryButton() {
        JButton historyButton = new JButton("HISTORY");
        historyButton.setForeground(Color.white);
        historyButton.setBackground(Color.black);
        historyButton.setFont(new Font("Broadway", Font.PLAIN, 14));
        historyButton.addActionListener(e -> {
            History h = new History();
            StaticConstants.list.add(h);
            dispose();
        });
        return historyButton;
    }

    private JButton getHomeButton() {
        JButton homeButton = new JButton("HOME");
        homeButton.setForeground(Color.white);
        homeButton.setBackground(Color.black);
        homeButton.setFont(new Font("Broadway", Font.PLAIN, 14));
        homeButton.addActionListener(e -> {
            Home h = new Home();
           StaticConstants.list.add(h);
            dispose();
        });
        return homeButton;
    }

    private JButton getBackButton() {
        Image img = new ImageIcon("./back.png").getImage().getScaledInstance(25,15, 20);
        JButton backButton = new JButton(new ImageIcon(img));
        backButton.setBackground(Color.BLACK);
        backButton.addActionListener(e -> {
            StaticConstants.list.remove(StaticConstants.list.size() - 1);
            Component c = StaticConstants.list.get(StaticConstants.list.size()-1);
            if (c instanceof History) {
                new History();
                dispose();
            }
            if (c instanceof Home) {
                new Home();
                dispose();
            }
        });
        return backButton;
    }

    public boolean isNewUserValid(String s) {
        boolean verificare = true;
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        if (s.length() == 0) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Toate campurile trebuie completate!","Eroare",JOptionPane.ERROR_MESSAGE);
        }
        if (specialCharPatten.matcher(s).find() || s.contains(" ")) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Caractere invalide!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        return verificare;
    }

    public boolean isNewMailValid(String s) {
        boolean verificare = true;
        int i, poz = 0;
        if (s.length() == 0) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Toate campurile trebuie completate!","Eroare",JOptionPane.ERROR_MESSAGE);
        }
        if (!s.contains("@")) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "E-mail invalid!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        StringBuilder verif = new StringBuilder();
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '@') {
                poz = i;
                break;
            }
            verif.append(s.charAt(i));
        }
        if (verif.toString().equals("")) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "E-mail invalid!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        verif = new StringBuilder();
        for (i = poz + 1; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                break;
            }
            verif.append(s.charAt(i));
        }
        if (verif.toString().equals("")) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "E-mail invalid!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        return verificare;
    }
}
