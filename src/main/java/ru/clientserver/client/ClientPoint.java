package ru.clientserver.client;

import ru.clientserver.connections.Connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientPoint {

    private static int port = Connect.PORT;
    private static String host = Connect.HOST;
    private static Scanner scan = new Scanner(System.in);



    private static String request(String req)throws IOException{
        try (
                Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {

            out.println(req);

            return in.readLine();

        } catch (NullPointerException exception) {

            exception.printStackTrace();

        }
        return "";
    }



    public static void main(String[] args)  {

            String request = "";

            while (true) {


                try {

                    String response = request(request);
                    System.out.println("Server: " + response);

                    String check = request(Connect.IS_DIALOG);
                    if (!check.equals("Y")) {
                        break;
                    }

                    System.out.print("You: ");
                    request = scan.nextLine();

                }catch (NoSuchElementException exception) {

                    exception.printStackTrace();
                    break;

                } catch (ConnectException e){
                    System.err.println(e.toString());
                    break;
                } catch (IOException e){
                    System.err.println(e.toString());
                    break;
                }

            }


    }
}
