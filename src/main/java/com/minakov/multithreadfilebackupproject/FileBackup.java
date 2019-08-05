package com.minakov.multithreadfilebackupproject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileBackup implements Runnable {

    private String path;

    public FileBackup(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        try {
            File file = new File(path);
            String name = file.getName().substring(0, file.getName().lastIndexOf("."));
            String format = file.getName().substring(file.getName().lastIndexOf("."));
            System.err.format("Start thread %s File: %s, format: %s, size: %d bytes\n", Thread.currentThread().getName(), name, format, file.length());
            Path copied = Paths.get(Main.BACKUP + name + "-backup" + format);
            Files.copy(file.toPath(), copied, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
