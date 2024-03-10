/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad1;
import java.util.ArrayList;
/**
 *
 * @author alejandro
 */
public class Line {
    
    private ArrayList<Character>linea;
    private int posCursor;
    private boolean insertar;
    
    
    public Line(){
        
        this.linea=new ArrayList<Character>();
        this.posCursor=0;
        this.insertar=true;
    }
  
    
    
    
 public void derecha(){
     
     if(this.posCursor<this.linea.size())
     {
         this.posCursor++;
     }
     
 }
 
 public void izquierda(){
     
     if(this.posCursor>0)
     {
         this.posCursor--;
     }
     
 }
 
 public void borrar()
 {
    if(this.posCursor!=this.linea.size())
    {
       this.linea.remove(this.posCursor);
       
    }
     
 }
 
 public void bksp()
 {
    if(this.posCursor!=0)
    {
       this.linea.remove(this.posCursor-1);
       
    }
     
 }
 
 
 
 public int home()
 {
     int posActual=this.posCursor;
     this.posCursor=0;
     
     return posActual;
     
 }
 
public int end()
{
    int posActual=this.posCursor;
    this.posCursor=this.linea.size();
    return posActual;
}

public void ins()
{
    if(this.insertar==true)
    {
        this.insertar=false;
    }
    else{
        this.insertar=true;
    }
}


public void add(char c)
{
    if(this.insertar==true)
    {
    this.linea.add(posCursor, c);
    }
    else
    {
       if(this.posCursor<this.linea.size())
       {
           this.linea.add(posCursor, c);
           this.derecha();
           
          
           this.borrar();
       }
    }    
   
   
}

public String toString1()
{
    StringBuilder sb = new StringBuilder();
        for (Character c : linea) {
            sb.append(c);
        }
        return sb.toString();
        
    }

public int size()
{
    return this.linea.size();
}
    
}

 
 

