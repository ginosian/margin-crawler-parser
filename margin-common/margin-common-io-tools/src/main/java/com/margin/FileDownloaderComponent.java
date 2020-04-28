package com.margin;

import com.margin.enums.LoadingStage;
import com.margin.error.ApiException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Component
public class FileDownloaderComponent {

    public void download(String url, String localDestinationPath) throws IOException {
        File dest = new File(localDestinationPath);
        boolean destFileCreated = dest.createNewFile();
        if (destFileCreated) FileUtils.copyURLToFile(new URL(url), dest, 500000, 5000000);
        else throw new ApiException("Destination file " + localDestinationPath + " could not be created", LoadingStage.SCRAPING);
    }
}
