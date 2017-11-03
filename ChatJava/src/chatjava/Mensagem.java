package chatjava;
import java.util.ArrayList;
public class Mensagem{
    //cores
    final String corReset="\u001B[0m";
    final String corVermelha="\u001B[31m";
    final String corAzul="\u001B[34m";
    //
    private String mensagem;
    private String destinatario;
    private String remetente;
    private ArrayList<Usuario> arrayUsuario;
    private Usuario usuarioRemetente;
    
    public Mensagem(){
        
    }
    public Mensagem(Usuario usuarioRemetente,String mensagem){
        this.mensagem=mensagem;
        this.usuarioRemetente=usuarioRemetente;
        this.remetente=usuarioRemetente.getNome();
    }
    
    public Mensagem(String nomeUsuario,String mensagem){
        this.mensagem=mensagem;
        this.remetente=nomeUsuario;
    }
    
    public void enviar(ArrayList<Usuario> arrayUsuario,String mensagem){
        this.arrayUsuario=arrayUsuario;
        this.mensagem=mensagem;
        this.mensagem=removerEspacosInicio();
        verificarMensagem();
    }   
    public String mensagemErro(String erro){
        return(corVermelha+(erro)+corReset);
    } 
    
    public void verificarMensagem(){
        //verificar se privada
        this.destinatario="todos";
        if(this.mensagem.length()>0)//se mensagem existe
            if(this.mensagem.substring(0,1).equals("@")){//se o primeiro caracter é @
                this.destinatario=pegarDestinatario(this.mensagem);
            }
        if(!procurarNome()){//se não encontra destinatário, envia a todos
           enviarParaTodos();            
        }
    }
    public void enviarPrivada(Usuario usuarioDestinatario){
        String mensagemPrivada=("["+new Tempo().getHoraMinutoAtual()+"] "+remetente+" (privado): "+mensagem);
        usuarioDestinatario.getClienteEnviar().enviarMensagem(new CriptografiaRSA().cifrar(mensagemPrivada));
    }
    
    String removerEspacosInicio() {
        int i=0;
        while((i<mensagem.length()) && (Character.isWhitespace(mensagem.charAt(i)))){
            i++;
        }
        return mensagem.substring(i);
    }
    
    String pegarDestinatario(String mensagem){
        int i=0;
        while((i<mensagem.length()) && (!Character.isWhitespace(mensagem.charAt(i)))) {
            i++;
        }
        return mensagem.substring(1,i);
    }
    
    public void enviarParaTodos(){
        this.mensagem=("["+new Tempo().getHoraMinutoAtual()+"] "+remetente+": "+mensagem);
        for(int i=0; i<arrayUsuario.size(); i++){
            arrayUsuario.get(i).getClienteEnviar().enviarMensagem(new CriptografiaRSA().cifrar(mensagem));//criptografia
        }
    }
    
    boolean procurarNome(){
        for(int i=0; i<arrayUsuario.size(); i++){
            if(arrayUsuario.get(i).getNome().equals(this.destinatario)){
                enviarPrivada(arrayUsuario.get(i));
                enviarPrivada(usuarioRemetente);
                return true;
            }
        }
        return false;
    }
}
