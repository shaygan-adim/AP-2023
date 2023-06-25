package View.MainFrames;

import Loading.ImageLoader;
import Model.Characters.Heroes.HeroName;
import Model.User;
import View.EntryFrames.FirstPage;
import View.MainFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfilePage extends MainFrame {
    // Fields
    private User user;
    private List<JLabel> heroesImages = new ArrayList<>();
    Border border1 = BorderFactory.createLineBorder(Color.BLACK, 5);
    Border border2 = BorderFactory.createLineBorder(Color.GRAY, 5);
    // Constructor
    protected ProfilePage(User user) {
        super(500, 625, ImageLoader.getProfileMenuImage());
        this.user = user;
        initComponents();

        // Create and execute the SwingWorker to run the mouseLoop method in a separate thread
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                mouseLoop();
                return null;
            }
        };
        worker.execute();
    }

    // Methods
    @Override
    public void initComponents() {
        // Initializing and configuring the components
        JLabel usernameLabel = new JLabel(this.user.getUsername());
        JLabel coinImage = new JLabel(ImageLoader.getCoinBiggerImage());
        JLabel coinLabel = new JLabel(String.valueOf(this.user.getCoin()));
        JLabel highscoreImage = new JLabel(ImageLoader.getHighscoreImage());
        JLabel highscoreLabel = new JLabel(String.valueOf(this.user.getHighscore()));
        JLabel characterLabel = new JLabel("Characters :");
        for (HeroName heroName : user.getOwnedHeroes()){
            if (heroName==HeroName.MARIO){
                heroesImages.add(new JLabel(ImageLoader.getMarioSmallImage()));
            }
            if (heroName==HeroName.LUIGI){
                heroesImages.add(new JLabel(ImageLoader.getLuigiSmallImage()));
            }
            if (heroName==HeroName.PRINCESS){
                heroesImages.add(new JLabel(ImageLoader.getPrincessSmallImage()));
            }
            if (heroName==HeroName.YOSHI){
                heroesImages.add(new JLabel(ImageLoader.getYoshiSmallImage()));
            }
            if (heroName==HeroName.TOAD){
                heroesImages.add(new JLabel(ImageLoader.getToadSmallImage()));
            }
        }
        JButton backButton = new JButton("Back");
        JButton logoutButton = new JButton("Logout");

        usernameLabel.setFont(new Font("Forte",Font.PLAIN,35));
        coinLabel.setFont(new Font("Forte",Font.PLAIN,35));
        highscoreLabel.setFont(new Font("Forte",Font.PLAIN,35));
        characterLabel.setFont(new Font("Forte",Font.PLAIN,17));
        backButton.setFont(new Font("Forte",Font.PLAIN,17));
        logoutButton.setFont(new Font("Forte",Font.PLAIN,17));

        backButton.setFocusPainted(false);
        logoutButton.setFocusPainted(false);

        // Setting listeners
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure ?", "", JOptionPane.YES_NO_OPTION);
                if (dialogResult==JOptionPane.YES_OPTION){
                    new FirstPage();
                    ProfilePage.super.dispose();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainPage(user);
                ProfilePage.super.dispose();
            }
        });

        // Adding the components and setting the locations
        super.mainPanel.add(usernameLabel);
        super.mainPanel.add(coinImage);
        super.mainPanel.add(coinLabel);
        super.mainPanel.add(highscoreImage);
        super.mainPanel.add(highscoreLabel);
        super.mainPanel.add(characterLabel);
        for (JLabel jlabel : heroesImages){
            super.mainPanel.add(jlabel);
        }
        super.mainPanel.add(backButton);
        super.mainPanel.add(logoutButton);

        usernameLabel.setBounds(20,-10,400,100);
        highscoreImage.setBounds(150-35,13-5,150,150);
        highscoreLabel.setBounds(220,67-5,300,50);
        coinImage.setBounds(-10,38-5,100,100);
        coinLabel.setBounds(70,67-5,150,50);
        characterLabel.setBounds(200,120,150,55);
        int i = 0;
        for (JLabel jLabel : heroesImages){
            if (i/110<=3){
                jLabel.setBounds(i+30,170,100,155);
            }
            else{
                jLabel.setBounds((i-4*110)+30,340,100,155);
            }
            i+=110;
        }
        backButton.setBounds(360,25,90,40);
        logoutButton.setBounds(360,75,90,40);
    }
    void mouseLoop(){
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY() - 30;
                for (int i = 30 ; i<=heroesImages.size()*110+30 ; i+=110){
                    if ((i-30)/110<=3){
                        if(x>i && x<i+100 && y>170 && y<325){
                            user.setActiveHero(user.getOwnedHeroes()[(i-30)/110]);
                            try {
                                user.save();
                            } catch (IOException ex) {}
                        }
                    }
                    else{
                        if(x>30+((i-30)/110)%4*110 && x<30+((i-30)/110)%4*110+100 && y>340 && y<495){
                            user.setActiveHero(user.getOwnedHeroes()[(30+((i-30)/110)%4*110-30)/110+4]);
                            try {
                                user.save();
                            } catch (IOException ex) {}
                        }
                    }
                }
            }
        });
        int frameX = super.getLocationOnScreen().x;
        int frameY = super.getLocationOnScreen().y;

        while (true) {
            // get the current position of the mouse pointer on the screen
            int mouseX = MouseInfo.getPointerInfo().getLocation().x;
            int mouseY = MouseInfo.getPointerInfo().getLocation().y;

            // calculate the position of the mouse pointer relative to the JFrame
            int relativeX = mouseX - frameX;
            int relativeY = mouseY - frameY - 30;

            int i = 0;
            for (JLabel jLabel : heroesImages) {
                if (i<=3){
                    if (relativeX > i * 110 + 30 && relativeX < i * 110 + 30 + 100 && relativeY > 170 && relativeY < 325) {
                        jLabel.setBorder(border2);
                    } else if (user.getOwnedHeroes()[i] == user.getActiveHero()) {
                        jLabel.setBorder(border1);
                    } else {
                        jLabel.setBorder(null);
                    }
                }
                else{
                    if (relativeX > i%4 * 110 + 30 && relativeX < i%4 * 110 + 30 + 100 && relativeY > 340 && relativeY < 495) {
                        jLabel.setBorder(border2);
                    } else if (user.getOwnedHeroes()[i] == user.getActiveHero()) {
                        jLabel.setBorder(border1);
                    } else {
                        jLabel.setBorder(null);
                    }
                }
                i++;
            }
        }
    }
}
