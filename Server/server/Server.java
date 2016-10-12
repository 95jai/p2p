package server;
/*As soon as client get connected in IndexServer, its socket is sent to this class
and run() starts executing.
*/
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread
{
    private static int id=0;
    private ArrayList<Peer> peerList;
    private Socket socket;
    IndexServer is =IndexServer.getGUI();
    private int getUniqueId()
    {
        synchronized(this)  //Every client gets a peer id..and clients get executed on seperate thread
        {
            return ++id;
        }
    }
    public void newPeerList()
    {
        peerList=new ArrayList<Peer>();
    }
    public void addPeer(Peer peer)
    {
        peerList.add(peer);
    }
    public boolean addAllPeer(ArrayList<Peer> peers)
    {
        peerList.addAll(peers);
        return true;
    }
    public Server(Socket socket)    //run method starts after Sever() constructor is called
    {
        this.socket=socket;
    }
    public boolean search(String filename)
    {
        newPeerList();
        return IndexServer.getIndex().containsKey(filename)?addAllPeer(IndexServer.getIndex().get(filename)):false; 
    }
    public void run()
    {
        try{
            DataInputStream dIn=new DataInputStream(socket.getInputStream());
            DataOutputStream dOut=new DataOutputStream(socket.getOutputStream());
            while(true){
            byte ch=dIn.readByte(); //Sent by client
            //System.out.println(ch);
            switch(ch)
            {
                case 0:
                    int peerId=getUniqueId();//Unique id is assigned to every peer
                    boolean end=false;
                    ArrayList<String> fileName=new ArrayList<String>();//list of files of a peer
                    int numFiles=0;
                    int port=0;
                    String directory=null;
                    String address=null;
                    while(!end)
                    {
                        byte messageType=dIn.readByte();
                        switch(messageType)
                        {
                            case 1:
                                numFiles=dIn.readInt();//Number of files: sent by client(peer)
                                //System.out.println("Peer "+peerId+"\nRegistering with numFiles: "+numFiles);
                                is.appendText("Peer "+peerId+"\nRegistering with numFiles: "+numFiles+"\n");
                                break;
                            case 2:
                                for(int i=0;i<numFiles;i++)
                                {
                                    fileName.add(dIn.readUTF());
                                    //System.out.println(fileName.get(i));
                                    is.appendText(fileName.get(i)+"\n");//File names sent by client
                                    //File names are added into array list
                                }
                                break;
                            case 3:
                                directory=dIn.readUTF();//Directory sent by peer
                                break;
                            case 4:
                                address = dIn.readUTF();//Address sent by peer
                                break;
                            case 5:
                                port = dIn.readInt();//Port sent by peer
                                break;
                            default:
                                end = true;
                        }
                    }
                    synchronized(this)
                    {
                        IndexServer.registry(peerId, numFiles, fileName, directory, address, port);
                        //Peer is then registered
                        //registry maps the peer with different files associated with it
                    }
                    dOut.writeInt(peerId);
                    dOut.flush();
                    //socket.close();
                    break;
                case 1:
                    String file_name=dIn.readUTF();//When a file name is requested by a client
                    //System.out.println("file_name");
                    boolean b=search(file_name);//It will search the file name and add all the peers containing the file into a peerList
                    try {
                        Thread.sleep(1);
                        } 
                    catch (InterruptedException e) {
                        e.printStackTrace();
                        }
                    if(b)
                    {
                        dOut.writeByte(1);
                        dOut.writeInt(peerList.size());
                        for(Peer p : peerList)
                        {
                            dOut.writeUTF("127.0.0.1"+":"+p.getPort()+":"+p.getPeerId());//p.getAddress()
                            dOut.flush();
                            //Sending the info of peers containing the file requested by client
                        }
                    }
                    //socket.close();
                    break;
                default:
                    is.appendText("Not an option");
                    //System.out.println("Not an option");
            }
        }}
        catch(Exception e)   
        {
            e.printStackTrace();
        }
    }
    
}