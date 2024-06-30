package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class BookScrabbleHandler implements ClientHandler {
    private DictionaryManager dictionaryManager=new DictionaryManager();
    private InputStream inFromClient;
    private OutputStream outToClient;
    private boolean closed = false;


    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        this.inFromClient = inFromClient;
        this.outToClient = outToClient;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(inFromClient));
             PrintWriter out = new PrintWriter(outToClient, true)) {

            String query = in.readLine();

            // Split the query into its components
            List<String> queryComponents = Arrays.asList(query.split(","));
            String queryType = queryComponents.get(0);
            String[] bookWords = queryComponents.subList(1, queryComponents.size()).toArray(new String[]{});
            String word = queryComponents.get(queryComponents.size()-1);


            boolean result=false;
            if (queryType.equals("Q")) {
                result = dictionaryManager.query(bookWords);
            }
            else if (queryType.equals("C")) {
                result = dictionaryManager.challenge(bookWords);
            }

            if (result)
                out.println("true");
            else
                out.println("false");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public void close() {
        if (!closed) {
            try {
                inFromClient.close();
                outToClient.close();
                closed = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
