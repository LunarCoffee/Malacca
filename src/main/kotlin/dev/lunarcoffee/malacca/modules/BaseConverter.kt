package dev.lunarcoffee.malacca.modules

import dev.lunarcoffee.malacca.*

class BaseConverter : Module {
    override fun decode(args: List<String>, input: String, raw: Boolean): String {
        val from = args[0].toIntOrExit() // Base to convert from.
        val to = args[1].toIntOrExit() // Base to convert to.

        // The first character of [args[2]] determines if [value] defined below is a delimiter
        // string or a chunk size integer.
        val delimOrChunk = args[2][0]
        val value = args[2].drop(1)

        val numbers = when (delimOrChunk) {
            'c' -> input.chunked(value.toIntOrExit()) // Chunk the input string.
            'd' -> input.split(value.toRegexOrExit()) // Split the input string.
            else -> exitAfterPrintln(
                "Error: '$delimOrChunk' contains no delimiter or chunk size!",
                2
            )
        }.map { it.toIntOrExit(from) } // Convert the number to integers.

        // Join the converted numbers into a whitespace delimited string.
        return numbers.joinToString(" ") { it.toString(to) }
    }
}
