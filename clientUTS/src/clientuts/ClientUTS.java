/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientuts;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmad
 */
public class ClientUTS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         String out;
        
    try {
            byte[] buf = new byte[10];
            
            Socket socket = new Socket("10.151.34.155", 6666);
            
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
                        
           // os.write("Username:5113100043\n".getBytes());
            //os.flush();
            int len;
         while (true){
            while(true) {
                buf = new byte[10];
                len = is.read(buf);
                if(len == -666) {
                    break;
                }
                
                System.out.print(new String(buf));
            }
            //Scanner rd=new Scanner(System.in);
            //out=rd.nextLine(); 
            //os.write(out.getBytes());
             os.write("Username:5113100043\n".getBytes());
            os.flush();
            System.out.print("cek");
            os.close();
            is.close();
            socket.close();
         }  
        } catch (IOException ex) {
            Logger.getLogger(ClientUTS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
