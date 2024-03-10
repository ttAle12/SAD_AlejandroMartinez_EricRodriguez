/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @author alejandro
 */
public class EditableBufferedReader extends BufferedReader{
    
    
   private Line linea;

  //  public EditableBufferedReader(InputStreamReader in){
    //    super(in);
     //   this.linea = new Line();
    //}

    
    
    public void setRaw() throws IOException{
        try{
            String[] coman={"/bin/sh", "-c", "stty -echo raw </dev/tty"}; //camviamos de modo cooked a modo Raw
            Runtime.getRuntime().exec(coman).waitFor(); //ejecutamos coman y esperamos a que acabe
        }catch(InterruptedException e){
        e.printStackTrace(); //?
        }
    }
    
    public void unsetRaw() throws IOException{
        try{
            String[] coman={"/bin/sh", "-c", "stty echo cooked </dev/tty"};//pasamos a modo cooked
            Runtime.getRuntime().exec(coman).waitFor();
        }catch(InterruptedException e){
        e.printStackTrace(); //mirar
            
        }
    }
    
     public int read() throws IOException {
        int input = 0;

        //Llegim el caràcter amb la funció main de BufferedReader
        input = super.read();
        //Mirem si els caràcters enviats són ^[[
        if(input == teclas.ESC){
            input = super.read();
            
            if(input == teclas.CORXET){
                input = super.read();
                
                switch(input){
                    
                    case teclas.DRETA:
                        return teclas.FLETXA_DRETA;
                        
                    case teclas.ESQUERRA:
                        return teclas.FLETXA_ESQUERRA;
                        
                    case teclas.INICI:
                        return teclas.INICI_RETURN;
                        
                    case teclas.FINAL:
                        return teclas.FINAL_RETURN;
                        
                    case teclas.INSERT:
                        //Mirem si el caràcter enviat és ~
                        if(super.read() == teclas.RAYA){
                            return teclas.INSERT_RETURN;
                        }
                        return -1;
                    case teclas.SUPRIMIR:
                        //Mirem si el caràcter enviat és ~
                        if(super.read() == teclas.RAYA){
                            return teclas.SUPRIMIR_RETURN;
                        }
                        return -1;
                    default:
                        //Si l'usuari prem ^[[ i un caràcter desconegut no retornem
                        return -1;
                }
            }
        //Si s'introdueix un caràcter comú el retornem
        } else {
            return input;
        }
        //Per si hi hagués algun error o cas no contemplat
        return -1;
    }
     
     
     
     public String readLine()throws IOException{
     
       
       int input=0;
       
       this.setRaw();
       
     
       while((input=this.read()) !=teclas.ENTER)
       {
           switch(input)
           {
               case teclas.FLETXA_DRETA:
                    linea.derecha();
                    System.out.print("\033[C");
                    break;
                    
               case teclas.FLETXA_ESQUERRA:
                    linea.izquierda();
                     System.out.print("\033[D");
                    break;
                    
               case teclas.INICI_RETURN:
                  int posActual=linea.home();
                   System.out.print("\033[" + posActual + "D");
                   break;
               
               case teclas.FINAL_RETURN:
                   int posActual1=linea.size()-linea.end();
                  
                 
                   System.out.print("\033[" +(posActual1) + "C");
                   break;
                   
               case teclas.INSERT_RETURN:
                   linea.ins();
                   System.out.print("\033[4l");
                   
                   break;
                   
               case teclas.SUPRIMIR_RETURN:
                   linea.borrar();
                   System.out.print("\033[P");
                   break;
                
               case teclas.BKSP_RETURN:
                    linea.bksp();
                      System.out.print("\033[D");
                       
                        System.out.print("\033[P");
                    break;
                   
                    
               default:
                   
                 linea.add((char)input);
                 System.out.println((char)input);
           }
             
       }
       
             this.unsetRaw();
             return this.linea.toString();
         
     }

}
