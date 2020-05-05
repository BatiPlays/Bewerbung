package com.homestream.HomeStream.main.exception;

public class CommandSyntaxException extends CommandException
{
    /**
     * Exception thrown when the structure of command and requiredCommand are unequal
     * * @param command
     * @param requiredCommand
     */
    public CommandSyntaxException(String command, String requiredCommand)
    {
        super("Unknown Command Syntax: \n" +
                "Required Command = " + requiredCommand + "\n" +
                "Received Command = " + command);
    }
}
