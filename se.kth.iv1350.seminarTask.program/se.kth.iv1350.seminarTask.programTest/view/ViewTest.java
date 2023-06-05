package view;

import controller.Controller;
import integration.ExternalHandlerCreator;
import observer.ObservedTotalRevenue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {
    private View view;
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() throws IOException {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ExternalHandlerCreator extCreator = new ExternalHandlerCreator();
        ObservedTotalRevenue totalRevenuePublisher = new ObservedTotalRevenue();
        Controller controller = new Controller(extCreator, totalRevenuePublisher);
        view = new View(controller);
    }

    @AfterEach
    void tearDown() {
        view= null;
        outContent = null;
        System.setOut(originalSysOut);
    }

    @Test
    void verifyOngoingPurchaseOutput() {

        view.purchaseSimulation(1,true);
        String outputResult = outContent.toString();
        String purchaseInformationToUser =
                "Information about the purchase:\n" +
                "Banana, 5.0, 3pc\n" +
                "Apple, 9.0, 1pc\n" +
                "Running total: 26,88\n" +
                "Total VAT: 2,88";
        String newPriceAfterDiscount =
                "Information about the purchase:\n" +
                        "Banana, 3.0, 3pc\n" +
                        "Apple, 9.0, 1pc\n" +
                        "Running total: 21,24\n" +
                        "Total VAT: 2,16";
        String changeInformationToUSer = "Change to give to customer: 178,76";
        String totalIncomeByObserver = "The total income is: 21,24";

        assertTrue(outputResult.contains(purchaseInformationToUser), "When entering items, the system does not print the information about the item");
        assertTrue(outputResult.contains(newPriceAfterDiscount), "After an eligible customer has asked for discount, the updated purchase information is not displayed");
        assertTrue(outputResult.contains(changeInformationToUSer), "After paying the system does not print information about what amount of change to give to customer");
        assertTrue(outputResult.contains(totalIncomeByObserver),"The observer in the view did not print the total income");

    }


    @Test
    void verifyExceptionOutput(){
        view.purchaseSimulation(1, false);
        String outputResult = outContent.toString();
        String databaseException = "An error occurred with the request.\nContact support if problem remains";
        String multipleSaleException = "Something went wrong with the request, contact support.";
        String wrongIdentifierException = "The identifier \"9999\" is not valid";
        String quantityException = "The requested quantity: \"100000\" of item Apple is not available";
        String paymentException = "Given payment \"0,00\" is less than requested amount \"26,88\"";
        String changeException = "Not enough change in the register";

        assertTrue(outputResult.contains(databaseException),"The database exception did not print to the user");
        assertTrue(outputResult.contains(multipleSaleException),"The multipleSale exception did not print to the user");
        assertTrue(outputResult.contains(wrongIdentifierException),"The invalid identifier exception did not print to the user");
        assertTrue(outputResult.contains(quantityException),"The invalid quantity exception did not print to the user");
        assertTrue(outputResult.contains(paymentException),"The payment exception from paying less than price did not print to the user");
        assertTrue(outputResult.contains(changeException),"The change exception from paying an amount resulting in greater change than"+
                "available did not print to the user");


    }

    @Test
    void verifyReceiptOutput(){
        view.purchaseSimulation(1,false);
        String outputResult = outContent.toString();
        String firstLine = "Customer id:\t1";
        String secondLine = "Time of purchase:";
        String thirdLine = java.time.LocalDate.now().toString();
        String fourthLine = "Banana\t5.0kr\t3 pcs";
        String fifthLine = "Apple\t9.0kr\t1 pcs";
        String sixthLine = "Total Price: 26,88";
        String seventhLine = "Total VAT: 2,88";
        String eightLine = "Change: 173,12";

        assertTrue(outputResult.contains(firstLine), "Wrong customer Id was posted");
        assertTrue(outputResult.contains(secondLine),"System.out did not print the string \"Time of purchase\"");
        assertTrue(outputResult.contains(thirdLine), "Wrong date printed to sys.out");
        assertTrue(outputResult.contains(fourthLine), "First item was not printed");
        assertTrue(outputResult.contains(fifthLine), "Second item was not printed");
        assertTrue(outputResult.contains(sixthLine),"Total price was not printed correctly");
        assertTrue(outputResult.contains(seventhLine), "Total VAT was not printed correctly");
        assertTrue(outputResult.contains(eightLine), "Change was not printed correctly");

    }
}