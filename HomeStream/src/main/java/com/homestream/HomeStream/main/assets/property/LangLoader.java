package com.homestream.HomeStream.main.assets.property;

import com.homestream.HomeStream.main.assets.Loader;
import com.homestream.HomeStream.main.exception.LineFormatMismatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LangLoader
{
    /**
     * Class to load .lang Files
     *
     * @author S. Schulze
     * @last_update 15.11.19
     */

    Loader loader = new Loader();

    private File lang, defaultLang;
    private static Map<String, String> data = new HashMap<>();

    /**
     * Init Language Loader
     */
    public LangLoader(){

        try
        {
            defaultLang = new File("./res/lang/en_US.lang");
            lang = new File("./res/lang/" + Properties.LANG + ".lang");

            if(!defaultLang.exists()) throw new FileNotFoundException("Default Language File 'en_US' not found");
            if(!lang.exists()) throw new FileNotFoundException("Language File '" + lang.getName() + "' not found");

            read();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Read Language File
     * @throws IOException
     */
    private void read() throws IOException {
        String[] lines = loader.LoadFileToStringArray(defaultLang);

        String[] elements;
        for(String line : lines)
        {
            if(line.replaceAll(" ","").length() > 0)
            {
                elements = line.split(" = ");
                if(elements.length != 2) throw new LineFormatMismatch();
                if(data.containsKey(elements[0])) throw new IOException("Language Key '" + elements[0] + "' contains multiple times!");
                data.put(elements[0],elements[1]);
            }
        }

        if(Properties.LANG != "en_US")
        {
            lines = loader.LoadFileToStringArray(lang);

            for(String line : lines)
            {
                if(line.replaceAll(" ","").length() > 0)
                {
                    elements = line.split(" = ");
                    if(elements.length != 2) throw new LineFormatMismatch();
                    data.replace(elements[0],elements[1]);
                }
            }
        }
    }

    /**
     * Return Language Value by Language Tag
     * @param key
     * @return
     */
    public static String getValue(String key)
    {
        if(data.get(key) == null) return key + "_NO_VALUE";
        else return data.get(key);
    }
}
