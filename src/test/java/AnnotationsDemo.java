import org.junit.jupiter.api.*;

public class AnnotationsDemo {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Это выполнится перед всеми тестами");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Это выполнится перед =каждым отдельным= тестом");
    }

    @Test
    void test01() {
        System.out.println("Это первый тест");
    }

    @Test
    void test02() {
        System.out.println("Это второй тест");
    }

    @Test
    void test03() {
        System.out.println("Это третий тест");
    }

    @Test
    @Disabled
    void test04() {
        System.out.println("Это четвертый тест, он отключен");
    }

    void justAMethod() {
        System.out.println("Это вообще не тест");
    }

    @AfterEach
    void afterEach() {
        System.out.println("Это выполнится после =каждого отдельного= теста");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Это выполнится =после всех= тестов");
    }


}
