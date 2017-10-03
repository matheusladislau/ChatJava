package chatjava;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class ClienteReceptor extends Thread{
    int porta;
    String ip;
    ControleInterface controle;
//cores
    final String corReset="\u001B[0m";
    final String corVermelha="\u001B[31m";
    final String corAzul="\u001B[34m";
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
            mensagemErro("Erro ao iniciar servidor receptor: "+e);
        }
    }
    /*
    public void enviarparaTodos(String mensagem)throws IOException{
        for(int i=0; i<arrayUsuario.size(); i++){
            PrintStream saida=new PrintStream(arrayUsuario.get(i).cliente.getOutputStream());
            saida.print(mensagem);
        }
    }
    */
    public String mensagemErro(String erro){
        return(corVermelha+(erro)+corReset);
    } 
}
