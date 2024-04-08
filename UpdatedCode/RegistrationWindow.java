import javax.swing.*;

public class RegistrationWindow extends JFrame {
    private CalorieTrackerPanel calorieTrackerPanel;
    private WorkoutLogPanel workoutLogPanel;
    private ProgressDashboardPanel progressDashboardPanel;
    private ProgressDataUpdater progressDataUpdater;

    RegistrationWindow() {
        setTitle("Registration Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window on the screen

        // Create a ProgressDataUpdater instance
        progressDataUpdater = new ProgressDataUpdater() {
            @Override
            public void updateProgressData() {
               
            }
        };

        // Initialize panels
        calorieTrackerPanel = new CalorieTrackerPanel(progressDataUpdater);
        workoutLogPanel = new WorkoutLogPanel(progressDataUpdater);
        progressDashboardPanel = new ProgressDashboardPanel(calorieTrackerPanel, workoutLogPanel);

        // Add panels to tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Calorie Tracker", calorieTrackerPanel);
        tabbedPane.addTab("Workout Log", workoutLogPanel);
        tabbedPane.addTab("Progress Dashboard", progressDashboardPanel);

        // Add tabbed pane to the frame
        getContentPane().add(tabbedPane);
    }
}