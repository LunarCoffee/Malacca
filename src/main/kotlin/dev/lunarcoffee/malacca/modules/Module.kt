package dev.lunarcoffee.malacca.modules

interface Module {
    fun decode(args: List<String>, input: String): String
}
