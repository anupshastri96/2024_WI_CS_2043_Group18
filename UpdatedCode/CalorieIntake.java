// CalorieIntake.java
import java.util.Date;

public class CalorieIntake implements DataEntry {
    private int calories;
    private Date date;
    private String mealType;
    private String description;
    private String foodType;
    private double servingSize;

    public CalorieIntake(int calories, Date date, String mealType, String description, String foodType, double servingSize) {
        this.calories = calories;
        this.date = date;
        this.mealType = mealType;
        this.description = description;
        this.foodType = foodType;
        this.servingSize = servingSize;
    }

    public int getCalories() {
        return calories;
    }

    public String getFoodType() {
        return foodType;
    }

    public double getServingSize() {
        return servingSize;
    }

    // Other getters and setters

    @Override
    public int getValue() {
        return calories;
    }

    @Override
    public String toString() {
        return "Calorie Intake: " + calories + " calories, Date: " + date + ", Meal Type: " + mealType + ", Description: " + description + ", Food Type: " + foodType + ", Serving Size: " + servingSize;
    }
}