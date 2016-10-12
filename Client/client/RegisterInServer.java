package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import utility.*;

class RegisterInServer
{
    private int peerId;
    private int numFiles;
    private ArrayList<String> fileNames;
    private String directory;
    private String address;
    private int port;
    private int numOfThreads=4;
    private ServerSocket serverSocket;
    private PeerQueue<Connect> pQueue;
    RegisterInServer(String directory,ArrayList<String> fileNames,int numFiles,String address,int port)
    {
        this.directory=directory;
        this.fileNames=fileNames;
        this.numFiles=numFiles;
        this.address=address;
        this.port=port;
        pQueue=new PeerQueue<Connect>();
    }
    //getters
    public int getPeerId(){
            return peerId;
    }

    public int getNumFiles(){
            return numFiles;
    }

    public ArrayList<String> getFileNames(){
            return fileNames;
    }

    public String getDirectory(){
            return directory;
    }

    public String getAddress(){
            return address;
    }

    public int getPort(){
            return port;
    }

    //setters
    public void setPeerId(int peerId){
            this.peerId = peerId;
    }

    public void setNumFiles(int numFiles){
            this.numFiles = numFiles;
    }

    public void setFileNames(ArrayList<String> fileNames){
            this.fileNames.addAll(fileNames);
    }

    public void addFileName(String fileName){
            this.fileNames.add(fileName);
    }

    public void setDirectory(String directory){
            this.directory = directory;
    }

    public void setAddress(String address){
            this.address = address;
    }

    public void setPort(int port){
            this.port = port;
    }
    public void register(Socket socket)throws IOException
    {
        //System.out.println("Connecting to the server");
        long start=System.currentTimeMillis();
        DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
        //Options to register in to the server(new peer)
        dOut.writeByte(0);
        dOut.flush();
        dOut.writeByte(1);
        dOut.writeInt(numFiles);
        dOut.flush();
        dOut.writeByte(2);
        for(String s: fileNames)
            dOut.writeUTF(s);
        dOut.flush();
        dOut.writeByte(3);
        dOut.writeUTF(address);
        dOut.flush();
    	dOut.writeByte(5);
    	dOut.writeInt(port);
    	dOut.flush();
    	dOut.writeByte(-1);
    	dOut.flush();
        DataInputStream dIn=new DataInputStream(socket.getInputStream());
        this.peerId=dIn.readInt();
        //dOut.close();
        //dIn.close();
        //socket.close();
        Client obj;
        obj=Client.getGUI();
        obj.logAppend("Running as Peer " + peerId +"\nIt Took " + (System.currentTimeMillis() - start) + "ms for register.\n");
        //System.out.println("Running as Peer " + peerId + "It Took " + (System.currentTimeMillis() - start) + "ms for register.");
    }
}