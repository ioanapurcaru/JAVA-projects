package view;

import controller.FlightController;
import controller.UserController;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

public class Delete extends JFrame {
     public Delete() {
         setLocationRelativeTo(null);
         setSize(400, 150);
         setVisible(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         Image icon = Toolkit.getDefaultToolkit().getImage("./1.jpg");
         setIconImage(icon);

         JLabel textLabel = getTextLabel();
         // add(textLabel, BorderLayout.CENTER);
         JPanel mainPanel = new JPanel();
         mainPanel.setLayout(new FlowLayout());
         mainPanel.setBackground(Color.BLACK);
         mainPanel.add(textLabel);

         JButton noButton = getNoButton();
         JButton yesButton = getYesButton();

         JPanel lowerPanel = getLowerPanel(noButton, yesButton);

         add(mainPanel,BorderLayout.CENTER);
         add(lowerPanel, BorderLayout.SOUTH);
     }

    private JPanel getLowerPanel(JButton noButton, JButton yesButton) {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout());
        lowerPanel.setBackground(Color.BLACK);
        lowerPanel.add(yesButton);
        lowerPanel.add(noButton);
        return lowerPanel;
    }

    private JButton getYesButton() {
        JButton yesButton = new JButton("DA");
        yesButton.setForeground(Color.red);
        yesButton.setBackground(Color.black);
        yesButton.setFont(new Font("Elephant", Font.BOLD, 15));
        yesButton.addActionListener(e -> {
            int i;
            for (i = StaticConstants.list.size() - 1; i >= 0; i--) {
                if (StaticConstants.list.get(i) instanceof Home) {
                    ((Home) StaticConstants.list.get(i)).dispose();
                }
            }
            FlightController.getInstance().remove(StaticConstants.flightId);
            Optional<User> user = UserController.getInstance().findId(StaticConstants.id);
            if (user.isPresent()) {
                Timestamp t = Timestamp.from(Instant.now());
               // HistoryActions.actions.put(t, user.get().getUsername() + " has deleted a flight");
                StaticConstants.time.add(t);
                StaticConstants.messages.add(user.get().getUsername() + " a sters un zbor.");
            }
            Home h = new Home();
            StaticConstants.list.add(h);
            dispose();
        });
        return yesButton;
    }

    private JButton getNoButton() {
        JButton noButton = new JButton("NU");
        noButton.setForeground(Color.red);
        noButton.setBackground(Color.black);
        noButton.setFont(new Font("Elephant", Font.BOLD, 15));
        noButton.addActionListener(e -> dispose());
        return noButton;
    }

    private JLabel getTextLabel() {
        JLabel textLabel = new JLabel();
        textLabel.setText("Sunteti sigur ca vreti sa stergeti zborul?");
        textLabel.setBorder(new EmptyBorder(0, 45, 0, 0));
        textLabel.setFont(new Font("Elephant",Font.PLAIN,16));
        textLabel.setForeground(Color.WHITE);
        return textLabel;
    }
}


