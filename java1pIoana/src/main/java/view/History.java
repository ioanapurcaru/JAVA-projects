package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.border.EmptyBorder;


    public class History extends JFrame {

        public History() {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(700, 400);
            setTitle("History");
            setLocationRelativeTo(null);
            setVisible(true);

            Image icon = Toolkit.getDefaultToolkit().getImage("./hist.png");
            setIconImage(icon);

            JButton backButton = getBackButton();

            getMenuBar(backButton);

            JLabel l1 = new JLabel("");
            JLabel l2 = new JLabel("");

            JButton homeButton = getHomeButton();
            JButton historyButton = getHistoryButton();
            JButton accountButton = getAccountButton();
            JButton logoutButton = getLogoutButton();

            JPanel westPanel = getWestPanel(l1, l2, homeButton, historyButton, accountButton, logoutButton);

            JLabel dateLabel = getDateLabel();

            JLabel actionLabel = getActionLabel();

            JPanel mainPanel = getMainPanel(dateLabel, actionLabel);

            add(mainPanel,BorderLayout.CENTER);
            add(westPanel, BorderLayout.WEST);

            ArrayList<Timestamp> keys = new ArrayList<>(StaticConstants.time);
            for (int i = keys.size() - 1; i >= 0; i--) {
                JLabel label = new JLabel();
                label.setText(keys.get(i).toString());
                label.setFont(new Font("Arial", Font.BOLD, 16));
                mainPanel.add(label);
                JLabel label1 = new JLabel();
                label1.setText(StaticConstants.messages.get(i));
                label1.setFont(new Font("Arial", Font.BOLD, 16));
                mainPanel.add(label1);
            }
        }

        private JPanel getMainPanel(JLabel dateLabel, JLabel actionLabel) {
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(Color.white);
            mainPanel.add(dateLabel);
            mainPanel.add(actionLabel);
            return mainPanel;
        }

        private JLabel getActionLabel() {
            JLabel actionLabel = new JLabel();
            actionLabel.setText("Action");
            actionLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
            actionLabel.setFont(new Font("Georgia", Font.BOLD, 16));
            return actionLabel;
        }

        private JLabel getDateLabel() {
            JLabel dateLabel = new JLabel();
            dateLabel.setText("Time");
            dateLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
            dateLabel.setFont(new Font("Georgia", Font.BOLD, 16));
            return dateLabel;
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

        private void getMenuBar(JButton backButton) {
            JMenuBar menuBar = new JMenuBar();
            add(menuBar, BorderLayout.BEFORE_FIRST_LINE);
            setJMenuBar(menuBar);
            menuBar.add(backButton);
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
                if (c instanceof Home) {
                    new Home();
                    dispose();
                }
                if (c instanceof MyAccount) {
                    new MyAccount();
                    dispose();
                }
            });
            return backButton;
        }
    }

