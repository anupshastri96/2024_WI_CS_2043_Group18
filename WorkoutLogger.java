// Sample code for optimizing workout logging in FitTracker application
public class WorkoutLogger {
    public void logWorkout(String workoutType, int duration, int intensity, List<String> equipmentUsed) {
        // Implementation logic for logging workout details
        // For demonstration, assume saving workout data to a database or file
        // This is a simplified example, actual implementation would involve error handling, validation, etc.
        Workout workout = new Workout(workoutType, duration, intensity, equipmentUsed);
        // Save workout data to database or file
        // Example:
        // workoutRepository.saveWorkout(workout);
    }
}
