package CadnunsFx.Utils;

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
public class Utils {
    
    public static void LimparSeString(JTextField campo){
        try{
            int numero  = Integer.parseInt(campo.getText());
        }catch(NumberFormatException e)
        {
            campo.setText("");
        }
    }
}
