import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
//import com.sun.management.OperatingSystemMXBean;
import java.time.LocalTime;
import java.time.ZoneId;

public class Clock extends Thread {

    public void run()
    {
        System.out.println("Thread Started Running...");
    }

    public static double getCPUUtilization() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
//        return osBean.getSystemCpuLoad();
        return 2.5;
    }

    public static void main(String[] args)
    {
        getCPUUtilization();
        ZoneId zone1 = ZoneId.of("Europe/Paris");
        LocalTime now1 = LocalTime.now(zone1);
        System.out.printf("Paris time: %s%n \n", now1);

        ZoneId zone2 = ZoneId.of("Europe/London");
        LocalTime now2 = LocalTime.now(zone2);
        System.out.printf("London time: %s%n \n", now2);

        ZoneId zone3 = ZoneId.of("Australia/Sydney");
        LocalTime now3 = LocalTime.now(zone3);
        System.out.printf("Sydney time: %s%n \n", now3);

        ZoneId zone4 = ZoneId.of("Asia/Seoul");
        LocalTime now4 = LocalTime.now(zone4);
        System.out.printf("Seoul time: %s%n \n", now4);

        Clock thread1 = new Clock();
        thread1.start();

        Clock thread2 = new Clock();
        thread2.start();

        Clock thread3 = new Clock();
        thread3.start();

        Clock thread4 = new Clock();
        thread4.start();


    }
}
