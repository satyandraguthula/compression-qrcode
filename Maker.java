/*-------------------------------------------------------------*/
/*The program is a part of testing for the BE Project. It takes*/
/*input from a file called input.txt and converts it into bytes*/
/*using a hash table                                           */
/*-------------------------------------------------------------*/
import java.io.*;
import java.util.*;
import java.net.*;
public class Maker {
    public static void main(String[] args) throws java.io.IOException, java.lang.InterruptedException {
	ServerSocket serverSocket = new ServerSocket(153);
	//serverSocket.setSoTimeout(10000);
	Compress com = new Compress();        
	FileOutputStream outputf = null;
	Reader inputStreamReader = null;
	Reader inputStreamReader1 = null;
	String line = "";
	String line1= "";	
	int data_int;
	
	/*initializing input and output*/
	try{
		Socket server = serverSocket.accept();
	  outputf = new FileOutputStream("outputjava.txt");
			DataInputStream in =
                  new DataInputStream(server.getInputStream());
		String input=in.readUTF();
          DataOutputStream output       = new DataOutputStream(outputf);
	  InputStream inputStream       = new FileInputStream(input);
	  inputStreamReader             = new InputStreamReader(inputStream);		
	  BufferedReader ip             = new BufferedReader(inputStreamReader);

	  InputStream inputStream1       = new FileInputStream("outputjava.txt");
	  inputStreamReader1             = new InputStreamReader(inputStream1);		
	  BufferedReader ip1             = new BufferedReader(inputStreamReader1);	
        /*Initialization ends here*/ 
	//server.close();
	int len=0;
	boolean comma_found=false;
	boolean fullstop_found=false;
	String postproc="";
	  while((line = ip.readLine()) != null){	
	    /*Data available as a string line here*/
	    String[] data = line.split(" ");                        //Line split on " " delimiter

	    for (int foo = 0; foo < data.length; foo++) {  
		//System.out.println(data[foo]);         // make foo unsigned too
		len=data[foo].length();
		if(data[foo].charAt(len-1)==',')
		{
			comma_found=true;
			postproc=data[foo].substring(0,len-1);
			System.out.println(postproc);
		}
		else if(data[foo].charAt(len-1)=='.')
		{
			fullstop_found=true;
			postproc=data[foo].substring(0,len-1);
		}
		else
		{
			fullstop_found=false;
			postproc=data[foo].substring(0,len);
		}
			
		data_int = com.hash(postproc);                     // replace with unsigned int
		System.out.println(postproc);
		System.out.println(Integer.toHexString(data_int));

                /* Writing the values to the output file*/
		if(data_int != -1){		
			//System.out.println(Integer.toHexString(data_int));		
			/*finding the range of the data*/	

			/*Uses bit steering*/
			
			if(data_int >= 0 && data_int <= 127){
			  output.writeByte((byte)data_int);   //single byte is written
			}
		
			else if(data_int >= 32768 && data_int <= 65407){
			  output.writeByte((byte)(data_int>>8));   //two bytes are written
			  output.writeByte((byte)(data_int));
			}
			//three bytes	
			else if(data_int>= 8421376 && data_int <= 16777087){
			  output.writeByte((byte)(data_int>>16));
			  output.writeByte((byte)(data_int>>8));
			  output.writeByte((byte)(data_int));	
			}
		}



		else{					
			output.writeByte(0x00);
			output.writeBytes(data[foo]);
			output.writeByte(0x01);
			//line1 = ip1.readLine();	
			//System.out.println(line1.charAt(7));		
		}	
		if(comma_found)
		{
		output.writeByte(0x02);
		comma_found=false;
		}
		if(fullstop_found)
		{
		output.writeByte(0x03);
		fullstop_found=false;
		}		
	    }
	    output.writeByte(0x04);
	    fullstop_found=false;				
	  }
	
	
	
	/*String serverName = "127.0.0.1";
      int port = 150;
      try
      {
         System.out.println("Connecting to " + serverName +
		 " on port " + port);
         Socket client = new Socket(serverName, port);
         System.out.println("Just connected to " 
		 + client.getRemoteSocketAddress());
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         out.writeUTF("outputjava.txt");
         InputStream inFromServer = client.getInputStream();
         DataInputStream in =
                        new DataInputStream(inFromServer);
         System.out.println("Server says " + in.readUTF());
         client.close();	
		}catch(Exception e){}*/
		System.out.println("Text Compressed");
		java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        // Start a new process: UNIX command ls
        java.lang.Process p = rt.exec("sudo python /home/satyandra/project_final/qrgen.py");
		p.waitFor();
	System.out.println("res.png created");
	 DataOutputStream writeclient       = new DataOutputStream(server.getOutputStream());
	writeclient.writeUTF("Finished");
	writeclient.close();
	server.close();
	}
	catch(Exception e){
	  e.printStackTrace();
	}
	finally{
	  if(inputStreamReader != null)
	    inputStreamReader.close();
	  if(outputf != null)
            outputf.close();
	}
    }
}

class Compress{
	Map<String, Integer> map = new HashMap<String, Integer>(); 
	/*Constructor creates hashtable*/
	public Compress() throws IOException{

	 BufferedReader reader = new BufferedReader(new FileReader(new File("hash2.txt")));
         String line = null;
	 while ((line = reader.readLine()) != null) {
            		if (line.contains(",")) {
                		String[] strings = line.split(",");
                		map.put(strings[0], Integer.parseInt(strings[1]));
            		}
        	}	
	}
	
	public int hash(String key){
	 int ret;
       	 if(map.get(key)!= null){
		ret = map.get(key);	
	 }

	 else{
		ret = -1;
	 }
	 return ret;    
        }
}
