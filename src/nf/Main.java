package nf;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        //System.out.println( new SimpleDateFormat("dd/MM/yyyy:HH-mm").format(System.currentTimeMillis()) );
        Date date = new Date( System.currentTimeMillis() );
        GregorianCalendar gregorianCalendar = new GregorianCalendar(2018, 1, 2);
    }
}

