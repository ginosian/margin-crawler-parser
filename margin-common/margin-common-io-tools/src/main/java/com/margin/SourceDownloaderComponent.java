package com.margin;

import com.margin.context.ComponentProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
public class SourceDownloaderComponent {

    @Autowired
    private FileDownloaderComponent downloader;

    @Autowired
    private UnzipComponent unzipped;

    public Optional<Path> downloadRegistryFile(String url, String suffix) throws IOException, URISyntaxException {
        String resourcesPath = "D:\\Margin Temp Data";

        String filename = Paths.get(new URI(url).getPath()).getFileName().toString();
        String fileLocalDestination = resourcesPath + File.separator + filename;
        downloader.download(url, fileLocalDestination);
        unzipped.unzip(fileLocalDestination, resourcesPath + File.separator + "extracted", true);
        return Files.walk(Paths.get(resourcesPath)).filter(f -> f.endsWith(suffix)).findFirst();
    }
}
