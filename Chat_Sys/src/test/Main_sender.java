package test;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import Controller.Controleur_Processor;
import Controller.Controller_reseau;
import Model.User;
import Model.messages.* ;
public class Main_sender {
	
	public static void main(String arg[]) throws SocketException, InterruptedException, UnknownHostException {
		try {
			//MsgNormal message = new MsgNormal("ceci est un message",10);
			User sender = new User("mouloud_send","127.0.0.1");
			Controleur_Processor process = new Controleur_Processor (sender);
			Controller_reseau chat = new Controller_reseau(process, sender);
			chat.sendMsgNormal("ceci est un msg", 10, sender.getAddr());
			}
		catch (IOException e)
        {
          System.err.println("Exception:  " + e);
          e.printStackTrace();
        }
	}
}
