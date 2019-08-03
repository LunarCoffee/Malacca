package dev.lunarcoffee.malacca

import java.util.regex.PatternSyntaxException
import kotlin.system.exitProcess

fun showHelpText(): Nothing {
    exitAfterPrintln(
        """
        |Malacca 0.1.0 (LunarCoffee) 2019.
        |
        |malacca [options] input
        |       -s <table> <regex>      translation table, regex to read rules
        |       -B <from> <to> <d>|c<c> base conversion with delimiter regex or chunks
        |       -u                      space delimited unicode codepoints to characters
        """.trimMargin(),
        1
    )
}

fun exitAfterPrintln(message: String, status: Int = 0): Nothing {
    println(message)
    exitProcess(status)
}

fun String.toRegexSafe(): Regex {
    try {
        return toRegex()
    } catch (e: PatternSyntaxException) {
        exitAfterPrintln("Error: $this isn't a regex!")
    }
}

fun String.splitSpaces() = split("""\s+""".toRegex())

fun String.toIntOrExit(radix: Int = 10): Int {
    return toIntOrNull(radix) ?: exitAfterPrintln("Error: '$this' is not a number!", 2)
}
