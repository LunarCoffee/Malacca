package dev.lunarcoffee.malacca.modules

import dev.lunarcoffee.malacca.toIntOrExit
import dev.lunarcoffee.malacca.toRegexSafe

class BaseConverter : Module {
    override fun decode(args: List<String>, input: String): String {
        // Base to convert from.
        val from = args[0].toIntOrExit()

        // Base to convert to.
        val to = args[1].toIntOrExit()

        val numbers = if (args[2].startsWith("c")) {
            val chunkSize = args[2].drop(1).toIntOrExit()
            input.chunked(chunkSize).map { it.toIntOrExit(from) }
        } else {
            val delim = args[2].toRegexSafe()
            input.split(delim).map { it.toIntOrExit(from) }
        }

        // Join the converted numbers into a whitespace delimited string.
        return numbers.joinToString(" ") { it.toString(to) }
    }
}
