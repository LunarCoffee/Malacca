package dev.lunarcoffee.malacca

fun main(args: Array<String>) {
    if (args.size < 2)
        showHelpText()

    val argParser = ArgumentParser(args)
    val module = argParser.getModule()
    val (moduleArgs, input, raw) = argParser.getModuleArgs()

    println(module.decode(moduleArgs, input, raw))
}
