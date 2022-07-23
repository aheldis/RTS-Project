import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
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


    public static void main(String[] args) {
        String cityName1 = "Europe/Paris";
        String cityName2 = "Europe/London";
        String cityName3 = "Australia/Sydney";
        String cityName4 = "Asia/Seoul";

        ZoneId zone1 = ZoneId.of(cityName1);
        LocalTime now1 = LocalTime.now(zone1);
        Clock thread1 = new Clock(now1,cityName1);
        thread1.setPriority(1);
        thread1.start();

        ZoneId zone2 = ZoneId.of(cityName2);
        LocalTime now2 = LocalTime.now(zone2);
        Clock thread2 = new Clock(now2,cityName2);
        thread1.setPriority(2);
        thread2.start();

        ZoneId zone3 = ZoneId.of(cityName3);
        LocalTime now3 = LocalTime.now(zone3);
        Clock thread3 = new Clock(now3,cityName3);
        thread1.setPriority(3);
        thread3.start();

        ZoneId zone4 = ZoneId.of(cityName4);
        LocalTime now4 = LocalTime.now(zone4);
        Clock thread4 = new Clock(now4,cityName4);
        thread1.setPriority(4);
        thread4.start();

//        Clock thread5 = new Clock();
//        thread5.start();
//
//        Clock thread6 = new Clock();
//        thread6.start();
//
//        Clock thread7 = new Clock();
//        thread7.start();
//
//        Clock thread8 = new Clock();
//        thread8.start();

    }
}
