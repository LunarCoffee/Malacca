import FullTestSuite.Companion.TEST_RES
import dev.lunarcoffee.malacca.modules.Substitutor
import org.junit.Test
import java.io.File

class SubstitutorTests {
    @Test
    fun test1() {
        val table = "$TEST_RES/substitutor/table0.txt"
        val input = File("$TEST_RES/substitutor/input0.txt").readText()
        val result = Substitutor().decode(listOf(table, "(.)=(.+)"), input, false)

        assert(result == "TEST!")
    }

    @Test
    fun test2() {
        val table = File("$TEST_RES/substitutor/table1.txt").readText()
        val input = File("$TEST_RES/substitutor/input1.txt").readText()
        val result = Substitutor().decode(listOf(table, "([a-z,]+)->(.)"), input, true)

        assert(result == "abc, cba.")
    }
}
