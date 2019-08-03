import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    SubstitutorTests::class,
    BaseConverterTests::class,
    UnicodeConverterTests::class
)
class FullTestSuite {
    companion object {
        const val TEST_RES = "src/test/resources"
    }
}
