package lt.techin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

// Čia dedame bendrus metodus, kuriuos gali naudoti visi puslapiai
public class BasePage {

    // WebDriver objektas - tai mūsų "naršyklės valdytojas"
    protected WebDriver driver;

    // Konstruktorius - metodas, kuris iškviečiamas kuriant objektą
    public BasePage(WebDriver driver) {
        this.driver = driver; // Išsaugome driver, kad galėtume jį naudoti

        // PageFactory.initElements inicializuoja visus @FindBy elementus
        // Tai reiškia, kad jis suras visus elementus puslapyje
        PageFactory.initElements(driver, this);
    }

    // Metodas gauti puslapio pavadinimą
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Metodas gauti dabartinį URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Metodas eiti į konkretų URL
    public void navigateTo(String url) {
        driver.get(url);
    }

    // Metodas atnaujinti puslapį
    public void refreshPage() {
        driver.navigate().refresh();
    }
}