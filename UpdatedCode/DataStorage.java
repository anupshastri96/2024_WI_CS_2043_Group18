import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private static final List<Workout> workouts = new ArrayList<>();
    private static final List<CalorieIntake> calorieIntakes = new ArrayList<>();

    public static void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    public static void addCalorieIntake(CalorieIntake calorieIntake) {
        calorieIntakes.add(calorieIntake);
    }

    public static List<Workout> getWorkouts() {
        return new ArrayList<>(workouts);
    }

    public static List<CalorieIntake> getCalorieIntakes() {
        return new ArrayList<>(calorieIntakes);
    }
}