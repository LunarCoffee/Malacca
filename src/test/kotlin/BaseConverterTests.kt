import dev.lunarcoffee.malacca.modules.BaseConverter
import org.junit.Test

class BaseConverterTests {
    @Test
    fun test1() {
        val result = BaseConverter().decode(listOf("16", "2", "d "), "A 123 FF DEDbeef", false)
        assert(result == "1010 100100011 11111111 1101111011011011111011101111")
    }

    @Test
    fun test2() {
        val result = BaseConverter()
            .decode(listOf("10", "10", "c3"), "123456293842032001000", true)
        assert(result == "123 456 293 842 32 1 0")
    }
}
