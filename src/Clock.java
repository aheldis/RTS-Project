import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

public class Clock extends Thread {

    LocalTime timezone;
    ZoneId zoneId;
    GraphicalClock graphicalClock;
    int threadNumber;

    public boolean graph;

    public Clock(LocalTime timezone, ZoneId zoneId, int threadNumber) {
        this.timezone = timezone;
        this.zoneId = zoneId;
        this.threadNumber = threadNumber;
        this.graph = true;
    }

    public void setTime(long time) {
        timezone = LocalTime.ofInstant(Instant.ofEpochSecond(time), this.zoneId);
    }

    public void run() {
        graphicalClock = new GraphicalClock();
        if (graph) {
            graphicalClock.threadNum = this.threadNumber;
            graphicalClock.setPriority(this.getPriority() - 4);
            graphicalClock.start();
        }else{
            graphicalClock.suspend();
        }
        while (true) {
            try {
                if (graph) {
                    graphicalClock.setTime(this.timezone);
                }
                System.out.print(zoneId + "\t" + timezone + "\n");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}