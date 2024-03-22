import javax.swing.*;
import java.awt.*;

public class FitTrackerMainFrame extends JFrame {

    public FitTrackerMainFrame() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("FitTracker - Your Fitness Journey Begins Here");
        setSize(600, 400); // Set the size of the frame
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(event -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Tabs for different functionalities
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Workout Logging Tab
        JPanel workoutLogPanel = new JPanel();
        workoutLogPanel.setLayout(new BorderLayout());
        workoutLogPanel.add(new JLabel("Workout Logging"), BorderLayout.CENTER);
        tabbedPane.addTab("Workout Log", workoutLogPanel);
        
        // Calorie Tracking Tab
        JPanel calorieTrackPanel = new JPanel();
        calorieTrackPanel.setLayout(new BorderLayout());
        calorieTrackPanel.add(new JLabel("Calorie Tracking"), BorderLayout.CENTER);
        tabbedPane.addTab("Calorie Tracker", calorieTrackPanel);
        
        // Progress Dashboard Tab
        JPanel progressDashboardPanel = new JPanel();
        progressDashboardPanel.setLayout(new BorderLayout());
        progressDashboardPanel.add(new JLabel("Progress Dashboard"), BorderLayout.CENTER);
        tabbedPane.addTab("Progress", progressDashboardPanel);
        
        // Goal Setting Tab
        JPanel goalSettingPanel = new JPanel();
        goalSettingPanel.setLayout(new BorderLayout());
        goalSettingPanel.add(new JLabel("Set Your Goals"), BorderLayout.CENTER);
        tabbedPane.addTab("Goals", goalSettingPanel);

        // Add tabbed pane to main frame
        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FitTrackerMainFrame frame = new FitTrackerMainFrame();
            frame.setVisible(true);
        });
    }
}

