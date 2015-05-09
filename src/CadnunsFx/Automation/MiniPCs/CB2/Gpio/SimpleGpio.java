/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadnunsFx.Automation.MiniPCs.CB2.Gpio;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;
import CadnunsFx.Arquivos.ManipulaArq;
/**
 *
 * @author Tiago Silva do Nasicmento
 */
public class SimpleGpio {
    /** 
     * Contante que guarda String que será usada para gerar comando RunTime(no caso, Leitura de dados em um pino gpio).
     * Deve ser usada ao inserir parametros "direction", nos metodos dessa classe
     */
    
    public static final String INPUT = "in";
    /**
     * 
     * Contante que guarda String que será usada para gerar comando RunTime(no caso, Escrita de dados em um pino gpio).
     * Deve ser usada ao inserir parametros "direction", nos metodos dessa classe
     */
    public static final String OUTPUT = "out";
    
    /**
     * Importando a classe que contém o mapeamento de Pins
     */
    //Pins Pins = new Pins();
    static ManipulaArq manFile = new ManipulaArq();
    
    
    /**
     * Usado para importar Pinos GPio na CB2, utilizando numero de referencia no Cubian OS
     * 
     * @param pin numero de referencia do pino a ser importado(relacionado as portas físicas no .fex file)
     * @param direction INPUT ou OUTPUT
     * @return 'false' se a operação não foi concluida com sucesso; 'true' se o pino foi importado corretamente
     */
    public static boolean importPin(int pin, String direction) throws IOException{
        int index = 0;
        boolean isFound = false;
        int port = 0;//se 1 pinsUSB, se 2 pinsSATA
        while(index < Pins.pinsUSB.length && !isFound){
            System.out.println("pino "+Pins.pinsUSB[index][0]);
            if(String.valueOf(pin).equals(Pins.pinsUSB[index][0])){
                isFound = true;
                port = 1;
            }
            else
                ++index;
        }
        
        if(!isFound)
            index = 0;
        
        while(index < Pins.pinsSATA.length && !isFound){
            System.out.println("pino "+Pins.pinsSATA[index][0]);
            if(String.valueOf(pin).equals(Pins.pinsSATA[index][0])){
                isFound = true;
                port = 2;
            }
            else
                ++index;
        }
        
        
        if(isFound){
            switch(port){
                case 1: 
                    //importPinSh(Pins.pinsUSB[index][0]);
                    importPinFile(Pins.pinsUSB[index][0]);
                    //setDirectionSh(Pins.pinsUSB[index][0], Pins.pinsUSB[index][1], direction);
                    setDirectionFile(Pins.pinsUSB[index][0], Pins.pinsUSB[index][1], direction);
                    
                    return true;
                case 2:                    
                    //importPinSh(Pins.pinsSATA[index][0]);
                    importPinFile(Pins.pinsSATA[index][0]);
                    //setDirectionSh(Pins.pinsSATA[index][0], Pins.pinsSATA[index][1], direction);
                    setDirectionFile(Pins.pinsSATA[index][0], Pins.pinsSATA[index][1], direction);
                    return true;
                default:
                    System.out.println("Erro ao acessar o pino "+index+" do PORT "+port+" : "+Pins.port[port]);
                    return false;
            }            
        }
        else
        return false;
    }
    /**
     * Função não implementada
     * 
     * @param fisicalPin posição física do pino na CB2
     * @param direction idem ao Método importPin()
     * @return 'false'
     */
    public boolean importPinByFisical(int fisicalPin, String direction){        
        System.out.println("NOT IMPLEMENTED");
        return false;
    }
    
    private static void importPinSh(String pin) throws IOException{
        String comando = "echo "+pin+" > /sys/class/gpio/export";
        System.out.println(comando);
        executeCommand(comando);        
    }
    
    private static void importPinFile(String pin) throws IOException{
        String file = "/sys/class/gpio/export";
        manFile.escreve(pin, file);
    }
    
    private static void setDirectionSh(String pin, String pinName,String direction)throws IOException {
        String comando = "echo "+direction+" >  /sys/class/gpio/gpio"+pin+"_"+pinName+"/direction";
        System.out.println(comando);
        System.out.println("aguarde 1 seg...");
        executeCommand("sleep 1");
        executeCommand(comando);             
    }
    
    private static void setDirectionFile(String pin, String pinName,String direction)throws IOException {
        String file = "/sys/class/gpio/gpio"+pin+"_"+pinName+"/direction";
        System.out.println("aguarde 1 seg...");
        executeCommand("sleep 1");
        manFile.escreve(direction, file);
    }
    
    private static final Logger log = Logger.getLogger(SimpleGpio.class.getName());  
    public static void executeCommand(final String command) throws IOException {
        final ArrayList<String> commands = new ArrayList<String>();
        commands.add("/bin/bash");
        commands.add("-c");
        commands.add(command);
        BufferedReader br = null;
        try {
            final ProcessBuilder p = new ProcessBuilder(commands);
            final Process process = p.start();
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Retorno do comando = [" + line + "]");
            }
        } catch (IOException ioe) {
            log.severe("Erro ao executar comando shell" + ioe.getMessage());
            throw ioe;
        } finally {
            secureClose(br);
        }
    }
    
    private static void secureClose(final Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException ex) {
            log.severe("Erro = " + ex.getMessage());
        }
    }


    

}
