import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class GraphicalClock {

    static void display() {
        JFrame frame = new JFrame("Digital Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.PINK);
        
        // create label to print time
        JLabel timeLabel = new JLabel("", SwingConstants.CENTER);
        timeLabel.setPreferredSize(new Dimension(400, 200));
        timeLabel.setFont(new Font("Georgia", Font.BOLD, 40));
        frame.getContentPane().add(timeLabel, BorderLayout.CENTER);
        
        // Display the window
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        int delay = 10;
        Timer timer = new Timer(delay, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedDateTime = now.format(formatter);
                timeLabel.setText(formattedDateTime);
            }
        });

        timer.start();
    }

    public static void main(String args[]) {
        display();
    }
}