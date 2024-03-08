import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

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
}
