package com.bridgelabz.utility;

import java.io.File;

public class FileSystem {
    public static String PATH = "src/test/resources/";
    static File file;

    //    CONSTRUCTOR
    public FileSystem(String fileName) {
        file = new File(PATH + fileName + ".json");
    }

    //    TO GET FILE
    public static File getFile() {
        return file;
    }
}
