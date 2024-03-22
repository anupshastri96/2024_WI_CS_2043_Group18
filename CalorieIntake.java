import java.util.Date;

public class CalorieIntake {
    private int calories;
    private Date date;

    public CalorieIntake(int calories, Date date) {
        this.calories = calories;
        this.date = date;
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
}
