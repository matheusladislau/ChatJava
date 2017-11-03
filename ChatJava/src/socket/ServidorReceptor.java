package socket;
import chatjava.ControleInterface;
import chatjava.Mensagem;
import chatjava.Usuario;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
public class ServidorReceptor extends Thread{ //rever metodo enviar para todos
    private int porta;
    private ControleInterface controle;
    private Usuario usuario;
    private ArrayList<Usuario> arrayUsuario;
//CONSTR
    public ServidorReceptor(ControleInterface controle,ArrayList<Usuario> arrayUsuario,int porta,Usuario usuario){
        this.controle=controle;
        this.arrayUsuario=arrayUsuario;
        this.porta=porta;
        this.usuario=usuario;
    }
//
    @Override
    public void run(){
        try{
            ServerSocket servidor=new ServerSocket(porta);    
            System.out.println("Servidor Receptor: Porta "+porta+" aberta!");
            Socket cliente=servidor.accept();
            System.out.println("Servidor Receptor: Recebendo conexão com o cliente "+   
                cliente.getInetAddress().getHostAddress()
                +" pela porta: "+this.porta);
            
            Scanner entrada=new Scanner(cliente.getInputStream());
             
            while(true){
                    //enquanto houver mensagens
                    while(entrada.hasNextLine()){
                        //remontar mensagem
                        String mensagem="";
                        mensagem+=entrada.nextLine();
                        //enviar
                        new Mensagem(usuario,mensagem).enviar(arrayUsuario,mensagem);
                }
            }
        }catch(IOException e) {
            new Mensagem().mensagemErro("Erro ao iniciar servidor receptor: "+e);
        }
    }
}
