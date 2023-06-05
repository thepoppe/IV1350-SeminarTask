package startup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Main mainMethod;
    final String[] args = null;
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        mainMethod = new Main();
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        mainMethod = null;
        outContent = null;
        System.setOut(originalSysOut);
    }

    @org.junit.jupiter.api.Test
    void mainTest() throws IOException {
        mainMethod.main(args);
        String outputResult = outContent.toString();
        String firstSystemOutRow = "A purchase is starting...\n";
        String lastSystemOutRow = "The total income is:";
        assertTrue(outputResult.contains(firstSystemOutRow),"Main did not reach the purchaseSimulation method");
        assertTrue(outputResult.contains(lastSystemOutRow),"Main did not execute the whole purchaseSimulation");

    }
}