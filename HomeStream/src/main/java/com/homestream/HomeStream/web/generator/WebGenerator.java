package com.homestream.HomeStream.web.generator;

import com.homestream.HomeStream.main.assets.ScriptEngine;
import com.homestream.HomeStream.main.assets.property.Properties;
import com.homestream.HomeStream.main.exception.ScriptFormatException;

import java.io.File;
import java.io.IOException;

public class WebGenerator
{
    ScriptEngine script = new ScriptEngine();

    /**
     * Main Function to generate
     * @param scriptFile
     * @return
     */
    public String generate(String scriptFile)
    {
        File file = new File(scriptFile);
        try {

            script.setScript(file);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String out = null;
        try {
            out = write();
        } catch (ScriptFormatException e) {
            e.printStackTrace();
        }
        return out;
    }

    /**
     * Function to 'write' Website
     * @return
     * @throws ScriptFormatException
     */
    private String write() throws ScriptFormatException {
        String out = "";

        out += "<!DOCTYPE html>\n";
        out += "<html>\n";
        out += "<head>\n";

        out += decrypt("title:", "include:");

        out += "</head>\n";
        out += "<body>\n";

        out += getContent("#HEAD");

        out += getContent("#AUDIOHEAD");
        out += getContent("#VIDEOHEAD");
        out += getContent("#IMGHEAD");
        out += getContent("#LOGIN");
        out += getContent("#SEARCH");
        out += getContent("#UPLOAD");

        out += "<div class=\"box\">";
        out += getContent("#NAVIGATION");
        out += decrypt("content:");
        out += "</div>";

        out += getJS("#MAIN_JS");
        out += getJS("#CONTENT_JS");

        out += "</body>\n";
        out += "</html>\n";

        return out;
    }

    /**
     * Function to pre Build static content
     * @param key
     * @param keys
     * @return
     */
    private String decrypt(String key, String... keys) {
        String[] keyList = new String[keys.length + 1];
        String[] lines;

        keyList[0] = key;
        if(keys != null)for( int i = 1; i < keyList.length; i++) keyList[i] = keys[i - 1];

        String out = "";
        for(String item : keyList)
        {
            try
            {
                lines = script.getLine(item);
                for(String i : lines)
                {
                    if(item.equals("title:") && i.equals("#TITLE")) out += "<title>" + Properties.TITLE + "</title>\n";
                    else if(item.equals("include:"))
                    {
                        if(i.endsWith("CSS")) out += "<link rel=\"stylesheet\" href=\"" + getCSS(i) + "\">\n";
                    }
                    else if(item.equals("content:")) if(i.startsWith("#"))
                        {
                            out += getContent(i);
                        }
                }
            }
            catch (IOException e)
            {

            }
        }
        return out;
    }

    /**
     * Set CSS links
     * @param item
     * @return
     */
    private String getCSS(String item)
    {
        byte index = Properties.THEME;

        if(item.endsWith("ICON_CSS")) return Properties.ICONS_CSS[index];
        else if(item.endsWith("MAIN_CSS")) return Properties.MAIN_CSS[index];
        else if(item.endsWith("COLOR_CSS")) return Properties.COLOR_CSS[index];
        else if(item.endsWith("CONTENT_CSS")) return Properties.CONTENT_CSS[index];
        else if(item.endsWith("NAVIGATION_CSS")) return Properties.NAVIGATION_CSS[index];
        else if(item.endsWith("BACKGROUND_CSS")) return Properties.BACKGROUND_CSS[index];
        else return null;
    }

    /**
     * Set JS links
     * @param item
     * @return
     */
    private String getJS(String item)
    {
        if(item.endsWith("MAIN_JS")) return "<script text=\"text/javascript\" src=\"" + Properties.MAIN_JS + "\">\n</script>";
        if(item.endsWith("CONTENT_JS")) return "<script text=\"text/javascript\" src=\"" + Properties.CONTENT_JS + "\">\n</script>";
        if(item.endsWith("AUDIO_JS")) return "<script text=\"text/javascript\" src=\"" + Properties.AUDIO_JS + "\">\n</script>";
        if(item.endsWith("VIDEO_JS")) return "<script text=\"text/javascript\" src=\"" + Properties.VIDEO_JS + "\">\n</script>";
        else return null;
    }

    /**
     * Set Content
     * @param item
     * @return
     * @throws ScriptFormatException
     */
    private String getContent(String item) throws ScriptFormatException {
        switch (item)
        {
            case "#HEAD":
                return Properties.CONTENT_TITLE[0].get();
            case "#AUDIOHEAD":
                return Properties.AUDIO_TITLE[0].get();
            case "#VIDEOHEAD":
                return Properties.VIDEO_TITLE[0].get();
            case "#IMGHEAD":
                return Properties.IMG_TITLE[0].get();
            case "#LOGIN":
                return Properties.LOGIN[0].get();
            case "#SEARCH":
                return Properties.SEARCH[0].get();
            case "#NAVIGATION":
                return Properties.CONTENT_NAVIGATION[0].get();
            case "#LATEST_UPLOADS":
            case "#LATEST_STREAMS":
            case "#FAVORITES":
            case "#SEARCH_RESULT":
                return Properties.CONTENT_ELEMENT[0].get();
            case "#UPLOAD":
                return Properties.UPLOAD[0].get();
            case "#FOOT":
                return Properties.FOOT.get();
            default:
                throw new ScriptFormatException(item);
        }
    }
}
