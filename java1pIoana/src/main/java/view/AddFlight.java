package view;

import controller.FlightController;
import controller.UserController;
import model.Flight;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddFlight extends JFrame{

    public AddFlight(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 550);
        setTitle("Add Flight Page");
        setLocationRelativeTo(null);
        setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage("./1.jpg");
        setIconImage(icon);

        JLabel l1 = new JLabel("");
        JLabel title = getTitleLabel();
        JLabel l2 = new JLabel("");

        JPanel northPanel = getNorthPanel(l1, title, l2);

        JLabel sourceLabel = getSourceLabel();
        JTextField source = new JTextField();
        JLabel destLabel = getDestLabel();
        JTextField dest = new JTextField();
        JLabel depTimeLabel = getDepartureLabel();
        JTextField departureTime = new JTextField();
        JLabel durationLabel = getDurationLabel();
        JTextField duration = new JTextField();
        JLabel priceLabel = getPriceLabel();
        JTextField price = new JTextField();
        JLabel daysLabel = getDaysLabel();

        JPanel centerPanel = getCenterPanel(sourceLabel, source, destLabel, dest, depTimeLabel, departureTime, durationLabel, duration, priceLabel, price, daysLabel);

        JPanel daysPanel = new JPanel();
        daysPanel.setLayout(new FlowLayout());
        daysPanel.setForeground(Color.WHITE);
        JCheckBox luni = new JCheckBox("Luni", false);
        daysPanel.add(luni);
        JCheckBox marti = new JCheckBox("Marti", false);
        daysPanel.add(marti);
        JCheckBox miercuri = new JCheckBox("Miercuri", false);
        daysPanel.add(miercuri);
        JCheckBox joi = new JCheckBox("Joi", false);
        daysPanel.add(joi);
        JCheckBox vineri = new JCheckBox("Vineri", false);
        daysPanel.add(vineri);
        JCheckBox sambata = new JCheckBox("Sambata", false);
        daysPanel.add(sambata);
        JCheckBox duminica = new JCheckBox("Duminica", false);
        daysPanel.add(duminica);

        centerPanel.add(daysPanel);

        JButton cancelButton = getCancelButton();

        JButton adaugareButton = getAddButton(source, dest, departureTime, duration, price, luni, marti, miercuri, joi, vineri, sambata, duminica);

        JPanel southPanel = getSouthPanel(cancelButton, adaugareButton);

        add(centerPanel,BorderLayout.CENTER);
        add(northPanel,BorderLayout.NORTH);
        add(southPanel,BorderLayout.SOUTH);

    }

    private JPanel getSouthPanel(JButton cancelButton, JButton adaugareButton) {
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.black);
        southPanel.add(cancelButton);
        southPanel.add(adaugareButton);
        return southPanel;
    }

    private JButton getAddButton(JTextField source, JTextField dest, JTextField departureTime, JTextField duration, JTextField price, JCheckBox luni, JCheckBox marti, JCheckBox miercuri, JCheckBox joi, JCheckBox vineri, JCheckBox sambata, JCheckBox duminica) {
        JButton adaugareButton = new JButton("Adauga zbor");
        adaugareButton.setForeground(Color.white);
        adaugareButton.setBackground(Color.black);
        adaugareButton.setFont(new Font("Broadway", Font.PLAIN, 14));
        adaugareButton.addActionListener(e -> {
            boolean ok = true;
            if (!luni.isSelected() && !marti.isSelected() && !miercuri.isSelected()
                    && !joi.isSelected() && !vineri.isSelected() && !sambata.isSelected() && !duminica.isSelected()) {
                JOptionPane.showMessageDialog(null, "Trebuie selectata minim o zi!", "Eroare", JOptionPane.ERROR_MESSAGE);
               ok = false;
            }
            if (ok && isValid(source.getText(), dest.getText(), departureTime.getText(), duration.getText(), price.getText())) {
                String s = "";
                if (luni.isSelected()) {
                    s = s + luni.getText() + " ";
                }
                if (marti.isSelected()) {
                    s = s + marti.getText() + " ";
                }
                if (miercuri.isSelected()) {
                    s = s + miercuri.getText() + " ";
                }
                if (joi.isSelected()) {
                    s = s + joi.getText() + " ";
                }
                if (vineri.isSelected()) {
                    s = s + vineri.getText() + " ";
                }
                if (sambata.isSelected()) {
                    s = s + sambata.getText() + " ";
                }
                if (duminica.isSelected()) {
                    s = s + duminica.getText() + " ";
                }

             Flight f = new Flight(0, source.getText(), dest.getText(), departureTime.getText(), duration.getText(), s, Integer.parseInt(price.getText()));
                StaticConstants.flight = f;
                FlightController.getInstance().create(f);
                Optional<User> user = UserController.getInstance().findId(StaticConstants.id);
                if (user.isPresent()) {
                    Timestamp t = Timestamp.from(Instant.now());
                    StaticConstants.time.add(t);
                    StaticConstants.messages.add(user.get().getUsername() + " a adaugat un zbor.");
                }
                Home h = new Home();
                StaticConstants.list.add(h);
                dispose();
            }
        });
        return adaugareButton;
    }

    private JButton getCancelButton() {
        JButton cancelButton = new JButton("Anuleaza");
        cancelButton.setForeground(Color.white);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setFont(new Font("Broadway", Font.PLAIN, 14));

        cancelButton.addActionListener(e -> {
            StaticConstants.list.remove(StaticConstants.list.size()-1);
            Component c = StaticConstants.list.get(StaticConstants.list.size()-1);
            StaticConstants.list.remove(StaticConstants.list.size()-1);
            if (c instanceof Home) {
                Home h = new Home();
                StaticConstants.list.add(h);
                dispose();
            }
        });
        return cancelButton;
    }

    private JPanel getNorthPanel(JLabel l1, JLabel title, JLabel l2) {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(3,0));
        northPanel.setBackground(Color.black);
        northPanel.add(l1);
        northPanel.add(title);
        northPanel.add(l2);
        return northPanel;
    }

    private JPanel getCenterPanel(JLabel sourceLabel, JTextField source, JLabel destLabel, JTextField dest, JLabel depTimeLabel, JTextField departureTime, JLabel durationLabel, JTextField duration, JLabel priceLabel, JTextField price, JLabel daysLabel) {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6,1));
        centerPanel.setBackground(Color.black);
        centerPanel.add(sourceLabel);
        centerPanel.add(source);
        centerPanel.add(destLabel);
        centerPanel.add(dest);
        centerPanel.add(depTimeLabel);
        centerPanel.add(departureTime);
        centerPanel.add(durationLabel);
        centerPanel.add(duration);
        centerPanel.add(priceLabel);
        centerPanel.add(price);
        centerPanel.add(daysLabel);
        return centerPanel;
    }

    private JLabel getDaysLabel() {
        JLabel daysLabel = new JLabel("Zile: ");
        daysLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        daysLabel.setFont(new Font("Elephant", Font.PLAIN, 16));
        daysLabel.setForeground(Color.WHITE);
        return daysLabel;
    }

    private JLabel getPriceLabel() {
        JLabel priceLabel = new JLabel("Pret: ");
        priceLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        priceLabel.setFont(new Font("Elephant", Font.PLAIN, 16));
        priceLabel.setForeground(Color.WHITE);
        return priceLabel;
    }

    private JLabel getDurationLabel() {
        JLabel durationLabel = new JLabel("Durata: ");
        durationLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        durationLabel.setFont(new Font("Elephant", Font.PLAIN, 16));
        durationLabel.setForeground(Color.WHITE);
        return durationLabel;
    }

    private JLabel getDepartureLabel() {
        JLabel depTimeLabel = new JLabel("Ora plecarii: ");
        depTimeLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        depTimeLabel.setFont(new Font("Elephant", Font.PLAIN, 16));
        depTimeLabel.setForeground(Color.WHITE);
        return depTimeLabel;
    }

    private JLabel getDestLabel() {
        JLabel destLabel = new JLabel("Destinatie: ");
        destLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        destLabel.setFont(new Font("Elephant", Font.PLAIN, 16));
        destLabel.setForeground(Color.WHITE);
        return destLabel;
    }

    private JLabel getSourceLabel() {
        JLabel sourceLabel = new JLabel("Sursa: ");
        sourceLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        sourceLabel.setFont(new Font("Elephant", Font.PLAIN, 16));
        sourceLabel.setForeground(Color.white);
        return sourceLabel;
    }

    private JLabel getTitleLabel() {
        JLabel title = new JLabel("Detalii zbor");
        title.setBorder(new EmptyBorder(0,120,0 ,0));
        title.setFont(new Font("Broadway", Font.ITALIC, 25));
        title.setForeground(Color.white);
        title.setAlignmentX(0.5f);
        title.setAlignmentY(0.5f);
        return title;
    }

    public boolean isValid(String sursa, String destinatie, String sosire, String durata,String pret) {
        boolean verificare = true;
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        if (sursa.length() < 3) {
            JOptionPane.showMessageDialog(null, "Sursa trebuie sa contina minim 3 caractere!", "Eroare", JOptionPane.ERROR_MESSAGE);
            verificare = false;
        }
        if (specialCharPatten.matcher(sursa).find() || sursa.contains(" ")) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Caractere invalide!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        if (digitCasePatten.matcher(sursa).find()) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Caractere invalide!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }

        if (destinatie.length() < 3 || destinatie.equals(sursa)) {
            JOptionPane.showMessageDialog(null, "Destinatia trebuie sa contina minim 3 caractere!", "Eroare", JOptionPane.ERROR_MESSAGE);
            verificare = false;
        } else {
            Optional<Flight> f = FlightController.getInstance().findBySrcAndDest(sursa,destinatie);
            if (f.isPresent()) {
                JOptionPane.showMessageDialog(null, "Acest zbor exista deja!", "Eroare", JOptionPane.ERROR_MESSAGE);
                verificare = false;
            }
        }
        if (specialCharPatten.matcher(destinatie).find() || destinatie.contains(" ")) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Caractere invalide!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        if (digitCasePatten.matcher(destinatie).find()) {
            verificare = false;
            JOptionPane.showMessageDialog(null, "Caractere invalide!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }

        if (!Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]").matcher(sosire).matches()) {
            JOptionPane.showMessageDialog(null, "Formatul orei este incorect!", "Eroare", JOptionPane.ERROR_MESSAGE);
            verificare = false;
        }

        if (!Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]").matcher(durata).matches()) {
            JOptionPane.showMessageDialog(null, "Formatul orei este incorect!", "Eroare", JOptionPane.ERROR_MESSAGE);
            verificare = false;
        }
        try {
            if (Integer.parseInt(pret) <= 0) {
                JOptionPane.showMessageDialog(null, "Pretul trebuie sa fie mai mare ca 0!", "Eroare", JOptionPane.ERROR_MESSAGE);
                verificare = false;
            }
        } catch (NumberFormatException er) {
            JOptionPane.showMessageDialog(null, "Caractere invalide!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        return verificare;

    }

}
