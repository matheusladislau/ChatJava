package chatjava;
import java.io.IOException;
import java.net.Socket;
public class Usuario{
    String ip;
    int portaEnvia;
    int portaRecebe;
    String nickName;
    Socket cliente;
    Usuario usuario;
//
    public Usuario(int porta,String ip,String nickName){
        this.portaEnvia=porta;
        this.ip=ip;
        this.nickName=nickName;
    }
    public Usuario(Usuario usuario){
        this.usuario=usuario;
        this.ip=this.usuario.getIp();
        this.portaEnvia=this.usuario.getPorta();
        this.nickName=this.usuario.getNickName();
    }
//
    public String getIp(){
        return this.ip;
    }
    public int getPorta(){
        return this.portaEnvia;
    }
    public String getNickName(){
        return this.nickName;
    }
//
    public void setIp(String ip){
        this.ip=ip;
    }
    public void setPorta(int porta){
        this.portaEnvia=porta;
    }
    public void setNickName(String nickName){
        this.nickName=nickName;
    }
    
}
