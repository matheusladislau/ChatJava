package chatjava;
import socket.ClienteEmissor;
public class Usuario{
    private String ip;
    private int portaEnvia;
    private int portaRecebe;
    private String nome;
    private Usuario usuario;
    private ClienteEmissor clienteEnviar;
//
    public Usuario(int porta,String ip,String nickName){
        this.portaEnvia=porta;
        this.portaRecebe=porta+1;
        this.ip=ip;
        this.nome=nickName;
    }
//
    public String getIp(){
        return ip;
    }

    public int getPortaEnvia(){
        return portaEnvia;
    }

    public int getPortaRecebe(){
        return portaRecebe;
    }

    public String getNome(){
        return nome;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public ClienteEmissor getClienteEnviar(){
        return clienteEnviar;
    }
   
//
    public void setIp(String ip){
        this.ip=ip;
    }
    
    public void setPorta(int porta){
        this.portaEnvia=porta;
    }
    
    public void setNickName(String nickName){
        this.nome=nickName;
    }

    public void setClienteEnviar(ClienteEmissor clienteEnviar){
        this.clienteEnviar=clienteEnviar;
    }
    
    
}
