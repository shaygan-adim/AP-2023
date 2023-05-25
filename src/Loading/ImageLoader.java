package Loading;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

abstract public class ImageLoader {
    // Fields
    private static Image mainMenuImage;
    private static Image userMenuImage;
    private static Image profileMenuImage;
    private static Image rankingPageImage;
    private static Image shopPageImage;
    private static Image gameOverImage;
    private static Image passedImage;
    private static Image marioInGameImage,luigiInGameImage,princessInGameImage,yoshiInGameImage,toadInGameImage;
    private static Image marioJumpImage,marioSeatImage;
    private static Image luigiJumpImage;
    private static Image princessJumpImage;
    private static Image yoshiJumpLeftImage,yoshiJumpRightImage;
    private static Image toadJumpImage;
    private static Image marioRight1Image,marioRight2Image,marioRight3Image,marioRight4Image;
    private static Image marioLeft1Image,marioLeft2Image,marioLeft3Image,marioLeft4Image;
    private static Image luigiRight1Image,luigiRight2Image,luigiRight3Image,luigiRight4Image;
    private static Image luigiLeft1Image,luigiLeft2Image,luigiLeft3Image,luigiLeft4Image;
    private static Image princessRight1Image,princessRight2Image,princessRight3Image,princessRight4Image;
    private static Image princessLeft1Image,princessLeft2Image,princessLeft3Image,princessLeft4Image;
    private static Image yoshiRight1Image,yoshiRight2Image,yoshiRight3Image,yoshiRight4Image;
    private static Image yoshiLeft1Image,yoshiLeft2Image,yoshiLeft3Image,yoshiLeft4Image;
    private static Image toadRight1Image,toadRight2Image,toadRight3Image,toadRight4Image;
    private static Image toadLeft1Image,toadLeft2Image,toadLeft3Image,toadLeft4Image;
    private static Image goombaRight1Image,goombaRight2Image,goombaRight3Image,goombaRight4Image,goombaRight5Image,goombaRight6Image;
    private static Image goombaLeft1Image,goombaLeft2Image,goombaLeft3Image,goombaLeft4Image,goombaLeft5Image,goombaLeft6Image;
    private static Image goombaDeadImage;
    private static Image plantImage;
    private static Image itemBlock,regularBlock,emptyBlock,slimeBlock;
    private static Image levelBackground;
    private static Image pipeImage;
    private static Image pitImage;
    private static ImageIcon icon;
    private static ImageIcon yImage;
    private static ImageIcon nImage;
    private static ImageIcon coinImage;
    private static ImageIcon coinBiggerImage;
    private static ImageIcon highscoreImage;
    private static Image coinInGameImage;
    private static Image flowerImage;
    private static Image mushroomImage;
    private static Image starImage;
    private static ImageIcon heartImage;
    private static ImageIcon scoreImage;
    private static ImageIcon timeImage;
    private static ImageIcon goldImage,silverImage,bronzeImage,rankImage;
    private static Image shieldImage;
    private static ImageIcon marioImage,luigiImage,princessImage,yoshiImage,toadImage;
    private static ImageIcon marioSmallImage,luigiSmallImage,princessSmallImage,yoshiSmallImage,toadSmallImage;

    // Methods
    public static void load() throws IOException {
        // Loading backgrounds
        ImageLoader.mainMenuImage = ImageIO.read(new File("src/Loading/Graphics/Backgrounds/MainMenu.jpg"));
        ImageLoader.userMenuImage = ImageIO.read(new File("src/Loading/Graphics/Backgrounds/UserMenu.jpg"));
        ImageLoader.profileMenuImage = ImageIO.read(new File("src/Loading/Graphics/Backgrounds/ProfileMenu.jpg"));
        ImageLoader.rankingPageImage = ImageIO.read(new File("src/Loading/Graphics/Backgrounds/RankingPage.jpg"));
        ImageLoader.shopPageImage = ImageIO.read(new File("src/Loading/Graphics/Backgrounds/ShopPage.jpg"));
        ImageLoader.gameOverImage = ImageIO.read(new File("src/Loading/Graphics/Backgrounds/GameOver.jpg"));
        ImageLoader.passedImage = ImageIO.read(new File("src/Loading/Graphics/Backgrounds/Passed.jpg"));

        // Loading in game characters
        ImageLoader.marioInGameImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Mario/MarioInGame.png"));
        ImageLoader.luigiInGameImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Luigi/LuigiInGame.png"));
        ImageLoader.princessInGameImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Princess/PrincessInGame.png"));
        ImageLoader.yoshiInGameImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Yoshi/YoshiInGame.png"));
        ImageLoader.toadInGameImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Toad/ToadInGame.png"));

        ImageLoader.marioRight1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Mario/MarioRight1.png"));
        ImageLoader.marioRight2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Mario/MarioRight2.png"));
        ImageLoader.marioRight3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Mario/MarioRight3.png"));
        ImageLoader.marioRight4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Mario/MarioRight4.png"));

        ImageLoader.marioLeft1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Mario/MarioLeft1.png"));
        ImageLoader.marioLeft2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Mario/MarioLeft2.png"));
        ImageLoader.marioLeft3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Mario/MarioLeft3.png"));
        ImageLoader.marioLeft4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Mario/MarioLeft4.png"));

        ImageLoader.luigiRight1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Luigi/LuigiRight1.png"));
        ImageLoader.luigiRight2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Luigi/LuigiRight2.png"));
        ImageLoader.luigiRight3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Luigi/LuigiRight3.png"));
        ImageLoader.luigiRight4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Luigi/LuigiRight4.png"));

        ImageLoader.luigiLeft1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Luigi/LuigiLeft1.png"));
        ImageLoader.luigiLeft2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Luigi/LuigiLeft2.png"));
        ImageLoader.luigiLeft3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Luigi/LuigiLeft3.png"));
        ImageLoader.luigiLeft4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Luigi/LuigiLeft4.png"));

        ImageLoader.princessRight1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Princess/PrincessRight1.png"));
        ImageLoader.princessRight2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Princess/PrincessRight2.png"));
        ImageLoader.princessRight3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Princess/PrincessRight3.png"));
        ImageLoader.princessRight4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Princess/PrincessRight4.png"));

        ImageLoader.princessLeft1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Princess/PrincessLeft1.png"));
        ImageLoader.princessLeft2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Princess/PrincessLeft2.png"));
        ImageLoader.princessLeft3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Princess/PrincessLeft3.png"));
        ImageLoader.princessLeft4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Princess/PrincessLeft4.png"));

        ImageLoader.yoshiRight1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Yoshi/YoshiRight1.png"));
        ImageLoader.yoshiRight2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Yoshi/YoshiRight2.png"));
        ImageLoader.yoshiRight3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Yoshi/YoshiRight3.png"));
        ImageLoader.yoshiRight4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Yoshi/YoshiRight4.png"));

        ImageLoader.yoshiLeft1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Yoshi/YoshiLeft1.png"));
        ImageLoader.yoshiLeft2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Yoshi/YoshiLeft2.png"));
        ImageLoader.yoshiLeft3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Yoshi/YoshiLeft3.png"));
        ImageLoader.yoshiLeft4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Yoshi/YoshiLeft4.png"));

        ImageLoader.toadRight1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Toad/ToadRight1.png"));
        ImageLoader.toadRight2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Toad/ToadRight2.png"));
        ImageLoader.toadRight3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Toad/ToadRight3.png"));
        ImageLoader.toadRight4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Toad/ToadRight4.png"));

        ImageLoader.toadLeft1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Toad/ToadLeft1.png"));
        ImageLoader.toadLeft2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Toad/ToadLeft2.png"));
        ImageLoader.toadLeft3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Toad/ToadLeft3.png"));
        ImageLoader.toadLeft4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Toad/ToadLeft4.png"));

        ImageLoader.goombaRight1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Goomba/GoombaRight1.png"));
        ImageLoader.goombaRight2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Goomba/GoombaRight2.png"));
        ImageLoader.goombaRight3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Goomba/GoombaRight3.png"));
        ImageLoader.goombaRight4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Goomba/GoombaRight4.png"));
        ImageLoader.goombaRight5Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Goomba/GoombaRight5.png"));
        ImageLoader.goombaRight6Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Goomba/GoombaRight6.png"));

        ImageLoader.goombaLeft1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Goomba/GoombaLeft1.png"));
        ImageLoader.goombaLeft2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Goomba/GoombaLeft2.png"));
        ImageLoader.goombaLeft3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Goomba/GoombaLeft3.png"));
        ImageLoader.goombaLeft4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Goomba/GoombaLeft4.png"));
        ImageLoader.goombaLeft5Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Goomba/GoombaLeft5.png"));
        ImageLoader.goombaLeft6Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Goomba/GoombaLeft6.png"));

        ImageLoader.goombaDeadImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Goomba/GoombaDead.png"));

        ImageLoader.marioJumpImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Mario/MarioJump.png"));
        ImageLoader.marioSeatImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Mario/MarioSeat.png"));
        ImageLoader.luigiJumpImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Luigi/LuigiJump.png"));
        ImageLoader.princessJumpImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Princess/PrincessJump.png"));
        ImageLoader.yoshiJumpRightImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Yoshi/YoshiJumpRight.png"));
        ImageLoader.yoshiJumpLeftImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Yoshi/YoshiJumpLeft.png"));
        ImageLoader.toadJumpImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Toad/ToadJump.png"));

        ImageLoader.plantImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Plant/Plant2.png"));

        // Loading blocks
        ImageLoader.regularBlock = ImageIO.read(new File("src/Loading/Graphics/Levels/Blocks/RegularBlock.png"));
        ImageLoader.itemBlock = ImageIO.read(new File("src/Loading/Graphics/Levels/Blocks/ItemBlock.png"));
        ImageLoader.emptyBlock = ImageIO.read(new File("src/Loading/Graphics/Levels/Blocks/EmptyBlock.png"));
        ImageLoader.slimeBlock = ImageIO.read(new File("src/Loading/Graphics/Levels/Blocks/SlimeBlock.png"));

        // Loading level's stuff
        ImageLoader.levelBackground = ImageIO.read(new File("src/Loading/Graphics/Levels/Background.jpg"));
        ImageLoader.pipeImage = ImageIO.read(new File("src/Loading/Graphics/Levels/Other/Pipe.png"));
        ImageLoader.pitImage = ImageIO.read(new File("src/Loading/Graphics/Levels/Other/Pit.png"));

        // Loading Items
        ImageLoader.coinInGameImage = ImageIO.read(new File("src/Loading/Graphics/Other/CoinInGAme.png"));
        ImageLoader.flowerImage = ImageIO.read(new File("src/Loading/Graphics/Levels/Items/Flower.png"));
        ImageLoader.mushroomImage = ImageIO.read(new File("src/Loading/Graphics/Levels/Items/Mushroom.png"));
        ImageLoader.starImage = ImageIO.read(new File("src/Loading/Graphics/Levels/Items/Star.png"));

        // Loading other stuff
        ImageLoader.yImage = new ImageIcon("src/Loading/Graphics/Other/Y.png");
        ImageLoader.nImage = new ImageIcon("src/Loading/Graphics/Other/N.png");
        ImageLoader.coinImage = new ImageIcon("src/Loading/Graphics/Other/Coin.png");
        ImageLoader.coinBiggerImage = new ImageIcon("src/Loading/Graphics/Other/CoinBigger.png");
        ImageLoader.coinInGameImage = ImageIO.read(new File("src/Loading/Graphics/Other/CoinInGAme.png"));
        ImageLoader.highscoreImage = new ImageIcon("src/Loading/Graphics/Other/Highscore.png");
        ImageLoader.heartImage = new ImageIcon("src/Loading/Graphics/Other/Heart.png");
        ImageLoader.scoreImage = new ImageIcon("src/Loading/Graphics/Other/Score.png");
        ImageLoader.timeImage = new ImageIcon("src/Loading/Graphics/Other/Time.png");
        ImageLoader.goldImage = new ImageIcon("src/Loading/Graphics/Other/Gold.png");
        ImageLoader.silverImage = new ImageIcon("src/Loading/Graphics/Other/Silver.png");
        ImageLoader.bronzeImage = new ImageIcon("src/Loading/Graphics/Other/Bronze.png");
        ImageLoader.rankImage = new ImageIcon("src/Loading/Graphics/Other/Rank.png");
        ImageLoader.shieldImage = ImageIO.read(new File("src/Loading/Graphics/Other/Shield.png"));
        ImageLoader.marioImage = new ImageIcon("src/Loading/Graphics/Characters/Mario/Mario.png");
        ImageLoader.marioSmallImage = new ImageIcon("src/Loading/Graphics/Characters/Mario/MarioSmall.png");
        ImageLoader.luigiImage = new ImageIcon("src/Loading/Graphics/Characters/Luigi/Luigi.png");
        ImageLoader.luigiSmallImage = new ImageIcon("src/Loading/Graphics/Characters/Luigi/LuigiSmall.png");
        ImageLoader.princessImage = new ImageIcon("src/Loading/Graphics/Characters/Princess/Princess.png");
        ImageLoader.princessSmallImage = new ImageIcon("src/Loading/Graphics/Characters/Princess/PrincessSmall.png");
        ImageLoader.yoshiImage = new ImageIcon("src/Loading/Graphics/Characters/Yoshi/Yoshi.png");
        ImageLoader.yoshiSmallImage = new ImageIcon("src/Loading/Graphics/Characters/Yoshi/YoshiSmall.png");
        ImageLoader.toadImage = new ImageIcon("src/Loading/Graphics/Characters/Toad/Toad.png");
        ImageLoader.toadSmallImage = new ImageIcon("src/Loading/Graphics/Characters/Toad/ToadSmall.png");
        ImageLoader.icon = new ImageIcon("src/Loading/Graphics/Other/icon.png");
    }

    // Getters
    public static Image getMainMenuImage() {return mainMenuImage;}
    public static Image getUserMenuImage() {return userMenuImage;}
    public static Image getProfileMenuImage() {return profileMenuImage;}
    public static Image getRankingPageImage() {return rankingPageImage;}
    public static Image getShopPageImage() {return shopPageImage;}
    public static Image getGameOverImage() {return gameOverImage;}
    public static Image getPassedImage() {return passedImage;}
    public static Image getMarioInGameImage() {return marioInGameImage;}
    public static Image getLuigiInGameImage() {return luigiInGameImage;}
    public static Image getPrincessInGameImage() {return princessInGameImage;}
    public static Image getYoshiInGameImage() {return yoshiInGameImage;}
    public static Image getToadInGameImage() {return toadInGameImage;}
    public static Image[] getMarioRightImages(){
        return new Image[]{marioRight1Image,marioRight2Image,marioRight3Image,marioRight4Image};
    }
    public static Image[] getMarioLeftImages(){
        return new Image[]{marioLeft1Image,marioLeft2Image,marioLeft3Image,marioLeft4Image};
    }
    public static Image[] getLuigiRightImages(){
        return new Image[]{luigiRight1Image,luigiRight2Image,luigiRight3Image,luigiRight4Image};
    }
    public static Image[] getLuigiLeftImages(){
        return new Image[]{luigiLeft1Image,luigiLeft2Image,luigiLeft3Image,luigiLeft4Image};
    }
    public static Image[] getPrincessRightImages(){
        return new Image[]{princessRight1Image,princessRight2Image,princessRight3Image,princessRight4Image};
    }
    public static Image[] getPrincessLeftImages(){
        return new Image[]{princessLeft1Image,princessLeft2Image,princessLeft3Image,princessLeft4Image};
    }
    public static Image[] getYoshiRightImages(){
        return new Image[]{yoshiRight1Image,yoshiRight2Image,yoshiRight3Image,yoshiRight4Image};
    }
    public static Image[] getYoshiLeftImages(){
        return new Image[]{yoshiLeft1Image,yoshiLeft2Image,yoshiLeft3Image,yoshiLeft4Image};
    }
    public static Image[] getToadRightImages(){
        return new Image[]{toadRight1Image,toadRight2Image,toadRight3Image,toadRight4Image};
    }
    public static Image[] getToadLeftImages(){
        return new Image[]{toadLeft1Image,toadLeft2Image,toadLeft3Image,toadLeft4Image};
    }
    public static Image[] getGoombaRightImages(){
        return new Image[]{goombaRight1Image,goombaRight2Image,goombaRight3Image,goombaRight4Image,goombaRight5Image,goombaRight6Image};
    }
    public static Image[] getGoombaLeftImages(){
        return new Image[]{goombaLeft1Image,goombaLeft2Image,goombaLeft3Image,goombaLeft4Image,goombaLeft5Image,goombaLeft6Image};
    }
    public static Image getGoombaDeadImage() {return goombaDeadImage;}
    public static Image getMarioJumpImage() {
        return marioJumpImage;
    }
    public static Image getMarioSeatImage() {return marioSeatImage;}
    public static Image getLuigiJumpImage() {
        return luigiJumpImage;
    }
    public static Image getPrincessJumpImage() {
        return princessJumpImage;
    }
    public static Image getYoshiJumpLeftImage() {return yoshiJumpLeftImage;}
    public static Image getYoshiJumpRightImage() {return yoshiJumpRightImage;}
    public static Image getToadJumpImage() {
        return toadJumpImage;
    }
    public static Image getPlantImage() {return plantImage;}
    public static Image getItemBlock() {return itemBlock;}
    public static Image getRegularBlock() {return regularBlock;}
    public static Image getEmptyBlock() {return emptyBlock;}
    public static Image getSlimeBlock() {return slimeBlock;}
    public static Image getLevelBackground() {return levelBackground;}
    public static Image getPipeImage() {return pipeImage;}
    public static Image getPitImage() {return pitImage;}
    public static ImageIcon getyImage() {return yImage;}
    public static ImageIcon getnImage() {return nImage;}
    public static ImageIcon getCoinImage() {return coinImage;}
    public static ImageIcon getCoinBiggerImage() {return coinBiggerImage;}
    public static ImageIcon getHighscoreImage() {return highscoreImage;}
    public static Image getCoinInGameImage() {return coinInGameImage;}
    public static Image getFlowerImage() {return flowerImage;}
    public static Image getMushroomImage() {return mushroomImage;}
    public static Image getStarImage() {return starImage;}
    public static ImageIcon getHeartImage() {return heartImage;}
    public static ImageIcon getScoreImage() {return scoreImage;}
    public static ImageIcon getTimeImage() {return timeImage;}
    public static ImageIcon getGoldImage() {return goldImage;}
    public static ImageIcon getSilverImage() {return silverImage;}
    public static ImageIcon getBronzeImage() {return bronzeImage;}
    public static ImageIcon getRankImage() {return rankImage;}
    public static Image getShieldImage() {return shieldImage;}
    public static ImageIcon getMarioImage() {return marioImage;}
    public static ImageIcon getMarioSmallImage() {return marioSmallImage;}
    public static ImageIcon getLuigiImage() {return luigiImage;}
    public static ImageIcon getLuigiSmallImage() {return luigiSmallImage;}
    public static ImageIcon getPrincessImage() {return princessImage;}
    public static ImageIcon getPrincessSmallImage() {return princessSmallImage;}
    public static ImageIcon getYoshiImage() {return yoshiImage;}
    public static ImageIcon getYoshiSmallImage() {return yoshiSmallImage;}
    public static ImageIcon getToadImage() {return toadImage;}
    public static ImageIcon getToadSmallImage() {return toadSmallImage;}
    public static ImageIcon getIcon() {return icon;}
}
