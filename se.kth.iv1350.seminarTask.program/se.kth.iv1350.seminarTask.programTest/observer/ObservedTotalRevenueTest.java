package observer;

import model.TotalRevenueFileOutput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.TotalRevenueView;

import java.io.IOException;

class ObservedTotalRevenueTest {

    private TotalRevenueView viewOutput;
    private TotalRevenueFileOutput fileOutput;
    private ObservedTotalRevenue observer;
    @BeforeEach
    void setUp() throws IOException {
        this.observer = new ObservedTotalRevenue();
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