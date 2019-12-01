package aoc.day01

import aoc.utils.Utils

typealias Mass = Long
typealias FuelRequirement = Long

fun task1(masses: Collection<Mass>) = sumOfFuelRequirements(masses, ::moduleFuelRequirements)
fun task2(masses: Collection<Mass>) = sumOfFuelRequirements(masses, ::totalFuelRequirements)

private fun sumOfFuelRequirements(masses: Collection<Mass>, algorithm: (Mass) -> FuelRequirement): FuelRequirement {
    return masses.map(algorithm).sum()
}


private fun FuelRequirement.isNegativeRequirement() = this < 0

internal fun moduleFuelRequirements(mass: Mass): FuelRequirement = mass / 3 - 2

internal fun totalFuelRequirements(mass: Mass): FuelRequirement {
    fun fuelRequirement(currentMass: Mass, sum: FuelRequirement): FuelRequirement {
        val moduleFuelRequirements = moduleFuelRequirements(currentMass)
        if (moduleFuelRequirements.isNegativeRequirement())
            return sum
        return fuelRequirement(moduleFuelRequirements, sum + moduleFuelRequirements)

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

