package com.homestream.HomeStream.main.assets;

import com.homestream.HomeStream.main.assets.property.Lang;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Assets
{
    /**
     * Main Class to Store Asset-Functions
     */

    /**
     * Function to Check File Name contains File Types
     * Return True if the
     * @param fileName
     * @param type
     * @param types
     * @return
     */
    public static boolean checkFileType(String fileName, String type, String... types)
    {
        if(fileName.endsWith(type)) return true;
        for (String s : types) if(fileName.endsWith(s)) return true;
        return false;
    }

    /**
     * Function to split String into a list
     * @param string
     * @param split
     * @return
     */
    public static ArrayList stringToList(String string, String split)
    {
        if(string == null || string.replaceAll(" ","").length() == 0) return new ArrayList();

        String[] temp = string.split(split);
        System.out.println(string + " " + temp.length);
        ArrayList<String> out = new ArrayList<>();
        for (String s : temp) out.add(s);

        return out;
    }

    /**
     * Function to shuffle a List
     * @param list
     * @return
     */
    public static List randomizeList(List list)
    {
        List temp = new LinkedList<>();

        while(list.size() > 0)
        {
            int i = (int)(Math.random() * 10) % list.size();
            temp.add(list.get(i));
            list.remove(i);
        }

        return temp;
    }

    /**
     * Simplified Function to replace all Targets by Value in Source
     * @param source
     * @param target
     * @param value
     * @return
     */
    public static String replaceAll(String source, String target, String value)
    {
        return source.replaceAll(target, value);
    }

    /**
     * Test if Source found in target Array
     * @param source
     * @param target
     * @return
     */
    public static boolean compareWithArray(String source, String[] target)
    {
        for (String s : target) if(s.equals(source)) return true;
        return false;
    }
    public static boolean compareWithArrayIgnoreCase(String source, String[] target)
    {
        for (String s : target) if(s.equalsIgnoreCase(source)) return true;
        return false;
    }

    /**
     * Function to Decrypt Search Query from extended Search
     * @param query
     * @return
     */
    public static String decryptQuery(String query)
    {
        String[] params = query.split("§");
        String out = "";

        out += Lang.EXT_SEARCH;
        out += "<br>";

        for(int i = 1; i < params.length; i++)
        {
            out += params[i].replace(",",": ");
            out += "<br>";
        }

        out = out.replace("TITLE", Lang.TITLE_NAME_SEARCH);
        out = out.replace("PLAYLIST", Lang.PLAYLIST_NAME_SEARCH);
        out = out.replace("ARTIST", Lang.ARTIST_ACTOR_SEARCH);
        out = out.replace("OWNER", Lang.OWNER_SEARCH);
        out = out.replace("RELEASE", Lang.RELEASE_SEARCH);
        out = out.replace("GENRE", Lang.GENRE_SEARCH);
        out = out.replace("TAGS", Lang.TAGS_SEARCH);

        return out;
    }

    /**
     * Compares Strings with Source String. Return True if one True
     * @param source
     * @param target
     * @param targets
     * @return
     */
    public static boolean compareByString_OR(String source, String target, String... targets)
    {
        if(source.equals(target)) return true;
        for(String s : targets) if(source.equals(s)) return true;
        return false;
    }
    public static boolean compareByStringIgnoreCase_OR(String source, String target, String... targets)
    {
        if(source.equalsIgnoreCase(target)) return true;
        for(String s : targets) if(source.equalsIgnoreCase(s)) return true;
        return false;
    }

    /**
     * Compares Strings with Source String. Return True if all True
     * @param source
     * @param target
     * @param targets
     * @return
     */
    public static boolean compareByString_AND(String source, String target, String... targets)
    {
        if(source.equals(target)) return true;
        for(String s : targets) if(source.equals(s)) return true;
        return false;
    }
    public static boolean compareByStringIgnoreCase_AND(String source, String target, String... targets)
    {
        if(!source.equalsIgnoreCase(target)) return false;
        for(String s : targets) if(!source.equalsIgnoreCase(s)) return false;
        return true;
    }
}
