package client;

import java.net.Socket;

class Connect
{
    private Socket socket;
    private String directory;
    Connect(Socket socket,String directory)
    {
        this.socket=socket;
        this.directory=directory;
    }
    public Socket getSocket()
    {
        return socket;
    }
    public String getDirectory()
    {
        return directory;
    }
    
}