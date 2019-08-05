package com.minakov.multithreadfilebackupproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final String ORIGIN = "C:\\projects\\multithread-file-backup-project\\src\\resources\\origin\\";
    public static final String BACKUP = "C:\\projects\\multithread-file-backup-project\\src\\resources\\backup\\";

    public static void main(String[] args) {
        try (Stream<Path> walk = Files.walk(Paths.get(ORIGIN))) {

            ArrayList<String> strings = walk.filter(Files::isRegularFile)
                    .map(Path::toString)
                    .collect(Collectors.toCollection(ArrayList::new));

            ExecutorService service = Executors.newFixedThreadPool(4);

            strings.forEach(s -> service.execute(new FileBackup(s)));

            while (!service.isTerminated()) {
                service.shutdown();
            }
            System.err.format("Thread %s is shutdown\n", Thread.currentThread().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
