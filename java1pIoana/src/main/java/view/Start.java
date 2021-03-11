package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Start extends JFrame {

    public Start() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1150, 450);
        setLocationRelativeTo(null);
        setVisible(true);

        Image icon = Toolkit.getDefaultToolkit().getImage("./1.jpg");
        setIconImage(icon);

        JLabel label1 = getLabel1();
        JLabel l1 = getL1();
        JLabel l2 = getL2();
        JLabel label2 = getLabel2();

        JLabel l3 = new JLabel();
        l3.setText("");

        JLabel l4 = new JLabel();
        l4.setText("");

        ImageIcon image1 = new ImageIcon("./plane.jpg");
        JLabel label = new JLabel("", image1, JLabel.CENTER);

        JButton loginButton = getLoginButton();
        JButton registerButton = getRegisterButton();

        JLabel twitter = getTwitterLabel();
        add(twitter);

        JLabel fb = getFbLabel();
        add(fb);

        JLabel insta = getInstaLabel();
        add(insta);

        JPanel secondPanel = getSecondPanel(twitter, fb, insta);
        JPanel eastPanel = getEastPanel(label1, l1, l2, l3, l4, loginButton, registerButton, secondPanel);
        JPanel southPanel = getSouthPanel(label2);
        JPanel mainPanel = getMainPanel(label);

        add(eastPanel,BorderLayout.EAST);
        add(southPanel,BorderLayout.SOUTH);
        add(mainPanel,BorderLayout.CENTER);
    }

    private JPanel getMainPanel(JLabel label) {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.add(label);
        return mainPanel;
    }

    private JPanel getSouthPanel(JLabel label2) {
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.white);
        southPanel.add(label2);
        return southPanel;
    }

    private JPanel getEastPanel(JLabel label1, JLabel l1, JLabel l2, JLabel l3, JLabel l4, JButton loginButton, JButton registerButton, JPanel secondPanel) {
        JPanel eastPanel = new JPanel();
        eastPanel.setBackground(Color.white);
        eastPanel.setLayout(new GridLayout(9,0));
        eastPanel.add(label1);
        eastPanel.add(l1);
        eastPanel.add(l2);
        eastPanel.add(l4);
        eastPanel.add(l3);
        eastPanel.add(loginButton);
        eastPanel.add(registerButton);
        eastPanel.add(secondPanel,BorderLayout.SOUTH);
        return eastPanel;
    }

    private JPanel getSecondPanel(JLabel twitter, JLabel fb, JLabel insta) {
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new GridLayout(1,3));
        secondPanel.add(twitter);
        secondPanel.add(fb);
        secondPanel.add(insta);
        return secondPanel;
    }

    private JLabel getInstaLabel() {
        ImageIcon i3 = new ImageIcon("./insta.jpg");
        Image image3 = i3.getImage(); // transform it
        Image newimg3 = image3.getScaledInstance(20, 20,  Image.SCALE_SMOOTH); // scale it the smooth way
        i3 = new ImageIcon(newimg3);  // transform it back
        JLabel insta = new JLabel(i3);
        insta.setBounds(10,10, 18,18);
        insta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        insta.setBackground(Color.white);

        insta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                String command = "rundll32 url.dll,FileProtocolHandler https://www.instagram.com/";
                try {
                    Process p = Runtime.getRuntime().exec(command);

                } catch (Exception e1) {

                    JOptionPane.showMessageDialog(null, "\n Error! \n");

                }

            }
        });
        return insta;
    }

    private JLabel getFbLabel() {
        ImageIcon i2 = new ImageIcon("./fb.png");
        Image image2 = i2.getImage(); // transform it
        Image newimg2 = image2.getScaledInstance(20, 20,  Image.SCALE_SMOOTH); // scale it the smooth way
        i2 = new ImageIcon(newimg2);  // transform it back
        JLabel fb = new JLabel(i2);
        fb.setBounds(10,10, 22,18);
        fb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        fb.setBackground(Color.white);

        fb.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                String command = "rundll32 url.dll,FileProtocolHandler https://www.facebook.com/";
                try {
                    Process p = Runtime.getRuntime().exec(command);

                } catch (Exception e1) {

                    JOptionPane.showMessageDialog(null, "\n Error! \n");

                }

            }
        });
        return fb;
    }

    private JLabel getTwitterLabel() {
        ImageIcon i1 = new ImageIcon("./logo.png");
        Image image = i1.getImage(); // transform it
        Image newimg = image.getScaledInstance(20, 20,  Image.SCALE_SMOOTH); // scale it the smooth way
        i1 = new ImageIcon(newimg);  // transform it back
        JLabel twitter = new JLabel(i1);
        twitter.setBounds(10,10, 18,18);
        twitter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        twitter.setBackground(Color.white);

        twitter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                String command = "rundll32 url.dll,FileProtocolHandler https://twitter.com/";
                try {
                    Process p = Runtime.getRuntime().exec(command);

                } catch (Exception e1) {

                    JOptionPane.showMessageDialog(null, "\n Error! \n");

                }

            }
        });
        return twitter;
    }

    private JButton getRegisterButton() {
        JButton registerButton = new JButton("Cont nou");
        registerButton.setForeground(Color.white);
        registerButton.setBackground(Color.BLACK);
        registerButton.setFont(new Font("Cooper Black", Font.PLAIN, 14));
        registerButton.addActionListener(e -> {
            RegisterPage reg = new RegisterPage();
            dispose();
        });
        return registerButton;
    }

    private JButton getLoginButton() {
        JButton loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.setForeground(Color.white);
        loginButton.setBackground(Color.BLACK);
        loginButton.setFont(new Font("Cooper Black", Font.PLAIN, 14));
        loginButton.addActionListener(e -> {
            Login log = new Login();
            dispose();
        });
        return loginButton;
    }

    private JLabel getLabel2() {
        JLabel label2 = new JLabel();
        label2.setText("To invent an airplane is nothing. To build one is something. But to fly is everything.");
        label2.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 25));
        label2.setForeground(Color.black);
        label2.setAlignmentX(0.5f);
        label2.setAlignmentY(0.5f);
        return label2;
    }

    private JLabel getL2() {
        JLabel l2 = new JLabel();
        l2.setText("American Airlines!");
        l2.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 35));
        l2.setForeground(Color.black);
        return l2;
    }

    private JLabel getL1() {
        JLabel l1 = new JLabel();
        l1.setText("                   to");
        l1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
        l1.setForeground(Color.BLACK);
        return l1;
    }

    private JLabel getLabel1() {
        JLabel label1 = new JLabel();
        label1.setText("        Welcome");
        label1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 35));
        label1.setForeground(Color.black);
        return label1;
    }
}
