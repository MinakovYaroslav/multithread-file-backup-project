package com.minakov.multithreadfilebackupproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ThreadFileBackupHandler implements Runnable {

    private String file;

    public ThreadFileBackupHandler(String file) {
        this.file = file;
    }

    @Override
    public void run() {
        try {
            String name = file.substring(0, file.lastIndexOf("."));
            String format = file.substring(file.lastIndexOf("."));
            Path origin = Paths.get(FilePath.ORIGIN.get() + "/" + file);
            Path copied = Paths.get(FilePath.BACKUP.get() + "/" + name + "-backup" + format);
            Files.copy(origin, copied, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
