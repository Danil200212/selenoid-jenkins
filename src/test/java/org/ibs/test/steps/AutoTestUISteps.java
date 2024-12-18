package org.ibs.test.steps;

import driver.RemoteDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.И;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.Properties;

public class AutoTestUISteps extends RemoteDriver {
    private Properties props;


    @Before
    public void link() throws MalformedURLException {
        super.link();
    }

//    @After
//    public void close() {
//        super.close();
//    }

    @И("открыт сайт")
    public void открыть_сайт() {
        driver.get("https://qualit.applineselenoid.fvds.ru/");
    }

    @И("перейти во вкладку \"Песочница\" и выбрать раздел \"Товары\"")
    public void перейти_во_вкладку () {

        WebElement btnSandbox = driver.findElement(By.xpath("//a[@class='nav-link dropdown-toggle']"));
        btnSandbox.click();

        WebElement btnGoods = driver.findElement(By.xpath("//a[@href='/food']"));
        btnGoods.click();
    }

    @И("нажать на кнопку \"Добавить\"")
    public void нажать_кнопку_добавить () {
        WebElement btnAdd = driver.findElement(By.xpath("//button[@data-toggle='modal']"));
        btnAdd.click();
    }

    @И("ввести название {string} в поле ввода имени товара")
    public void ввести_навзвание_товара (String name) {
        WebElement inputName = driver.findElement(By.xpath("//input[@id='name']"));
        inputName.sendKeys(name);
    }

    @И("выбрать тип товара \"Фрукт\"")
    public void выбрать_тип_товара () {
        WebElement typeDropdown = driver.findElement(By.xpath("//select[@id='type']/..//option[@value='FRUIT']"));
        typeDropdown.click();
    }

    @И("выбрать тип товара \"Овощ\"")
    public void выбрать_тип_товара2 () {
        WebElement typeDropdown = driver.findElement(By.xpath("//select[@id='type']/..//option[@value='VEGETABLE']"));
        typeDropdown.click();
    }

    @И("поставить галочку \"Экзотический\" если {string} равно \"Да\"")
    public void нажать_на_чек_бокс(String condition) {
            WebElement exoticCheckbox = driver.findElement(By.xpath("//input[@id='exotic']"));
            exoticCheckbox.click();
            Assertions.assertTrue(exoticCheckbox.isSelected());
    }

    @И("нажать на кнопку сохранить")
    public void сохранить_изменения () {
        WebElement saveButton = driver.findElement(By.xpath("//button[@id='save']"));
        saveButton.click();
    }

    @И("проверить что добавленный товар отображается в таблице под номером {int}")
    public void проверить_товар (int rowNumber) {
        WebElement rowTitle = driver.findElement(By.xpath(String.format("//tr[%d]/th[@scope='row']", rowNumber)));
        Assertions.assertEquals(Integer.toString(rowNumber), rowTitle.getText(), "Товар не был добавлен.");
    }

    @И("перейти во вкладку \"Песочница\" и выбираю раздел \"Сброс данных\"")
    public void сброс_данных () {
        WebElement btnSandbox = driver.findElement(By.xpath("//a[@class='nav-link dropdown-toggle']"));
        btnSandbox.click();

        WebElement btnReset = driver.findElement(By.xpath("//a[@id='reset']"));
        btnReset.click();
    }

    @И("закрыть страницу")
    public void закрыть_страницу () {
        driver.close();
        driver.quit();
    }

}
