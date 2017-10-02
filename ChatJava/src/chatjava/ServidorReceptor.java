package chatjava;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
public class ServidorReceptor extends Thread{
    int porta;
    ControleInterface controle;
    Usuario usuario;
    ArrayList<Usuario> arrayUsuario;
    //cores
    public static final String corReset="\u001B[0m";
    public static final String corVermelha="\u001B[31m";
    public static final String corAzul="\u001B[34m";
//CONSTR
//CONSTR  
    public ServidorReceptor(ControleInterface controle,ArrayList<Usuario> arrayUsuario,int porta,Usuario usuario){
        this.arrayUsuario=arrayUsuario;
        this.controle=controle;
        this.usuario=usuario;
        this.porta=porta;
    }
//
    @Override
    public void run(){
        try{
            ServerSocket servidor=new ServerSocket(porta);    
            System.out.println("Porta "+porta+" aberta!");
            Socket cliente=servidor.accept();
            System.out.println("Servidor Receptor: Recebendo conexão com o cliente "+   
                cliente.getInetAddress().getHostAddress()
                +" pela porta: "+this.porta);
            
            Scanner entrada=new Scanner(cliente.getInputStream());
             
             while(true){
                  while(entrada.hasNextLine()){
                    String mensagem="";
                    mensagem+=usuario.getNickName();
                    mensagem+=" ["+new Tempo().getHoraMinutoAtual()+"]:  "+entrada.nextLine();
                    controle.atualizarChat(mensagem);
                    //enviarparaTodos(mensagem);
                }
             }
            
        }catch(IOException e) {
            mensagemErro("Erro ao iniciar servidor receptor: "+e);
        }
    }
    
    public void enviarparaTodos(String mensagem)throws IOException{
        for(int i=0; i<arrayUsuario.size(); i++){
            PrintStream saida=new PrintStream(arrayUsuario.get(i).cliente.getOutputStream());
            saida.print(mensagem);
        }
    }
    public String mensagemErro(String erro){
        return(corVermelha+(erro)+corReset);
    } 
}
