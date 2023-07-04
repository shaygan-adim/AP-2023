package Logic;

import java.io.Serializable;

public class Stopwatch implements Serializable {
    // Fields
    private long startTime;
    private long pauseTime;
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
    public void pause(){
        pauseTime = System.currentTimeMillis();
    }
    public void resume(){
        if (isStarted()){
            startTime += System.currentTimeMillis()-pauseTime;
        }
    }

    // Getters
    public boolean isStarted() {return started;}
}
