package startup;

import integration.ExternalHandlerCreator;
import view.View;
import controller.Controller;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        ExternalHandlerCreator extCreator = new ExternalHandlerCreator();
        Controller controller = new Controller(extCreator);

        new View(controller).purchaseSimulation();
    }
}


