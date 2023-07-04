package View.InGameFrames;


public class GameLoop implements Runnable {
    private static final int FPS = 200;
    private final AnimationPanel animationPanel;
    private boolean running = false;

    public GameLoop(AnimationPanel animationPanel) {
        this.animationPanel = animationPanel;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        long lastUpdateTime = System.nanoTime();
        long currentTime;
        double deltaTime;

        while (running) {
            currentTime = System.nanoTime();
            deltaTime = (currentTime - lastUpdateTime) / 1000000000.0;
            lastUpdateTime = currentTime;

            try {
                update(deltaTime);
            } catch (InterruptedException e) {}

            animationPanel.repaint();

            try {
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update(double deltaTime) throws InterruptedException {
        animationPanel.update();
    }
}
