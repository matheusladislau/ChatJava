package chatjava;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class Tempo{    
    
    public String getData(){
        return new SimpleDateFormat("yyy-MM-dd").format(Calendar.getInstance().getTime());
    }
    public String getHoraCompletaAtual(){
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }
    public String getHoraMinutoAtual(){
        return new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
    }
}
