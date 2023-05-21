package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ExceptionLogger {
    private static final String file_name = "Exception_log.txt";
    private final PrintWriter printer;

    public ExceptionLogger() throws IOException{
        printer = new PrintWriter(new FileWriter(file_name,true),true);

    }

    public void storeExceptionToFile(Exception thrownException){

        printer.println(createErrorMessage(thrownException));
        thrownException.printStackTrace(printer);
        printer.println("------------------------------------------------------------------------\n");

    }
    private String createErrorMessage(Exception thrownException){
        LocalDateTime timeOfException = LocalDateTime.now();
        String errorMessage = thrownException.getLocalizedMessage();
        return  "An error was caught at: " + timeOfException +
                ".\nError message: " + errorMessage;
    }
}
