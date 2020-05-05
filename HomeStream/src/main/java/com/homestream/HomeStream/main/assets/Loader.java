package com.homestream.HomeStream.main.assets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Loader
{
    /**
     * Class to read line based Files
     */


    BufferedReader reader;

    /**
     * Return File Data, as Single Line
     * @param file
     * @return
     * @throws IOException
     */
    public String LoadFileToString(File file) throws IOException
    {
        String out = "";

        reader = new BufferedReader(new FileReader(file));

        String line;
        while((line = reader.readLine()) != null) out += line;

        return out;
    }

    /**
     * Return File Data, Line by Line
     * @param file
     * @return
     * @throws IOException
     */
    public String[] LoadFileToStringArray(File file) throws IOException
    {
        ArrayList<String> out = new ArrayList<>();

        reader = new BufferedReader(new FileReader(file));

        String line;
        while((line = reader.readLine()) != null) out.add(line);

        String[] outArray = new String[out.size()];

        for(int i = 0; i < out.size(); i++) outArray[i] = out.get(i);

        return outArray;
    }

    /**
     * Return Filenames with specific File Ending in Directory
     * @param file
     * @param fileExt
     * @return
     */
    public static String[] readDirectory(String file, String fileExt)
    {
        ArrayList<String> tempBuffer = new ArrayList<>();

        File directory = new File(file);
        if(!directory.exists() || !directory.isDirectory()) return null;

        File[] files = directory.listFiles();
        ArrayList<String> tempOut = new ArrayList<>();
        for(File f : files) if(f.getName().endsWith(".lang")) tempOut.add(f.getName());

        for(int i = 0; i < tempOut.size(); i++) if(tempOut.get(i).endsWith(fileExt)) tempBuffer.add(tempOut.get(i));
        
        String[] out = new String[tempBuffer.size()];
        for(int i = 0; i < out.length; i++) out[i] = tempBuffer.get(i);

        return out;
    }

}
