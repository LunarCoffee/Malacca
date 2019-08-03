package dev.lunarcoffee.malacca.modules

import dev.lunarcoffee.malacca.splitSpaces
import dev.lunarcoffee.malacca.toIntOrExit

class UnicodeConverter : Module {
    override fun decode(args: List<String>, input: String): String {
        return input
            .splitSpaces()
            .joinToString("") { Character.toChars(it.toIntOrExit()).joinToString("") }
    }
}
