package startup;

import integration.ExternalHandlerCreator;
import view.View;
import controller.Controller;


public class Main {
    public static void main(String[] args) {

        ExternalHandlerCreator extCreator = new ExternalHandlerCreator();
        Controller controller = new Controller(extCreator);

        new View(controller).purchaseSimulation();
    }
}


