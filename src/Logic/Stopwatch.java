package Logic;

public class Stopwatch {
    // Fields
    private long startTime;
    private final int unit;
    private boolean started = false;


    // Constructor
    public Stopwatch(int unit){
        this.unit = unit;
    }

    // Methods
    public void start(){
        this.startTime = System.currentTimeMillis();
        started=true;
    }
    public long passedTime(){
        if (!started){
            return 0;
        }
        if (unit==1){
            return (System.currentTimeMillis()-this.startTime)/1000;
        }
        return System.currentTimeMillis()-this.startTime;
    }
    public void stop(){
        started = false;
    }

    // Getters
    public boolean isStarted() {return started;}
}
