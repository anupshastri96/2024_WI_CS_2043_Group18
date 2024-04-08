// WorkoutLogPanel.java
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class WorkoutLogPanel extends JPanel {
    // Input fields for the workout details
    private JComboBox<String> typeComboBox;
    private JTextField durationField;
    private JSlider intensitySlider;
    private JTextField equipmentField;
    private JTextField setsField;
    private JTextField repsField;
    private JButton submitButton;
    private ProgressDataUpdater progressUpdater;
    private List<Workout> workouts;
    private JTextField distanceField;

    private static final Color PANEL_BACKGROUND_COLOR = new Color(240, 240, 240);
    private static final Color LABEL_COLOR = new Color(50, 50, 50);
    private static final Color BUTTON_COLOR = new Color(102, 204, 102); // Light green

    public WorkoutLogPanel(ProgressDataUpdater progressUpdater) {
        this.progressUpdater = progressUpdater;
        this.workouts = new ArrayList<>();
        setLayout(new GridBagLayout());
        setBackground(PANEL_BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4); // Some padding

        // Dropdown for selecting workout type
        String[] workoutTypes = {"Cardio", "Strength Training", "Flexibility"};
        typeComboBox = new JComboBox<>(workoutTypes);

        // Input for duration in minutes
        durationField = new JTextField(5);

        // Slider for intensity from 1 to 10
        intensitySlider = new JSlider(1, 10);

        // Input for equipment used
        equipmentField = new JTextField(10);

        // Input for number of sets (visible only for Strength Training and Flexibility)
        setsField = new JTextField(5);

        // Input for number of repetitions (visible only for Strength Training)
        repsField = new JTextField(5);

        // Input for distance covered
        distanceField = new JTextField(10);

        // Submit button
        submitButton = new JButton("Log Workout");
        submitButton.setBackground(BUTTON_COLOR);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> logWorkout());

        // Add components to panel
        add(new JLabel("Workout Type:"), gbc);
        add(typeComboBox, gbc);
        add(new JLabel("Duration (minutes):"), gbc);
        add(durationField, gbc);
        add(new JLabel("Intensity (1-10):"), gbc);
        add(intensitySlider, gbc);
        add(new JLabel("Equipment Used (optional):"), gbc);
        add(equipmentField, gbc);
        add(new JLabel("Sets (optional):"), gbc);
        add(setsField, gbc);
        add(new JLabel("Reps (optional):"), gbc);
        add(repsField, gbc);
        add(new JLabel("Distance Covered (km):"), gbc);
        add(distanceField, gbc);
        add(submitButton, gbc);

        // Setup listener for workout type changes to show/hide sets and reps
        typeComboBox.addActionListener(e -> updateSetsAndRepsVisibility());
    }

    // ... (rest of the code remains the same)


    private void logWorkout() {
        // Here, we gather all input data and create a Workout object
        String type = (String) typeComboBox.getSelectedItem();
        String durationText = durationField.getText().trim();
        if (durationText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid duration.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int duration = Integer.parseInt(durationText);
        int intensity = intensitySlider.getValue();
        String equipment = equipmentField.getText();
        int sets = 0;
        int reps = 0;

        // Parse sets and reps only if the fields are visible
        if (setsField.isVisible()) {
            String setsText = setsField.getText().trim();
            if (!setsText.isEmpty()) {
                sets = Integer.parseInt(setsText);
            }
        }

        if (repsField.isVisible()) {
            String repsText = repsField.getText().trim();
            if (!repsText.isEmpty()) {
                reps = Integer.parseInt(repsText);
            }
        }

        String distanceText = distanceField.getText().trim();
        double distanceCovered = 0.0;
        if (!distanceText.isEmpty()) {
            distanceCovered = Double.parseDouble(distanceText);
        }

        // Create a Workout object and add it to the workouts list
        Workout workout = new Workout(type, duration, new Date(), intensity, equipment, "", distanceCovered);
        workouts.add(workout);
        DataStorage.addWorkout(workout);

        progressUpdater.updateProgressData();
    }

    private void updateSetsAndRepsVisibility() {
        String selectedType = (String) typeComboBox.getSelectedItem();
        // Sets and reps are only relevant for Strength Training and Flexibility
        boolean isStrengthOrFlexibility = selectedType.equals("Strength Training") || selectedType.equals("Flexibility");
        setsField.setVisible(isStrengthOrFlexibility);
        // Reps are only relevant for Strength Training
        boolean isStrength = selectedType.equals("Strength Training");
        repsField.setVisible(isStrength);

        // Update the UI to reflect the changes
        WorkoutLogPanel.this.revalidate();
        WorkoutLogPanel.this.repaint();
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }
}