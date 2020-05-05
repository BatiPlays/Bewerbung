package com.homestream.HomeStream.web.assets;

import com.homestream.HomeStream.main.exception.FileEndingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSS
{
    File file;

    String data;

    public CSS(File cssFile) throws IOException {
        String name = cssFile.getName().toLowerCase();
        if(name.endsWith(".css")) file = cssFile;
        else throw new FileEndingException(name.split(".")[1], "css");

        data = "";
        read();
    }

    private void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine()) != null) data += line;

        reader.close();
    }

    public String get(){ return data; }
}
