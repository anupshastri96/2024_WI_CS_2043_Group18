import java.util.Date;

public class Workout {
    private String type;
    private int durationMinutes;
    private Date date;
    private int intensity; 
    private String location;
    private String notes;

    public Workout(String type, int durationMinutes, Date date, int intensity, String location, String notes) {
        this.type = type;
        this.durationMinutes = durationMinutes;
        this.date = date;
        this.intensity = intensity;
        this.location = location;
        this.notes = notes;
    }
    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
