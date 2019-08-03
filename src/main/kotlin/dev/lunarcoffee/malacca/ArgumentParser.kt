package dev.lunarcoffee.malacca

import dev.lunarcoffee.malacca.modules.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class ArgumentParser(private val args: Array<String>) {
    // Gets the appropriate decoding module to use based on the provided switch.
    fun getModule(): Module {
        return if (args[0] matches switch) {
            when (args[0][1]) {
                's' -> Substitutor()
                'B' -> BaseConverter()
                'u' -> UnicodeConverter()
                else -> showHelpText()
            }
        } else {
            showHelpText()
        }
    }

    // Returns the arguments to be passed to the module's [decode] method in the first element, and
    // the contents of the input file in the second element.
    fun getModuleArgs(): Pair<List<String>, String> {
        val path = args.last()
        val input = if (Files.exists(Paths.get(path))) {
            File(path).readText()
        } else {
            exitAfterPrintln("Error: couldn't find input file!")
        }

        return Pair(args.drop(1).dropLast(1), input)
    }

    companion object {
        // Matches a command line switch.
        private val switch = """-\w""".toRegex()
    }
}
