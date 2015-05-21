/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadnunsFx.Servidor;

import java.net.*;
import java.io.*;

public class HomeControl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int porta = 12345;
        ServerSocket servidor = new ServerSocket(porta);
        Socket socket;

        while (true) {
            System.out.println("Servidor rodando na porta " + porta + ".\nSe Desejar fechar aplicato, pressione ctrl+c \n \tAguardando clientes.");
            socket = servidor.accept();

            //após receber conexao
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            String dados = entrada.readUTF();
            String enviar = null;
            System.out.println(dados);

            if (dados.trim().equals("update")) {
                enviar = "pin1=0;pin2=1;pin3=0;pin1=0;pin2=1;pin3=0;pin1=0;pin2=1;pin3=0;pin1=0;pin2=1;pin3=0;pin1=0;pin2=1;pin3=0;pin1=0;pin2=1;pin3=0";
            } else {
                enviar = "Servidor recebeu : " + dados;
            }

            saida.writeBytes(enviar);
            saida.flush();

            System.out.println("Mensagem Enviada!");
            System.out.println("fechando conexão");

            saida.close();
            socket.close();
        }
    }

}
