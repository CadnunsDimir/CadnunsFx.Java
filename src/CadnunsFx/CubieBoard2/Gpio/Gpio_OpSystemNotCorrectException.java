/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package CadnunsFx.CubieBoard2.Gpio;

import CadnunsFx.Tools.Uteis;

/**
 *
 * @author Tiago Silva do Nascimento
 */
public class Gpio_OpSystemNotCorrectException extends Exception{
    String erro = "GPIO INCORRECT OPERATION SYSTEM";
    String msg = " : Para manipular a Gpio, é necessário utilizar uma placa que "
                +"execute o Sistema Operational Linux, como a Cubieboard ou Raspberry Pi\n\t"
                +"Sistema Operacional : "+Uteis.SO_Name();
        
    @Override
    public String toString(){        
        return erro + msg;
    }
    
    @Override
    public String getLocalizedMessage(){
        return toString()+"\n\t"+ super.getLocalizedMessage();
    }
    
    public String getMessage(){
        return toString()+"\n\t"+super.getMessage();
    }
}
