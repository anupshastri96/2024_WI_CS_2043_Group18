import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class ProgressDashboardPanel extends JPanel {
    private CalorieTrackerPanel calorieTrackerPanel;
    private WorkoutLogPanel workoutLogPanel;
    private JTextArea progressTextArea;
    private JPanel summaryPanel;
    private LineChartPanel lineChartPanel;
    private JLabel calorieGoalLabel;
    private JLabel workoutDurationGoalLabel;

    private static final Color PANEL_BACKGROUND_COLOR = new Color(240, 240, 240);
    private static final Color LABEL_COLOR = new Color(50, 50, 50);
    private static final Color SUMMARY_BACKGROUND_COLOR = new Color(220, 220, 220);
    private static final Color SUMMARY_TEXT_COLOR = new Color(80, 80, 80);
    private static final Color CALORIE_INTAKE_COLOR = new Color(255, 102, 102); // Light red
    private static final Color WORKOUT_COLOR = new Color(102, 204, 102); // Light green

    public ProgressDashboardPanel(CalorieTrackerPanel calorieTrackerPanel, WorkoutLogPanel workoutLogPanel) {
        this.calorieTrackerPanel = calorieTrackerPanel;
        this.workoutLogPanel = workoutLogPanel;
        setLayout(new BorderLayout());
        setBackground(PANEL_BACKGROUND_COLOR);

        progressTextArea = new JTextArea();
        progressTextArea.setEditable(false);
        progressTextArea.setBackground(Color.WHITE);
        progressTextArea.setForeground(LABEL_COLOR);
        JScrollPane scrollPane = new JScrollPane(progressTextArea);
        add(scrollPane, BorderLayout.CENTER);

        summaryPanel = new JPanel(new GridLayout(1, 2));
        summaryPanel.setBackground(SUMMARY_BACKGROUND_COLOR);

        JPanel calorieIntakePanel = new JPanel(new BorderLayout());
        calorieIntakePanel.setBackground(SUMMARY_BACKGROUND_COLOR);
        JLabel calorieIntakeLabel = new JLabel("Calorie Intake Summary");
        calorieIntakeLabel.setForeground(CALORIE_INTAKE_COLOR);
        calorieIntakePanel.add(calorieIntakeLabel, BorderLayout.NORTH);
        summaryPanel.add(calorieIntakePanel);

        JPanel workoutPanel = new JPanel(new BorderLayout());
        workoutPanel.setBackground(SUMMARY_BACKGROUND_COLOR);
        JLabel workoutLabel = new JLabel("Workout Summary");
        workoutLabel.setForeground(WORKOUT_COLOR);
        workoutPanel.add(workoutLabel, BorderLayout.NORTH);
        summaryPanel.add(workoutPanel);

        JPanel goalPanel = new JPanel(new GridLayout(1, 2));
        goalPanel.setBackground(SUMMARY_BACKGROUND_COLOR);

        calorieGoalLabel = new JLabel("Calorie Goal: " + GoalManager.getCalorieGoal());
        calorieGoalLabel.setForeground(CALORIE_INTAKE_COLOR);
        goalPanel.add(calorieGoalLabel);

        workoutDurationGoalLabel = new JLabel("Workout Duration Goal: " + GoalManager.getWorkoutDurationGoal() + " minutes");
        workoutDurationGoalLabel.setForeground(WORKOUT_COLOR);
        goalPanel.add(workoutDurationGoalLabel);

        add(goalPanel, BorderLayout.NORTH);

        add(summaryPanel, BorderLayout.SOUTH);

        // Create the line chart panel
        lineChartPanel = new LineChartPanel();
        add(lineChartPanel, BorderLayout.NORTH);

        updateProgressData();
    }

    public void updateProgressData() {
    StringBuilder progressText = new StringBuilder();

    List<CalorieIntake> calorieIntakes = calorieTrackerPanel.getCalorieIntakes();
    List<Workout> workouts = workoutLogPanel.getWorkouts();

    int totalCalories = 0;
    double totalServingSize = 0.0;
    JPanel calorieIntakePanel = (JPanel) summaryPanel.getComponent(0);
    calorieIntakePanel.removeAll();
    calorieIntakePanel.add(new JLabel("Calorie Intake Summary"), BorderLayout.NORTH);
    for (CalorieIntake calorieIntake : calorieIntakes) {
        totalCalories += calorieIntake.getCalories();
        totalServingSize += calorieIntake.getServingSize();
        JLabel summaryLabel = new JLabel(calorieIntake.getFoodType() + ": " + calorieIntake.getServingSize() + " servings");
        summaryLabel.setForeground(CALORIE_INTAKE_COLOR);
        calorieIntakePanel.add(summaryLabel, BorderLayout.CENTER);
    }
    JLabel totalCaloriesLabel = new JLabel("Total Calories: " + totalCalories);
    totalCaloriesLabel.setForeground(CALORIE_INTAKE_COLOR);
    calorieIntakePanel.add(totalCaloriesLabel, BorderLayout.SOUTH);
    JLabel totalServingSizeLabel = new JLabel("Total Serving Size: " + totalServingSize);
    totalServingSizeLabel.setForeground(CALORIE_INTAKE_COLOR);
    calorieIntakePanel.add(totalServingSizeLabel, BorderLayout.SOUTH);

    int totalWorkoutDuration = 0;
    double totalDistanceCovered = 0.0;
    JPanel workoutPanel = (JPanel) summaryPanel.getComponent(1);
    workoutPanel.removeAll();
    workoutPanel.add(new JLabel("Workout Summary"), BorderLayout.NORTH);
    for (Workout workout : workouts) {
        totalWorkoutDuration += workout.getDurationMinutes();
        totalDistanceCovered += workout.getDistanceCovered();
        JLabel summaryLabel = new JLabel(workout.getType() + ": " + workout.getDurationMinutes() + " minutes, " + workout.getDistanceCovered() + " km");
        summaryLabel.setForeground(WORKOUT_COLOR);
        workoutPanel.add(summaryLabel, BorderLayout.CENTER);
    }
    JLabel totalWorkoutDurationLabel = new JLabel("Total Workout Duration: " + totalWorkoutDuration + " minutes");
    totalWorkoutDurationLabel.setForeground(WORKOUT_COLOR);
    workoutPanel.add(totalWorkoutDurationLabel, BorderLayout.SOUTH);
    JLabel totalDistanceCoveredLabel = new JLabel("Total Distance Covered: " + totalDistanceCovered + " km");
    totalDistanceCoveredLabel.setForeground(WORKOUT_COLOR);
    workoutPanel.add(totalDistanceCoveredLabel, BorderLayout.SOUTH);

    progressText.append("Calorie Intake:\n");
    for (CalorieIntake calorieIntake : calorieIntakes) {
        progressText.append(calorieIntake.toString()).append("\n");
    }

    progressText.append("\nWorkouts:\n");
    for (Workout workout : workouts) {
        progressText.append(workout.toString()).append("\n");
    }

    progressTextArea.setForeground(LABEL_COLOR);
    progressTextArea.setText(progressText.toString());

    // Update the line chart data
    List<Double> calorieData = new ArrayList<>();
    List<Double> workoutData = new ArrayList<>();

    // Add data from CalorieTrackerPanel
    for (CalorieIntake intake : calorieIntakes) {
        calorieData.add((double) intake.getCalories());
    }

    // Add data from WorkoutLogPanel
    for (Workout workout : workouts) {
        workoutData.add((double) workout.getDurationMinutes());
    }

    lineChartPanel.setData(calorieData, workoutData);

    calorieGoalLabel.setText("Calorie Goal: " + GoalManager.getCalorieGoal());
    workoutDurationGoalLabel.setText("Workout Duration Goal: " + GoalManager.getWorkoutDurationGoal() + " minutes");
}

    private static class LineChartPanel extends JPanel {
        private List<Double> calorieData;
        private List<Double> workoutData;
        private int maxValue;

        public LineChartPanel() {
            setPreferredSize(new Dimension(600, 300));
            setBackground(Color.WHITE);
        }

        public void setData(List<Double> calorieData, List<Double> workoutData) {
            this.calorieData = calorieData;
            this.workoutData = workoutData;
            maxValue = calculateMaxValue();
            repaint();
        }

        private int calculateMaxValue() {
            int max = 0;
            for (double value : calorieData) {
                max = Math.max(max, (int) Math.ceil(value));
            }
            for (double value : workoutData) {
                max = Math.max(max, (int) Math.ceil(value));
            }
            return max;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int padding = 20;
            int plotWidth = width - 2 * padding;
            int plotHeight = height - 2 * padding;

            // Draw the chart area
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(padding, padding, plotWidth, plotHeight);

            // Draw the x-axis and y-axis
            g2d.setColor(Color.BLACK);
            g2d.drawLine(padding, height - padding, width - padding, height - padding); // x-axis
            g2d.drawLine(padding, padding, padding, height - padding); // y-axis

            // Draw the data points and lines
            int dataSize = Math.max(calorieData.size(), workoutData.size());
            double xScale = (double) plotWidth / (dataSize - 1);
            double yScale = (double) plotHeight / maxValue;

            g2d.setColor(CALORIE_INTAKE_COLOR);
            for (int i = 0; i < calorieData.size(); i++) {
                int x = padding + (int) (i * xScale);
                int y = height - padding - (int) (calorieData.get(i) * yScale);
                g2d.fillOval(x - 3, y - 3, 6, 6);
                if (i > 0) {
                    int prevX = padding + (int) ((i - 1) * xScale);
                    int prevY = height - padding - (int) (calorieData.get(i - 1) * yScale);
                    g2d.drawLine(prevX, prevY, x, y);
                }
            }

            g2d.setColor(WORKOUT_COLOR);
            for (int i = 0; i < workoutData.size(); i++) {
                int x = padding + (int) (i * xScale);
                int y = height - padding - (int) (workoutData.get(i) * yScale);
                g2d.fillOval(x - 3, y - 3, 6, 6);
                if (i > 0) {
                    int prevX = padding + (int) ((i - 1) * xScale);
                    int prevY = height - padding - (int) (workoutData.get(i - 1) * yScale);
                    g2d.drawLine(prevX, prevY, x, y);
                }
            }
        }
    }
}