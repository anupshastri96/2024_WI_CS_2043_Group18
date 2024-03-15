// Sample code for optimizing workout logging in FitTracker application
import java.util.List;

public class WorkoutLogger {
    public void logWorkout(String workoutType, int duration, int intensity, List<String> equipmentUsed) {
        Workout workout = new Workout(workoutType, duration, intensity, equipmentUsed);
        // Save workout data to database or file
        WorkoutDatabase.saveWorkout(workout);
    }

    // Additional helper classes and methods
    static class Workout {
        private String workoutType;
        private int duration;
        private int intensity;
        private List<String> equipmentUsed;

        public Workout(String workoutType, int duration, int intensity, List<String> equipmentUsed) {
            this.workoutType = workoutType;
            this.duration = duration;
            this.intensity = intensity;
            this.equipmentUsed = equipmentUsed;
        }
    }

    static class WorkoutDatabase {
        public static void saveWorkout(Workout workout) {
            // Implementation logic for saving workout data to database
            System.out.println("Workout logged successfully: " + workout);
        }
    }
}
