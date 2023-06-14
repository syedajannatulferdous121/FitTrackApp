import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class WeightEntry(val weight: Double, val timestamp: LocalDateTime)

data class Exercise(val name: String, val duration: Int, val caloriesBurned: Int)

data class Meal(val name: String, val calories: Int, val macronutrients: Map<String, Double>)

class FitTrack {
    private val weightHistory = mutableListOf<WeightEntry>()
    private val exerciseHistory = mutableListOf<Exercise>()
    private val mealHistory = mutableListOf<Meal>()

    fun logWeight(weight: Double) {
        val entry = WeightEntry(weight, LocalDateTime.now())
        weightHistory.add(entry)
        println("Weight logged: $weight kg")
    }

    fun logExercise(exercise: Exercise) {
        exerciseHistory.add(exercise)
        println("Exercise logged: ${exercise.name} (${exercise.duration} minutes)")
    }

    fun logMeal(meal: Meal) {
        mealHistory.add(meal)
        println("Meal logged: ${meal.name} (${meal.calories} calories)")
    }

    fun displayWeightHistory() {
        println("Weight History:")
        weightHistory.forEach {
            val timestampStr = it.timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            println("$timestampStr - ${it.weight} kg")
        }
    }

    fun displayExerciseHistory() {
        println("Exercise History:")
        exerciseHistory.forEach {
            println("${it.name} - ${it.duration} minutes - ${it.caloriesBurned} calories burned")
        }
    }

    fun displayMealHistory() {
        println("Meal History:")
        mealHistory.forEach {
            val macronutrientsStr = it.macronutrients.entries.joinToString { "${it.key}: ${it.value}g" }
            println("${it.name} - ${it.calories} calories - $macronutrientsStr")
        }
    }

    fun calculateTotalCaloriesBurned(): Int {
        return exerciseHistory.sumBy { it.caloriesBurned }
    }

    fun calculateTotalCaloriesConsumed(): Int {
        return mealHistory.sumBy { it.calories }
    }

    fun calculateNetCalories(): Int {
        val totalCaloriesBurned = calculateTotalCaloriesBurned()
        val totalCaloriesConsumed = calculateTotalCaloriesConsumed()
        return totalCaloriesBurned - totalCaloriesConsumed
    }

    fun suggestMealPlan(targetCalories: Int): List<Meal> {
        val suggestedMeals = mutableListOf<Meal>()
        var remainingCalories = targetCalories - calculateTotalCaloriesConsumed()

        // Implement meal planning algorithm to suggest meals based on remaining calories
        // You can consider factors like macronutrient distribution and dietary preferences

        return suggestedMeals
    }
}

fun main() {
    val fitTrack = FitTrack()

    fitTrack.logWeight(70.5)
    fitTrack.logExercise(Exercise("Running", 30, 300))
    fitTrack.logMeal(Meal("Chicken Salad", 350, mapOf("Protein" to 25.0, "Carbohydrates" to 10.0, "Fat" to 5.0)))

    fitTrack.displayWeightHistory()
    fitTrack.displayExerciseHistory()
    fitTrack.displayMealHistory()

    val totalCaloriesBurned = fitTrack.calculateTotalCaloriesBurned()
    val totalCaloriesConsumed = fitTrack.calculateTotalCaloriesConsumed()
    val netCalories = fitTrack.calculateNetCalories()
    println("Total Calories Burned: $totalCaloriesBurned")
    println("Total Calories Consumed: $totalCaloriesConsumed")
    println("Net Calories: $netCalories")

    val targetCalories = 2000
    val suggestedMeals = fitTrack.suggestMealPlan(targetCalories)
    println("Suggested Meal Plan for $targetCalories calories:")
    suggestedMeals.forEach {
        println("${it.name} - ${it.calories} calories")
    }
}
