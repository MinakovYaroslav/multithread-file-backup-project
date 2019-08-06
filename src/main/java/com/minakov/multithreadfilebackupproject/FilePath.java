package com.minakov.multithreadfilebackupproject;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum FilePath {
    BACKUP(Paths.get("src", "resources", "backup")),
    ORIGIN(Paths.get("src", "resources", "origin"));

    private Path path;

    public Path get() {
        return path;
    }

    FilePath(Path path) {
        this.path = path;
    }
}
