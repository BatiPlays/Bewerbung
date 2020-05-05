package com.homestream.HomeStream.main.exception;

import java.io.IOException;

public class ScriptFormatException extends IOException {

    /**
     * Exception thrown by unknown Element in Script File
     * @param item
     */
    public ScriptFormatException(String item)
    {
        super("Unknown Script Element '" + item + "'");
    }
}
