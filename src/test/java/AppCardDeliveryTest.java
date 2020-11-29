import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.open;


public class AppCardDeliveryTest {


    @BeforeAll
    static void setUp() {
        Configuration.startMaximized = true;
    }

    @Test
    public void shouldRegistered() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DATE, +3);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        open("http://localhost:9999/");
        element("[data-test-id='city'] .input__control").setValue("Москва");
        element("[data-test-id='date'] .input__box ").click();
        element(By.xpath("//td[contains(text()," + day + ")]")).click();
        element("[data-test-id='name'] .input__control").setValue("Скитович Руслан");
        element("[data-test-id='phone'] .input__control").setValue("+79150000000");
        element(".checkbox__box").click();
        element(".button__text").click();
        element(".notification__title").waitUntil(Condition.text("Успешно"), 15000);
    }

    @Test
    public void shouldNotRegisteredByCity() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DATE, +3);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

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
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DATE, +3);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

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
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DATE, +3);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

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
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DATE, +3);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

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
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DATE, +7);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        open("http://localhost:9999/");
        element("[data-test-id='city'] .input__control").setValue("Мо");
        element("div.menu > div:nth-of-type(3) > .menu-item__control").click();
        element("[data-test-id='date'] .input__box ").click();
        element(By.xpath("//td[contains(text()," + day + ")]")).click();
        element("[data-test-id='name'] .input__control").setValue("Скитович Руслан");
        element("[data-test-id='phone'] .input__control").setValue("+79150000000");
        element(".checkbox__box").click();
        element(".button__text").click();
        element(".notification__title").waitUntil(Condition.text("Успешно"), 15000);
    }
}
