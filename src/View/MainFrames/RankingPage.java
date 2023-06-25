package View.MainFrames;

import Loading.ImageLoader;
import Model.User;
import View.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankingPage extends MainFrame {
    // Fields
    private User user;
    // Constructor
    protected RankingPage(User user) {
        super(500, 625, ImageLoader.getRankingPageImage());

        this.user = user;

        initComponents();
    }

    // Methods
    @Override
    public void initComponents() {
        // Initializing the buttons and configuring them
        JLabel goldImage = new JLabel(ImageLoader.getGoldImage());
        JLabel silverImage = new JLabel(ImageLoader.getSilverImage());
        JLabel bronzeImage = new JLabel(ImageLoader.getBronzeImage());
        JLabel rankImage = new JLabel(ImageLoader.getRankImage());
        JLabel goldLabel = new JLabel(User.orderedUsers()[0].getUsername()+" - "+User.orderedUsers()[0].getHighscore());
        goldLabel.setForeground(Color.BLACK);
        if (this.user.myRank()==1){
            goldLabel.setForeground(Color.ORANGE);
        }
        JLabel silverLabel = null;
        JLabel bronzeLabel = null;
        if (User.orderedUsers().length>1){
            silverLabel = new JLabel(User.orderedUsers()[1].getUsername()+" - "+User.orderedUsers()[1].getHighscore());
            silverLabel.setForeground(Color.BLACK);
            if (this.user.myRank()==2){
                silverLabel.setForeground(Color.ORANGE);
            }
            if (User.orderedUsers().length>2){
                bronzeLabel = new JLabel(User.orderedUsers()[2].getUsername()+" - "+User.orderedUsers()[2].getHighscore());
                bronzeLabel.setForeground(Color.BLACK);
                if (this.user.myRank()==3){
                    bronzeLabel.setForeground(Color.ORANGE);
                }
            }
        }
        JLabel rankLabel = new JLabel();
        JLabel rankNumber = new JLabel();
        JButton backButton = new JButton("Back");

        backButton.setFocusPainted(false);

        goldLabel.setFont(new Font("Ink Free",Font.BOLD,30));
        if (silverLabel!=null){
            silverLabel.setFont(new Font("Ink Free",Font.BOLD,30));
        }
        else{
            silverImage.setVisible(false);
        }
        if (bronzeLabel!=null){
            bronzeLabel.setFont(new Font("Ink Free",Font.BOLD,30));
        }
        else{
            bronzeImage.setVisible(false);
        }
        rankLabel.setFont(new Font("Ink Free",Font.BOLD,30));
        rankNumber.setFont(new Font("Arial",Font.BOLD,20));
        backButton.setFont(new Font("Forte",Font.PLAIN,16));

        if (this.user.myRank()<=3){
            rankLabel.setVisible(false);
            rankNumber.setVisible(false);
            rankImage.setVisible(false);
        }
        else{
            rankNumber.setText(String.valueOf(this.user.myRank()));
            rankLabel.setText(this.user.getUsername()+" - "+this.user.getHighscore());
            rankLabel.setForeground(Color.ORANGE);
        }

        // Setting listeners
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainPage(user);
                RankingPage.super.dispose();
            }
        });

        // Adding the buttons and setting the locations
        super.mainPanel.add(goldImage);
        super.mainPanel.add(silverImage);
        super.mainPanel.add(bronzeImage);
        super.mainPanel.add(rankNumber);
        super.mainPanel.add(rankImage);
        super.mainPanel.add(goldLabel);
        if (silverLabel!=null){
            super.mainPanel.add(silverLabel);
        }
        if (bronzeLabel!=null){
            super.mainPanel.add(bronzeLabel);
        }
        super.mainPanel.add(rankLabel);
        super.mainPanel.add(backButton);

        goldImage.setBounds(100,150,100,100);
        goldLabel.setBounds(185,178,300,50);
        silverImage.setBounds(100,200,100,100);
        if (silverLabel!=null){
            silverLabel.setBounds(185,228,300,50);
        }
        bronzeImage.setBounds(100,250,100,100);
        if (bronzeLabel!=null){
            bronzeLabel.setBounds(185,278,300,50);
        }
        rankImage.setBounds(100,320,100,100);
        if (rankNumber.getText().length()==1){
            rankNumber.setBounds(145,315,100,100);
        }
        else{
            rankNumber.setBounds(140,315,100,100);
        }
        rankLabel.setBounds(185,348,300,50);
        backButton.setBounds(200,418,80,30);
    }
}
