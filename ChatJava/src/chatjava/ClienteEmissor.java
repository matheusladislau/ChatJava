package chatjava;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
public class ClienteEmissor{
    int porta;
    String ip;
    Socket cliente;
//cores
    final String corReset="\u001B[0m";
    final String corVermelha="\u001B[31m";
    final String corAzul="\u001B[34m";
//CONSTR
    public ClienteEmissor(String ip,int porta) throws IOException{
        this.ip=ip;
        this.porta=porta;
        this.cliente=new Socket(this.ip,this.porta);
        System.out.println("Cliente emissor: conectado ao servidor pela porta "+this.porta);
    }
    public void enviarMensagem(String mensagem){
        try{
            PrintStream saida=new PrintStream(cliente.getOutputStream());
            saida.println(mensagem);
        }catch(IOException e){
            System.out.println("Erro ao enviar mensagem por cliente emissor: "+e);
        }
    }
}
