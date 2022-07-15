package ru.clientserver.client;

import ru.clientserver.connections.Connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class ClientPoint {

    private static int port = Connect.port;


    public static void main(String[] args) throws IOException {

        Socket socket = null;

        try {
            socket = new Socket("127.0.0.1", port);
        }catch (ConnectException exception){
            System.err.println(exception.toString());
        }



        try(        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    Scanner scan = new Scanner(System.in);
        ) {

            while (true) {

                String str = scan.nextLine();

                if (str.equals("--end")) break;

                out.println(str);
                System.out.println("Server: " + in.readLine());

            }

        }catch(NullPointerException exception){

            System.err.println(exception.toString());

        }catch(IOException ex){

            System.err.println(ex.toString());

        }

    }
}
