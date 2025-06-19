package lt.techin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

// CalcPage - kalkuliatoriaus puslapis su stale element fix
public class CalcPage extends BasePage {

    // WebDriverWait objektas laukimui
    private WebDriverWait wait;

    // Element locators - ieškome elementų kiekvieną kartą iš naujo
    private By firstInputLocator = By.id("number1");
    private By functionDropdownLocator = By.id("function");
    private By secondInputLocator = By.id("number2");
    private By calculateButtonLocator = By.id("calculate");
    private By answerFieldLocator = By.id("answer");

    // Konstruktorius
    public CalcPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("CalcPage objektas sukurtas");
    }

    // Metodai, kurie kiekvieną kartą ieško elementų iš naujo

    private WebElement getFirstInput() {
        return wait.until(ExpectedConditions.elementToBeClickable(firstInputLocator));
    }

    private WebElement getFunctionDropdown() {
        return wait.until(ExpectedConditions.elementToBeClickable(functionDropdownLocator));
    }

    private WebElement getSecondInput() {
        return wait.until(ExpectedConditions.elementToBeClickable(secondInputLocator));
    }

    private WebElement getCalculateButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(calculateButtonLocator));
    }

    private WebElement getAnswerField() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(answerFieldLocator));
    }

    // Metodas įvesti pirmą skaičių
    public void enterFirstNumber(String number) {
        System.out.println("Ivedu pirma skaiciu: " + number);
        WebElement firstInput = getFirstInput();
        firstInput.clear();
        firstInput.sendKeys(number);
    }

    // Metodas pasirinkti operaciją
    public void selectOperation(String operation) {
        System.out.println("Renkuosi operacija: " + operation);
        WebElement functionDropdown = getFunctionDropdown();
        Select operationSelect = new Select(functionDropdown);
        operationSelect.selectByValue(operation);
    }

    // Metodas įvesti antrą skaičių
    public void enterSecondNumber(String number) {
        System.out.println("Ivedu antra skaiciu: " + number);
        WebElement secondInput = getSecondInput();
        secondInput.clear();
        secondInput.sendKeys(number);
    }

    // Metodas paspausti skaičiavimo mygtuką
    public void clickCalculate() {
        System.out.println("Spaudziu Calculate mygtuka");
        WebElement calculateButton = getCalculateButton();
        calculateButton.click();

        // Palaukiame, kad atsakymas pasirodytų
        wait.until(ExpectedConditions.presenceOfElementLocated(answerFieldLocator));
    }

    // Metodas gauti atsakymą
    public String getResult() {
        WebElement answerField = getAnswerField();
        String result = answerField.getText();
        System.out.println("Gautas rezultatas: " + result);
        return result;
    }

    // Metodas išvalyti visus laukus
    public void clearAllFields() {
        System.out.println("Isvalau visus laukus");

        try {
            WebElement firstInput = getFirstInput();
            firstInput.clear();

            WebElement secondInput = getSecondInput();
            secondInput.clear();
        } catch (Exception e) {
            System.out.println("Klaida valant laukus: " + e.getMessage());
            // Atnaujinki puslapį, jei elementai neprieinami
            driver.navigate().refresh();
        }
    }

    // Metodas atlikti pilną skaičiavimą vienu kartu
    public String performCalculation(String firstNumber, String operation, String secondNumber) {
        System.out.println("Atliekamas skaiciavimas: " + firstNumber + " " + operation + " " + secondNumber);

        try {
            enterFirstNumber(firstNumber);
            selectOperation(operation);
            enterSecondNumber(secondNumber);
            clickCalculate();

            return getResult();

        } catch (Exception e) {
            System.err.println("Klaida skaiciuojant: " + e.getMessage());
            // Bandyk atnaujinti puslapį ir pakartoti
            driver.navigate().refresh();

            // Pakartok po refresh
            enterFirstNumber(firstNumber);
            selectOperation(operation);
            enterSecondNumber(secondNumber);
            clickCalculate();

            return getResult();
        }
    }

    // Metodas patikrinti, ar kalkuliatorius veikia
    public boolean isCalculatorReady() {
        try {
            // Tikriname, ar visi elementai egzistuoja
            getFirstInput();
            getFunctionDropdown();
            getSecondInput();
            getCalculateButton();
            getAnswerField();

            System.out.println("✓ Kalkuliatorius pasiruoses");
            return true;

        } catch (Exception e) {
            System.out.println("❌ Kalkuliatorius nepasiruoses: " + e.getMessage());
            return false;
        }
    }

    // Metodas gauti puslapio pavadinimą
    public String getCalculatorPageTitle() {
        return getPageTitle();
    }
}