package View;

import Loading.ImageLoader;
import javax.swing.*;
import java.awt.*;

abstract public class MainFrame extends JFrame {
    // Fields
    protected JPanel mainPanel;
    private int height,width;
    private Image backgroundImage;

    // Constructor
    protected MainFrame(int width,int height,Image backgroundImage){
        this.width = width;
        this.height = height;
        this.backgroundImage = backgroundImage;
        initFrame();
    }

    // Methods
    protected void initFrame(){
        // Initializing the main frame
        this.setTitle("Super Mario +");
        this.setSize(this.width,this.height);
        this.setIconImage(ImageLoader.getIcon().getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        // Loading and showing the background image
        this.mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        this.mainPanel.setLayout(null);
        this.setContentPane(this.mainPanel);
    }
    abstract void initComponents();
}
