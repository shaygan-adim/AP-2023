package Controller;

import Model.Part;
import Model.PhysicsHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener{
    // Fields
    private boolean[] keyPressed = {false,false,false};
    private Part part;
    private PhysicsHandler physicsHandler;

    // Constructor
    public Controller(Part part, PhysicsHandler physicsHandler){
        this.part = part;
        this.physicsHandler = physicsHandler;
    }

    // Methods
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'w'){
            this.physicsHandler.jump();
            this.physicsHandler.stand();
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
            this.physicsHandler.seat();
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
        }
    }
}
