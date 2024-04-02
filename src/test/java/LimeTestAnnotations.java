import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class LimeTestAnnotations extends LimeTestBase{

    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = false;
    }

    @BeforeEach
    void setUp() {
        // Открываем страницу
        open("https://lime-shop.com/ru_ru");

        // Ждём три секунды.
        sleep(3000);

    }

    @Test
    void test01() {
        $("button[data-ok]").shouldBe(Condition.visible);
        $("button[data-ok]").click();
        $("button[data-ok]").shouldNotBe(Condition.visible);
    }

    @Test
    void test02() {
        $("button[data-ok]").shouldBe(Condition.visible);
        $("button[data-ok]").click();
        $("button[data-ok]").shouldNotBe(Condition.visible);
    }

    @AfterEach
    void tearDown() {
        clearBrowserCookies();
    }


}
