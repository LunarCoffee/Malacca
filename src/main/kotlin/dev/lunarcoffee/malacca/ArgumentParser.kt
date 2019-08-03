package dev.lunarcoffee.malacca

import dev.lunarcoffee.malacca.modules.*
import java.io.File

class ArgumentParser(private val args: Array<String>) {
    // Gets the appropriate decoding module to use based on the provided switch.
    fun getModule(): Module {
        return if (args[0] matches switch) {
            when (args[0][1]) {
                's' -> Substitutor()
                'b' -> BaseConverter()
                'u' -> UnicodeConverter()
                else -> showHelpText()
            }
        } else {
            showHelpText()
        }
    }

    // Returns the arguments to be passed to the module's [decode] method in the first element, and
    // the contents of the input file (or just the raw input if the --raw flag is specified) in the
    // second element. The third element is whether or not the input is to be treated as raw.
    fun getModuleArgs(): Triple<List<String>, String, Boolean> {
        val raw = args.last() == "--raw"
        val args = if (raw) args.dropLast(1) else args.toList()

        val path = args.last().trim()
        val input = if (raw) path else File(path).readTextOrExit().trim()

        return Triple(args.drop(1).dropLast(1), input, raw)
    }

    companion object {
        // Matches a command line switch.
        private val switch = """-\w""".toRegex()
    }
}
