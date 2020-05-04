package com.bridgelabz.utility;

import java.io.File;

public class FileSystem {
    public static String PATH = "src/test/resources/";
    static File file;


    public FileSystem(String fileName) {
        file = new File(PATH + fileName + ".json");
    }

    public static File getFile() {
        return file;
    }
}
