package startup;

import observer.ObservedTotalRevenue;
import integration.ExternalHandlerCreator;
import util.RandomWithComposition;
import util.RandomWithInheritance;
import view.View;
import controller.Controller;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        ExternalHandlerCreator extCreator = new ExternalHandlerCreator();
        ObservedTotalRevenue totalRevenuePublisher = new ObservedTotalRevenue();
        Controller controller = new Controller(extCreator, totalRevenuePublisher);
        View view = new View(controller);


        RandomWithInheritance firstRandom = new RandomWithInheritance();
        RandomWithComposition secondRandom = new RandomWithComposition();
        int firstCustomerId = firstRandom.nextInt(1000,10000);
        int secondCustomerId = secondRandom.nextInt(1000,10000);
        boolean testOfInheritedRandom = firstRandom.nextBoolean();
        System.out.println("RandomWithInheritance created this boolean:" + (testOfInheritedRandom));


        view.purchaseSimulation(firstCustomerId,false);
        view.purchaseSimulation(secondCustomerId,false);
    }
}


