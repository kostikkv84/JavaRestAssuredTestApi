package TestsUI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class Tests {
    private final String base_url = "https://yandex.ru";

    @Before
    public void SetUp(){
        WebDriverManager.chromedriver().setup();

        Configuration.baseUrl = base_url;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1700x900";
        Configuration.driverManagerEnabled = true;
        Configuration.headless = false;

    }
    @After
    public void tearDown(){
        Selenide.closeWebDriver();
    }

    @Test
    public void check(){
        int a = 5;
        Assert.assertEquals(5,a);
    }


}
