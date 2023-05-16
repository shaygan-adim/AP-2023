package Model;

import Loading.UserLoader;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    // Fields
    private final String username;
    private final String password;
    private int coin = 0;
    private int highscore = 0;
    private PartName[] partNames = new PartName[3];
    private int[] partScore = new int[3];
    private int[] partHeart = new int[3];
    private int[] partCoin = new int[3];
    private HeroName[] ownedHeroes = new HeroName[]{HeroName.MARIO};
    private HeroName activeHero = HeroName.MARIO;
    private int activeSlot;

    // Constructor
    public User(String username, String password){
        this.username = username;
        this.password = password;

        try {
            this.save();
        } catch (IOException e) {}
        try {
            UserLoader.loadUsers();
        } catch (IOException e) {}
    }

    // Methods
    public void save() throws IOException {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader("src/Loading/Users/UsersData.json")) {
            // Convert the JSON string to a list of objects
            User[] users = gson.fromJson(fileReader, User[].class);
            List<User> userList = new ArrayList<>();
            if (users!=null){
                for (User user : users){
                    if (!(user.username.equals(this.username))){
                        userList.add(user);
                    }
                }
            }
            userList.add(this);
            String jsonString = gson.toJson(userList);
            try (FileWriter fileWriter = new FileWriter("src/Loading/Users/UsersData.json")) {
                fileWriter.write(jsonString);
            }
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

    // Getters
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public int getCoin() {return coin;}
    public int getHighscore() {return highscore;}
    public PartName[] getPartNames() {return partNames;}
    public int[] getPartScore() {return partScore;}
    public int[] getPartHeart() {return partHeart;}
    public int[] getPartCoin() {return partCoin;}
    public HeroName[] getOwnedHeroes() {return ownedHeroes;}
    public HeroName getActiveHero() {return activeHero;}
    public int getActiveSlot() {return activeSlot;}

    // Setters
    public void setCoin(int coin) {this.coin = coin;}
    public void setHighscore(int highscore) {this.highscore = highscore;}
    public void setPart1(PartName partName) {this.partNames[0] = partName;}
    public void setPart2(PartName partName) {this.partNames[1] = partName;}
    public void setPart3(PartName partName) {this.partNames[2] = partName;}
    public void setPartScore1(int a){this.partScore[0] = a;}
    public void setPartScore2(int a){this.partScore[1] = a;}
    public void setPartScore3(int a){this.partScore[2] = a;}
    public void setPartCoin1(int a){this.partCoin[0] = a;}
    public void setPartCoin2(int a){this.partCoin[1] = a;}
    public void setPartCoin3(int a){this.partCoin[2] = a;}
    public void setPartHeart1(int a){this.partHeart[0] = a;}
    public void setPartHeart2(int a){this.partHeart[1] = a;}
    public void setPartHeart3(int a){this.partHeart[2] = a;}
    public void setOwnedHeroes(HeroName[] ownedHeroes) {this.ownedHeroes = ownedHeroes;}
    public void setActiveHero(HeroName activeHero) {this.activeHero = activeHero;}
    public void setActiveSlot(int activeSlot) {this.activeSlot = activeSlot;}
}
