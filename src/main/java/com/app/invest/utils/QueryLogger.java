package com.app.invest.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class QueryLogger {

    private static final String SQL_QUERY_LOG_FILE = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\sql_queries.txt";

    private File queryLogFile = new File(SQL_QUERY_LOG_FILE);


    private void writeQueriesToFile(String sqlQuery) throws IOException {
        if (queryLogFile.createNewFile()) {
            log.info("Arquivo criado");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(queryLogFile, true));
             PrintWriter pr = new PrintWriter(writer)) {
            pr.println(sqlQuery);
            log.info("Successfully wrote to the file.");
        } catch (IOException e) {
            log.info("An error occurred.");
            e.printStackTrace();
        }
    }

    public void logToFile(String query) {
        CompletableFuture.runAsync(() -> {
            try {
                log.info("Criando log de query - {}", query);
                writeQueriesToFile(query);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }).thenAccept( result -> {
            log.info("Terminado async log query.");
        });
    }

}
