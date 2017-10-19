package chatjava;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class ClienteReceptor extends Thread{
    int porta;
    String ip;
    ControleInterface controle;
//CONSTR
    public ClienteReceptor(ControleInterface controle,int porta){
        this.controle=controle;
        this.porta=porta;
    }
//
    @Override
    public void run(){
        try{
            ServerSocket servidor=new ServerSocket(this.porta);    
            System.out.println("Cliente Receptor: Porta "+porta+" aberta!");
            Socket cliente=servidor.accept();
            System.out.println("Cliente Receptor: Recebendo conexão com o cliente "+   
                cliente.getInetAddress().getHostAddress()
                +" pela porta: "+this.porta);
            
            Scanner entrada=new Scanner(cliente.getInputStream());
             //SET SOCKET CLIENTE
             while(true){
                  while(entrada.hasNextLine()){
                    String mensagem="";
                    mensagem+=entrada.nextLine();
                    controle.atualizarChat(mensagem);
                }
             }
        }catch(IOException e) {
            new Mensagem().mensagemErro("Erro ao iniciar servidor receptor: "+e);
        }
    }
}
