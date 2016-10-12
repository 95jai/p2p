package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import utility.Help;

class Server 
{
    private String directory;
    private Socket socket;
    Server(Socket socket,String directory)
    {
        this.directory=directory;
        this.socket=socket;
    }
    public void runn()
    {
        try{
            DataInputStream dIn=new DataInputStream(socket.getInputStream());
            DataOutputStream dOut=new DataOutputStream(socket.getOutputStream());
            String fileName=dIn.readUTF();
            System.out.println(fileName);
            String fullFileName=directory+"\\"+fileName;
            File file=new File(fullFileName);
            InputStream in=new FileInputStream(file);
            long fileLength=file.length();
            dOut.writeLong(fileLength);
            OutputStream out=socket.getOutputStream();
            Help.copy2(in, out,fileLength);
            //dIn.close();
            //in.close();
            //out.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}