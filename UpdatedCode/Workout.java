// Workout.java
import java.util.Date;

public class Workout implements DataEntry {
    private String type;
    private int durationMinutes;
    private Date date;
    private int intensity;
    private String location;
    private String notes;
    private double distanceCovered;

    public Workout(String type, int durationMinutes, Date date, int intensity, String location, String notes, double distanceCovered) {
        this.type = type;
        this.durationMinutes = durationMinutes;
        this.date = date;
        this.intensity = intensity;
        this.location = location;
        this.notes = notes;
        this.distanceCovered = distanceCovered;
    }

    public String getType() {
        return type;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public double getDistanceCovered() {
        return distanceCovered;
    }

    // Other getters and setters

    @Override
    public int getValue() {
        return durationMinutes;
    }

    @Override
    public String toString() {
        return "Workout Type: " + type + ", Duration: " + durationMinutes + " minutes, Date: " + date + ", Intensity: " + intensity + ", Location: " + location + ", Notes: " + notes + ", Distance Covered: " + distanceCovered + " km";
    }
}