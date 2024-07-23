import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LimeDependent extends LimeTestBase {

    @BeforeAll
    static void setUp() {

        Configuration.baseUrl = "https://lime-shop.com";

        // Открываем страницу
        open("/ru_ru");

        // Ждём три секунды.
        sleep(3000);
        $("button[data-ok]").click();

        $("a[href='/ru_ru#lk'] div").click();
        $(".btn-outline").click();

        $("[tabindex='1']").setValue("boykovabkru@mailnesia.com");
        $("[tabindex='2']").setValue("boykovabkru03");
        $("button[type='submit']").click();
    }

    @AfterAll
    static void tearDown() {
        // Убираем куки, чтобы страница снова была "как новенькая".
        // Потом исправим.
        clearBrowserCookies();

    }

    @Test
    void loginTest01() {

        // Проверяем что мы можем залогиниться на сайт с вот таким логином и паролем.
        $x("//button[contains(text(),'Изменить пароль')]").shouldBe(Condition.visible);

    }

    @Test
    void loginTest02() {

        $("form h2").shouldHave(Condition.text("Выберите интересующие вас разделы:"));

    }
}