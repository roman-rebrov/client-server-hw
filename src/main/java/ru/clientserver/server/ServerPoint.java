package ru.clientserver.server;

import ru.clientserver.connections.Connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPoint {

    private static int port = Connect.PORT;
    private static ServerSocket serverSocket;
    private static Socket clientSocket;


    public static void main(String[] args) throws IOException {

        try {
            serverSocket = new ServerSocket(port);
            PrintWriter out = null;
            BufferedReader in = null;

            DialogBuilder dialog = new DialogBuilderImpl();

            while (true) {

                try {

                    clientSocket = serverSocket.accept();
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    System.out.println("New connection accepted");


                    final String request = in.readLine();
                    String response = dialog.append(request);

                    out.println(response);

                    System.out.println(request);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    in.close();
                    out.close();
                }

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            serverSocket.close();
            clientSocket.close();
        }


    }
}
