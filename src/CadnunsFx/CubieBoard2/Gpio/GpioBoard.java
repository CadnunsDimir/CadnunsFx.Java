/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadnunsFx.CubieBoard2.Gpio;

import com.sun.javafx.Utils;
import java.util.ArrayList;

/**
 *
 * @author Tiagop
 */
public class GpioBoard {
    static ArrayList <GpioPin> listaGpioPins = new ArrayList<GpioPin>();
    protected static String GpioPath = "/sys/class/gpio/";
    public static void NewPin(int numberPin, GpioPin.Direction direction)throws Gpio_OpSystemNotCorrectException, Gpio_PermissionFileException{
        if(!HasThisPinOnApp(numberPin) && Utils.isUnix())
          listaGpioPins.add(new GpioPin(numberPin, direction));
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
        if(!listaGpioPins.isEmpty()){
            String[][] lista = new String[listaGpioPins.size()][2];
            for(int i=0; i < listaGpioPins.size();i++){                
              lista[i][0]= String.valueOf(listaGpioPins.get(i).getNumberPin());
            }
            return lista;
        }else
            return null;
    }
}
