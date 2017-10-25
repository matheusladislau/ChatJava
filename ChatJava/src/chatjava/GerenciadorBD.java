package chatjava;
import java.sql.DriverManager;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
public class GerenciadorBD{
    String banco="CHATJAVA";
    public void inserirMensagem(String mensagem){ //alterar tipo para data ou time
        String comando="INSERT INTO Mensagem VALUES (2,'2017-10-25 10:11:12','teste','127.0.0.1','";
        try{
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+banco,"root","");
            PreparedStatement stm=(PreparedStatement)connection.prepareStatement(comando+mensagem+"');");            
            stm.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
