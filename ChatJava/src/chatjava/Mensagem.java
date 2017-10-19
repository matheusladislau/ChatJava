package chatjava;
import java.util.ArrayList;
public class Mensagem{
    //cores
    final String corReset="\u001B[0m";
    final String corVermelha="\u001B[31m";
    final String corAzul="\u001B[34m";
    //
    public void enviarParaTodos(ArrayList<ClienteEmissor> arrayCliente,String mensagem){
        mensagem=("["+new Tempo().getHoraMinutoAtual()+"] "+mensagem);
        for(int i=0; i<arrayCliente.size(); i++){
            
            arrayCliente.get(i).enviarMensagem(mensagem);
        }
    }
    
    public String mensagemErro(String erro){
        return(corVermelha+(erro)+corReset);
    } 
    
//    public void enviarparaTodos(ArrayList<Usuario> arrayUsuario,String mensagem)throws IOException{
//        for(int i=0; i<arrayUsuario.size(); i++){
//            PrintStream saida=new PrintStream(arrayUsuario.get(i).cliente.getOutputStream());
//            saida.print(mensagem);
//        }
//    }
//    
//    public void enviarMensagem(Socket cliente,String mensagem){
//        try{
//            PrintStream saida=new PrintStream(cliente.getOutputStream());
//            saida.println(mensagem);
//        }catch(IOException e){
//            System.out.println("Erro ao enviar mensagem por cliente emissor: "+e);
//        }
//    }
}
