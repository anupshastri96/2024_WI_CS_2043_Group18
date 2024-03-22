import java.util.Date;
import java.util.List;

public class progressDashboard {

    
    public static void displayProgressStatistics(List<Workout> workouts, List<CalorieIntake> calorieIntakes) {
        
        System.out.println("Workout Statistics:");
        System.out.println("Total Workouts: " + workouts.size());
        int totalWorkoutDuration = calculateTotalWorkoutDuration(workouts);
        System.out.println("Total Workout Duration (minutes): " + totalWorkoutDuration);
        double averageWorkoutIntensity = calculateAverageWorkoutIntensity(workouts);
        System.out.println("Average Workout Intensity: " + averageWorkoutIntensity);
        int maxWorkoutIntensity = calculateMaxWorkoutIntensity(workouts);
        System.out.println("Maximum Workout Intensity: " + maxWorkoutIntensity);
        int minWorkoutIntensity = calculateMinWorkoutIntensity(workouts);
        System.out.println("Minimum Workout Intensity: " + minWorkoutIntensity);

       
        System.out.println("\nCalorie Intake Statistics:");
        System.out.println("Total Calorie Intake: " + calculateTotalCalorieIntake(calorieIntakes));
    }

    
    private static int calculateTotalWorkoutDuration(List<Workout> workouts) {
        int totalDuration = 0;
        for (Workout workout : workouts) {
            totalDuration += workout.getDurationMinutes();
        }
        return totalDuration;
    }

   
    private static double calculateAverageWorkoutIntensity(List<Workout> workouts) {
        if (workouts.isEmpty()) {
            return 0.0;
        }
        int totalIntensity = 0;
        for (Workout workout : workouts) {
            totalIntensity += workout.getIntensity();
        }
        return (double) totalIntensity / workouts.size();
    }

   
    private static int calculateMaxWorkoutIntensity(List<Workout> workouts) {
        if (workouts.isEmpty()) {
            return 0;
        }
        int maxIntensity = Integer.MIN_VALUE;
        for (Workout workout : workouts) {
            maxIntensity = Math.max(maxIntensity, workout.getIntensity());
        }
        return maxIntensity;
    }

    
    private static int calculateMinWorkoutIntensity(List<Workout> workouts) {
        if (workouts.isEmpty()) {
            return 0;
        }
        int minIntensity = Integer.MAX_VALUE;
        for (Workout workout : workouts) {
            minIntensity = Math.min(minIntensity, workout.getIntensity());
        }
        return minIntensity;
    }

   
    private static int calculateTotalCalorieIntake(List<CalorieIntake> calorieIntakes) {
        int totalCalories = 0;
        for (CalorieIntake calorieIntake : calorieIntakes) {
            totalCalories += calorieIntake.getCalories();
        }
        return totalCalories;
    }

}
