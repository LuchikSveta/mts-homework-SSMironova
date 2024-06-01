package ru.mts.siebel.starter.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class FilePathsService {

    @Value("${secretStore.path.rep}")
    private String secretStore;

    @Value("${secretStore.path.file}")
    private String secretStoreFile;

    @Value("${logData.path.rep}")
    private String logData;

    @Value("${logData.path.file}")
    private String logDataFile;

    @Value("${results.path.rep}")
    private String results;

    @Value("${results.path.file}")
    private String resultsFile;

}
