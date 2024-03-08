import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Lime00Test {

    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;

        open("https://lime-shop.com/ru_ru");
        sleep(1500);
        $("button[data-ok]").click();
    }

    @Test
    void firstTest() {

        $(".l-jivo-label").click();
        $("textarea").shouldBe(Condition.visible)
                .shouldHave(Condition.attribute("placeholder", "Введите сообщение"));

    }

    @Test
    void searchTest01() {

        $(".SearchBox__button").click();

        $(".SearchBox__input").setValue("платье").pressEnter();

        $(".CatalogProduct__title").shouldHave(Condition.text("Платье"));

    }

    @Test
    void menuTest01() {

        $("#AppNavbar .hamburger-menu").click();

        $("[href='/ru_ru/catalog/new'] span").click();

        $("h1").shouldHave(Condition.text("НОВИНКИ"));

        String currentUrl = getWebDriver().getCurrentUrl();

        Assertions.assertEquals("https://lime-shop.com/ru_ru/catalog/new", currentUrl);

    }

    @Test
    void menuTest02() {

        $("#AppNavbar .hamburger-menu").click();

        $(".mainmenu__kinds").$(byText("Мужчины")).click();

        $("[href='/ru_ru/catalog/men_blazers'] span").shouldHave(Condition.text("БЛЕЙЗЕРЫ"));

    }

}