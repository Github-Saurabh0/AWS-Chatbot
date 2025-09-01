import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock extends JFrame {
    private JLabel timeLabel;
    private JLabel dateLabel;
    private JLabel ampmLabel;
    private JPanel mainPanel;
    private Font timeFont;
    private Font dateFont;
    private Font ampmFont;
    private SimpleDateFormat timeFormat;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat ampmFormat;
    private Timer timer;
    
    // AI theme colors
    private final Color BG_COLOR = new Color(25, 25, 35);
    private final Color TIME_COLOR = new Color(0, 195, 255);
    private final Color DATE_COLOR = new Color(170, 170, 255);
    private final Color AMPM_COLOR = new Color(255, 100, 255);
    private final Color PANEL_COLOR = new Color(35, 35, 45);
    
    public DigitalClock() {
        // Set up the frame
        setTitle("AI Digital Clock");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(BG_COLOR);
        setLayout(new BorderLayout());
        
        // Initialize fonts
        timeFont = new Font("Consolas", Font.BOLD, 72);
        dateFont = new Font("Consolas", Font.PLAIN, 24);
        ampmFont = new Font("Consolas", Font.BOLD, 28);
        
        // Initialize date formats
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        ampmFormat = new SimpleDateFormat("a");
        
        // Create main panel with a slight glow effect
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                
                // Create a radial gradient for the glow effect
                RadialGradientPaint paint = new RadialGradientPaint(
                    new Point(getWidth() / 2, getHeight() / 2),
                    getWidth() / 2,
                    new float[] { 0.0f, 1.0f },
                    new Color[] { 
                        new Color(45, 45, 65),
                        PANEL_COLOR
                    }
                );
                
                g2d.setPaint(paint);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2d.dispose();
            }
        };
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(PANEL_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Create time label
        timeLabel = new JLabel();
        timeLabel.setFont(timeFont);
        timeLabel.setForeground(TIME_COLOR);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create date label
        dateLabel = new JLabel();
        dateLabel.setFont(dateFont);
        dateLabel.setForeground(DATE_COLOR);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create AM/PM label
        ampmLabel = new JLabel();
        ampmLabel.setFont(ampmFont);
        ampmLabel.setForeground(AMPM_COLOR);
        ampmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add components to panel
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(timeLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(ampmLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(dateLabel);
        mainPanel.add(Box.createVerticalGlue());
        
        // Add panel to frame
        add(mainPanel, BorderLayout.CENTER);
        
        // Create and start timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();
        
        // Initial time update
        updateTime();
        
        // Center on screen
        setLocationRelativeTo(null);
    }
    
    private void updateTime() {
        Date now = new Date();
        timeLabel.setText(timeFormat.format(now));
        dateLabel.setText(dateFormat.format(now));
        ampmLabel.setText(ampmFormat.format(now));
    }
    
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Run the application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DigitalClock().setVisible(true);
            }
        });
    }
}