
package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;


public class Help {
    public static ArrayList<String> listFileForFolder(final File folder)
    {
        ArrayList<String> fileName=new ArrayList<String>();
        for(final File file_entry : folder.listFiles())
            fileName.add(file_entry.getName());
        return fileName;
    }
    public static void copy(InputStream in,OutputStream out,long fileLength)throws IOException
    {
        byte buffer[]=new byte[8192];
        int count=0;
        int recvCount=0;
        long start=System.currentTimeMillis();
        while((count=in.read(buffer))!=-1)
        {
            out.write(buffer,0,count);
            recvCount+=count;
            System.out.println("DOWNLOADING.. "+recvCount*100/fileLength+"%% COMPLETED");
        }
        System.out.println("Download Took : "+(System.currentTimeMillis() - start) + " ms.");
    }
    public static void copy2(InputStream in,OutputStream out,long fileLength)throws IOException
    {
        long current=0;
        byte[] contents;
        while(current!=fileLength)
        {
            int size=8192;
            if(fileLength-current>size)
                current=current+size;
            else{
                size=(int)(fileLength-current);
                current=fileLength; //This block executes last iteration. Current = fileLength to break the loop
            }
            contents=new byte[8192];
            in.read(contents,0,size);
            out.write(contents);
            System.out.println("UPLOADING.. "+current*100/fileLength+"% COMPLETED");
        }
        out.flush();
    }
    public static long toSeconds(long start, long end){
		return (end-start)/1000;
	}
	
	public static String getExternalIP() throws IOException{
		URL url = new URL("http://checkip.amazonaws.com/");
    	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
    	return br.readLine();
	}
	
	public static double calculateAverage(ArrayList<Long> times) {
		  Long sum = 0L;
		  if(!times.isEmpty()) {
		    for (Long time : times) {
		        sum += time;
		    }
		    return sum.doubleValue() / times.size();
		  }
		  return sum;
		}
}
    
