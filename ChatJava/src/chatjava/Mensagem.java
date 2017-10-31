package chatjava;
import java.util.ArrayList;
public class Mensagem{
    //cores
    final String corReset="\u001B[0m";
    final String corVermelha="\u001B[31m";
    final String corAzul="\u001B[34m";
    //
    public void enviarParaTodos(ArrayList<ClienteEmissor> arrayCliente,String mensagem){
        mensagem=("["+new Tempo().getHoraCompletaAtual()+"] "+mensagem);
        for(int i=0; i<arrayCliente.size(); i++){
            arrayCliente.get(i).enviarMensagem(mensagem);
            //new GerenciadorBD().inserirMensagem(mensagem); funcionando
        }
        
        String crip=new CriptografiaRSA().cifrar(mensagem);
        String decrip=new CriptografiaRSA().decifrar(crip);
        System.out.println("mensagem: "+mensagem);
        System.out.println("Criptografada: "+crip);
        System.out.println("Decrip: "+decrip);
        
    }   
    public String mensagemErro(String erro){
        return(corVermelha+(erro)+corReset);
    } 
}
