package chatjava;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class Tempo{
    public String getHoraCompletaAtual(){
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }
    public String getHoraMinutoAtual(){
        return new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
    }
    public String getHoraAtual(){
        return new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
    }
    public String getMinutoAtual(){
        return new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
    }
    public String getSegundoAtual(){
        return new SimpleDateFormat("ss").format(Calendar.getInstance().getTime());
    }
}
