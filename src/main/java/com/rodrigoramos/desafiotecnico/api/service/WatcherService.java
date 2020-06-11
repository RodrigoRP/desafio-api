package com.rodrigoramos.desafiotecnico.api.service;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.*;

@Service
public class WatcherService implements Runnable {

    private final DatabaseService databaseService;
    private static final Logger logger = LoggerFactory.getLogger(WatcherService.class);

    @Value("${data.caminho-entrada}")
    private String pathStr;

    @Autowired
    public WatcherService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }


    @SneakyThrows
    @Override
    public void run() {

        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(pathStr);

        path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                logger.info("Event kind: {}. File affected: {}.", event.kind(), event.context());
                if (event.kind().name().contains("ENTRY_CREATE")) {
                    databaseService.instantiateDatabase(event.context().toString());
                    databaseService.generateReport();
                }
            }
            key.reset();
        }
    }


}
