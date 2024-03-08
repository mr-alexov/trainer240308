import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SimpleSnippegs {

    {

        Configuration.holdBrowserOpen = true; // Инструкция оставить браузер открытым

        Configuration.pageLoadStrategy = "eager"; // Энергичная стратегия загрузки "не ждать отстающих"

        open("page.com"); //Открыть страницу page.com

        $("selector"); //Найти элемент по CSS селектору

        $x("XPath"); //Найти элемент по XPath

        $(byText("Text")); //Найти элемент по тексту

        $("selector").click(); //Кликнуть по найденному элементу

        $("selector").setValue("Value"); //Установить значение найденному элементу

        $("selector").sendKeys("Value"); //Ввести значение найденному элементу (может работать если setValue не работает)

        $("selector").selectOption("Value"); //Выбрать значение из элемента типа select

        $("selector").pressEnter(); //Отправить элементу нажатие клавиши Enter

        $("selector").uploadFromClasspath("test_passport.png"); //Загрузить файл в инпут соответствующего типа

        executeJavaScript("$('selector').remove();"); //Выполнить какой-то джаваскрипт, например удаление элемента

        $("selector").shouldHave(Condition.text("Text")); //Проверить что в элементе есть соответствующий текст

    }

}
