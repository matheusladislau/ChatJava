package chatjava;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
public class ServidorReceptor extends Thread{ //rever metodo enviar para todos
    int porta;
    ControleInterface controle;
    Usuario usuario;
    ArrayList<Usuario> arrayUsuario;
//cores
    final String corReset="\u001B[0m";
    final String corVermelha="\u001B[31m";
    final String corAzul="\u001B[34m";
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
                    mensagem+=usuario.getNome();
                    mensagem+=" ["+new Tempo().getHoraMinutoAtual()+"]:  "+entrada.nextLine();
                    //atualizar interface grafica
                    controle.atualizarChat(mensagem);
                    //envia paratodos outros usuários
                    enviarparaTodos(arrayUsuario,mensagem); 
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
    public void enviarparaTodos(ArrayList<Usuario> arrayUsuario,String mensagem)throws IOException{
        if(arrayUsuario.size()>0){
        /*for(int i=0; i<arrayUsuario.size(); i++){    
            PrintStream saida=new PrintStream(arrayUsuario.get(1).getCliente().getOutputStream());
            saida.print(mensagem);
        }
        */
            for(int i=0; i<arrayUsuario.size(); i++){
                System.out.println("nome: "+arrayUsuario.get(i).getNome());
                arrayUsuario.get(i).clieEmiss.enviarMensagem(mensagem);
            }
        }
    }
    public String mensagemErro(String erro){
        return(corVermelha+(erro)+corReset);
    } 
}
