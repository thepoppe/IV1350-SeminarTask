package observer;

import model.purchase.TotalRevenueFileOutput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.TotalRevenueView;

import java.io.IOException;

class PurchaseObserverTest {

    private TotalRevenueView viewOutput;
    private TotalRevenueFileOutput fileOutput;
    private PurchaseObserver observer;
    @BeforeEach
    void setUp() throws IOException {
        this.observer = new PurchaseObserver();
        this.viewOutput = new TotalRevenueView();
        this.fileOutput = new TotalRevenueFileOutput();
    }

    @AfterEach
    void tearDown() {
        observer = null;
        viewOutput = null;
        fileOutput = null;
    }


    @Test
    void performPurchase() {

        observer.addObserver(viewOutput);
        observer.performPurchase(500);
        observer.addObserver(fileOutput);
        observer.performPurchase(200);
        observer.removeAllObserver();
        observer.performPurchase(300);

    }
}