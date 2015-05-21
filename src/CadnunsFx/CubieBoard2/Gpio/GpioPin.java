/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadnunsFx.CubieBoard2.Gpio;

/**
 *
 * @author Tiagop
 */
public class GpioPin {
    
    private int numberPin;
    
    public GpioPin(int numberPin, Direction direction){
        this.numberPin = numberPin;
        
    }

    /**
     * @return the numberPin
     */
    public int getNumberPin() {
        return numberPin;
    }
    
    
    
    //----------------Definição das contantes ou enum abaixo dessa linha ----------------
    public enum Direction{
        in,out
    }
    
    public enum Value{
        Value_on, Value_off
    }
}
