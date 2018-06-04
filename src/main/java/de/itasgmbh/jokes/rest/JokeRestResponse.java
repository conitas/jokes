package de.itasgmbh.jokes.rest;

import de.itasgmbh.jokes.model.Joke;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class JokeRestResponse {
    public final List<Joke> jokes;
    public final String server;

    public JokeRestResponse(List<Joke> jokes)

    {
        this.jokes = jokes;
        String localServer = "";
        try {
            localServer = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        server = localServer;

    }

}
