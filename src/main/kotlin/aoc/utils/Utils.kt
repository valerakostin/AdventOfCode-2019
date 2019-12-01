package aoc.utils

import java.nio.file.Files
import java.nio.file.Paths

internal object Utils {
    fun linesFromResource(resource: String): List<String> {
        val url = Utils.javaClass.classLoader.getResource(resource)
        if (url != null)
            return Files.readAllLines(Paths.get(url.toURI()))
        return emptyList()
    }

    fun lineFromResource(resource: String): String {
        return linesFromResource(resource)[0]
    }

    fun <T> itemsFromResource(resource: String, transform: ((String) -> T)): List<T> {
        return linesFromResource(resource).map(transform).toList()
    }
}