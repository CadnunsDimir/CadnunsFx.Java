/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadnunsFx.CubieBoard2.Gpio;

import CadnunsFx.Tools.Uteis;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiagop
 */
public class GpioPin {
    
    private final int numberPin;
    private Direction sentido;
    private String absolutePinFolder;
    public GpioPin(int numberPin, Direction direction) throws Gpio_PermissionFileException{
        this.numberPin = numberPin;
        if(this.IsImportedOnCubie())
            this.ResetPinOnCubie();
        this.ImportPinOnCubie();
        this.SetDirection(direction);
        this.sentido = direction;
    }

    /**
     * @return the numberPin
     */
    private void ResetPinOnCubie() throws Gpio_PermissionFileException{
        File arquivo = new File(GpioBoard.GpioPath+"unexport");   
        Uteis.c("Tentando escrever no arquivo"+arquivo.getAbsolutePath());
        if(arquivo.exists()){
            FileWriter fw;
            try {
                fw = new FileWriter(arquivo);
//                BufferedWriter bufferCursor = new BufferedWriter(fw);
//                bufferCursor.write(numberPin);
//                bufferCursor.close();
                //String.valueOf(numberPin);
                fw.write(String.valueOf(numberPin));
                fw.flush();
                fw.close();
            } catch (Exception ex) {
                throw new Gpio_PermissionFileException(GpioExceptionType.Export, ex);
            }                    
        }else{
            throw new Gpio_PermissionFileException(
                    GpioExceptionType.ArquivoNaoExiste, 
                    new FileNotFoundException("nome do arquivo :"+arquivo.getAbsolutePath()));
        }         
    }
    
    private boolean IsImportedOnCubie(){
        String[] pasta = new File(GpioBoard.GpioPath).list();
        String nomePasta = "gpio"+numberPin;
        File arquivo = null;
        for(String file : pasta){
            if(file.contains(nomePasta)) {
                return true;
            }
        }
        return false;
    }
    
    private void ImportPinOnCubie() throws Gpio_PermissionFileException{
        File arquivo = new File(GpioBoard.GpioPath+"export");   
        Uteis.c("Tentando escrever no arquivo"+arquivo.getAbsolutePath());
        if(arquivo.exists()){
            FileWriter fw;
            try {
                fw = new FileWriter(arquivo);
//                BufferedWriter bufferCursor = new BufferedWriter(fw);
//                bufferCursor.write(numberPin);
//                bufferCursor.close();
                //String.valueOf(numberPin);
                fw.write(String.valueOf(numberPin));
                fw.flush();
                fw.close();
            } catch (Exception ex) {
                throw new Gpio_PermissionFileException(GpioExceptionType.Export, ex);
            }                    
        }else{
            throw new Gpio_PermissionFileException(
                    GpioExceptionType.ArquivoNaoExiste, 
                    new FileNotFoundException("nome do arquivo :"+arquivo.getAbsolutePath()));
        }         
    }
    
    private void SetDirection(Direction direcao) throws Gpio_PermissionFileException{
        //String caminho= GpioBoard.GpioPath+"gpio"+numberPin+"*/";
        String[] pasta = new File(GpioBoard.GpioPath).list();
        String nomePasta = "gpio"+numberPin;
        File arquivo = null;
        for(String file : pasta){
            if(file.contains(nomePasta)) {
                absolutePinFolder = GpioBoard.GpioPath+file;
                arquivo =  new File(absolutePinFolder+"/direction");
            }
        }
        
        Uteis.c("Tentando escrever no arquivo "+arquivo.getAbsolutePath());
        if(arquivo.exists()){
            FileWriter fw;
            try {
                fw = new FileWriter(arquivo);
                fw.write(direcao.toString());
                fw.close();
            } catch (Exception ex) {
                throw new Gpio_PermissionFileException(GpioExceptionType.SetDirection, ex);
            }                    
        }else{
            throw new Gpio_PermissionFileException(
                    GpioExceptionType.ArquivoNaoExiste, 
                    new FileNotFoundException("nome do arquivo :"+arquivo.getAbsolutePath()));
        }        
    }
    
    public void SetValue(Value v) throws Gpio_PermissionFileException{
        if(sentido == Direction.out){
            try {
                FileWriter fw = new FileWriter(absolutePinFolder+"/value");
                fw.write((v == Value.Value_on)?"0":"1");
                fw.close();
            } catch (Exception ex) {
                throw new Gpio_PermissionFileException(GpioExceptionType.SetDirection, ex);
            }
        }else{
            throw new Gpio_PermissionFileException(GpioExceptionType.SetValue,
                    new Exception("O Pino "+numberPin+" não está configurado como uma saída padrão nessa aplicação.\n\tSentido(direction) configurado: "+sentido.name()));
        }                
    }
    
    public int GetValue() throws Gpio_PermissionFileException{
        int retorno;
        if(sentido == Direction.in){
            try {
                FileReader fr = new FileReader(absolutePinFolder+"/value");
                retorno = fr.read();
                fr.close();
                return retorno;
            } catch (Exception ex) {
                throw new Gpio_PermissionFileException(GpioExceptionType.GetValue, ex);
            }
        }else{
            throw new Gpio_PermissionFileException(GpioExceptionType.GetValue,
                    new Exception("O Pino "+numberPin+" não está configurado como uma entrada padrão nessa aplicação.\n\tSentido(direction) configurado: "+sentido.name()));
        }                
    }    
    
    public int getNumberPin() {
        return numberPin;
    }

    /**
     * @return the sentido
     */
    public Direction getSentido() {
        
        return sentido;
    }

    /**
     * @param sentido the sentido to set
     */
    public void setSentido(Direction sentido) throws Gpio_PermissionFileException {        
        SetDirection(sentido);
        this.sentido = sentido;
    }
    
    
    //----------------Definição das contantes ou enum abaixo dessa linha ----------------
    public enum Direction{
        in,out
    }
    
    public enum Value{
        Value_off, Value_on 
    }
    
    static enum GpioExceptionType{
        Export,SetDirection,SetValue,ArquivoNaoExiste,GetValue            
    }
    
    
}


