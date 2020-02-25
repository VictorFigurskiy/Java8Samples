package com.sample.soft_serve_training;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class DirectoryCleanupUtil {

    public static void main(String[] args) {
        try {
            DirectoryCleanupUtil.cleanup("C:/test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cleanup(String directoryPath) throws IOException {

        File directory = new File(directoryPath);
        if (!directory.exists()){
            directory.mkdir();
        } else {
            FileUtils.cleanDirectory(directory);
        }
    }
}
