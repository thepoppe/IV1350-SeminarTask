package startup;

import observer.PurchaseObserver;
import integration.ExternalHandlerCreator;
import view.View;
import controller.Controller;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        ExternalHandlerCreator extCreator = new ExternalHandlerCreator();
        PurchaseObserver totalRevenueObserver = new PurchaseObserver();
        Controller controller = new Controller(extCreator, totalRevenueObserver);
        View view = new View(controller);
        for (int i= 0; i < 2;i++)
            view.purchaseSimulation();
    }
}


