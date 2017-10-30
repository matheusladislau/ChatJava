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
    ArrayList<ClienteEmissor> arrayCliente;
    boolean iniciado=false;
//CONSTR
    public ServidorNegociador(ControleInterface controle,ArrayList<Usuario> arrayUsuario,
                                ArrayList<ClienteEmissor> arrayCliente){
        this.controle=controle;
        this.arrayUsuario=arrayUsuario;
        this.arrayCliente=arrayCliente;
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
            criarUsuario(portaLiberada,ipUsuario,nome);
            Usuario novoUsuario=new Usuario(portaLiberada,ipUsuario,nome);
        
            //DEU CERTO: enviarparaTodos(nome+"se conectou");
            String mensagem=(nome+" se conectou ao chat");
            //----controle.atualizarChat(());
            new Mensagem().enviarParaTodos(arrayCliente,mensagem);
            
            saida.println(portaLiberada); //envia a portaEnvia ao usuário
            
            
        //CRIA 2 NOVAS PORTAS, mas não por ser servidor(?):
            ServidorReceptor novoServidor=new ServidorReceptor(controle,arrayUsuario,arrayCliente,portaLiberada,novoUsuario);
            novoServidor.start();
            
            ClienteEmissor novoCliente=new ClienteEmissor();
            novoCliente.configurar(ipUsuario,portaLiberada+1);
            arrayCliente.add(novoCliente);
            //novoUsuario.setClienteEmissor(novoCE);
            //novoUsuario.setCliente(c.getCliente());
            //new ServidorEnvia();
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
    public void criarUsuario(int porta,String ip,String nome){
        this.arrayUsuario.add(new Usuario(porta,ip,nome));
    }
}
