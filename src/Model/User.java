package Model;

import Loading.UserLoader;
import Model.Characters.Heroes.HeroName;
import Model.Levels.Level;
import Model.Levels.PartName;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
    // Fields
    private final String username;
    private final String password;
    private int coin = 0;
    private int highscore = 0;
    private Level[] savedLevels = new Level[3];
    private HeroName[] ownedHeroes = new HeroName[]{HeroName.MARIO};
    private HeroName activeHero = HeroName.MARIO;
    private int activeSlot;

    // Constructor
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    // Methods
    public void save()  {
        ArrayList<User> loadedObj = null;
        try {
            FileInputStream fileIn = new FileInputStream("src/Loading/Users/UsersData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadedObj = (ArrayList<User>) in.readObject();
            in.close();
            fileIn.close();
            loadedObj.remove(this);
            loadedObj.add(this);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("src/Loading/Users/UsersData.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(loadedObj);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int myRank(){
        int i = 1;
        for (User user : User.orderedUsers()){
            if (user.getUsername().equals(this.username)){
                return i;
            }
            i++;
        }
        return -1;
    }
    public static User[] orderedUsers(){
        try {
            UserLoader.loadUsers();
        } catch (IOException e) {}
        User[] users = UserLoader.getUsers();
        boolean sorted = false;
        User helperUser;
        while (!sorted){
            sorted = true;
            for (int i = 0 ; i< users.length-1 ; i++){
                if (users[i].highscore<users[i+1].highscore){
                    sorted = false;
                    helperUser = users[i];
                    users[i] = users[i+1];
                    users[i+1] = helperUser;
                }
            }
        }
        return users;
    }
    @Override
    public boolean equals(Object o){
        if (!(o instanceof User)) return false;
        if (((User)o).getUsername().equals(username)) return true;
        return false;
    }

    // Getters
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public int getCoin() {return coin;}
    public int getHighscore() {return highscore;}
    public HeroName[] getOwnedHeroes() {return ownedHeroes;}
    public HeroName getActiveHero() {return activeHero;}
    public int getActiveSlot() {return activeSlot;}

    public Level[] getSavedLevels() {
        return savedLevels;
    }

    // Setters
    public void setCoin(int coin) {this.coin = coin;}
    public void setHighscore(int highscore) {this.highscore = highscore;}
    public void setOwnedHeroes(HeroName[] ownedHeroes) {this.ownedHeroes = ownedHeroes;}
    public void setActiveHero(HeroName activeHero) {this.activeHero = activeHero;}
    public void setActiveSlot(int activeSlot) {this.activeSlot = activeSlot;}

}
