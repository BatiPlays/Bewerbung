package com.homestream.HomeStream.main.exception;

import java.io.IOException;

public class DuplicatedObjectOnServerFoundException extends IOException
{
    public DuplicatedObjectOnServerFoundException(String objectName)
    {
        super("Object '" + objectName + "' Already Exist on Server! \n" +
                "Activate the Property 'REPLACE_UPLOADED_FILES' to replace Already uploaded Files");
    }
}
