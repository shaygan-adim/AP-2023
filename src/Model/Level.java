package Model;

public class Level {
    // Fields
    private final int id;
    private final Part[] parts;
    private int totalCoins = 0;
    private int totalTime = 0;
    private Part activePart;
    private int done = 0;

    // Constructor
    public Level(Part[] parts,int id){
        this.id=id;
        this.parts = parts;
        this.activePart = parts[0];
    }

    // Methods
    public void addCoin(int n){
        this.totalCoins+=n;
    }
    public void addTime(int n){
        this.totalTime+=n;
    }

    // Setters
    public void setActivePart(Part activePart) {this.activePart = activePart;}
    public void setDone(int done) {this.done = done;}

    // Getters
    public int getId() {return id;}
    public Part[] getParts() {return parts;}
    public Part getActivePart() {return activePart;}
    public int getDone() {return done;}
    public int getTotalCoins() {return totalCoins;}
    public int getTotalTime() {return totalTime;}
}
