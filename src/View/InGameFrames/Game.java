package View.InGameFrames;

import Controller.Controller;
import Loading.ImageLoader;
import Logic.PhysicsHandler;
import Model.*;
import Model.Characters.Enemies.Plant;
import Model.Characters.Enemies.Enemy;
import Model.Levels.Level;
import View.MainFrames.MainPage;
import View.MainFrames.SlotMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {
    // Fields
    private AnimationPanel mainPanel;
    private final Level level;
    private final User user;
    private final Controller controller;
    private final PhysicsHandler physicsHandler;
    private JLabel coinImage,coinLabel,heartImage,heartLabel,scoreImage,scoreLabel,timeImage,timeLabel,levelLabel,summaryLabel;
    private JButton homeButton, playAgainButton,nextLevelButton;

    //  Constructor
    public Game(Level level, User user){
        this.level = level;
        this.user = user;
        physicsHandler = new PhysicsHandler(level,user);
        this.controller = new Controller(this.physicsHandler);
        this.addKeyListener(this.controller);
        initFrame();
        initAnimation();
    }

    // Methods
    void initFrame(){
        // Initializing the main frame
        this.setTitle("Super Mario +");
        this.setSize(1280,800);
        this.setIconImage(ImageLoader.getIcon().getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        // Creating and configuring components
        coinImage = new JLabel(ImageLoader.getCoinBiggerImage());
        coinLabel = new JLabel("0");
        heartImage = new JLabel(ImageLoader.getHeartImage());
        heartLabel = new JLabel(String.valueOf(this.level.getActivePart().getHeroes()[0].getLives()));
        scoreImage = new JLabel(ImageLoader.getScoreImage());
        scoreLabel = new JLabel("0");
        timeImage = new JLabel(ImageLoader.getTimeImage());
        timeLabel = new JLabel(String.valueOf(this.level.getActivePart().getTime()));
        levelLabel = new JLabel("World "+(this.level.getId()+1)+"-"+(this.level.getActivePart().getId()+1));
        summaryLabel = new JLabel("Summary :");
        homeButton = new JButton("Home");
        playAgainButton = new JButton("Play again");
        nextLevelButton = new JButton("Next level");

        homeButton.setFocusPainted(false);
        playAgainButton.setFocusPainted(false);
        nextLevelButton.setFocusPainted(false);

        coinLabel.setFont(new Font("Forte",Font.PLAIN,35));
        heartLabel.setFont(new Font("Forte",Font.PLAIN,35));
        scoreLabel.setFont(new Font("Forte",Font.PLAIN,35));
        timeLabel.setFont(new Font("Forte",Font.PLAIN,35));
        levelLabel.setFont(new Font("Ink Free",Font.BOLD,40));
        summaryLabel.setFont(new Font("Forte",Font.PLAIN,40));
        playAgainButton.setFont(new Font("Forte",Font.PLAIN,16));
        homeButton.setFont(new Font("Forte",Font.PLAIN,16));
        nextLevelButton.setFont(new Font("Forte",Font.PLAIN,16));

        coinLabel.setForeground(Color.BLACK);
        heartLabel.setForeground(Color.BLACK);
        scoreLabel.setForeground(Color.BLACK);
        timeLabel.setForeground(Color.BLACK);
        levelLabel.setForeground(Color.BLACK);
        summaryLabel.setForeground(Color.BLACK);

        summaryLabel.setVisible(false);
        homeButton.setVisible(false);
        nextLevelButton.setVisible(false);
        playAgainButton.setVisible(false);

        // Setting listeners
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainPage(user);
                Game.super.dispose();
            }
        });
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SlotMenu(user);
                Game.super.dispose();


            }
        });

        // Setting main panel up
        this.mainPanel = new AnimationPanel(this.level,null,this.physicsHandler,this);
        this.mainPanel.setLayout(null);
        this.setContentPane(this.mainPanel);

        // Adding components to the panel
        this.mainPanel.add(coinImage);
        this.mainPanel.add(coinLabel);
        this.mainPanel.add(heartImage);
        this.mainPanel.add(heartLabel);
        this.mainPanel.add(scoreLabel);
        this.mainPanel.add(scoreImage);
        this.mainPanel.add(timeLabel);
        this.mainPanel.add(timeImage);
        this.mainPanel.add(levelLabel);
        this.mainPanel.add(summaryLabel);
        this.mainPanel.add(homeButton);
        this.mainPanel.add(nextLevelButton);
        this.mainPanel.add(playAgainButton);

        // Setting components' locations
        heartImage.setBounds(0,0,100,100);
        heartLabel.setBounds(85,30,150,50);
        coinImage.setBounds(95,0,100,100);
        coinLabel.setBounds(180,30,150,50);
        scoreImage.setBounds(200,0,100,100);
        scoreLabel.setBounds(285,30,150,50);
        timeImage.setBounds(1100,0,100,100);
        timeLabel.setBounds(1185,30,150,50);
        levelLabel.setBounds(580,30,200,50);
        summaryLabel.setBounds(50,100,100,50);
    }
    void initAnimation(){
        this.mainPanel.startTheAnimation();
        for (Enemy enemy : this.level.getActivePart().getEnemies()){
            if (enemy instanceof Plant){
                ((Plant) enemy).getStopwatch().start();
            }
        }
        this.level.getActivePart().getStopwatch().start();
    }

    // Getters
    public JLabel getCoinImage() {return coinImage;}
    public JLabel getCoinLabel() {return coinLabel;}
    public JLabel getHeartImage() {return heartImage;}
    public JLabel getHeartLabel() {return heartLabel;}
    public JLabel getScoreImage() {return scoreImage;}
    public JLabel getScoreLabel() {return scoreLabel;}
    public JLabel getTimeImage() {return timeImage;}
    public JLabel getTimeLabel() {return timeLabel;}
    public JLabel getLevelLabel() {return levelLabel;}
    public JLabel getSummaryLabel() {return summaryLabel;}
    public JButton getHomeButton() {return homeButton;}
    public JButton getPlayAgainButton() {return playAgainButton;}
    public JButton getNextLevelButton() {return nextLevelButton;}
}
