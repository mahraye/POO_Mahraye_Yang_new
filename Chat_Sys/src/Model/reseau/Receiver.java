package Model.reseau;
import java.io.*;
import java.net.*;
import Model.messages.Message;

import Controller.Controller_reseau;
public class Receiver extends Thread {
	private DatagramSocket _servSock ;
    private Controller_reseau _chat;
    
    private InetAddress _remoteAddr;
    
    public Receiver(Controller_reseau controler) throws SocketException{ 
        _chat = controler;
        _servSock = new DatagramSocket(23464); 

    }

    private Message recvObjFrom()
    {
        try{
          byte[] recvBuf = new byte[5000];
          DatagramPacket packet = new DatagramPacket(recvBuf,recvBuf.length);
          _servSock.receive(packet);
          System.out.println("message re�u");
          _remoteAddr = packet.getAddress();
          System.out.println(_remoteAddr);
         // int byteCount = packet.getLength();
          ByteArrayInputStream byteStream = new ByteArrayInputStream(recvBuf);
          ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
          Message m = (Message)is.readObject();
          is.close();
          return(m);
        }
        catch (IOException e)
        {
          System.err.println("Exception:  " + e);
          e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        { e.printStackTrace(); }   
        return null;
    }
    public void close() {
    	_servSock.close();
    }
    @Override
        public void run() {
            while(true){ 
                try {
                	
                    _chat.messageHandle( recvObjFrom(), _remoteAddr.getHostAddress());
                    System.out.println("message handled");
                } catch (IOException ex) {
                }
            }
        }
}
