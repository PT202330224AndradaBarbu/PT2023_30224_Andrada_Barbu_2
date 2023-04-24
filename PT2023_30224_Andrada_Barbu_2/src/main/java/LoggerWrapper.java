import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerWrapper {

    public static final Logger myLogger = Logger.getLogger("Test");

    private static LoggerWrapper instance = null;

    public static LoggerWrapper getInstance() {
        if(instance == null) {
            try {
                LoggerWrapper.prepareLogger();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            instance = new LoggerWrapper ();
        }
        return instance;
    }

    private static void prepareLogger() throws IOException {
        FileHandler myFileHandler = new FileHandler("log.txt");
        myFileHandler.setFormatter(new SimpleFormatter());
        myLogger.addHandler(myFileHandler);
        myLogger.setUseParentHandlers(false);
        myLogger.setLevel(Level.FINEST);
    }


}