package view;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

public class Time extends JPanel {

    private final JLabel clock;

    public Time() {
        setLayout(new BorderLayout());
        clock = new JLabel();
        clock.setHorizontalAlignment(JLabel.CENTER);
        clock.setFont(UIManager.getFont("Label.font").deriveFont(Font.BOLD, 25f));
        tickTock();
        add(clock);

        Timer timer = new Timer(500, e -> tickTock());
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
    }

    public void tickTock() {
        clock.setText(DateFormat.getDateTimeInstance().format(new Date()));
    }
}

