package Model;

public class Stopwatch {
    // Fields
    private long startTime;
    private int unit;


    // Constructor
    public Stopwatch(int unit){
        this.unit = unit;
    }

    // Methods
    public void start(){
        this.startTime = System.currentTimeMillis();
    }
    public long passedTime(){
        if (unit==1){
            return (System.currentTimeMillis()-this.startTime)/1000;
        }
        return System.currentTimeMillis()-this.startTime;
    }
    public void sleep(int delay){
        this.startTime += delay;
    }
}
