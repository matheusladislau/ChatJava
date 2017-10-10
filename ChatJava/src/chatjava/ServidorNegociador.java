package chatjava;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
public class ServidorNegociador extends Thread{
    int porta=2000;
    ArrayList<Usuario> arrayUsuario;
    ControleInterface controle;

//cores
    public static final String corReset="\u001B[0m";
    public static final String corVermelha="\u001B[31m";
    public static final String corAzul="\u001B[34m";
//
    public ServidorNegociador(ControleInterface controle,ArrayList<Usuario> arrayUsuario){
        this.controle=controle;
        this.arrayUsuario=arrayUsuario;
    }
    @Override
    public void run(){
        while(true){
            conectar();
        }
    }
    public synchronized void conectar(){
        try{
            ServerSocket servidor=new ServerSocket(porta);
            System.out.println("Servidor Negociador: Porta "+porta+" aberta!");
            Socket cliente=servidor.accept();
            System.out.println("Servidor Negociador: Recebendo conexão com o cliente "+   
                cliente.getInetAddress().getHostAddress()
                +" pela porta: "+this.porta);
            
        //permitindo conversa provisoria
            PrintStream saida=new PrintStream(cliente.getOutputStream());
            Scanner entrada=new Scanner(cliente.getInputStream());
            
            int portaLiberada=(2001+(2*(arrayUsuario.size())));
            String ipUsuario=cliente.getInetAddress().getHostAddress();
            String nome=(entrada.nextLine());
            if(nome.equals(""))
                nome="anônimo";
            criarUsuario(portaLiberada,ipUsuario,nome);
            Usuario novoUsuario=new Usuario(portaLiberada,ipUsuario,nome);
        
            //DEU CERTO: enviarparaTodos(nome+"se conectou");
            controle.atualizarChat(("["+new Tempo().getHoraMinutoAtual()+"] "+nome+" se conectou ao chat"));
            
            
            saida.println(portaLiberada); //envia a portaEnvia ao usuário
            
            
        //CRIA 2 NOVAS PORTAS, mas não por ser servidor(?):
            new ServidorReceptor(controle,arrayUsuario,portaLiberada,novoUsuario).start();
            ClienteEmissor novoCE=new ClienteEmissor();
            novoCE.configurar(ipUsuario,portaLiberada+1);
            novoUsuario.setClienteEmissor(novoCE);
            //novoUsuario.setCliente(c.getCliente());
            //new ServidorEnvia();
        //encerrando:
            entrada.close();
            servidor.close();
            cliente.close();
        }catch(IOException e){
            mensagemErro("Erro ao criar ServidorNegociador: "+e);
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
    public void criarUsuario(int porta,String ip,String nome){
        this.arrayUsuario.add(new Usuario(porta,ip,nome));
    }
    public String mensagemErro(String erro){
        return(corVermelha+(erro)+corReset);
    } 
}
