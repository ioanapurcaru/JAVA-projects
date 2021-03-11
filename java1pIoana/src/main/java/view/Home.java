package view;

import controller.FlightController;
import controller.UserController;
import model.Flight;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Home extends JFrame {
    JPanel panel;
    JPanel mainPanel;

    public Home() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage("./home.png");
        setIconImage(icon);

        Optional<User> user = UserController.getInstance().findId(StaticConstants.id);
        if (user.isPresent()) {
            Timestamp t = Timestamp.from(Instant.now());
            StaticConstants.time.add(t);
            StaticConstants.messages.add(user.get().getUsername() + " a accesat pagina de Home");
        }



        JLabel l1 = new JLabel("");
        JLabel l2 = new JLabel("");

        JButton addButton = getAddButton();

        JButton clearAllButton = getClearAllButton(user);
        JButton backButton = getBackButton();

        getMenuBar(addButton, clearAllButton, backButton);

        JButton homeButton = getHomeButton();
        JButton historyButton = getHistoryButton();
        JButton accountButton = getAccountButton();

        ImageIcon i4 = new ImageIcon("./logout.png");
        Image image4 = i4.getImage(); // transform it
        Image newimg4 = image4.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        i4 = new ImageIcon(newimg4);  // transform it back
        JButton logoutButton = getLogoutButton(user, i4);

        JPanel eastPanel = getEastPanel(l1, l2, homeButton, historyButton, accountButton, logoutButton);

        JLabel emptyLabel = new JLabel();

        JLabel sourceLabel = getSourceLabel();
        JLabel destinationLabel = getDestinationLabel();
        JLabel departureLabel = getDepartureLabel();
        JLabel arriveLabel = getArriveLabel();
        JLabel daysLabel = getDaysLabel();
        JLabel priceLabel = getPriceLabel();

        getPanel(emptyLabel, sourceLabel, destinationLabel, departureLabel, arriveLabel, daysLabel, priceLabel);

        mainPanel = new JPanel();
        mainPanel.add(panel,BorderLayout.NORTH);

        List<Flight> flights = FlightController.getInstance().select();
        for (Flight f : flights) {
            panel.add(f.getButton());
            JLabel label = new JLabel();
            label.setText(f.getSursa());
            panel.add(label);
            JLabel label1 = new JLabel();
            label1.setText(f.getDest());
            panel.add(label1);
            JLabel label2 = new JLabel();
            label2.setText(f.getOraPlecarii());
            panel.add(label2);
            JLabel label3 = new JLabel();
            label3.setText(getOra(f.getOraPlecarii(), f.getDurata()));
            panel.add(label3);
            JLabel label4 = new JLabel();
            label4.setText(f.getZile());
            panel.add(label4);
            JLabel label5 = new JLabel();
            label5.setText(String.valueOf(f.getPret()));
            panel.add(label5);
        }

        add(mainPanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.WEST);
    }

    private void getPanel(JLabel emptyLabel, JLabel sourceLabel, JLabel destinationLabel, JLabel departureLabel, JLabel arriveLabel, JLabel daysLabel, JLabel priceLabel) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 7));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBackground(Color.white);
        panel.add(emptyLabel);
        panel.add(sourceLabel);
        panel.add(destinationLabel);
        panel.add(departureLabel);
        panel.add(arriveLabel);
        panel.add(daysLabel);
        panel.add(priceLabel);
    }

    private JLabel getPriceLabel() {
        JLabel priceLabel = new JLabel("Pret");
        priceLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
        priceLabel.setFont(new Font("Elephant", Font.BOLD, 16));
        return priceLabel;
    }

    private JLabel getDaysLabel() {
        JLabel daysLabel = new JLabel("Zile");
        daysLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        daysLabel.setFont(new Font("Elephant", Font.BOLD, 16));
        return daysLabel;
    }

    private JLabel getArriveLabel() {
        JLabel arriveLabel = new JLabel("Ora Sosirii");
        arriveLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        arriveLabel.setFont(new Font("Elephant", Font.BOLD, 16));
        return arriveLabel;
    }

    private JLabel getDepartureLabel() {
        JLabel departureLabel = new JLabel("Ora Plecarii");
        departureLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        departureLabel.setFont(new Font("Elephant", Font.BOLD, 16));
        return departureLabel;
    }

    private JLabel getDestinationLabel() {
        JLabel destinationLabel = new JLabel("Destinatie");
        destinationLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
        destinationLabel.setFont(new Font("Elephant", Font.BOLD, 16));
        return destinationLabel;
    }

    private JLabel getSourceLabel() {
        JLabel sourceLabel = new JLabel("Sursa");
        sourceLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        sourceLabel.setFont(new Font("Elephant", Font.BOLD, 16));
        return sourceLabel;
    }

    private JPanel getEastPanel(JLabel l1, JLabel l2, JButton homeButton, JButton historyButton, JButton accountButton, JButton logoutButton) {
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(6, 0));
        eastPanel.setBackground(Color.white);
        eastPanel.add(l1);
        eastPanel.add(homeButton);
        eastPanel.add(historyButton);
        eastPanel.add(accountButton);
        eastPanel.add(logoutButton);
        eastPanel.add(l2);
        return eastPanel;
    }

    private JButton getLogoutButton(Optional<User> user, ImageIcon i4) {
        JButton logoutButton = new JButton("LOGOUT");
        logoutButton.setForeground(Color.white);
        logoutButton.setBackground(Color.black);
        logoutButton.setIcon(i4);
        logoutButton.setHorizontalAlignment(SwingConstants.LEFT);
        logoutButton.setFont(new Font("Elephant", Font.PLAIN, 18));
        logoutButton.addActionListener(e -> {
            Optional<User> user1 = UserController.getInstance().findId(StaticConstants.id);
            if (user1.isPresent()) {
                Timestamp t = Timestamp.from(Instant.now());
                StaticConstants.time.add(t);
                StaticConstants.messages.add(user.get().getUsername() + " s-a delogat");
            }
            Login log = new Login();
            StaticConstants.list.add(log);
            dispose();
        });
        return logoutButton;
    }

    private JButton getAccountButton() {
        ImageIcon i3 = new ImageIcon("./acc2.png");
        Image image3 = i3.getImage(); // transform it
        Image newimg3 = image3.getScaledInstance(20, 20,  Image.SCALE_SMOOTH); // scale it the smooth way
        i3 = new ImageIcon(newimg3);  // transform it back
        JButton accountButton = new JButton("MY ACCOUNT");
        accountButton.setForeground(Color.white);
        accountButton.setBackground(Color.black);
        accountButton.setIcon(i3);
        accountButton.setHorizontalAlignment(SwingConstants.LEFT);
        accountButton.setFont(new Font("Elephant", Font.PLAIN, 18));
        accountButton.addActionListener(e -> {
            MyAccount acc = new MyAccount();
            StaticConstants.list.add(acc);
            dispose();
        });
        return accountButton;
    }

    private JButton getHistoryButton() {
        ImageIcon i2 = new ImageIcon("./history.png");
        Image image2 = i2.getImage(); // transform it
        Image newimg2 = image2.getScaledInstance(20, 20,  Image.SCALE_SMOOTH); // scale it the smooth way
        i2 = new ImageIcon(newimg2);  // transform it back
        JButton historyButton = new JButton("HISTORY");
        historyButton.setForeground(Color.white);
        historyButton.setBackground(Color.black);
        historyButton.setFont(new Font("Elephant", Font.PLAIN, 18));
        historyButton.setIcon(i2);
        historyButton.setHorizontalAlignment(SwingConstants.LEFT);
        historyButton.addActionListener(e -> {
            History h = new History();
           StaticConstants.list.add(h);
            dispose();
        });
        return historyButton;
    }

    private JButton getHomeButton() {
        ImageIcon i1 = new ImageIcon("./homee.png");
        Image image = i1.getImage(); // transform it
        Image newimg = image.getScaledInstance(20, 20,  Image.SCALE_SMOOTH); // scale it the smooth way
        i1 = new ImageIcon(newimg);  // transform it back
        JButton homeButton = new JButton("HOME");
        homeButton.setForeground(Color.white);
        homeButton.setBackground(Color.black);
        homeButton.setFont(new Font("Elephant", Font.PLAIN, 18));
        homeButton.setIcon(i1);
        homeButton.setHorizontalAlignment(SwingConstants.LEFT);
        return homeButton;
    }

    private void getMenuBar(JButton addButton, JButton clearAllButton, JButton backButton) {
        JMenuBar menuBar = new JMenuBar();
        add(menuBar, BorderLayout.BEFORE_FIRST_LINE);
        setJMenuBar(menuBar);
        menuBar.add(backButton);
        menuBar.add(addButton,BorderLayout.CENTER);
        menuBar.add(clearAllButton,BorderLayout.CENTER);
        menuBar.add(new Time(), BorderLayout.EAST);
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
            if (c instanceof MyAccount) {
                new MyAccount();
                dispose();
            }
        });
        return backButton;
    }

    private JButton getClearAllButton(Optional<User> user) {
        JButton clearAllButton = new JButton("Sterge tot");
        clearAllButton.setForeground(Color.white);
        clearAllButton.setBackground(Color.black);
        clearAllButton.setFont(new Font("Broadway", Font.PLAIN, 14));
        clearAllButton.addActionListener(e -> {
            int i;
            for (i = StaticConstants.list.size() - 1; i >= 0; i--) {
                if (StaticConstants.list.get(i) instanceof Home) {
                    ((Home)StaticConstants.list.get(i)).dispose();
                }
            }
            FlightController.getInstance().removeAll();
            Optional<User> user1 = UserController.getInstance().findId(StaticConstants.id);
            if (user1.isPresent()) {
                Timestamp t = Timestamp.from(Instant.now());
                StaticConstants.time.add(t);
                StaticConstants.messages.add(user.get().getUsername() + " a sters toate zborurile.");
            }
            Home h = new Home();
            StaticConstants.list.add(h);
        });
        return clearAllButton;
    }

    private JButton getAddButton() {
        JButton addButton = new JButton("Adauga zbor");
        addButton.setForeground(Color.white);
        addButton.setBackground(Color.black);
        addButton.setFont(new Font("Broadway", Font.PLAIN, 14));

        addButton.addActionListener(e -> {
           AddFlight x = new AddFlight();
            StaticConstants.list.add(x);
            dispose();
        });
        return addButton;
    }

    public String getOra(String oraP, String durata) {
        try {
            StringBuilder ora = new StringBuilder();
            StringBuilder minut = new StringBuilder();
            SimpleDateFormat df = new SimpleDateFormat("hh:mm");
            Date date = df.parse(oraP);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int index = durata.indexOf(":");
            for (int i = 0; i < index; i++) {
                ora.append(durata.charAt(i));
            }
            for (int i = index + 1; i < durata.length(); i++) {
                minut.append(durata.charAt(i));
            }
            cal.add(Calendar.HOUR, Integer.parseInt(ora.toString()));
            cal.add(Calendar.MINUTE, Integer.parseInt(minut.toString()));
            int h = cal.get(Calendar.HOUR_OF_DAY);
            int m = cal.get(Calendar.MINUTE);
            return h + ":" + m;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
