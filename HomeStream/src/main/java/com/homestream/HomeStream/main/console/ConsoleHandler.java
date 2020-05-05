package com.homestream.HomeStream.main.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHandler
{
    /**
     * Class to Handle Console Inputs
     */



    public String readCommand(String command)
    {
        if(command.startsWith("say ")) return command;

        return "";
    }

    public void run()
    {
        ConsoleDecrypts consoleDecrypts = new ConsoleDecrypts();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            try {
                consoleDecrypts.decrypt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
