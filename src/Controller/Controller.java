package Controller;

import Logic.PhysicsHandler;
import Logic.Stopwatch;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener{
    // Fields
    private final boolean[] keyPressed = {false,false,false,false};
    private final PhysicsHandler physicsHandler;
    private final Stopwatch stopwatch = new Stopwatch(1000);

    // Constructor
    public Controller( PhysicsHandler physicsHandler){
        this.physicsHandler = physicsHandler;
    }

    // Methods
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '.'){
            this.physicsHandler.shoot(true);
        }
        if (e.getKeyChar() == ','){
            this.physicsHandler.shoot(false);
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D || keyPressed[0]){
            keyPressed[0] = true;
            this.physicsHandler.right();
            this.physicsHandler.stand();
        }
        if (e.getKeyCode() == KeyEvent.VK_A || keyPressed[1]){
            keyPressed[1] = true;
            this.physicsHandler.left();
            this.physicsHandler.stand();
        }
        if (e.getKeyCode() == KeyEvent.VK_S || keyPressed[2]){
            keyPressed[2] = true;
            if (keyPressed[3]){
                physicsHandler.stand();
                if (!stopwatch.isStarted()){
                    stopwatch.start();
                }
            }
            else{
                this.physicsHandler.seat();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_W || keyPressed[3]){
            keyPressed[3] = true;
            if (keyPressed[2]){
                if (!stopwatch.isStarted()){
                    stopwatch.start();
                }
            }
            else{
                this.physicsHandler.jump();
                this.physicsHandler.stand();
            }
            if (stopwatch.isStarted()){
                if (stopwatch.passedTime()>2000){
                    physicsHandler.stop();
                    physicsHandler.stand();
                    physicsHandler.swordOperation();
                    stopwatch.stop();
                }
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D){
            keyPressed[0] = false;
            this.physicsHandler.stop();
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            keyPressed[1] = false;
            this.physicsHandler.stop();
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            keyPressed[2] = false;
            this.physicsHandler.stand();
            stopwatch.stop();
        }
        if (e.getKeyCode() == KeyEvent.VK_W){
            keyPressed[3] = false;
            stopwatch.stop();
        }
    }
}
