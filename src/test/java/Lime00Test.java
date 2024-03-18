import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Lime00Test {

    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.pollingInterval = 1000;

        open("https://lime-shop.com/ru_ru");
        sleep(3000);
        $("button[data-ok]").click();
    }

    @Test
    void firstTest() {

        $(".l-jivo-label").click();
        $("textarea").shouldBe(Condition.visible)
                .shouldHave(Condition.attribute("placeholder", "Ваше сообщение*"));

    }

    @Test
    void searchTest01() {

        $(".SearchBox__button").click();

        $(".SearchBox__input").setValue("платье").pressEnter();

        $(".CatalogProduct__title").shouldHave(Condition.text("Платье"));

    }

    @Test
    void searchTest02() {

        $(".SearchBox__button").click();

        $(".SearchBox__input").setValue("2854-529").pressEnter();

        $(".CatalogProduct__title a").click();

        $(".product__article").shouldHave(Condition.text("Арт. 2854-529"));

    }

    @Disabled
    @Test
    void menuTest01() {

        $("#AppNavbar .hamburger-menu").click();

        $("[href='/ru_ru/catalog/new'] span").click();

        $("h1").shouldHave(Condition.text("НОВИНКИ"));

        String currentUrl = getWebDriver().getCurrentUrl();

        Assertions.assertEquals("https://lime-shop.com/ru_ru/catalog/new", currentUrl);

    }

    @Disabled
    @Test
    void menuTest02() {

        $("#AppNavbar .hamburger-menu").click();

        $(".mainmenu__kinds").$(byText("Мужчины")).click();

        $("[href='/ru_ru/catalog/men_blazers'] span").shouldHave(Condition.text("БЛЕЙЗЕРЫ"));

    }

    @AfterEach
    void tearDown() {
        clearBrowserCookies();
    }
}