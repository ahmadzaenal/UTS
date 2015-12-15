/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientuts;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cancer
 */
class ThreadRead implements Runnable{
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream ous;
    private boolean done;
    
     public ThreadRead(Socket socket) throws IOException {
        this.socket = socket;
        this.ois = new ObjectInputStream(this.socket.getInputStream());
        this.ous = new ObjectOutputStream(this.socket.getOutputStream());
        this.done = false;
    }
     
      public void send(Message message) throws IOException {
        this.ous.writeObject(message);
        this.ous.flush();
        this.ous.reset();
    }

    public void stop() {
        this.done = true;
    }

    @Override
    public void run() {
        
         while(!this.done){
             try {
                 String response = ois.readLine();
                 System.out.println(response);
             } catch (IOException ex) {
                 Logger.getLogger(ThreadRead.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
    }
    
}
