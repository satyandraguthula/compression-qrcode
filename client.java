import java.io.*;
import java.util.*;
import java.net.*;
public class client{
public static void main(String [] args)
   {
      String serverName = "127.0.0.1";
      int port = 153;
      try
      {
         System.out.println("Connecting to " + serverName +
		 " on port " + port);
         Socket client = new Socket(serverName, port);
         System.out.println("Just connected to " 
		 + client.getRemoteSocketAddress());
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
	System.out.println("Enter the name of input file");
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         out.writeUTF(reader.readLine());
         InputStream inFromServer = client.getInputStream();
         DataInputStream in =
                        new DataInputStream(inFromServer);
		
         System.out.println(in.readUTF());
         client.close();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }

}
