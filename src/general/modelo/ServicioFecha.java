package general.modelo;

import java.sql.Date;
import java.util.Calendar;

/**
 * Clase que realiza operaciones con fechas.
 * @author Camilo
 */
public class ServicioFecha {
    /**
     * Método que resta dos fechas y retorna la diferencia en días.
     * @param fechaMayor
     * @param fechaMenor
     * @return int
     */
    public static int diferenciaEnDias(Date fechaMayor, Date fechaMenor) {
        long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
        long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
        return (int) dias;
    }
    
    /**
     * Método que permite sumar días a una fecha
     * @param fecha
     * @param dias
     * @return 
     */
    public static java.util.Date sumarDiasAFecha(java.util.Date fecha, int dias){
        if (dias==0) return fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); 
        calendar.add(Calendar.DAY_OF_YEAR, dias);  
        calendar.add(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime(); 
    }
}
