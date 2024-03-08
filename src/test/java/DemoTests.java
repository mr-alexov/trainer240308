import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoTests {

    @Test
    void theInternetHerokuLoginTest() {

        open("https://the-internet.herokuapp.com/login");

        $("#username").shouldBe(Condition.interactable);

        $("#username").setValue("tomsmith");
        $("#password").setValue("SuperSecretPassword!");
        $("button.radius").click();

        $("div.flash").shouldHave(text("You logged into a secure area!"));

    }

}
