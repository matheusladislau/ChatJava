package chatjava;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class ControleInterface{
    static JTextArea txt_chat;
    static JTextField txt_enviar;

    public ControleInterface(JTextArea textChat,JTextField txtEnviar){
        this.txt_chat=textChat;
        this.txt_enviar=txtEnviar;
    }
    public void atualizarChat(String mensagem){   
        if(txt_chat.getText().equals(""))
            txt_chat.setText(mensagem);
        else
            txt_chat.setText(txt_chat.getText()+"\n"+mensagem);
    }
}
