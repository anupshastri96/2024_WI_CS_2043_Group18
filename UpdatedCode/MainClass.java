import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass extends JFrame {
    private JFrame frame;
    private CalorieTrackerPanel calorieTrackerPanel;
    private WorkoutLogPanel workoutLogPanel;
    private ProgressDashboardPanel progressDashboardPanel;
    private ProgressDataUpdater progressDataUpdater;

    public MainClass() {
        // Create a ProgressDataUpdater instance
        progressDataUpdater = this::updateProgressData;

        SwingUtilities.invokeLater(() -> {
            // Create and display the registration window
            frame = new JFrame("Fitness Tracking");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null); // Center the window on the screen

            // Initialize panels
            calorieTrackerPanel = new CalorieTrackerPanel(progressDataUpdater);
            workoutLogPanel = new WorkoutLogPanel(progressDataUpdater);
            progressDashboardPanel = new ProgressDashboardPanel(calorieTrackerPanel, workoutLogPanel);

            // Add panels to tabbed pane
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Calorie Tracker", calorieTrackerPanel);
            tabbedPane.addTab("Workout Log", workoutLogPanel);
            tabbedPane.addTab("Progress Dashboard", progressDashboardPanel);
            tabbedPane.addTab("Calorie Tracker", calorieTrackerPanel);
            tabbedPane.addTab("Workout Log", workoutLogPanel);
            tabbedPane.addTab("Progress Dashboard", progressDashboardPanel);
            tabbedPane.addTab("Goal Setting", new GoalSettingPanel());

            // Add tabbed pane to the frame
            frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

            frame.setVisible(true);

            JMenuBar menuBar = new JMenuBar();
            JMenu settingsMenu = new JMenu("Settings");
            JMenuItem setGoalsMenuItem = new JMenuItem("Set Goals");
            setGoalsMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GoalSettingDialog goalSettingDialog = new GoalSettingDialog(MainClass.this);
                    goalSettingDialog.setVisible(true);
                }
            });
            settingsMenu.add(setGoalsMenuItem);
            menuBar.add(settingsMenu);
            frame.setJMenuBar(menuBar);
        });
    }

    private void updateProgressData() {
        progressDashboardPanel.updateProgressData();
    }

    // Main method
    public static void main(String[] args) {
        new MainClass();
    }
}
