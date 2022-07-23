import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

public class Main extends Thread{

    static private long currentTime = 0;

    static Clock thread1;
    static Clock thread2;
    static Clock thread3;
    static Clock thread4;

    static public long getCurrentTime(){
        return currentTime;
    }

    public void run(){
        currentTime = Instant.now().getEpochSecond();
        while (true){
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                System.err.print("Can't Sleep");
            }
            currentTime ++;
        }
    }

    public static void main(String[] args) {
        String cityName1 = "Europe/Paris";
        String cityName2 = "Europe/London";
        String cityName3 = "Australia/Sydney";
        String cityName4 = "Asia/Seoul";

        ZoneId zone1 = ZoneId.of(cityName1);
        LocalTime now1 = LocalTime.now(zone1);
        thread1 = new Clock(now1,cityName1);
        thread1.setPriority(1);
        thread1.start();

        ZoneId zone2 = ZoneId.of(cityName2);
        LocalTime now2 = LocalTime.now(zone2);
        thread2 = new Clock(now2,cityName2);
        thread2.setPriority(2);
        thread2.start();

        ZoneId zone3 = ZoneId.of(cityName3);
        LocalTime now3 = LocalTime.now(zone3);
        thread3 = new Clock(now3,cityName3);
        thread3.setPriority(3);
        thread3.start();

        ZoneId zone4 = ZoneId.of(cityName4);
        LocalTime now4 = LocalTime.now(zone4);
        thread4 = new Clock(now4,cityName4);
        thread4.setPriority(4);
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
