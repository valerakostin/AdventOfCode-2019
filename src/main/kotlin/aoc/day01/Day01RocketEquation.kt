package aoc.day01

import aoc.utils.Utils

typealias Mass = Long
typealias FuelRequirement = Long

fun task1(masses: Collection<Mass>) = sumOfFuelRequirements(masses, ::moduleFuelRequirement)
fun task2(masses: Collection<Mass>) = sumOfFuelRequirements(masses, ::totalFuelRequirement)

private fun sumOfFuelRequirements(masses: Collection<Mass>, algorithm: (Mass) -> FuelRequirement): FuelRequirement {
    return masses.map(algorithm).sum()
}

private fun FuelRequirement.isNegativeRequirement() = this < 0

internal fun moduleFuelRequirement(mass: Mass): FuelRequirement = mass / 3 - 2

internal fun totalFuelRequirement(mass: Mass): FuelRequirement {
    fun fuelRequirement(currentMass: Mass, sum: FuelRequirement): FuelRequirement {
        val fuelRequirement = moduleFuelRequirement(currentMass)
        if (fuelRequirement.isNegativeRequirement())
            return sum
        return fuelRequirement(fuelRequirement, sum + fuelRequirement)
    }
    return fuelRequirement(mass, 0)
}

fun main(args: Array<String>) {
    val masses = Utils.itemsFromResource("InputDay01.txt") { it.toLong() }
    val task1 = task1(masses)
    val task2 = task2(masses)

    println(
            """
               Day01
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

