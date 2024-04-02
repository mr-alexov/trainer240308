import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Lime00Test extends LimeTestBase {

    @BeforeEach
    void setUp() {
        // Открываем страницу
        open("https://lime-shop.com/ru_ru");

        // Ждём три секунды.
        sleep(3000);
        $("button[data-ok]").click();
    }

    @Test
    void checkChatOpens() {
        // Жмем на кнопку чата и проверяем что окошко чата открылось
        $(".l-jivo-label").click();
        $("textarea").shouldBe(Condition.visible)
                .shouldHave(Condition.attribute("placeholder", "Ваше сообщение*"));
    }

    @Test
    void searchTest01() {
        // Кликаем кнопку поиска и вводим запрос, потом проверяем что по запросу нашлись соответствующие товары.
        $(".SearchBox__button").click();
        $(".SearchBox__input").setValue("платье").pressEnter();
        $(".CatalogProduct__title").shouldHave(Condition.text("Платье"));

    }

    @Test
    void searchTest02() {
        // Кликаем кнопку поиска и вводим запрос по артиклю, проверяем что нашелся товар с этим артиклем.
        $(".SearchBox__button").click();
        $(".SearchBox__input").setValue("2854-529").pressEnter();
        $(".CatalogProduct__title a").click();
        $(".product__article").shouldHave(Condition.text("Арт. 2854-529"));

    }

    @Test
    void searchAndAddToCart() {
        // Кликаем кнопку поиска, ищем товар
        // Добавляем товар в корзину из поиска, проверяем что добавился.
        $(".SearchBox__button").click();
        $(".SearchBox__input").setValue("брюки").pressEnter();
        $(".CatalogProduct__title a").click();
        $(".btn-cart").click();
        open("https://lime-shop.com/ru_ru/cart");
        $(".CustomerCart__main").shouldHave(Condition.text("Брюки"));

    }


    @Test
    void menuTest01() {

        // Проверяем что в меню есть пункт Новинки, и переход по нему ведёт на нужную страницу.

        $("#AppNavbar .hamburger-menu").click();
        $("[href='/ru_ru/catalog/new'] span").click();
        $("h1").shouldHave(Condition.text("НОВИНКИ"));
        String currentUrl = getWebDriver().getCurrentUrl();
        Assertions.assertEquals("https://lime-shop.com/ru_ru/catalog/new", currentUrl);

    }

    @Test
    void menuTest02() {

        // Проверяем что в меню есть пункт Мужчины, и при переходе на странице есть мужской товар.

        $("#AppNavbar .hamburger-menu").click();

        $(".mainmenu__kinds").$(byText("Мужчины")).click();

        $("[href='/ru_ru/catalog/men_blazers'] span").shouldHave(Condition.text("БЛЕЙЗЕРЫ"));

    }

    @Test
    void loginTest01() {

        // Проверяем что мы можем залогиниться на сайт с вот таким логином и паролем.

        $("a[href='/ru_ru#lk'] div").click();
        $(".btn-outline").click();

        $("[tabindex='1']").setValue("boykovabkru@mailnesia.com");
        $("[tabindex='2']").setValue("boykovabkru03");
        $("button[type='submit']").click();

        $x("//button[contains(text(),'Изменить пароль')]").shouldBe(Condition.visible);

    }

    @AfterEach
    void tearDown() {
        // Убираем куки, чтобы страница снова была "как новенькая".
        // Потом исправим.
        clearBrowserCookies();

    }
}