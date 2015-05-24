/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadnunsFx.CubieBoard2.Gpio;

import CadnunsFx.CubieBoard2.Gpio.GpioPin.GpioExceptionType;
import java.io.IOException;

/**
 *
 * @author Tiagop
 */
public class Gpio_PermissionFileException extends Exception {
    private GpioExceptionType type;
    private Exception parentException;
    public Gpio_PermissionFileException(GpioPin.GpioExceptionType type, Exception e){
        this.type = type;
        this.parentException = e;
    }

    private String ErrorType(GpioPin.GpioExceptionType type){
        switch(type){
            case Export:
                return "Não foi possivel exportar/importar o Pino | ";
            case SetDirection:
                return "Não foi possivel Configurar a Direção(Set Direction) do Pino | ";
            case SetValue:
                return "Não foi possivel Alterar o valor de saida (Set Value) do Pino | ";
            case GetValue:
                return "Não foi possivel Ler o valor de saida (Get Value) do Pino | ";
            case ArquivoNaoExiste:
                return "O arquivo Solicitado não existe | ";
            default : 
                return "Verificar StackTrace para analisar erro |";
        }
    }
    
    @Override
    public String toString(){        
        return ErrorType(type) + "\n\t"+parentException.toString();
    }
    
    @Override
    public String getLocalizedMessage(){
        return ErrorType(type) + "\n\t"+parentException.getLocalizedMessage();
    }
    
    @Override
    public String getMessage(){
        return ErrorType(type) + "\n\t"+parentException.getMessage();
    }
}
