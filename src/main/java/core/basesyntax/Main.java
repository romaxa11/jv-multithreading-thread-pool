package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        List<Future<String>> futures = new ArrayList<>();
        // write your code here
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 20; i++) {
            futures.add(executorService.submit(new MyThread()));
        }
        executorService.shutdown();
        try {
            for (Future<String> future : futures) {
                logger.log(Level.INFO, future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Something went wrong...");
        }
    }
}
