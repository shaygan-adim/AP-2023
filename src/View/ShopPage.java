package View;

import Loading.ImageLoader;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ShopPage extends MainFrame{
    // Fields
    private User user;

    // Constructor
    protected ShopPage(User user) {
        super(500, 625, ImageLoader.getShopPageImage());
        this.user = user;

        initComponents();
    }

    // Methods
    @Override
    void initComponents() {
        // Initializing the buttons and configuring them
        JLabel coinImage = new JLabel(ImageLoader.getCoinBiggerImage());
        JLabel coinLabel = new JLabel(String.valueOf(this.user.getCoin()));
        JLabel characterLabel = new JLabel("Characters :");
        List<JLabel> heroesImages = new ArrayList<>();
        List<JLabel> heroesPrices = new ArrayList<>();
        List<JButton> heroesButtons = new ArrayList<>();
        heroesImages.add(new JLabel(ImageLoader.getLuigiSmallImage()));
        heroesImages.add(new JLabel(ImageLoader.getPrincessSmallImage()));
        heroesImages.add(new JLabel(ImageLoader.getYoshiSmallImage()));
        heroesImages.add(new JLabel(ImageLoader.getToadSmallImage()));
        heroesPrices.add(new JLabel(String.valueOf(Luigi.getPrice())));
        heroesPrices.add(new JLabel(String.valueOf(Princess.getPrice())));
        heroesPrices.add(new JLabel(String.valueOf(Yoshi.getPrice())));
        heroesPrices.add(new JLabel(String.valueOf(Toad.getPrice())));
        heroesButtons.add(new JButton("Buy"));
        if (Arrays.asList(user.getOwnedHeroes()).contains(HeroName.LUIGI)){
            heroesButtons.get(0).setText("Owned");
            heroesButtons.get(0).setEnabled(false);
        }
        heroesButtons.add(new JButton("Buy"));
        if (Arrays.asList(user.getOwnedHeroes()).contains(HeroName.PRINCESS)){
            heroesButtons.get(1).setText("Owned");
            heroesButtons.get(1).setEnabled(false);
        }
        heroesButtons.add(new JButton("Buy"));
        if (Arrays.asList(user.getOwnedHeroes()).contains(HeroName.YOSHI)){
            heroesButtons.get(2).setText("Owned");
            heroesButtons.get(2).setEnabled(false);
        }
        heroesButtons.add(new JButton("Buy"));
        if (Arrays.asList(user.getOwnedHeroes()).contains(HeroName.TOAD)){
            heroesButtons.get(3).setText("Owned");
            heroesButtons.get(3).setEnabled(false);
        }

        JButton backButton = new JButton("Back");

        coinLabel.setFont(new Font("Forte",Font.PLAIN,35));
        characterLabel.setFont(new Font("Forte",Font.PLAIN,17));
        backButton.setFont(new Font("Forte",Font.PLAIN,17));
        for (JLabel jLabel : heroesPrices){
            jLabel.setFont(new Font("Forte",Font.PLAIN,20));
        }
        for (JButton jButton : heroesButtons){
            jButton.setFont(new Font("Forte",Font.PLAIN,13));
            jButton.setFocusPainted(false);
        }


        backButton.setFocusPainted(false);

        // Setting listeners
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainPage(user);
                ShopPage.super.dispose();
            }
        });
        heroesButtons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getCoin()<Luigi.getPrice()){
                    JOptionPane.showMessageDialog(null,"You miss "+(Luigi.getPrice()-user.getCoin())+" coins to buy this character.","Not Enough Coins",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to pay "+Luigi.getPrice()+" coins ?", "Confirm purchase", JOptionPane.YES_NO_OPTION);
                    if (dialogResult==JOptionPane.YES_OPTION){
                        user.setCoin(user.getCoin()-Luigi.getPrice());
                        coinLabel.setText(String.valueOf(user.getCoin()));
                        HeroName[] heroNames = Arrays.copyOf(user.getOwnedHeroes(),user.getOwnedHeroes().length+1);
                        heroNames[user.getOwnedHeroes().length] = HeroName.LUIGI;
                        user.setOwnedHeroes(heroNames);
                        try {
                            user.save();
                        } catch (IOException ex) {}
                        heroesButtons.get(0).setText("Owned");
                        heroesButtons.get(0).setEnabled(false);
                        JOptionPane.showMessageDialog(null,"Luigi is added to your characters !","Successful purchase",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        heroesButtons.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getCoin()<Princess.getPrice()){
                    JOptionPane.showMessageDialog(null,"You miss "+(Princess.getPrice()-user.getCoin())+" coins to buy this character.","Not Enough Coins",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to pay "+Princess.getPrice()+" coins ?", "Confirm purchase", JOptionPane.YES_NO_OPTION);
                    if (dialogResult==JOptionPane.YES_OPTION){
                        user.setCoin(user.getCoin()-Princess.getPrice());
                        coinLabel.setText(String.valueOf(user.getCoin()));
                        HeroName[] heroNames = Arrays.copyOf(user.getOwnedHeroes(),user.getOwnedHeroes().length+1);
                        heroNames[user.getOwnedHeroes().length] = HeroName.PRINCESS;
                        user.setOwnedHeroes(heroNames);
                        try {
                            user.save();
                        } catch (IOException ex) {}
                        heroesButtons.get(1).setText("Owned");
                        heroesButtons.get(1).setEnabled(false);
                        JOptionPane.showMessageDialog(null,"Princess is added to your characters !","Successful purchase",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        heroesButtons.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getCoin()<Yoshi.getPrice()){
                    JOptionPane.showMessageDialog(null,"You miss "+(Yoshi.getPrice()-user.getCoin())+" coins to buy this character.","Not Enough Coins",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to pay "+Yoshi.getPrice()+" coins ?", "Confirm purchase", JOptionPane.YES_NO_OPTION);
                    if (dialogResult==JOptionPane.YES_OPTION){
                        user.setCoin(user.getCoin()-Yoshi.getPrice());
                        coinLabel.setText(String.valueOf(user.getCoin()));
                        HeroName[] heroNames = Arrays.copyOf(user.getOwnedHeroes(),user.getOwnedHeroes().length+1);
                        heroNames[user.getOwnedHeroes().length] = HeroName.YOSHI;
                        user.setOwnedHeroes(heroNames);
                        try {
                            user.save();
                        } catch (IOException ex) {}
                        heroesButtons.get(2).setText("Owned");
                        heroesButtons.get(2).setEnabled(false);
                        JOptionPane.showMessageDialog(null,"Yoshi is added to your characters !","Successful purchase",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        heroesButtons.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getCoin()<Toad.getPrice()){
                    JOptionPane.showMessageDialog(null,"You miss "+(Toad.getPrice()-user.getCoin())+" coins to buy this character.","Not Enough Coins",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to pay "+Toad.getPrice()+" coins ?", "Confirm purchase", JOptionPane.YES_NO_OPTION);
                    if (dialogResult==JOptionPane.YES_OPTION){
                        user.setCoin(user.getCoin()-Toad.getPrice());
                        coinLabel.setText(String.valueOf(user.getCoin()));
                        HeroName[] heroNames = Arrays.copyOf(user.getOwnedHeroes(),user.getOwnedHeroes().length+1);
                        heroNames[user.getOwnedHeroes().length] = HeroName.TOAD;
                        user.setOwnedHeroes(heroNames);
                        try {
                            user.save();
                        } catch (IOException ex) {}
                        heroesButtons.get(3).setText("Owned");
                        heroesButtons.get(3).setEnabled(false);
                        JOptionPane.showMessageDialog(null,"Toad is added to your characters !","Successful purchase",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        // Adding the components and setting the locations
        super.mainPanel.add(coinImage);
        super.mainPanel.add(coinLabel);
        super.mainPanel.add(characterLabel);
        for (JLabel jlabel : heroesImages){
            super.mainPanel.add(jlabel);
        }
        for (JLabel jLabel : heroesPrices){
            super.mainPanel.add(jLabel);
        }
        for (JButton jButton : heroesButtons){
            super.mainPanel.add(jButton);
        }
        super.mainPanel.add(backButton);

        coinImage.setBounds(350-40,38-15,100,100);
        coinLabel.setBounds(430-40,67-15,150,50);
        characterLabel.setBounds(30,110,150,55);
        backButton.setBounds(380,400,70,30);

        int i = 0;
        for (JLabel jLabel : heroesImages){
            if (i/110<=3){
                jLabel.setBounds(i+30,160,100,155);
            }
            else{
                jLabel.setBounds((i-4*110)+30,330,100,155);
            }
            i+=110;
        }
        int j = 0;
        for (JLabel jLabel : heroesPrices){
            if (j/110<=3){
                jLabel.setBounds(j+30+37,255,100,155);
            }
            else{
                jLabel.setBounds((j-4*110)+30,340,100,155);
            }
            j+=110;
        }
        int k = 0;
        for (JButton jButton : heroesButtons){
            if (k/110<=3){
                jButton.setBounds(k+30+17,345,70,20);
            }
            else{
                jButton.setBounds((k-4*110)+30,340,70,20);
            }
            k+=110;
        }
    }
}
