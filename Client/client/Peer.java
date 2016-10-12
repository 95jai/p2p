package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import utility.*;
class Peer
{
    private int peerId;
    LinkedList<String> fullFileList=new LinkedList<>();
    private int numFiles;
    private ArrayList<String> fileNames;
    private String directory;
    private String address;
    private int port;
    private int numOfThreads=4;
    private ServerSocket serverSocket;
    private PeerQueue<Connect> pQueue;
    Peer(String directory,ArrayList<String> fileNames,int numFiles,String address,int port)
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
        System.out.println("Running as Peer " + peerId + "! " + "It Took " + (System.currentTimeMillis() - start) + "ms for register.");
    }
    public String[] lookUp(String fileName,Socket socket,int count,int peerId) throws IOException
    {
        //System.out.println("Here for lookup");
        this.peerId=peerId;
        String[] peerAddress;
        DataInputStream dIn;
        DataOutputStream dOut=null;
       try{ dOut = new DataOutputStream(socket.getOutputStream());
       
       }catch(Exception e)
       {
           System.out.println(e);
       }
            dOut.writeByte(1);//Option for lookup
            //System.out.println("Looking up");
            peerAddress = new String[0];
            //file name
            dOut.writeUTF(fileName);
            dOut.flush();
            System.out.println("Peer "+peerId+ " looking for file "+fileName);
            //Reading the peer address that has the files
            dIn = new DataInputStream(socket.getInputStream());
            byte found=dIn.readByte();
            System.out.println("found : "+found);
            if(found==1)
            {
                int qt=dIn.readInt();
                peerAddress = new String[qt];
                for(int i = 0; i < qt; i++){
                    try{
                        peerAddress[i] = dIn.readUTF();
                    }catch (Exception e){
                        i--;
                    }
                    String paddress[] = peerAddress[i].split(":");
                    System.out.println("Peer " + paddress[2] + " - " + paddress[0] +":" + paddress[1] + " has the file " + fileName + "! - Looked by Peer " +peerId );
                }
            } else if(found == 0){
                System.out.println("File not found in the system");
                peerAddress = new String[0];
        } //Option for lookup
        //dIn.close();
        //socket.close();
        return peerAddress;
        }
    public LinkedList<String> getFullFileList()
    {
        
        return fullFileList;
    }
  public void download(String peerAddress,int port,String fileName,int i,String dir) throws IOException
    {
        port=55219;
        //clientActServer obj=new clientActServer();
        //obj.ClientAsServerSocket.close();
        Socket socket=new Socket("127.0.0.1",55219);
        //socket.bind(new InetSocketAddress("127.0.0.1",6677));
        //socket.connect(new InetSocketAddress("127.0.0.1", 6677));
        System.out.println(socket);
        System.out.println(peerAddress+" "+port);
        DataOutputStream dOut=new DataOutputStream(socket.getOutputStream());
        DataInputStream dIn=new DataInputStream(socket.getInputStream());
        System.out.println("Giving the file NAme");
        dOut.writeUTF(fileName);
        long fileLength=dIn.readLong();
        String folder=dir+"\\"+"downloads-peer"+peerId+"\\";
        System.out.println(folder);
        File f=new File(folder);
        Boolean created=false;
        if (!f.exists()){
            try {
                created = f.mkdir();
                System.out.println(created);
            }catch (Exception e){
                System.out.println("Couldn't create the folder, the file will be saved in the current directory!");
            }
        
        if(i!=-1)
            fileName=fileName+1;
        System.out.println(i);
        FileOutputStream out = created?new FileOutputStream(f.toString()+"/"+fileName): new FileOutputStream(fileName);
        //Help.copy(in,out,fileLength);
        byte[] buffer = new byte[8192];
        int read = 0;
        int totalRead = 0;
        int remaining = (int)fileLength;
        while((read = dIn.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
                System.out.println("read " + totalRead + " bytes.");
                out.write(buffer, 0, read);
        }
        System.out.println("File " + fileName + " received from peer " + peerAddress + ":" + port);
        dOut.close();
        out.close();
        socket.close();
    }
}
}