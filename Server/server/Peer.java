package server;
/*This class contains all the information of connected peer and the files which it is going to contribute
It has various utility functions including the search() which checks if a file is present in the peer or not.
*/
import java.util.ArrayList;

class Peer
{
    private int peerId;  //Id of peer;
    private int numFiles;   //Number of files peer contain
    private ArrayList<String> fileName; //Array list of file names
    private String directory;   //Name of the directory
    private String address;     //Ip address
    private int port;           //Port number
    public Peer(int peerId, int numFiles,ArrayList<String> fileName,String directory,String address,int port)
    {
        this.peerId=peerId;
        this.numFiles=numFiles;
        this.fileName=fileName;
        this.directory=directory;
        this.address=address;
        this.port=port;
    }
    
    /*Get functions*/
    public int getPeerId()
    {
        return peerId;
    }
    public int getNumFiles()
    {
        return numFiles;
    }
    public ArrayList<String> getFileName(){
		return fileName;
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
	
	public void setFileName(ArrayList<String> fileNames){
		this.fileName.addAll(fileNames);
	}
	
	public void addFileName(String fileName){
		this.fileName.add(fileName);
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
        public boolean searchFile(String name)
        {
            for(String f : fileName)
            {
                if(f.equals(name))
                    return true;
            }
            return false;
        }
}