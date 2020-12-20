import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.open;


public class AppCardDeliveryTest {


    @BeforeAll
    static void setUp() {
        Configuration.startMaximized = true;
        Configuration.browser = "firefox";
    }

    @Test
    public void shouldRegistered() {
        LocalDate now = LocalDate.now().plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


        open("http://localhost:9999/");
        element("[data-test-id='city'] .input__control").setValue("Москва");
        element("[data-test-id=date] [placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE,now.format(formatter));
        element("[data-test-id='name'] .input__control").setValue("Скитович Руслан");
        element("[data-test-id='phone'] .input__control").setValue("+79150000000");
        element(".checkbox__box").click();
        element(".button__text").click();
        element(".notification__content").waitUntil(Condition.visible, 15000).shouldHave(Condition.text(now.format(formatter)));
    }

    @Test
    public void shouldNotRegisteredByCity() {
        LocalDate now = LocalDate.now().plusDays(4);
        int day = now.getDayOfMonth();

        open("http://localhost:9999/");
        element("[data-test-id='date'] .input__box ").click();
        element(By.xpath("//td[contains(text()," + day + ")]")).click();
        element("[data-test-id='name'] .input__control").setValue("Скитович Руслан");
        element("[data-test-id='phone'] .input__control").setValue("+79150000000");
        element(".checkbox__box").click();
        element(".button__text").click();
        element("span.input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldNotRegisteredByName() {
        LocalDate now = LocalDate.now().plusDays(4);
        int day = now.getDayOfMonth();

        open("http://localhost:9999/");
        element("[data-test-id='city'] .input__control").setValue("Москва");
        element("[data-test-id='date'] .input__box ").click();
        element(By.xpath("//td[contains(text()," + day + ")]")).click();
        element("[data-test-id='phone'] .input__control").setValue("+79150000000");
        element(".checkbox__box").click();
        element(".button__text").click();
        element("[data-test-id='name'] .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldNotRegisteredByPhone() {
        LocalDate now = LocalDate.now().plusDays(4);
        int day = now.getDayOfMonth();

        open("http://localhost:9999/");
        element("[data-test-id='city'] .input__control").setValue("Москва");
        element("[data-test-id='date'] .input__box ").click();
        element(By.xpath("//td[contains(text()," + day + ")]")).click();
        element("[data-test-id='name'] .input__control").setValue("Скитович Руслан");
        element(".checkbox__box").click();
        element(".button__text").click();
        element("[data-test-id='phone'] .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldNotRegisteredByCheckBox() {
        LocalDate now = LocalDate.now().plusDays(4);
        int day = now.getDayOfMonth();

        open("http://localhost:9999/");
        element("[data-test-id='city'] .input__control").setValue("Москва");
        element("[data-test-id='date'] .input__box ").click();
        element(By.xpath("//td[contains(text()," + day + ")]")).click();
        element("[data-test-id='name'] .input__control").setValue("Скитович Руслан");
        element("[data-test-id='phone'] .input__control").setValue("+79150000000");
        element(".button__text").click();
        element(".input_invalid").should(Condition.exist);
    }

    @Test
    public void shouldRegisteredTaskWithStar() {
        LocalDate now = LocalDate.now().plusDays(30);
        int day = now.getDayOfMonth();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        open("http://localhost:9999/");
        element("[data-test-id='city'] .input__control").setValue("Мо");
        element("div.menu > div:nth-of-type(3) > .menu-item__control").click();
        element("[data-test-id='date'] .input__box ").click();
        element("[data-step='1']").click();
        element(By.xpath("//td[contains(text()," + day + ")]")).click();
        element("[data-test-id='name'] .input__control").setValue("Скитович Руслан");
        element("[data-test-id='phone'] .input__control").setValue("+79150000000");
        element(".checkbox__box").click();
        element(".button__text").click();
        element(".notification__content").waitUntil(Condition.visible, 15000).shouldHave(Condition.text(now.format(formatter)));
    }
}
