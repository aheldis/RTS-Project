import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

public class Clock extends Thread {

    LocalTime timezone;
    ZoneId zoneId;
    GraphicalClock graphicalClock;
    int id;

    public boolean graph;
    
    public Clock(LocalTime timezone, ZoneId zoneId, int id) {
        this.timezone = timezone;
        this.zoneId = zoneId;
        this.id = id;
        this.graph = true;
    }

    public void setTime(long time) {
        timezone = LocalTime.ofInstant(Instant.ofEpochSecond(time), this.zoneId);
    }

    public void run() {
        graphicalClock = new GraphicalClock(this.timezone, id, zoneId);
        graphicalClock.setTime(this.timezone);
        Thread graphicalClockThread = graphicalClock.getClockThread();
        if (graph) {
            graphicalClockThread.setPriority(this.getPriority() - 4);
            graphicalClockThread.start();
        }else{
            graphicalClockThread.suspend();
        }
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
