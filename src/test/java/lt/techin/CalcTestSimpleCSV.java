package lt.techin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;

// Paprastas CSV testavimas - taip kaip mokė mokykloje!
@DisplayName("Calculator tests with CSV data")
@Tag("Calculator")
public class CalcTestSimpleCSV extends BaseTest {

    private CalcPage calcPage;

    @BeforeEach
    public void setUpCalcPage() {
        calcPage = new CalcPage(driver);
        assertTrue(calcPage.isCalculatorReady(), "Kalkuliatorius nepasirenges!");
    }

    // METODAS 1: CSV duomenys tiesiai kode su @CsvSource
    @Tag("Positive")
    @Tag("CsvSource")
    @ParameterizedTest(name = "Testas {index}: {0} {1} {2} = {3}")
    @CsvSource({
            "5, plus, 3, 8",
            "10, minus, 4, 6",
            "6, times, 7, 42",
            "15, divide, 3, 5",
            "0, plus, 5, 5",
            "-3, plus, 2, -1",
            "100, divide, 1, 100",
            "7, minus, 7, 0"
    })
    @DisplayName("Kalkuliatoriaus testai su @CsvSource")
    public void testCalculatorWithCsvSource(String number1, String operation, String number2, String expected) {

        System.out.println("Testuojame: " + number1 + " " + operation + " " + number2 + " = " + expected);

        // Atliekame skaičiavimą
        String actualResult = calcPage.performCalculation(number1, operation, number2);

        // Tikriname rezultatą
        assertEquals(expected, actualResult,
                number1 + " " + operation + " " + number2 + " turetu buti " + expected);

        System.out.println("✓ Sekmingai: " + number1 + " " + operation + " " + number2 + " = " + actualResult);
    }

    // METODAS 2: CSV duomenys iš failo su @CsvFileSource
    @Tag("Positive")
    @Tag("CsvFile")
    @ParameterizedTest(name = "CSV failas {index}: {0} {1} {2} = {3}")
    @CsvFileSource(
            resources = "/calculator_test_data.csv",
            numLinesToSkip = 1
    )
    @DisplayName("Kalkuliatoriaus testai su CSV failu")
    public void testCalculatorWithCsvFile(String number1, String operation, String number2, String expected) {

        System.out.println("CSV testas: " + number1 + " " + operation + " " + number2 + " = " + expected);

        // Išvalom laukus
        calcPage.clearAllFields();

        // Atliekame skaičiavimą
        String actualResult = calcPage.performCalculation(number1, operation, number2);

        // Tikriname rezultatą
        assertEquals(expected, actualResult,
                "CSV testas nepavyko: " + number1 + " " + operation + " " + number2 +
                        " turėetu buti " + expected + ", bet gavome " + actualResult);

        System.out.println("✓ CSV testas OK: " + actualResult);
    }

    // METODAS 3: Tik sudėjimo testai
    @Tag("Positive")
    @Tag("Addition")
    @ParameterizedTest(name = "Sudejimas: {0} + {1} = {2}")
    @CsvSource({
            "1, 1, 2",
            "5, 3, 8",
            "10, 15, 25",
            "0, 0, 0",
            "-5, 3, -2"
    })
    @DisplayName("Tik sudejimo testai")
    public void testOnlyAddition(String num1, String num2, String expected) {

        System.out.println("Sudejimo testas: " + num1 + " + " + num2);

        String result = calcPage.performCalculation(num1, "plus", num2);
        assertEquals(expected, result);

        System.out.println("✓ " + num1 + " + " + num2 + " = " + result);
    }

    // METODAS 4: Su tekstiniais pavadinimais
    @Tag("Positive")
    @Tag("NamedTests")
    @ParameterizedTest(name = "{4}: {0} {1} {2} = {3}")
    @CsvSource({
            "5, plus, 3, 8, 'Paprastas sudejimas'",
            "10, minus, 4, 6, 'Paprastas atimimas'",
            "6, times, 7, 42, 'Paprastas dauginimas'",
            "15, divide, 3, 5, 'Paprastas dalijimas'",
            "10, divide, 0, 0, 'Dalijimas iš nulio'"
    })
    @DisplayName("Testai su pavadinimais")
    public void testWithNames(String num1, String operation, String num2, String expected, String testName) {

        System.out.println("\n=== " + testName + " ===");
        System.out.println("Skaiciuojame: " + num1 + " " + operation + " " + num2);

        String result = calcPage.performCalculation(num1, operation, num2);

        assertEquals(expected, result, testName + " nepavyko");
        System.out.println("✓ " + testName + " sekmingas: " + result);
    }
}