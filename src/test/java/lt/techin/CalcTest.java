package lt.techin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;

// CalcTest - kalkuliatoriaus testų klasė su @Tag anotacijomis
@DisplayName("Calculator basic functionality tests")
@Tag("Calculator")
public class CalcTest extends BaseTest {

    // CalcPage objektas - mūsų kalkuliatoriaus puslapis
    private CalcPage calcPage;

    // Metodas, kuris vykdomas prieš kiekvieną testą
    @BeforeEach
    public void setUpCalcPage() {
        // Sukuriame CalcPage objektą su WebDriver
        calcPage = new CalcPage(driver);

        // Tikriname, ar kalkuliatorius pasiruošęs
        assertTrue(calcPage.isCalculatorReady(), "Kalkuliatorius nepasiruošęs testui!");

        System.out.println("CalcPage pasiruošęs testui");
    }

    // PAPRASTI TESTAI:

    @Tag("Positive")
    @Tag("BasicOperations")
    @Tag("Addition")
    @Test
    @DisplayName("Testas: Paprastas sudėjimas")
    public void testSimpleAddition() {
        System.out.println("\n=== TESTAS: Paprastas sudėjimas ===");

        // Atliekame skaičiavimą: 5 + 3
        String result = calcPage.performCalculation("5", "plus", "3");

        // Tikriname rezultatą
        assertEquals("8", result, "5 + 3 turėtų būti 8");

        System.out.println("✓ Sudėjimo testas sėkmingas!");
    }

    @Tag("Positive")
    @Tag("BasicOperations")
    @Tag("Subtraction")
    @Test
    @DisplayName("Testas: Paprastas atimimas")
    public void testSimpleSubtraction() {
        System.out.println("\n=== TESTAS: Paprastas atimimas ===");

        // Atliekame skaičiavimą: 10 - 4
        String result = calcPage.performCalculation("10", "minus", "4");

        // Tikriname rezultatą
        assertEquals("6", result, "10 - 4 turėtų būti 6");

        System.out.println("✓ Atimimo testas sėkmingas!");
    }

    @Tag("Positive")
    @Tag("BasicOperations")
    @Tag("Multiplication")
    @Test
    @DisplayName("Testas: Paprastas dauginimas")
    public void testSimpleMultiplication() {
        System.out.println("\n=== TESTAS: Paprastas dauginimas ===");

        // Atliekame skaičiavimą: 6 * 7
        String result = calcPage.performCalculation("6", "times", "7");

        // Tikriname rezultatą
        assertEquals("42", result, "6 * 7 turėtų būti 42");

        System.out.println("✓ Dauginimo testas sėkmingas!");
    }

    @Tag("Positive")
    @Tag("BasicOperations")
    @Tag("Division")
    @Test
    @DisplayName("Testas: Paprastas dalijimas")
    public void testSimpleDivision() {
        System.out.println("\n=== TESTAS: Paprastas dalijimas ===");

        // Atliekame skaičiavimą: 15 / 3
        String result = calcPage.performCalculation("15", "divide", "3");

        // Tikriname rezultatą
        assertEquals("5", result, "15 / 3 turėtų būti 5");

        System.out.println("✓ Dalijimo testas sėkmingas!");
    }

    // SPECIALŪS ATVEJAI:

    @Tag("Positive")
    @Tag("EdgeCases")
    @Tag("ZeroHandling")
    @Test
    @DisplayName("Testas: Sudėjimas su nuliu")
    public void testAdditionWithZero() {
        System.out.println("\n=== TESTAS: Sudėjimas su nuliu ===");

        String result = calcPage.performCalculation("0", "plus", "5");
        assertEquals("5", result, "0 + 5 turėtų būti 5");

        System.out.println("✓ Sudėjimo su nuliu testas sėkmingas!");
    }

    @Tag("Positive")
    @Tag("EdgeCases")
    @Tag("NegativeNumbers")
    @Test
    @DisplayName("Testas: Neigiami skaičiai")
    public void testNegativeNumbers() {
        System.out.println("\n=== TESTAS: Neigiami skaičiai ===");

        // Bandome: -3 + 2 = -1
        String result = calcPage.performCalculation("-3", "plus", "2");
        assertEquals("-1", result, "-3 + 2 turėtų būti -1");

        System.out.println("✓ Neigiamų skaičių testas sėkmingas!");
    }

    @Tag("Negative")
    @Tag("EdgeCases")
    @Tag("DivisionByZero")
    @Test
    @DisplayName("Testas: Dalijimas iš nulio")
    public void testDivisionByZero() {
        System.out.println("\n=== TESTAS: Dalijimas iš nulio ===");

        String result = calcPage.performCalculation("10", "divide", "0");

        // Šis testpages.herokuapp.com kalkuliatorius grąžina "0"
        assertEquals("0", result, "10 / 0 = 0 (šio kalkuliatoriaus logika)");

        System.out.println("✓ Dalijimo iš nulio testas sėkmingas!");
    }

    @Tag("Positive")
    @Tag("EdgeCases")
    @Tag("LargeNumbers")
    @Test
    @DisplayName("Testas: Dideli skaičiai")
    public void testLargeNumbers() {
        System.out.println("\n=== TESTAS: Dideli skaičiai ===");

        String result = calcPage.performCalculation("1000", "times", "2000");
        assertEquals("2000000", result, "1000 * 2000 turėtų būti 2000000");

        System.out.println("✓ Didelių skaičių testas sėkmingas!");
    }

    // TESTAI SU ATSKIRAIS ŽINGSNIAIS:

    @Tag("Positive")
    @Tag("StepByStep")
    @Tag("ManualSteps")
    @Test
    @DisplayName("Testas: Atskiri žingsniai")
    public void testStepByStep() {
        System.out.println("\n=== TESTAS: Po žingsnį ===");

        // Darome po vieną žingsnį ir tikriname
        calcPage.enterFirstNumber("8");
        calcPage.selectOperation("minus");
        calcPage.enterSecondNumber("3");
        calcPage.clickCalculate();

        String result = calcPage.getResult();
        assertEquals("5", result, "8 - 3 turėtų būti 5");

        System.out.println("✓ Žingsnis po žingsnio testas sėkmingas!");
    }

    @Tag("Positive")
    @Tag("MultipleOperations")
    @Tag("SequentialTests")
    @Test
    @DisplayName("Testas: Keletas skaičiavimų iš eilės")
    public void testMultipleCalculations() {
        System.out.println("\n=== TESTAS: Keli skaičiavimai ===");

        // Pirmas skaičiavimas
        String result1 = calcPage.performCalculation("2", "plus", "3");
        assertEquals("5", result1, "Pirmas skaičiavimas: 2 + 3 = 5");

        // Išvalom laukus
        calcPage.clearAllFields();

        // Antras skaičiavimas
        String result2 = calcPage.performCalculation("10", "divide", "2");
        assertEquals("5", result2, "Antras skaičiavimas: 10 / 2 = 5");

        System.out.println("✓ Kelių skaičiavimų testas sėkmingas!");
    }

    // TESTAS PUSLAPIO INFORMACIJAI:

    @Tag("Positive")
    @Tag("PageValidation")
    @Tag("UITests")
    @Test
    @DisplayName("Testas: Puslapio pavadinimas")
    public void testPageTitle() {
        System.out.println("\n=== TESTAS: Puslapio pavadinimas ===");

        String title = calcPage.getCalculatorPageTitle();
        System.out.println("Puslapio pavadinimas: " + title);

        // Tikriname, kad pavadinimas nėra tuščias
        assertNotNull(title, "Puslapio pavadinimas neturėtų būti null");
        assertFalse(title.isEmpty(), "Puslapio pavadinimas neturėtų būti tuščias");

        System.out.println("✓ Puslapio pavadinimo testas baigtas!");
    }
}