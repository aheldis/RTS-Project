import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

public class Clock extends Thread {

    LocalTime timezone;
    ZoneId zoneId;
    GraphicalClock graphicalClock;

    public Clock(LocalTime timezone, ZoneId zoneId) {
        this.timezone = timezone;
        this.zoneId = zoneId;
    }

    public void setTime(long time) {
        timezone = LocalTime.ofInstant(Instant.ofEpochSecond(time), this.zoneId);
    }

    public void run() {
        graphicalClock = new GraphicalClock();
        graphicalClock.setPriority(this.getPriority() + 4);
        graphicalClock.start();
        while (true) {
            try {
                graphicalClock.setTime(this.timezone);
                System.out.print(zoneId + "\t" + timezone + "\n");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
