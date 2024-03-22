import java.util.Date;

public class Workout {
    private String type;
    private int durationMinutes;
    private Date date;

    public Workout(String type, int durationMinutes, Date date) {
        this.type = type;
        this.durationMinutes = durationMinutes;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
