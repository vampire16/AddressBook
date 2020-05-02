package com.bridgelabz.utility;

import java.io.File;

public class FileSystem {
    static File file;
    String PATH = "src/test/resources/";


    public FileSystem(String fileName) {
        file = new File(PATH + fileName + ".json");
    }

    public static File getFile(){
        return file;
    }
}
