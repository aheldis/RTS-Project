import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Random;

public class Main extends Thread {

    static private long currentTime = 0;

    static Clock[] threads = new Clock[4];


    static public long getCurrentTime() {
        return currentTime;
    }

    public static int[] lock = new int[4];
    public static int[] priorities = new int[4];

    public static double cpu() {
        OperatingSystemMXBean os = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        return os.getSystemCpuLoad();
    }

    public void run() {
        currentTime = Instant.now().getEpochSecond();
        Random rand = new Random();
        while (true) {

            try {
                Thread.sleep(1000);
                System.out.println(cpu());
                System.out.println("--------------");
                if (cpu() > 0.90) {
                    threads[lock[3]].setTime(currentTime);
                    for (int i = 3; i >= 0; i--) {
                        threads[lock[i]].graph = false;
                    }
                } else if (cpu() > 0.70) {
                    threads[lock[3]].setTime(currentTime);
                    threads[lock[2]].setTime(currentTime);
                    for (int i = 3; i >= 0; i--) {
                        threads[lock[i]].graph = false;
                    }

                } else if (cpu() > 0.60) {
                    for (int i = 3; i >= 0; i--) {
                        threads[lock[i]].setTime(currentTime);
                        threads[lock[i]].graph = false;
                    }
                } else {
                    for (int i = 3; i >= 0; i--) {
                        threads[lock[i]].setTime(currentTime);
                        threads[lock[i]].graph = true;
                    }
                }
                if (currentTime % 10 == 0) {
                    for (int i = 0; i < 4; i++) {
                        priorities[i] = 0;
                    }
                    for (int i = 0; i < 4; i++) {
                        int int_random = rand.nextInt(4) + 5;
                        while (priorities[int_random - 5] == 1) {
                            int_random = rand.nextInt(4) + 5;
                        }
                        priorities[int_random - 5] = 1;
                        threads[i].setPriority(int_random);
                        lock[int_random - 5] = i;
                    }
                }
            } catch (Exception e) {
                System.err.print("Can't Sleep");
            }

            currentTime++;
        }
    }

    public static void main(String[] args) {
        String cityName1 = "Europe/Paris";
        String cityName2 = "Europe/London";
        String cityName3 = "Australia/Sydney";
        String cityName4 = "Asia/Seoul";

        System.out.println(cpu());

        ZoneId zone1 = ZoneId.of(cityName1);
        LocalTime now1 = LocalTime.now(zone1);
        threads[0] = new Clock(now1, zone1, 1);
        threads[0].setPriority(5);
        threads[0].start();

        ZoneId zone2 = ZoneId.of(cityName2);
        LocalTime now2 = LocalTime.now(zone2);
        threads[1] = new Clock(now2, zone2, 2);
        threads[1].setPriority(6);
        threads[1].start();

        ZoneId zone3 = ZoneId.of(cityName3);
        LocalTime now3 = LocalTime.now(zone3);
        threads[2] = new Clock(now3, zone3, 3);
        threads[2].setPriority(7);
        threads[2].start();

        ZoneId zone4 = ZoneId.of(cityName4);
        LocalTime now4 = LocalTime.now(zone4);
        threads[3] = new Clock(now4, zone4, 4);
        threads[3].setPriority(8);
        threads[3].start();

        for (int i = 0; i < 4; i++) {
            lock[i] = i;
        }
        Main mainThread = new Main();
        mainThread.setPriority(Thread.MAX_PRIORITY);
        mainThread.start();

    }
}