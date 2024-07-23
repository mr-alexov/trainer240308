import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Lime00Test extends LimeTestBase {

    @BeforeEach
    void setUp() {

        Configuration.baseUrl = "https://lime-shop.com";

        open("/favicon.ico");
        WebDriver currentDriver = WebDriverRunner.getWebDriver();
        Cookie confirmedCookieData = new Cookie("l-accept-cookies", "true");
        currentDriver.manage().addCookie(confirmedCookieData);

        // Открываем страницу
        open("/ru_ru");

        // Код когда мы ждём кнопки согласия на куки.
        // sleep(3000);
        // $("button[data-ok]").click();
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

        //$("#AppNavbar .hamburger-menu").click();

        $$("#AppNavbar .hamburger-menu").get(0).click();

        $(".mainmenu__kinds").$(byText("Мужчины")).click();

        $("[href='/ru_ru/catalog/men_blazers'] span").shouldHave(Condition.text("БЛЕЙЗЕРЫ"));

    }

    @Test
    @Order(value = 1)
    void loginTest01() {

        // Проверяем что мы можем залогиниться на сайт с вот таким логином и паролем.

        $("a[href='/ru_ru#lk'] div").click();
        $(".btn-outline").click();

        $("[tabindex='1']").setValue("boykovabkru@mailnesia.com");
        $("[tabindex='2']").setValue("boykovabkru03");
        $("button[type='submit']").click();

        $x("//button[contains(text(),'Изменить пароль')]").shouldBe(Condition.visible);

    }

    @Order(value = 2)
    void loginTest02() {

        $("form h2").has(Condition.text("Выберите интересующие вас разделы:"));

    }

    @Test
    void elementsCollectionDemo0() {
        $(".SearchBox__button").click();
        $(".SearchBox__input").setValue("платье").pressEnter();

        ElementsCollection filteredCollection =
        $$(".CatalogProduct__title").filter(Condition.text("льн"));

        filteredCollection.get(0).click();
    }

    @Test
    void elementsCollectionDemo1() {
        $(".SearchBox__button").click();
        $(".SearchBox__input").setValue("брюки").pressEnter();

        $$(".CatalogProduct__title").shouldHave(CollectionCondition.sizeGreaterThan(10));

        ElementsCollection filteredCollection =
                $$(".CatalogProduct__title").filter(Condition.text("льн"));

        filteredCollection
                .asFixedIterable()
                .stream()
                .forEach(e -> System.out.println(e.getText()));

    }

    @Test
    void elementsCollectionDemo2() {
        $(".SearchBox__button").click();
        $(".SearchBox__input").setValue("брюки").pressEnter();

        $$(".CatalogProduct__title").shouldHave(CollectionCondition.sizeGreaterThan(10));

        ElementsCollection filteredCollection =
                $$(".CatalogProduct__content-col").filter(Condition.text("3599"));

        filteredCollection
                .asFixedIterable()
                .stream()
                .forEach(e -> System.out.println(e.getText()));

    }

    @AfterEach
    void tearDown() {
        // Убираем куки, чтобы страница снова была "как новенькая".
        // Потом исправим.
        clearBrowserCookies();

    }
}