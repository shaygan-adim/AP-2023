package View.InGameFrames;

import Loading.AudioLoader;
import Loading.ImageLoader;
import Model.Characters.Enemies.Bowser;
import Model.Characters.Enemies.Enemy;
import Model.Levels.Level;
import Model.User;
import View.MainFrames.MainPage;
import View.MainFrames.RankingPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseMenu extends JFrame {
    private final AnimationPanel animationPanel;
    private final User user;
    private final Level level;

    public PauseMenu(AnimationPanel animationPanel,User user,Level level){
        this.animationPanel = animationPanel;
        this.user = user;
        this.level = level;
        initFrame();
    }

    public void initFrame(){
        // Initializing the main frame
        this.setTitle("");
        this.setSize(200,200);
        this.setIconImage(ImageLoader.getIcon().getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        // Creating and configuring components
        JButton soundButton;
        if (!animationPanel.getGame().isMute()){
            soundButton = new JButton("Sounds Off");
        }
        else{
            soundButton = new JButton("Sounds On");
        }
        JButton resumeButton = new JButton("Resume");
        JButton backButton = new JButton("Save & Exit");

        soundButton.setFocusPainted(false);
        resumeButton.setFocusPainted(false);
        backButton.setFocusPainted(false);

        soundButton.setFont(new Font("Forte",Font.PLAIN,15));
        resumeButton.setFont(new Font("Forte",Font.PLAIN,15));
        backButton.setFont(new Font("Forte",Font.PLAIN,15));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);

        panel.add(soundButton);
        panel.add(resumeButton);
        panel.add(backButton);

        soundButton.setBounds(20,10,150,30);
        resumeButton.setBounds(45,65,100,30);
        backButton.setBounds(20,120,150,30);

        // Setting listeners
        soundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (soundButton.getText().equals("Sounds Off")){
                    animationPanel.getGame().setMute(true);
                    animationPanel.getPhysicsHandler().setMute(true);
                    soundButton.setText("Sounds On");
                }
                else{
                    animationPanel.getGame().setMute(false);
                    animationPanel.getPhysicsHandler().setMute(false);
                    soundButton.setText("Sounds Off");
                }
            }
        });

        resumeButton.addActionListener(e -> {
            animationPanel.startTheAnimation();
            level.getActivePart().getStopwatch().resume();
            Bowser bowser = null;
            for (Enemy enemy : level.getActivePart().getEnemies()) {
                if (enemy instanceof Bowser) bowser = (Bowser) enemy;
            }
            if (bowser!=null){
                bowser.getAttackReloadStopwatch().resume();
                bowser.getDizzyStopwatch().resume();
                if (bowser.getReloadStopwatches()[0]!=null){
                    bowser.getGrabAttackStopwatch().resume();
                    bowser.getReloadStopwatches()[0].resume();
                    bowser.getReloadStopwatches()[1].resume();
                    bowser.getReloadStopwatches()[2].resume();
                    bowser.getReloadStopwatches()[3].resume();
                }
            }
            level.getActivePart().getHeroes()[0].getStopwatchForShield().resume();
            level.getActivePart().getHeroes()[0].getStopwatchForTransitioning().resume();
            PauseMenu.super.dispose();
        });
        backButton.addActionListener(e -> {
            user.getSavedLevels()[user.getActiveSlot()-1] = level;
            user.save();
            PauseMenu.super.dispose();
            animationPanel.getGame().dispose();
            new MainPage(user);
        });

    }
}
