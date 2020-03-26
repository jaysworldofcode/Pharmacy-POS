
import java.time.Duration;
import java.time.ZonedDateTime;

public class tester {
    
public static void main(String[] a) throws Exception {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime oldDate = ZonedDateTime.parse("2019-08-24T08:00:00+00:00[Europe/London]");
        Duration duration = Duration.between(oldDate, now);
        System.out.println("Days: " + duration.toDays());
    }
}