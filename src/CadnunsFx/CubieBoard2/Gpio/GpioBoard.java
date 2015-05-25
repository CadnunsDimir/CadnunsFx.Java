/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadnunsFx.CubieBoard2.Gpio;

import com.sun.javafx.Utils;
import java.util.ArrayList;
import CadnunsFx.Tools.Uteis;
/**
 *
 * @author Tiagop
 */
public class GpioBoard {
    static ArrayList <GpioPin> listaGpioPins = new ArrayList<GpioPin>();
    protected static String GpioPath = "/sys/class/gpio/";
    public static void NewPin(int numberPin, GpioPin.Direction direction)throws Gpio_OpSystemNotCorrectException, Gpio_PermissionFileException{
        if(!HasThisPinOnApp(numberPin) && Utils.isUnix()){
            Uteis.c("não tenho o pino e é linux");
          listaGpioPins.add(new GpioPin(numberPin, direction));
        }
        else if(!Utils.isUnix())
            throw new Gpio_OpSystemNotCorrectException();        
    }
    
    public static boolean HasThisPinOnApp(int numberPin){
        if(!listaGpioPins.isEmpty()){
            for(GpioPin pino : listaGpioPins){                
                if(pino.getNumberPin() == numberPin)  {
                    return true;
                }                    
            }
        }        
        return false;
    }
    public static String[][] ListarPins(){
        return ListarPins(null);
    }
    private static String[][] ListarPins(GpioPin.Direction dir){        
        if(!listaGpioPins.isEmpty()){
            String[][] lista = new String[listaGpioPins.size()][2];
            
            for(int i=0; i < listaGpioPins.size();i++){
                if(dir.equals(null) || dir.equals(listaGpioPins.get(i).getSentido())){
                    lista[i][0]= String.valueOf(listaGpioPins.get(i).getNumberPin());
                    lista[i][1]= listaGpioPins.get(i).getSentido().name();
                }              
            }
            return lista;
        }else
            return null;
    }
    public static ArrayList<String[]> ListarPinsByDirection(GpioPin.Direction dir) throws Exception{
       ArrayList <String[]> lista = new ArrayList<String[]>();       
       String[][] pinos = ListarPins(dir);
       if(!pinos.equals(null)){
            for(String[] pino : pinos){
                if(!pino.equals(null)){
                    lista.add(pino);
                }
            }
            return lista;
       }else
           throw new Exception("A lista de pinos está vazia");       
    }
    public static GpioPin get(int numberPin) throws Exception{
        if(!listaGpioPins.isEmpty()){
            for(GpioPin pino : listaGpioPins){                
                if(pino.getNumberPin() == numberPin)  {
                    return pino;
                }                    
            }
        }else{
            throw new Exception("A lista de pinos está vazia");
        }return null;
    }
}
