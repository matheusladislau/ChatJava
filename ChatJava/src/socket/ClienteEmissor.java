package socket;
import chatjava.Mensagem;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
public class ClienteEmissor{
    private int porta;
    private String ip;
    private Socket cliente;
//CONSTR
    public ClienteEmissor(){
    }
    
    public ClienteEmissor(String ip,int porta) throws IOException{
        this.ip=ip;
        this.porta=porta;
        this.cliente=new Socket(this.ip,this.porta);
        System.out.println("Cliente emissor: conectado ao servidor pela porta "+this.porta);
    }
    
    public void configurar(String ip,int porta) throws IOException{
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
            new Mensagem().mensagemErro("Erro ao enviar mensagem por cliente emissor: "+e);
        }
    }
    
}
