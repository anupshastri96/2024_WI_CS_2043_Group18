import java.util.Date;

public class CalorieIntake {
    private int calories;
    private Date date;
    private String mealType;
    private String description;

    public CalorieIntake(int calories, Date date, String mealType, String description) {
        this.calories = calories;
        this.date = date;
        this.mealType = mealType;
        this.description = description;
    }
    
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String toString() {
        return "Calorie Intake: " + calories + " calories, Date: " + date + ", Meal Type: " + mealType + ", Description: " + description;
    }
}
