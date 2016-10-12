
package client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import utility.Help;

public class Client extends javax.swing.JFrame {

    /**
     * Creates new form Client
     */
    Socket sc11;
    String dir;
    static Hub hobj;
    static Client cobj;
    static int c=0; //Used to switch between alter and update
    static int dirUpdated=0;
    public Client() {
        initComponents();
        DirectoryText.setText(System.getProperty("user.dir"));
        logAppend("Welcome\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        gaussianBlurFilter1 = new org.edisoncor.gui.util.GaussianBlurFilter();
        panel1 = new org.edisoncor.gui.panel.Panel();
        labelHeader1 = new org.edisoncor.gui.label.LabelHeader();
        DirectoryText = new org.edisoncor.gui.textField.TextFieldRound();
        updateDirBtn = new org.edisoncor.gui.button.ButtonRect();
        labelHeader2 = new org.edisoncor.gui.label.LabelHeader();
        IPText = new org.edisoncor.gui.textField.TextFieldRound();
        labelHeader3 = new org.edisoncor.gui.label.LabelHeader();
        PortText = new org.edisoncor.gui.textField.TextFieldRound();
        ConnectBtn = new org.edisoncor.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        LogText = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelHeader1.setText("DC Client");
        labelHeader1.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N

        DirectoryText.setEditable(false);
        DirectoryText.setText("DIRECTORY");

        updateDirBtn.setForeground(new java.awt.Color(0, 102, 102));
        updateDirBtn.setText("ALTER");
        updateDirBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateDirBtnMouseClicked(evt);
            }
        });

        labelHeader2.setText("IP");

        IPText.setText("127.0.0.1");

        labelHeader3.setText("PORT");

        PortText.setText("6666");
        PortText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PortTextActionPerformed(evt);
            }
        });

        ConnectBtn.setForeground(new java.awt.Color(0, 102, 102));
        ConnectBtn.setText("CONNECT");
        ConnectBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ConnectBtnMouseClicked(evt);
            }
        });
        ConnectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectBtnActionPerformed(evt);
            }
        });

        LogText.setEditable(false);
        LogText.setColumns(20);
        LogText.setFont(new java.awt.Font("Agency FB", 0, 19)); // NOI18N
        LogText.setRows(5);
        jScrollPane1.setViewportView(LogText);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                        .addComponent(labelHeader3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PortText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(labelHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IPText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(DirectoryText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateDirBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ConnectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DirectoryText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateDirBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IPText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHeader3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PortText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(ConnectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConnectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConnectBtnActionPerformed

    private void PortTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PortTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PortTextActionPerformed

    private void ConnectBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConnectBtnMouseClicked
        new Thread(){
            public void run(){
                initial();
            }
        }.start();
        ConnectBtn.setEnabled(false);
    }//GEN-LAST:event_ConnectBtnMouseClicked

    private void updateDirBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateDirBtnMouseClicked
        //String name=updateDirBtn.getName();
        
        if(c==0)
        {
            DirectoryText.setEditable(false);
            JFileChooser dirChooser = new JFileChooser();
            dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int res=dirChooser.showOpenDialog(Client.this);
            File file=dirChooser.getSelectedFile();
            String path=file.getAbsolutePath();
            DirectoryText.setText(path);
            updateDirBtn.setText("UPDATE");
            c=1;
            dirUpdated=0;
        }
        else
        {
            DirectoryText.setEditable(false);
            updateDirBtn.setText("ALTER");
            dirUpdated=1;
            c=0;
        }
    }//GEN-LAST:event_updateDirBtnMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                cobj=new Client();
                cobj.setVisible(true);
            }
        });
    }
    public static Client getGUI()
    {
        return cobj;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public org.edisoncor.gui.button.ButtonRect ConnectBtn;
    public org.edisoncor.gui.textField.TextFieldRound DirectoryText;
    public org.edisoncor.gui.textField.TextFieldRound IPText;
    public javax.swing.JTextArea LogText;
    public org.edisoncor.gui.textField.TextFieldRound PortText;
    private org.edisoncor.gui.util.GaussianBlurFilter gaussianBlurFilter1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.label.LabelHeader labelHeader1;
    private org.edisoncor.gui.label.LabelHeader labelHeader2;
    private org.edisoncor.gui.label.LabelHeader labelHeader3;
    private org.edisoncor.gui.panel.Panel panel1;
    public org.edisoncor.gui.button.ButtonRect updateDirBtn;
    // End of variables declaration//GEN-END:variables
    public void logAppend(String str)
    {
       LogText.append(str);
    }
    
    public  void initial() 
    {
        //String serverAddress="127.0.0.1";
        String serverAddress=IPText.getText().toString();
        //int serverPort=6666;
        int serverPort=Integer.parseInt(PortText.getText().toString());
        Scanner scan=new Scanner(System.in);
        final int clientAsServerPort=55219;
        dir=DirectoryText.getText().toString();
        logAppend("Current Directory: "+dir+"\n");
        logAppend("Connecting to "+serverAddress+":"+serverPort+"\n");
         //dir="C:\\Users\\jaisethia\\Desktop\\Charlizards\\src";
        // dir="C:\\Users\\jaisethia\\Desktop\\ResumeAnkit";
        File folder=new File(dir);
        int option;
        String fileName=null;
        while(!folder.isDirectory())
        {
            //System.out.println("Enter full path of a valid directory");
            logAppend("Enter full path of a valid directory\n");
            dirUpdated=0;
            //dir=scan.nextLine();
            while(dirUpdated!=1)
            {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    continue;
                }
            }
            dir=DirectoryText.getText().toString();
            folder=new File(dir);
        }
        //Util.getExternalIp();
        String address="1270.0.0.1";
        //address = InetAddress.getLocalHost().getHostAddress();
        int port=6666;
       /* try{
            port=Integer.parseInt(args[1]);
        }
        catch(Exception e)
        {
            System.out.println("Enter a valid port number");
        }*/
        ArrayList<String> fileNames=Help.listFileForFolder(folder);
        final Peer peer=new Peer(dir,fileNames,fileNames.size(),address,port);
        //clientActServer cas=new clientActServer();
        final RegisterInServer clientRegistry= new RegisterInServer(dir,fileNames,fileNames.size(),address,port);
        Socket socket=null;
        //InetAddress localad=InetAddress.getLocalHost();
        try{
            socket=new Socket(serverAddress,serverPort); //Connecting with the server localhost:6666
            //System.out.println("Connection to server "+socket);
            logAppend("Successfull Connected\n");
            clientRegistry.register(socket);//clientRegistry.register gets the registry done in server
        }catch(Exception e)
        {
            //System.out.println("There is no instance of server running");
            logAppend("There is no instance of server running\n");
            
            return;
        }
//        ServerSocket cASsSocket=new ServerSocket(55219);
//        new Thread(){
//          public void run(){
//               
//              try {
//                  while(true)
//                      try {
//                          sc11=cASsSocket.accept();
//                          
//                          break;
//                      } catch (IOException ex) {
//                          continue;
//                      }
//                  down();
//              } catch (IOException ex) {
//                  Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//              }
//          }  
//        }.start();
        hobj=new Hub();
        hobj.setVisible(true);
        String[] peerAddress=new String[0];
        Scanner sc=new Scanner(System.in);
    while(true){
//        System.out.println("\n\nSelect the option:");
//        System.out.println("1 - Lookup for a file");
//        System.out.println("2 - Download file");
        logAppend("\nSelect the option\n");
        logAppend("1-Lookup for a file\n");
        logAppend("2-Get File list\n");
        logAppend("3-Downlod file");
        Socket cAScSocket=null;
        option = sc.nextInt();
        System.out.println("option = "+option);
        int optpeer;
        if(option==1)
        {
            System.out.println("Enter File name");
            fileName=sc.next();
            System.out.println("Searching for file "+fileName);
            try {
                peerAddress=peer.lookUp(fileName, socket, port,clientRegistry.getPeerId());
            } catch (IOException ex) {
                logAppend("Unable to look-up\n");
            }
        }
        else if(option==2)
        {
            LinkedList<String> fileNameList= peer.getFullFileList();
            
        }
        else if(option==3)
        {  
            System.out.println(peerAddress[0]);
            //cASsSocket.close();
            if(peerAddress.length==0)
                System.out.println("Lookup for the peers first");
            else if(peerAddress.length == 1 && Integer.parseInt(peerAddress[0].split(":")[2]) == clientRegistry.getPeerId()){
    				System.out.println("This peer has the file already, not downloading then.");
    			}else if(peerAddress.length == 1){
    				String[] addrport = peerAddress[0].split(":");
    				System.out.println("Downloading from peer " + addrport[2] + ": " + addrport[0] + ":" + clientAsServerPort);
                try {
                    //peer.download(addrport[0], Integer.parseInt(addrport[1]), fileName, -1,dir);
                    peer.download(addrport[0],clientAsServerPort , fileName, -1,dir);
                } catch (IOException ex) {
                    logAppend("Unable to download\n");
                }
    			}else {
    				System.out.println("Select from which peer you want to Download the file:");
    				for(int i = 0; i < peerAddress.length; i++){
    					String[] addrport = peerAddress[i].split(":");
    					System.out.println((i+1) + " - " + addrport[0] + ":" + addrport[1]);
    				}
    				optpeer = sc.nextInt();
    				while(optpeer > peerAddress.length || optpeer < 1){
    					System.out.println("Select a valid option:");
    					optpeer = sc.nextInt();
    				}
    				String[] addrport = peerAddress[optpeer-1].split(":");
                try {
                    peer.download(addrport[0], Integer.parseInt(addrport[1]), fileName, -1,dir);
                } catch (IOException ex) {
                    logAppend("Unable to Download\n");
                }
    			}
    		}
        else{
    			sc.close();
    			//System.out.println("Peer desconnected!");
                        logAppend("Peer Disconnected\n");
    			return;
    		}
    }
    }
    public void down() throws IOException
    {
        //Uploading
        DataInputStream dinDown=new DataInputStream(sc11.getInputStream());
        DataOutputStream doutDown = new DataOutputStream(sc11.getOutputStream());
        String fileName=dinDown.readUTF();
        System.out.println(fileName);
        String fullFileName=dir+"\\"+fileName;
        File file=new File(fullFileName);
        InputStream in=new FileInputStream(file);
        long fileLength=file.length();
        doutDown.writeLong(fileLength);
        FileInputStream fis=new FileInputStream(file);
        byte buffer[]=new byte[8192];
        while(fis.read(buffer)>0)
        {
            doutDown.write(buffer);
        }
        fis.close();
        doutDown.close();
        dinDown.close();
    }
            
}

