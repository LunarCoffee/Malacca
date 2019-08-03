package dev.lunarcoffee.malacca.modules

import dev.lunarcoffee.malacca.exitAfterPrintln
import dev.lunarcoffee.malacca.toRegexSafe
import java.io.File

class Substitutor : Module {
    override fun decode(args: List<String>, input: String): String {
        val table = getTranslationTable(args[1].toRegexSafe(), args[0])

        // Map each character by the table, leaving the character as it is if not found.
        return input.map { table.getOrDefault(it.toString(), it.toString()) }.joinToString("")
    }

    private fun getTranslationTable(regex: Regex, tableFile: String): Map<String, String> {
        return File(tableFile)
            .readLines()
            .flatMap { rule ->
                // The provided regex should produce two match groups, the first being the encoded
                // character present in the ciphertext, and the second being its decoded value.
                val destructured = regex.matchEntire(rule)?.destructured
                    ?: exitAfterPrintln("Error: rule '$rule' doesn't match regex!", 2)

                val (input, output) = destructured
                val inputChars = input.split(",")

                // Only support one character inputs. Maybe support multiple later?
                if (inputChars.any { it.length > 1 })
                    exitAfterPrintln("Error: rule '$rule' has too long of an input string!", 2)

                // Map all input characters to the single output.
                inputChars.map { it to output }
            }
            .toMap()
    }
}
