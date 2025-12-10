package util;

public class ChronoUtil {
    private long startTime;
    private long stopTime;
    private int state;
    private final static int RESET = 0;
    private final static int STOPPED = 1;
    private final static int RUNNING = 2;

    public ChronoUtil() {
        startTime = 0;
        stopTime = 0;
        state = RESET;
    }

    public void start() {
        switch (state) {
            case RESET:
                startTime = System.nanoTime();
                state = RUNNING;
                break;
            case STOPPED:
                long currentTime = System.nanoTime();
                startTime = startTime + currentTime - stopTime; // TODO: Test this out (sets start time to start time + duration of pause)
                stopTime = 0;
                state = RUNNING;
                break;
        }
    }

    public void stop() {
        if(state == RUNNING) stopTime = System.nanoTime();
        state = STOPPED;
    }

    public void reset() {
        startTime = 0;
        stopTime = 0;
        state = RESET;
    }

    public long getTimeInNano() {
        long  currentTime = System.nanoTime();
        if (state == RESET) return 0;
        return currentTime - startTime;
    }

    public double getTimeInSeconds() {
        long  currentTime = System.nanoTime();
        if (state == RESET) return 0;
        double time = (double)(currentTime - startTime) / 1000000000.0;
        return Math.round(time * 100.0)/100.0;
    }

}
