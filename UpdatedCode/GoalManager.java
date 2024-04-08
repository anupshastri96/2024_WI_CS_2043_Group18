import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GoalManager {
    private static int calorieGoal;
    private static int workoutDurationGoal;
    private static List<Goal> goals = new ArrayList<>();

    public static void setCalorieGoal(int calorieGoal) {
        GoalManager.calorieGoal = calorieGoal;
    }

    public static int getCalorieGoal() {
        return calorieGoal;
    }

    public static void setWorkoutDurationGoal(int workoutDurationGoal) {
        GoalManager.workoutDurationGoal = workoutDurationGoal;
    }

    public static int getWorkoutDurationGoal() {
        return workoutDurationGoal;
    }

    public static void addGoal(Goal goal) {
        goals.add(goal);
    }

    public static List<Goal> getGoals() {
        return goals;
    }

    public static void updateGoalProgress(Goal goal, int progress) {
        goal.setProgress(progress);
    }

    public static class Goal {
        private String name;
        private int target;
        private int progress;
        private GoalCategory category;
        private LocalDate dueDate;

        public Goal(String name, int target, GoalCategory category, LocalDate dueDate) {
            this.name = name;
            this.target = target;
            this.category = category;
            this.dueDate = dueDate;
            this.progress = 0;
        }

        public String getName() {
            return name;
        }

        public int getTarget() {
            return target;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public GoalCategory getCategory() {
            return category;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }
    }
}
