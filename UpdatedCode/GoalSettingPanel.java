import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GoalSettingPanel extends JPanel {
    private List<GoalManager.Goal> goals;
    private JTextField goalNameField;
    private JTextField goalTargetField;
    private JComboBox<GoalCategory> goalCategoryComboBox;
    private JSpinner daySpinner;
    private JSpinner monthSpinner;
    private JSpinner yearSpinner;
    private JTextArea goalListArea;

    public GoalSettingPanel() {
        goals = new ArrayList<>();
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel goalNameLabel = new JLabel("Goal Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(goalNameLabel, gbc);

        goalNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(goalNameField, gbc);

        JLabel goalTargetLabel = new JLabel("Goal Target:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputPanel.add(goalTargetLabel, gbc);

        goalTargetField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(goalTargetField, gbc);

        JLabel goalCategoryLabel = new JLabel("Goal Category:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(goalCategoryLabel, gbc);

        goalCategoryComboBox = new JComboBox<>(new GoalCategory[]{
                new GoalCategory("Fitness"),
                new GoalCategory("Health"),
                new GoalCategory("Career"),
                new GoalCategory("Personal Development")
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        inputPanel.add(goalCategoryComboBox, gbc);

        JLabel dueDateLabel = new JLabel("Due Date:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        inputPanel.add(dueDateLabel, gbc);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        daySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        monthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        yearSpinner = new JSpinner(new SpinnerNumberModel(2023, 2023, 2050, 1));
        datePanel.add(daySpinner);
        datePanel.add(monthSpinner);
        datePanel.add(yearSpinner);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        inputPanel.add(datePanel, gbc);

        JButton addGoalButton = new JButton("Add Goal");
        addGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGoal();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(addGoalButton, gbc);

        add(inputPanel, BorderLayout.NORTH);

        goalListArea = new JTextArea();
        goalListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(goalListArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addGoal() {
        String goalName = goalNameField.getText();
        String goalTargetText = goalTargetField.getText().trim();

        if (goalName.isEmpty() || goalTargetText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a goal name and target value.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int goalTarget;
        try {
            goalTarget = Integer.parseInt(goalTargetText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid target value. Please enter a numeric value.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        GoalCategory selectedCategory = (GoalCategory) goalCategoryComboBox.getSelectedItem();
        int day = (int) daySpinner.getValue();
        int month = (int) monthSpinner.getValue();
        int year = (int) yearSpinner.getValue();
        LocalDate dueDate = LocalDate.of(year, month, day);

        GoalManager.Goal newGoal = new GoalManager.Goal(goalName, goalTarget, selectedCategory, dueDate);
        goals.add(newGoal);
        GoalManager.addGoal(newGoal);
        updateGoalList();

        goalNameField.setText("");
        goalTargetField.setText("");
    }

    private void updateGoalList() {
        StringBuilder goalListBuilder = new StringBuilder();
        for (GoalManager.Goal goal : goals) {
            goalListBuilder.append(goal.getName()).append(" - Target: ").append(goal.getTarget())
                    .append(", Category: ").append(goal.getCategory().getName())
                    .append(", Due Date: ").append(goal.getDueDate()).append("\n");
        }
        goalListArea.setText(goalListBuilder.toString());
    }
}
