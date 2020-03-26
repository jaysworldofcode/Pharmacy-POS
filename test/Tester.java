import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Tester {
    
    public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }    
    
    public static void main(String args) throws ParseException{
        
//	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//	LocalDateTime now = LocalDateTime.now();
//	System.out.println(dtf.format(now)); //2016/11/16 12:08:43
//        
//        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dtf.format(now));
//        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dtf.format(now));
//        
//        ZonedDateTime nowx = ZonedDateTime.now();
//        ZonedDateTime oldDate = nowx.minusDays(1).minusMinutes(10);
//        Duration duration = Duration.between(oldDate, now);
//        System.out.println("ISO-8601: " + duration);
//        System.out.println("Minutes: " + duration.toMinutes());
    }
}