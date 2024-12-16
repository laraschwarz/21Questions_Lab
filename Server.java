import java.net.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Server {
    private static List<ObjectOutputStream> outputs = new ArrayList<>();

    public static void main(String[] args) {
        int portNumber = 1024;

        boolean running = true;
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            while (running) {
                Socket clientSocket = serverSocket.accept(); // wait for a connection
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " +
                    portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

    private static void handleClient(Socket socket) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            System.out.println("Client Connected ");
            outputs.add(out);
            if (outputs.size() == 1) {
                outputs.get(0).writeObject("true");
            }

            boolean clientRunning = true;
            while (clientRunning) {
                Object o = in.readObject();
                
                if(o instanceof String){
                    System.out.println("Received Stirng");
                    String message = (String) o;
                    for (ObjectOutputStream otherOut : outputs) {
                        otherOut.reset();
                        otherOut.writeObject(message);
                    }  
                }
                else if (o instanceof MyArrayList){
                    MyArrayList list = (MyArrayList) o;
                    System.out.println("Received List");
                    for (ObjectOutputStream otherOut : outputs) {
                        otherOut.reset();
                        otherOut.writeObject(list);
                    }
                }
            
                
                
            }

            outputs.remove(out);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
