// CalorieTrackerPanel.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class CalorieTrackerPanel extends JPanel {
    private JTextField caloriesField;
    private JComboBox<String> mealTypeComboBox;
    private JTextArea descriptionArea;
    private JButton submitButton;
    private JSpinner dateSpinner;
    private ProgressDataUpdater progressUpdater;
    private List<CalorieIntake> calorieIntakes;
    private JTextField foodTypeField;
    private JTextField servingSizeField;

    private static final Color PANEL_BACKGROUND_COLOR = new Color(240, 240, 240);
    private static final Color LABEL_COLOR = new Color(50, 50, 50);
    private static final Color BUTTON_COLOR = new Color(255, 102, 102); // Light red

    public CalorieTrackerPanel(ProgressDataUpdater progressUpdater) {
        this.progressUpdater = progressUpdater;
        this.calorieIntakes = new ArrayList<>();
        setLayout(new GridBagLayout());
        setBackground(PANEL_BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

        // Input for calories
        caloriesField = new JTextField(10);

        // Dropdown for selecting meal type
        String[] mealTypes = {"Breakfast", "Lunch", "Dinner", "Snack"};
        mealTypeComboBox = new JComboBox<>(mealTypes);

        // Text area for description
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);

        // Date spinner for date of the meal
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        // Input for food type
        foodTypeField = new JTextField(10);

        // Input for serving size
        servingSizeField = new JTextField(10);

        // Submit button
        submitButton = new JButton("Log Calorie Intake");
        submitButton.setBackground(BUTTON_COLOR);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this::logCalorieIntake);

        // Add components to panel
        add(new JLabel("Calories:"), gbc);
        add(caloriesField, gbc);
        add(new JLabel("Meal Type:"), gbc);
        add(mealTypeComboBox, gbc);
        add(new JLabel("Description:"), gbc);
        add(scrollPane, gbc);
        add(new JLabel("Date:"), gbc);
        add(dateSpinner, gbc);
        add(new JLabel("Food Type:"), gbc);
        add(foodTypeField, gbc);
        add(new JLabel("Serving Size:"), gbc);
        add(servingSizeField, gbc);
        add(submitButton, gbc);
    }

    private void logCalorieIntake(ActionEvent e) {
        try {
            int calories = Integer.parseInt(caloriesField.getText());
            String mealType = (String) mealTypeComboBox.getSelectedItem();
            String description = descriptionArea.getText();
            Date date = (Date) dateSpinner.getValue();
            String foodType = foodTypeField.getText();
            double servingSize = Double.parseDouble(servingSizeField.getText());

            CalorieIntake calorieIntake = new CalorieIntake(calories, date, mealType, description, foodType, servingSize);
            calorieIntakes.add(calorieIntake);
            DataStorage.addCalorieIntake(calorieIntake);

            // Clear the input fields after logging the data
            caloriesField.setText("");
            descriptionArea.setText("");
            dateSpinner.setValue(new Date());
            foodTypeField.setText("");
            servingSizeField.setText("");

            progressUpdater.updateProgressData();

            // Show confirmation message
            JOptionPane.showMessageDialog(this, "Calorie intake logged successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid calorie information.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<CalorieIntake> getCalorieIntakes() {
        return calorieIntakes;
    }
}