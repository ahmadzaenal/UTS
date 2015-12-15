/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientuts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.*;

/**
 *
 * @author ahmad
 */
public class ClientUTS {
    private static Socket socket;
    private static PrintWriter os;
    private static BufferedReader is;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         String out;
         Scanner rd=new Scanner(System.in);
        
    try {
            byte[] buf = new byte[10];
            
            socket = new Socket("10.151.43.147", 6666);
            
            os = new PrintWriter( socket.getOutputStream(), true); 
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        
           
        String response,nama,a;
        //response = is.readLine();
        //membaca response header
        while ((response = is.readLine())!=null){
            System.out.println(response);
             a=response.split(" ")[8];
           
             if(a.equalsIgnoreCase("USERNAME:NRP\\n")){
                 System.out.println("Input NRP saja:\n");
                 nama=rd.nextLine();
                 os.write("Username:"+nama+"\n");
                 os.flush();
                  
                 //os.write("Username:5113100043\n");
                 //os.flush();
                 
                 while ((response = is.readLine())!=null){
                    
                    System.out.println(response);
                    if((response.split(" ").length)<4){
                     a=response.split(":")[0];
                    
                     if(a.equalsIgnoreCase("Length")){
                         
                       
                         response = is.readLine();
                         os.write("Hash:"+response+"\n");
                         os.flush();
                        
                             
                         
                         
                     }
                    }else{
                        a=response.split(" ")[3];
                    }
                     
                     
                     if(a.equalsIgnoreCase("=")){
                         String num1,num2,notasi,hsl;
                         int nm1,nm2,hasil;
                         num1=response.split(" ")[0];
                         notasi=response.split(" ")[1];
                         num2=response.split(" ")[2];
                         nm1 = Integer.parseInt(num1);
                         nm2 = Integer.parseInt(num2);
                         
                         if(notasi.equalsIgnoreCase("+")){
                             hasil=(nm1+nm2);
                            
                             hsl="Result:"+hasil+"\n";
                             os.write(hsl);
                             os.flush();
                             
                         }else if(notasi.equalsIgnoreCase("-")){
                              hasil=(nm1-nm2);
                              
                             hsl="Result:"+hasil+"\n";
                             os.write(hsl);
                             os.flush();
                         }else if(notasi.equalsIgnoreCase("x")){
                              hasil=(nm1*nm2);
                             
                              hsl="Result:"+hasil+"\n";
                              os.write(hsl);
                              os.flush();
                         }else if(notasi.equalsIgnoreCase("mod")){
                            hasil=nm1%nm2;
                            System.out.println(hasil);
                            hsl="Result:"+hasil+"\n";
                            os.write(hsl);
                            os.flush();
                            
                          }
                         
                           
                     }else continue;
                        //System.out.println("cek");
                    }
                 
             }
        }
       
         
           
        } catch (IOException ex) {
            Logger.getLogger(ClientUTS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        
    }
    
}
