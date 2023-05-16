package View;

import Loading.ImageLoader;
import Model.HeroName;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends MainFrame{
    // Fields
    private User user;
    // Constructor
    public MainPage(User user) {
        super(500, 625, ImageLoader.getUserMenuImage());
        this.user = user;
        initComponents();
    }

    // Methods
    @Override
    void initComponents() {
        // Initializing and configuring the components
        JButton playButton = new JButton("Play");
        JButton leaderboardButton = new JButton("Ranking");
        JButton shopButton = new JButton("Shop");
        JButton profileButton = new JButton("...");
        JLabel welcomeLabel1 = new JLabel("Welcome "+this.user.getUsername()+".");
        JLabel welcomeLabel2 = new JLabel("Lets save the princess !");
        JLabel characterLabel = new JLabel("Active character");
        JLabel usernameLabel = new JLabel(this.user.getUsername());
        JLabel coinImage = new JLabel(ImageLoader.getCoinImage());
        JLabel coinLabel = new JLabel(String.valueOf(this.user.getCoin()));
        JLabel heroImage = null;
        if (this.user.getActiveHero() == HeroName.MARIO) heroImage = new JLabel(ImageLoader.getMarioImage());
        if (this.user.getActiveHero() == HeroName.LUIGI) heroImage = new JLabel(ImageLoader.getLuigiImage());
        if (this.user.getActiveHero() == HeroName.PRINCESS) heroImage = new JLabel(ImageLoader.getPrincessImage());
        if (this.user.getActiveHero() == HeroName.YOSHI) heroImage = new JLabel(ImageLoader.getYoshiImage());
        if (this.user.getActiveHero() == HeroName.TOAD) heroImage = new JLabel(ImageLoader.getToadImage());

        playButton.setFont(new Font("Forte",Font.PLAIN,20));
        leaderboardButton.setFont(new Font("Forte",Font.PLAIN,20));
        shopButton.setFont(new Font("Forte",Font.PLAIN,20));
        profileButton.setFont(new Font("Forte",Font.PLAIN,15));
        welcomeLabel1.setFont(new Font("Ink Free",Font.BOLD,18));
        welcomeLabel2.setFont(new Font("Ink Free",Font.BOLD,18));
        characterLabel.setFont(new Font("Ink Free",Font.BOLD,12));
        usernameLabel.setFont(new Font("Ink Free",Font.BOLD,22));
        coinLabel.setFont(new Font("Ink Free",Font.BOLD,22));

        characterLabel.setForeground(Color.BLACK);

        playButton.setFocusPainted(false);
        shopButton.setFocusPainted(false);
        leaderboardButton.setFocusPainted(false);
        profileButton.setFocusPainted(false);

        // Setting listeners
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfilePage(user);
                MainPage.super.dispose();
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SlotMenu(user);
                MainPage.super.dispose();
            }
        });
        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RankingPage(user);
                MainPage.super.dispose();
            }
        });
        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShopPage(user);
                MainPage.super.dispose();
            }
        });

        // Adding the components and setting the locations
        super.mainPanel.add(playButton);
        super.mainPanel.add(leaderboardButton);
        super.mainPanel.add(shopButton);
        super.mainPanel.add(profileButton);
        super.mainPanel.add(welcomeLabel1);
        super.mainPanel.add(welcomeLabel2);
        super.mainPanel.add(characterLabel);
        super.mainPanel.add(usernameLabel);
        super.mainPanel.add(coinImage);
        super.mainPanel.add(coinLabel);
        super.mainPanel.add(heroImage);

        welcomeLabel1.setBounds(150,-100,300,300);
        welcomeLabel2.setBounds(150,-80,300,300);
        heroImage.setBounds(160,150,161,250);
        usernameLabel.setBounds(40,160,100,100);
        coinImage.setBounds(40,232,15,15);
        coinLabel.setBounds(60,230,100,25);
        profileButton.setBounds(15,195,20,20);
        playButton.setBounds(350,400,80,30);
        shopButton.setBounds(47,400,80,30);
        leaderboardButton.setBounds(35,360,105,30);
        characterLabel.setBounds(195,370,100,100);
    }
}
