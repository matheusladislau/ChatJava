package socket;
import chatjava.ControleInterface;
import chatjava.Mensagem;
import chatjava.Usuario;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
public class ServidorNegociador extends Thread{
    private int porta=2000;
    private ArrayList<Usuario> arrayUsuario;
    private ControleInterface controle;
    private boolean iniciado=false;
//CONSTR
    public ServidorNegociador(ControleInterface controle,ArrayList<Usuario> arrayUsuario){
        this.controle=controle;
        this.arrayUsuario=arrayUsuario;
    }
//
    @Override
    public void run(){
        while(true){
            conectar();
        }
    }
    public synchronized void conectar(){
        while(this.iniciado==true){
            try{
                wait();
            }catch(InterruptedException e){
                new Mensagem().mensagemErro(" Erro ao aguardar: "+e);
            }
        }
        this.iniciado=true;
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
            Usuario novoUsuario=new Usuario(portaLiberada,ipUsuario,nome);
            
            saida.println(portaLiberada); //envia a portaEnvia ao usuário
            
            
        //CRIA 2 NOVAS PORTAS
            ServidorReceptor novoServidor=new ServidorReceptor(controle,arrayUsuario,portaLiberada,novoUsuario);
            novoServidor.start();
            
            ClienteEmissor novoCliente=new ClienteEmissor();
            novoCliente.configurar(ipUsuario,portaLiberada+1);
            novoUsuario.setClienteEnviar(novoCliente);
            arrayUsuario.add(novoUsuario);

            String mensagem=(nome+" se conectou ao chat");
            new Mensagem("servidor",mensagem).enviar(arrayUsuario,mensagem);
         
        //encerrando:
            entrada.close();
            servidor.close();
            cliente.close();
            
            this.iniciado=false;
            notifyAll();
        }catch(IOException e){
            new Mensagem().mensagemErro("Erro ao criar ServidorNegociador: "+e);
        }
    }
}
