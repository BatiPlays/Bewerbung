package com.homestream.HomeStream.main.exception;

public class CommandItemException extends CommandException
{
    public CommandItemException(String command, String item)
    {
        super("Unknown Command Item: " + command.replaceFirst(item, "'" + item + "'"));
    }
}
