package chatjava;
import java.sql.DriverManager;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public void criarBD(){
        String comando=(" -- MySQL Workbench Forward Engineering\n" +
            "    SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;\n" +
            "    SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;\n" +
            "    SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';\n" +
            "\n" +
            "    -- -----------------------------------------------------\n" +
            "    -- Schema CHATJAVA\n" +
            "    -- -----------------------------------------------------\n" +
            "\n" +
            "    -- -----------------------------------------------------\n" +
            "    -- Schema CHATJAVA\n" +
            "    -- -----------------------------------------------------\n" +
            "    CREATE SCHEMA IF NOT EXISTS `CHATJAVA` DEFAULT CHARACTER SET utf8 ;\n" +
            "    USE `CHATJAVA` ;\n" +
            "\n" +
            "    -- -----------------------------------------------------\n" +
            "    -- Table `CHATJAVA`.`Mensagem`\n" +
            "    -- -----------------------------------------------------\n" +
            "    CREATE TABLE IF NOT EXISTS `CHATJAVA`.`Mensagem` (\n" +
            "      `idMensagem` INT NOT NULL,\n" +
            "      `data` DATETIME NOT NULL,\n" +
            "      `remetente` VARCHAR(100) NOT NULL,\n" +
            "      `destinatario` VARCHAR(100) NOT NULL,\n" +
            "      `mensagem` LONGTEXT NOT NULL,\n" +
            "      PRIMARY KEY (`idMensagem`))\n" +
            "    ENGINE = InnoDB;\n" +
            "\n" +
            "\n" +
            "    SET SQL_MODE=@OLD_SQL_MODE;\n" +
            "    SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;\n" +
            "    SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;");
        try{
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

            PreparedStatement stm=(PreparedStatement)connection.prepareStatement(comando);            
            stm.execute();
        }catch(Exception e){
            e.printStackTrace();
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
