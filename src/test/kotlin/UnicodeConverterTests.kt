import dev.lunarcoffee.malacca.modules.UnicodeConverter
import org.junit.Test

class UnicodeConverterTests {
    @Test
    fun test1() {
        val result = UnicodeConverter().decode(emptyList(), "129382 32 65 97", true)
        assert(result == "\uD83E\uDD66 Aa")
    }
}
