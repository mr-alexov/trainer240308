import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class LimeTestBase {

    @BeforeAll
    static void beforeAll() {
        // Настройка оставлять браузер открытым или нет.
        Configuration.holdBrowserOpen = true;

        // Настройка размера экрана
        Configuration.browserSize = "1920x1080";

        // Настройка таймаута -- сколько мы ждём элементов.
        Configuration.timeout = 10000;

        // Настройка работы управления браузером -- пауза между запросами за элементами.
        Configuration.pollingInterval = 1000;
    }
}
