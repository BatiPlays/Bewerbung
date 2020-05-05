package com.homestream.HomeStream.web.assets;

import com.homestream.HomeStream.main.assets.Loader;

import java.io.File;
import java.io.IOException;

public class TEMP
{
    /**
     * Class to store TEMP-Files
     *
     * @author S. Schulze
     * @last_update 21.11.19
     */

    String data;
    Loader loader = new Loader();

    /**
     * Load Temp-File
     * @param file
     */
    public TEMP(String file)
    {
        try {
            if(file.endsWith(".temp")) data = loader.LoadFileToString(new File(file));
            else data = loader.LoadFileToString(new File(file + ".temp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return Data
     * @return
     */
    public String get() { return data; }
}
