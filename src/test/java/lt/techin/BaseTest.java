package lt.techin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

// BaseTest - tėvinė klasė visiems testams
public class BaseTest {

    // WebDriver objektas
    protected WebDriver driver;

    // URL, kurį testuojame
    protected String BASE_URL = "https://testpages.herokuapp.com/styled/calculator";

    // Metodas prieš kiekvieną testą
    @BeforeEach
    public void setUp() {
        System.out.println("=== PRADEDAME TESTĄ ===");

        try {
            // Chrome nustatymai
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--remote-allow-origins=*");
            // options.addArguments("--headless"); // Užkomentuok, jei nori matyti naršyklę

            // Sukuriame WebDriver (reikia turėti ChromeDriver PATH arba projekte)
            driver = new ChromeDriver(options);

            // Nustatome laukimo laikus
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

            // Maksimizuojame langą
            driver.manage().window().maximize();

            // Einame į kalkuliatoriaus puslapį
            System.out.println("Einame į: " + BASE_URL);
            driver.get(BASE_URL);

            System.out.println("✓ WebDriver sėkmingai nustatytas");

        } catch (Exception e) {
            System.err.println("❌ Klaida nustatant WebDriver: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // Metodas po kiekvieno testo
    @AfterEach
    public void tearDown() {
        System.out.println("=== BAIGIAME TESTĄ ===");

        if (driver != null) {
            try {
                driver.quit();
                System.out.println("✓ WebDriver uždarytas");
            } catch (Exception e) {
                System.err.println("⚠ Klaida uždarant WebDriver: " + e.getMessage());
            }
        }
    }

    // Papildomas metodas laukimui
    protected void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println("Laukimo klaida: " + e.getMessage());
        }
    }
}
