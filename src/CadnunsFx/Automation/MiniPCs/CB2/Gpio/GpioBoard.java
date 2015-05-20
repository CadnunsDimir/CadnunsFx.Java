/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadnunsFx.Automation.MiniPCs.CB2.Gpio;

import java.util.ArrayList;

/**
 *
 * @author Tiagop
 */
public class GpioBoard {
    static ArrayList <GpioPin> listaGpioPins = new ArrayList();
    
    public static void NewPin(int numberPin, GpioPin.Direction direction)throws Exception{
        if(!HasThisPin(numberPin))
          listaGpioPins.add(new GpioPin(numberPin, direction));
        //else 
            //throw new Exception("Pino já importado!!!");
    }
    
    public static boolean HasThisPin(int numberPin){
        if(!listaGpioPins.isEmpty()){
            for(GpioPin pino : listaGpioPins){                
                if(pino.getNumberPin() == numberPin)  {
                    return true;
                }                    
            }
        }
        
        return false;
    }
}
