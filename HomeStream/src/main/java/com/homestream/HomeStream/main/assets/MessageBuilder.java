package com.homestream.HomeStream.main.assets;

import java.util.ArrayList;

public class MessageBuilder
{
    /**
     * Print out String Arrays Parallel
     * @param title
     * @param array
     * @param arrays
     */
    public static void printArraysWithTitle_Parallel(String title, String[] array, String[]... arrays)
    {
        ArrayList<String[]> list = new ArrayList<>();
        list.add(array);
        for (String[] s : arrays)
        {
            if(s.length != array.length) throw new IndexOutOfBoundsException("All Arrays must have the same Length");
            list.add(s);
        }

        System.out.println(title + ":");
        for (int i = 0; i < array.length; i++)
        {
            for (String[] a : list) System.out.print(a[i] + " ");
            System.out.println();
        }
    }

    /**
     * Print out String Arrays one after another
     * @param title
     * @param array
     * @param arrays
     */
    public static void printArraysWithTitle_Successively(String title, String[] array, String[]... arrays)
    {
        System.out.println(title + ":");
        for (String s : array) System.out.print(s);
        for (String[] a : arrays)
        {
            System.out.println();
            for (String s : a) System.out.print(s);
        }
    }

    /**
     * Print out Messages with Title
     * @param message
     * @param messages
     */
    public static void printSeverMessages(String message, String... messages)
    {
        System.out.println("Server:");
        System.out.println(message);

        for (String s : messages) System.out.println(s);
    }

    /**
     * Print out Hint (regular System.out.println)
     * @param hint
     */
    public static void hint(String hint)
    {
        System.out.println(hint);
    }
}
