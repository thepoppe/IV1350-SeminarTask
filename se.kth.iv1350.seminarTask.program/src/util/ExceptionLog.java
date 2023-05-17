package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ExceptionLog {
    private static final String file_name = "exception_log.txt";
    private final PrintWriter printer;

    public ExceptionLog() throws IOException{
        printer = new PrintWriter(new FileWriter(file_name,true),true);

    }

    public void storeExceptionToFile(Exception thrownException){
        printer.println(createErrorMessage(thrownException));
        thrownException.printStackTrace(printer);


    }
    private String createErrorMessage(Exception thrownException){
        LocalDateTime timeOfException = LocalDateTime.now();
        String errorMessage = thrownException.getMessage();
        return  "An error was caught at: " + timeOfException + ".\nThe error thrown:" + errorMessage;
    }
}
