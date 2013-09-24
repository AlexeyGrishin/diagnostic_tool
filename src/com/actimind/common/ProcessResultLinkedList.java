package com.actimind.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class ProcessResultLinkedList extends LinkedList<String>
{
    public void addFromStream(InputStream is) throws IOException
    {
        if (is.available() > 0)
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null)
            {
                if (line.length() > 0)
                    add(line.trim());
            }

            reader.close();
        }
    }

}