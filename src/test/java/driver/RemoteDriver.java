package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class RemoteDriver {

    protected WebDriver driver;
    protected Properties props;
    protected Connection connection;

    public void link() throws MalformedURLException {
        // Загрузка настроек из файла свойств
        props = new Properties();

        // Определяем, где запускается тест (локально или удаленно)
        boolean isRemote = Boolean.parseBoolean(System.getProperty("remote", "true"));

        if (isRemote) {
            // Удаленный запуск через Selenoid
            DesiredCapabilities capabilities = new DesiredCapabilities();
            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("browserName", "chrome"); // Указываем браузер
            selenoidOptions.put("browserVersion", "109.0"); // Указываем версию браузера
            selenoidOptions.put("enableVNC", true); // Включаем VNC
            selenoidOptions.put("enableVideo", false); // Отключаем запись видео
            capabilities.setCapability("selenoid:options", selenoidOptions);

            // Подключение к Selenoid
            driver = new RemoteWebDriver(
                    URI.create(props.getProperty("selenoid", "http://jenkins.applineselenoid.fvds.ru:4444/wd/hub")).toURL(),
                    capabilities
            );
        } else {
            // Локальный запуск
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void close() {
        if (driver != null) {
            driver.quit();
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
