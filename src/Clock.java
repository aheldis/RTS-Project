import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

public class Clock extends Thread {

    LocalTime timezone;
    String cityname;

    public Clock(LocalTime timezone, String cityname) {
        this.timezone = timezone;
        this.cityname = cityname;
    }

    public void run() {
        System.out.print(cityname + "\t" + timezone + "\n");
    }
}
