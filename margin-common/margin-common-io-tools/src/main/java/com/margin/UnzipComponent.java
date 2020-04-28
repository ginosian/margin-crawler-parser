package com.margin;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class UnzipComponent {

    public Set<String> unzip(String zipFilePath, String destDirectory, boolean saveWithOriginalNames) throws IOException {
        boolean mkExtractedDirs = new File(destDirectory).mkdirs();

        Set<String> extractedFileNames = new HashSet<>();

        ZipInputStream zipInUtil = new ZipInputStream(new FileInputStream(zipFilePath), Charset.forName("CP437"));
        ZipEntry entryUtil = zipInUtil.getNextEntry();
        // iterates over entries in the zip file
        int counter = 0;
        while (entryUtil != null) {
            String filePath;
            String nameOrg = entryUtil.getName();
            String extension = nameOrg.substring(nameOrg.lastIndexOf(".")).toLowerCase();
            if (saveWithOriginalNames) filePath = destDirectory + File.separator + nameOrg;
            else filePath = destDirectory + File.separator + "file_" + counter + extension;

            if (!entryUtil.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipInUtil, filePath, 4096);
                extractedFileNames.add(nameOrg);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipInUtil.closeEntry();
            entryUtil = zipInUtil.getNextEntry();
        }
        zipInUtil.close();
        return extractedFileNames;
    }

    private void extractFile(ZipInputStream zipIn, String filePath, int bufferSize) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[bufferSize];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}
