package socket;
import chatjava.ControleInterface;
import chatjava.Mensagem;
import chatjava.Tempo;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
public class ClienteNegociador extends Thread{
    int porta=2000;
    String ip;
    String nome;
    ControleInterface controle;
    ClienteEmissor clienteE;
//CONSTR
    public ClienteNegociador(ClienteEmissor cliente,ControleInterface controle,String ip,String nome){
        this.clienteE=cliente;
        this.controle=controle;
        this.ip=ip;
        this.nome=nome;
    }
//
    @Override
    public void run(){
        try{
            Socket cliente=new Socket(this.ip,this.porta);
            System.out.println("Cliente negociador: conectado ao servidor!");
            controle.atualizarChat(new Tempo().getHoraMinutoAtual()+": você se conectou ao servidor.");
            PrintStream saida=new PrintStream(cliente.getOutputStream());
            Scanner entrada=new Scanner(cliente.getInputStream());

            saida.println(nome);
            int portaLiberada=(Integer.parseInt(entrada.nextLine()));
        //CRIA DUAS PORTAS    
            //new ClienteEmissor
            this.clienteE.configurar(this.ip,portaLiberada);
            new ClienteReceptor(this.controle,portaLiberada+1).start();
            
            saida.close();
            cliente.close();
        }catch(IOException e){
            new Mensagem().mensagemErro("Erro ao criar socket de cliente negociador"+e);
        }
    }
}
