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
        assertTrue(calcPage.isCalculatorReady(), "Kalkuliatorius nepasiruoses testui!");

        System.out.println("CalcPage pasiruoses testui");
    }

    // PAPRASTI TESTAI:

    @Tag("Positive")
    @Tag("BasicOperations")
    @Tag("Addition")
    @Test
    @DisplayName("Testas: Paprastas sudejimas")
    public void testSimpleAddition() {
        System.out.println("\n=== TESTAS: Paprastas sudejimas ===");

        // Atliekame skaičiavimą: 5 + 3
        String result = calcPage.performCalculation("5", "plus", "3");

        // Tikriname rezultatą
        assertEquals("8", result, "5 + 3 turetu buti 8");

        System.out.println("✓ Sudejimo testas sekmingas!");
    }

    @Tag("Positive")
    @Tag("BasicOperations")
    @Tag("Subtraction")
    @Test
    @DisplayName("Testas: Paprastas atemimas")
    public void testSimpleSubtraction() {
        System.out.println("\n=== TESTAS: Paprastas atemimas ===");

        // Atliekame skaičiavimą: 10 - 4
        String result = calcPage.performCalculation("10", "minus", "4");

        // Tikriname rezultatą
        assertEquals("6", result, "10 - 4 turėtų būti 6");

        System.out.println("✓ Atemimo testas sekmingas!");
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
        assertEquals("42", result, "6 * 7 turetu buti 42");

        System.out.println("✓ Dauginimo testas sekmingas!");
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
        assertEquals("5", result, "15 / 3 turetu buti 5");

        System.out.println("✓ Dalijimo testas sekmingas!");
    }

    // SPECIALŪS ATVEJAI:

    @Tag("Positive")
    @Tag("EdgeCases")
    @Tag("ZeroHandling")
    @Test
    @DisplayName("Testas: Sudejimas su nuliu")
    public void testAdditionWithZero() {
        System.out.println("\n=== TESTAS: Sudejimas su nuliu ===");

        String result = calcPage.performCalculation("0", "plus", "5");
        assertEquals("5", result, "0 + 5 turetu buti 5");

        System.out.println("✓ Sudejimo su nuliu testas sekmingas!");
    }

    @Tag("Positive")
    @Tag("EdgeCases")
    @Tag("NegativeNumbers")
    @Test
    @DisplayName("Testas: Neigiami skaiciai")
    public void testNegativeNumbers() {
        System.out.println("\n=== TESTAS: Neigiami skaiciai ===");

        // Bandome: -3 + 2 = -1
        String result = calcPage.performCalculation("-3", "plus", "2");
        assertEquals("-1", result, "-3 + 2 turetu buti -1");

        System.out.println("✓ Neigiamu skaičiu testas sekmingas!");
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

        System.out.println("✓ Dalijimo iš nulio testas sekmingas!");
    }

    @Tag("Positive")
    @Tag("EdgeCases")
    @Tag("LargeNumbers")
    @Test
    @DisplayName("Testas: Dideli skaiciai")
    public void testLargeNumbers() {
        System.out.println("\n=== TESTAS: Dideli skaiciai ===");

        String result = calcPage.performCalculation("1000", "times", "2000");
        assertEquals("2000000", result, "1000 * 2000 turetu buti 2000000");

        System.out.println("✓ Dideliu skaičiu testas sėkmingas!");
    }

    // TESTAI SU ATSKIRAIS ŽINGSNIAIS:

    @Tag("Positive")
    @Tag("StepByStep")
    @Tag("ManualSteps")
    @Test
    @DisplayName("Testas: Atskiri zingsniai")
    public void testStepByStep() {
        System.out.println("\n=== TESTAS: Po zingsni ===");

        // Darome po vieną žingsnį ir tikriname
        calcPage.enterFirstNumber("8");
        calcPage.selectOperation("minus");
        calcPage.enterSecondNumber("3");
        calcPage.clickCalculate();

        String result = calcPage.getResult();
        assertEquals("5", result, "8 - 3 turetu buti 5");

        System.out.println("✓ Zingsnis po zingsnio testas sekmingas!");
    }

    @Tag("Positive")
    @Tag("MultipleOperations")
    @Tag("SequentialTests")
    @Test
    @DisplayName("Testas: Keletas skaiciavimu iš eiles")
    public void testMultipleCalculations() {
        System.out.println("\n=== TESTAS: Keli skaičiavimai ===");

        // Pirmas skaičiavimas
        String result1 = calcPage.performCalculation("2", "plus", "3");
        assertEquals("5", result1, "Pirmas skaiciavimas: 2 + 3 = 5");

        // Išvalom laukus
        calcPage.clearAllFields();

        // Antras skaičiavimas
        String result2 = calcPage.performCalculation("10", "divide", "2");
        assertEquals("5", result2, "Antras skaiciavimas: 10 / 2 = 5");

        System.out.println("✓ Kelių skaiciavimu testas sekmingas!");
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
        assertNotNull(title, "Puslapio pavadinimas neturetu buti null");
        assertFalse(title.isEmpty(), "Puslapio pavadinimas neturėtų buti tuscias");

        System.out.println("✓ Puslapio pavadinimo testas baigtas!");
    }
}