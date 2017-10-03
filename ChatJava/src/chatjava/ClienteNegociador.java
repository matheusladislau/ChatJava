package chatjava;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
public class ClienteNegociador extends Thread{
    int porta=2000;
    String ip;
    String nome;
    ControleInterface controle;
//cores
    final String corReset="\u001B[0m";
    final String corVermelha="\u001B[31m";
    final String corAzul="\u001B[34m";
//
    public ClienteNegociador(ControleInterface controle,String ip,String nome){
        this.controle=controle;
        this.ip=ip;
        this.nome=nome;
    }

    @Override
    public void run(){
        try{
            Socket cliente=new Socket(this.ip,this.porta);
            System.out.println("Cliente negociador: conectado ao servidor!");

            PrintStream saida=new PrintStream(cliente.getOutputStream());
            Scanner entrada=new Scanner(cliente.getInputStream());

            saida.println(nome);
            int portaLiberada=(Integer.parseInt(entrada.nextLine()));
        //CRIA DUAS PORTAS    
            //new ClienteEmissor
            new ClienteEmissor(this.ip,portaLiberada);
            new ClienteReceptor(this.controle,portaLiberada+1).start();
            
            saida.close();
            
        }catch(IOException e){
            mensagemErro("Erro ao criar socket de cliente negociador"+e);
        }
    }
    public String mensagemErro(String erro){
        return(corVermelha+(erro)+corReset);
    } 
}
