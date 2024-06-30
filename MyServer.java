package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

class MyServer implements ClientHandler {
    private int port;
    private ClientHandler clientHandler;
    private volatile boolean stop;
    private ServerSocket serverSocket;

    public MyServer(int port, ClientHandler clientHandler) {
        this.port = port;
        this.clientHandler = clientHandler;
        stop=false;
    }
    public void start()  {
        new Thread(()-> {
            try { runServer();}
            catch (IOException e) {e.printStackTrace();}
        }).start();
    }
    public void runServer() throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(1000);
        while (!stop) {
            try {
                Socket aClient = serverSocket.accept();
                try {
                    clientHandler.handleClient(aClient.getInputStream(), aClient.getOutputStream());
                    aClient.close();
                    clientHandler.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            }
        }
            serverSocket.close();

    }



    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {

    }
    @Override
    public void close()  {
        stop = true;
//        try {
//            serverSocket.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        clientHandler.close();
   }
}

