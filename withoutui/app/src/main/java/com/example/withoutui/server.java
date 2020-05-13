package com.example.withoutui;

import android.util.Log;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

import static android.content.ContentValues.TAG;
import static com.example.withoutui.service.add;


public class server extends NanoHTTPD {
    private final static int PORT = 8080;
    private String name;
    public server() throws IOException {
        super(PORT);
        start();

        System.out.println( "\nRunning! Point your browers to http://localhost:8080/ \n" );
    }

    @Override
    public Response serve(final IHTTPSession session) {

                        if (session.getUri().equals("/add")) {

                             name = add();
                            Log.v(TAG, "called" + name);
                        }
        String msg = "Hello from server\n";
         msg += "" + session.getRemoteIpAddress() + "..." +name; ;
        return newFixedLengthResponse( msg );
    }

}
