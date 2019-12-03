import java.io.*;
import java.util.*;

public class expand {

    public static void main(String[] args) throws IOException {
boolean ascflag=false;
String huestr0intr="";
String huestr0="";

String strarray[]=new String[18000000];
 try {
            File fileh1 = new File("hash1.txt");
            InputStream inputStream = new BufferedInputStream(new FileInputStream(fileh1));

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains(",")) {
                        String[] strings = line.split(",");
                        strarray[Integer.parseInt(strings[1])] = strings[0];
                    }
                }

            }
        }
            catch(Exception exception)
            {
		System.out.println(exception);
            }
File fileh1=new File("hue0.txt");
File fileh2=new File("hue1.txt");
File fileh3=new File("hue2.txt");
File fileh4=new File("hue3.txt");
int c,j=0;
                        int data=0;
                        char dat;
                        String finstr="";
                        StringBuilder stringBuilder = new StringBuilder();
                        byte arr1[]= new byte[(int)fileh1.length()];
                       FileInputStream in=new FileInputStream(fileh1);
                        in.read(arr1);
                        in.close();
                        byte arr2[]= new byte[(int)fileh2.length()];
                        in=new FileInputStream(fileh2);
                        in.read(arr2);
                        in.close();
                        byte arr3[]= new byte[(int)fileh3.length()];
                        in=new FileInputStream(fileh3);
                        in.read(arr3);
                        in.close();
                        byte arr4[]= new byte[(int)fileh4.length()];
                        in=new FileInputStream(fileh4);
                        in.read(arr4);
                        in.close();
                        byte[] arr = new byte[arr1.length+arr2.length+arr3.length+arr4.length];
                        System.arraycopy(arr1, 0, arr, 0, arr1.length);
                        System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
                        System.arraycopy(arr3, 0, arr,arr1.length+arr2.length, arr3.length);
                        System.arraycopy(arr4, 0, arr, arr1.length+arr2.length+arr3.length, arr4.length);
                            for (int i=0;i<arr.length;i++)
                        {


                            if (arr[i]==0x00&&!ascflag)
                            {ascflag=true;
				System.out.println("ascii flag raised");
                                continue;
                            }
				if (arr[i]==0x02&&!ascflag)
                            {
                                stringBuilder.append(", ");
                                continue;
                            }
				if (arr[i]==0x03&&!ascflag)
                            {
                                stringBuilder.append(". ");
                                continue;
                            }
                            if (arr[i]==0x01&&ascflag)
                            {ascflag=false;
				System.out.println("ascii remooved");
                                stringBuilder.append(" ");
                                continue;
                            }
                            if (ascflag)
                            {
                                dat=(char)arr[i];
				if(dat!='.')
                                stringBuilder.append(dat);
                            }
                            else {
				
                                if(arr[i]>= 0){
                                    data =arr[i];
                                    if(strarray[arr[i]]!=null)
                                        stringBuilder.append(strarray[arr[i]]);   //single byte is written
					System.out.println(strarray[data]);
					System.out.println("");
                                    stringBuilder.append(" ");
                                }

                                else {
                                    data=arr[i]&127;
                                    data=data<<8;
                                    data=data|32768;
                                    data+=arr[++i]&127;
					if(arr[i]<0)
						data|=128;
                                    if(data<=65407){
                                        if (strarray[data]!=null)
                                        {stringBuilder.append(strarray[data]);
                                            stringBuilder.append(" ");
						System.out.println("");
						System.out.println(strarray[data]);
					System.out.println("");}

                                    }

                                    //three bytes
                                    else{
                                        data|=128;
                                        data=data<<8;
                                        data+=arr[++i]&127;
					if(arr[i]<0)
						data|=128;
                                        if (strarray[data]!=null)
                                        {stringBuilder.append(strarray[data]);
                                            stringBuilder.append(" ");}
                                    }
                                }
                            }
                        }
huestr0=stringBuilder.toString();
System.out.println(huestr0);
}
}
