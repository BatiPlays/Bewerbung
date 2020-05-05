package com.homestream.HomeStream.main.assets.property;

import com.homestream.HomeStream.main.assets.Loader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PropertiesLoader
{
    /**
     * Class to load Properties File
     */

    Map<String,String> properties = new HashMap<>();
    Loader loader = new Loader();
    String[] data;

    {
        try {
            data = loader.LoadFileToStringArray(new File(".\\res\\other\\properties.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read properties.properties
     */
    public PropertiesLoader(){
        for(String s : data)
        {
            String[] elements = s.split("=");
            if(elements.length == 2)
            {
                properties.put(elements[0].replaceAll(" ",""),elements[1].replaceFirst(" ",""));
            }
        }
    }

    /**
     * Return Value as Byte
     * @param key
     * @return
     * @throws NumberFormatException
     */
    public byte getAsByte(String key) throws NumberFormatException
    {
        switch (properties.get(key))
        {
            case "light":
                return 0;
            default:
                throw new NumberFormatException("Key '" + key + "' does not represent a byte value");
        }
    }

    public boolean getAsBoolean(String key) {
        if(properties.get(key).equalsIgnoreCase("true")) return true;
        else return false;
    }

    /**
     * Return Value as String
     * @param key
     * @return
     */
    public String getAsString(String key)
    {
        return properties.get(key);
    }

    /**
     * Return Value as String[]
     * @param key
     * @return
     */
    public String[] getAsArray(String key)
    {
        return properties.get(key).split(",");
    }
}
