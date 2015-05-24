package CadnunsFx.Tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tiagop
 */
public class Uteis {
    
    public static void LimparSeString(JTextField campo){
        try{
            int numero  = Integer.parseInt(campo.getText());
        }catch(NumberFormatException e)
        {
            campo.setText("");
        }
    }
    
    public static void PropriedadesSistema(){        
        System.getProperties().list(System.out);
    }
    
    public static String SO_Name(){
        return System.getProperties().get("os.name").toString();
    }
    
    public static String DataAtual() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    /**
     *escreve na saida de sistema   |   equivale ao método System.out.println()
     * @param l a mensagem que se deseja escreve na saida padrão da aplicação(console)
     */
    public static void c(String l){
        System.out.println(l);
    }

   
}
