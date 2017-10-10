package chatjava;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
public class Mensagem{
    
    public void enviarparaTodos(ArrayList<Usuario> arrayUsuario,String mensagem)throws IOException{
        for(int i=0; i<arrayUsuario.size(); i++){
            PrintStream saida=new PrintStream(arrayUsuario.get(i).cliente.getOutputStream());
            saida.print(mensagem);
        }
    }
    public void enviarMensagem(Socket cliente,String mensagem){
        try{
            PrintStream saida=new PrintStream(cliente.getOutputStream());
            saida.println(mensagem);
        }catch(IOException e){
            System.out.println("Erro ao enviar mensagem por cliente emissor: "+e);
        }
    }
}
