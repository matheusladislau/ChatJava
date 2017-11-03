package chatjava;
import socket.ClienteEmissor;
import java.util.ArrayList;
public class Mensagem{
    //cores
    final String corReset="\u001B[0m";
    final String corVermelha="\u001B[31m";
    final String corAzul="\u001B[34m";
    //
    String mensagem;
    String destinatario;
    
    public Mensagem(){
        
    }
    
    public void enviarParaTodos(ArrayList<ClienteEmissor> arrayCliente,String mensagem){
//        mensagem=("["+new Tempo().getHoraCompletaAtual()+"] "+mensagem);
        for(int i=0; i<arrayCliente.size(); i++){
            verificarNome(mensagem);
            arrayCliente.get(i).enviarMensagem(new CriptografiaRSA().cifrar(mensagem));
//            arrayCliente.get(i).enviarMensagem(mensagem);
//            new GerenciadorBD().inserirMensagem(mensagem); funcionando
        }
//        String crip=new CriptografiaRSA().cifrar(mensagem);
//        String decrip=new CriptografiaRSA().decifrar(crip);
//        System.out.println("mensagem: "+mensagem);
//        System.out.println("Criptografada: "+crip);
//        System.out.println("Decrip: "+decrip);
    }   
    public String mensagemErro(String erro){
        return(corVermelha+(erro)+corReset);
    } 
    
    public void verificarNome(String mensagem){
        if(mensagem.contains("@")){
            int i=0;
            
            while(i<mensagem.length()){
//                System.out.println(i+":"+mensagem.charAt(i));
                if(Character.isWhitespace(mensagem.charAt(i))){
                    System.out.println("-    "+mensagem.substring(i)+" i: "+i); 
                }
                i++;
            }
        }
    }
}
