package chatjava;
import java.sql.DriverManager;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
public class GerenciadorBD{
    
    private String banco="CHATJAVA";
    public void insertMensagem(String data,String hora,String nomeRemetente,String nomeDestinatario,String mensagem){
        String comando=("INSERT INTO Mensagem VALUES ("+(maxIdMensagem()+1)+",'"+data+" "+hora+"','"+nomeRemetente+"','"+nomeDestinatario+"','"+mensagem+"');");
        System.out.println(comando);
        try{
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+banco,"root","");
            PreparedStatement stm=(PreparedStatement)connection.prepareStatement(comando);            
            stm.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public int maxIdMensagem(){
        int resultado=0;
        try{
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+banco,"root","");
            PreparedStatement stm=(PreparedStatement)connection.prepareStatement("SELECT MAX(idMensagem) FROM Mensagem;");
            ResultSet rs=stm.executeQuery();
        while(rs.next()){
            resultado=rs.getInt("max(idMensagem)");
        }
            return resultado;
        }catch(Exception e){
            return 1;
        }
    }
    
}

/*
    -- MySQL Workbench Forward Engineering

    SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
    SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
    SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

    -- -----------------------------------------------------
    -- Schema CHATJAVA
    -- -----------------------------------------------------

    -- -----------------------------------------------------
    -- Schema CHATJAVA
    -- -----------------------------------------------------
    CREATE SCHEMA IF NOT EXISTS `CHATJAVA` DEFAULT CHARACTER SET utf8 ;
    USE `CHATJAVA` ;

    -- -----------------------------------------------------
    -- Table `CHATJAVA`.`Mensagem`
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `CHATJAVA`.`Mensagem` (
      `idMensagem` INT NOT NULL,
      `data` DATETIME NOT NULL,
      `remetente` VARCHAR(100) NOT NULL,
      `destinatario` VARCHAR(100) NOT NULL,
      `mensagem` LONGTEXT NOT NULL,
      PRIMARY KEY (`idMensagem`))
    ENGINE = InnoDB;


    SET SQL_MODE=@OLD_SQL_MODE;
    SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
    SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

*/
