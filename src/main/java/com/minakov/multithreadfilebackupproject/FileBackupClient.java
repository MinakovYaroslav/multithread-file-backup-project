package com.minakov.multithreadfilebackupproject;

public class FileBackupClient {

    public static void main(String[] args) {
        new FileBackupService().run();
    }
}
