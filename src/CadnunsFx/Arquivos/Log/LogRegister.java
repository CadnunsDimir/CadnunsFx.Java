/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadnunsFx.Arquivos.Log;

import CadnunsFx.Tools.Uteis;
import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.*;

/**
 *
 * @author Tiagop
 */
public class LogRegister{
    String fileName;
    File arquivo;
    FileWriter cursor;
    BufferedWriter bufferCursor;
    FileReader leitor;
    BufferedReader bufferLeitor;
    boolean isInit = true;
    public LogRegister(String fileName, String AppName){
        this.fileName = fileName;
        RegistraEventos("inicializada aplicação \'" + AppName+"\'!!");
    }
    
    public void RegistraEventos(String eventos){
        
        try {
            arquivo = new File(fileName);
            leitor = new FileReader(arquivo);
            bufferLeitor = new BufferedReader(leitor);
            Vector texto = new Vector();
            
            while(bufferLeitor.ready()){
                texto.add(bufferLeitor.readLine());
            }
            cursor = new FileWriter(arquivo);
            bufferCursor = new BufferedWriter(cursor);
            for(int i=0; i < texto.size();i++){
                bufferCursor.write(texto.get(i).toString());
                bufferCursor.newLine();                
            }
            if(isInit){             
             bufferCursor.write("--------------------------------------------------------------------------------------\n");
             isInit = false;
            }
            bufferCursor.write(Uteis.DataAtual()+" | " +eventos);
            bufferCursor.close();
            bufferLeitor.close();
        } catch (FileNotFoundException ex) {
            try {
                arquivo.createNewFile();
                RegistraEventos(eventos);
                RegistraEventos(ex.getMessage());
            } catch (IOException ex1) {
                System.exit(0);
            }
            Logger.getLogger(LogRegister.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ioe){
            
        }
        
    }
    
    public void RegistraException(String Exception){
        RegistraEventos("[ERRO]"+Exception);
    }
}
