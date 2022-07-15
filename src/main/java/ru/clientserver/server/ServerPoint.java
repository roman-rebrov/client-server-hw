package ru.clientserver.server;

import ru.clientserver.connections.Connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPoint {

    private static int port = Connect.port;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket = serverSocket.accept();

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        System.out.println("New connection accepted");

        final String name = in.readLine();

        out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));

        System.out.println(name);



    }
}
