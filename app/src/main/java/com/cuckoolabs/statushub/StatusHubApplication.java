package com.cuckoolabs.statushub;


import android.app.Application;
import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URISyntaxException;

/**
 * Created by insjena021 on 3/15/2017.
 */

public class StatusHubApplication extends Application {

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("https://statushub-dev.herokuapp.com/");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }
}