import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoalSettingDialog extends JDialog {
    private JTextField calorieGoalField;
    private JTextField workoutDurationGoalField;

    public GoalSettingDialog(JFrame parent) {
        super(parent, "Set Goals", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel calorieGoalLabel = new JLabel("Calorie Goal:");
        panel.add(calorieGoalLabel);
        calorieGoalField = new JTextField();
        panel.add(calorieGoalField);

        JLabel workoutDurationGoalLabel = new JLabel("Workout Duration Goal (minutes):");
        panel.add(workoutDurationGoalLabel);
        workoutDurationGoalField = new JTextField();
        panel.add(workoutDurationGoalField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGoals();
            }
        });
        panel.add(new JLabel());
        panel.add(saveButton);

        add(panel, BorderLayout.CENTER);
    }

    private void saveGoals() {
        int calorieGoal = Integer.parseInt(calorieGoalField.getText());
        int workoutDurationGoal = Integer.parseInt(workoutDurationGoalField.getText());

        // Save the goals to the application's data structure
        // For example, you could have a GoalManager class that stores the goals
        GoalManager.setCalorieGoal(calorieGoal);
        GoalManager.setWorkoutDurationGoal(workoutDurationGoal);

        dispose(); // Close the dialog
    }
}
