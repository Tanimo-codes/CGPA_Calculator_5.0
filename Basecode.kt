package com.example.cgpacalculator50

//This function does the actual calculation
fun calculateCGPA(
    firstSemesterCourses: MutableMap<String, Int>,
    sem2Marks: MutableMap<String, Int>? = null,
    firstSemesterCreditUnit: MutableMap<String, Int>,
    sem2CreditUnit: MutableMap<String, Int>? = null
): Double {
    val totalUnits = firstSemesterCreditUnit.values.sum() + (sem2CreditUnit?.values?.sum() ?: 0)

    var totalGradePoints = 0
    for ((course, score) in firstSemesterCourses) {
        val gradePoint = when {
            score >= 70 -> 5
            score >= 60 -> 4
            score >= 50 -> 3
            score >= 45 -> 2
            score >= 40 -> 1
            else -> 0
        }
        totalGradePoints += gradePoint * firstSemesterCreditUnit[course]!!
    }

    sem2Marks?.let {
        for ((course, score) in it) {
            val gradePoint = when {
                score >= 70 -> 5
                score >= 60 -> 4
                score >= 50 -> 3
                score >= 45 -> 2
                score >= 40 -> 1
                else -> 0
            }
            totalGradePoints += gradePoint * sem2CreditUnit!![course]!!
        }
    }
    /*
    In Kotlin, .let is a scope function that takes a lambda expression as an argument and invokes the lambda on the object (or value) that the function is called on.
    The result of the lambda expression is the return value of the .let function.
    The purpose of .let is to execute some code on an object in a safe way and to ensure that the object is not null. When using .let,
    you can perform null checks on the object before executing the code in the lambda expression.
    */

    return totalGradePoints.toDouble() / totalUnits
}





fun main() {
    println("Enter your name: ")
    val name = readln()
    println("Hi $name, welcome to the CGPA Calculator!")

// An attempt at creating a mutable map for storing the scores for each course
    val firstSemesterCourses = mutableMapOf(
        "CHM211" to 1, "CHM221" to 2, "CHM251" to 1,
        "CHM231" to 1, "MTS241" to 1, "CSC203" to 1,
        "PHS251" to 1, "STS201" to 1, "CHM291" to 1,
        "CHM292" to 1)
    val firstSemesterCreditUnit = mutableMapOf(
        "CHM211" to 2, "CHM221" to 2, "CHM251" to 2,
        "CHM231" to 2, "MTS241" to 3, "CSC203" to 3,
        "PHS251" to 3, "STS201" to 3, "CHM291" to 1,
        "CHM292" to 1)

//  These are some of the courses minus two electives, ...

    println("Enter your score for CHM211: ")
    val two1one = readln()
    //As suspected readln() returns a string, simply convert it to an Integer
    // firstSemesterCourses.put("CHM211", two1one.toInt()) this can be neater
    firstSemesterCourses["CHM211"] = two1one.toInt()


    // So it works, now to do it for all the others
    println("Enter your score for CHM221: ")
    val two2one = readln()
    firstSemesterCourses["CHM221"] = two2one.toInt()


    println("Enter your score for CHM251: ")
    val two5one = readln()
    firstSemesterCourses["CHM251"] = two5one.toInt()


    println("Enter your score for CHM231: ")
    val two3one = readln()
    firstSemesterCourses["CHM231"] = two3one.toInt()

    println("Enter your score for MTS241: ")
    val mtwo4one = readln()
    firstSemesterCourses["MTS241"] = mtwo4one.toInt()

    println("Enter your score for CSC203: ")
    val ctwo0one = readln()
    firstSemesterCourses["CSC203"] = ctwo0one.toInt()

    println("Enter your score for PHS251: ")
    val ptwo5one = readln()
    firstSemesterCourses["PHS251"] = ptwo5one.toInt()

    println("Enter your score for STS201: ")
    val stwo0one = readln()
    firstSemesterCourses["STS201"] = stwo0one.toInt()

    println("Enter your score for CHM291: ")
    val two9one = readln()
    firstSemesterCourses["CHM291"] = two9one.toInt()

    println("Enter your score for CHM292: ")
    val two9two = readln()
    firstSemesterCourses["CHM292"] = two9two.toInt()
    println(firstSemesterCourses) // Checks if the above works, IT WORKS!


    //Mutablemap that holds results for the second semester.
    //next problem is how to not allow this run, if they are no second semester results
    val sem2Marks = mutableMapOf("Math" to 80, "English" to 90, "Chemistry" to 75, "Physics" to 85, "Statistics" to 70)

    val Sem2CreditUnit = mutableMapOf("Math" to 3, "English" to 2, "Chemistry" to 3, "Physics" to 2, "Statistics" to 3)

    //Solved it!, kinda easy
    println("Do you have results for both semesters? (yes or no)")
    val input0 = readLine()
    if (input0?.equals("no", ignoreCase = true) == true){
        println("Alright, only Cgpa for first semester will be provided")
    } else {

        println("Enter your score for Math: ")
        val Math = readln()
        sem2Marks["Math"] = Math.toInt()

        println("Enter your score for English: ")
        val English = readln()
        sem2Marks["English"] = English.toInt()

        println("Enter your score for Chemistry: ")
        val Chemistry = readln()
        sem2Marks["Chemistry"] = Chemistry.toInt()

        println("Enter your score for Physics: ")
        val Physics = readln()
        sem2Marks["Physics"] = Physics.toInt()

        println("Enter your score for Statistics: ")
        val Statistics = readln()
        sem2Marks["Chemistry"] = Statistics.toInt()
    }
/*
honestly feel there is still a better way to all this, next mission is how to perform calculations on all these values

--Was later able to tackle that, phew
*/

    // Conditional that caters for single semesters

    if (input0?.equals("no", ignoreCase = true) == true) { // this allows the program to take in input regardless of the case
        val cgpaSem1 = calculateCGPA(firstSemesterCourses, null, firstSemesterCreditUnit)
        println("CGPA for Semester 1: $cgpaSem1")
    } else {
        val cgpaYear = calculateCGPA(firstSemesterCourses, sem2Marks, firstSemesterCreditUnit, Sem2CreditUnit)
        println("CGPA for the academic year: $cgpaYear")
    }


}
