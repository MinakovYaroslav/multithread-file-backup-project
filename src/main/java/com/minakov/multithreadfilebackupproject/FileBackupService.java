package com.minakov.multithreadfilebackupproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileBackupService {

    public void run() {
        try (Stream<Path> walk = Files.walk(FilePath.ORIGIN.get())) {

            ArrayList<String> strings = walk.filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toCollection(ArrayList::new));

            ExecutorService service = Executors.newFixedThreadPool(4);

            strings.forEach(s -> service.execute(new ThreadFileBackupHandler(s)));

            while (!service.isTerminated()) {
                service.shutdown();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
