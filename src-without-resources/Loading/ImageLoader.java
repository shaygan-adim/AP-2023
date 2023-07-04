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
    private static Image koopaRight1Image,koopaRight2Image;
    private static Image koopaLeft1Image,koopaLeft2Image;
    private static Image koopaShellImage;
    private static Image spinyRight1Image,spinyRight2Image,spinyRight3Image,spinyRight4Image;
    private static Image spinyLeft1Image,spinyLeft2Image,spinyLeft3Image,spinyLeft4Image;
    private static Image spinyRightRun1Image,spinyRightRun2Image,spinyRightRun3Image,spinyRightRun4Image;
    private static Image spinyLeftRun1Image,spinyLeftRun2Image,spinyLeftRun3Image,spinyLeftRun4Image;
    private static Image bowserResting;
    private static Image bowserStandingLeft,bowserStandingRight;
    private static Image bowserRightRun1Image,bowserRightRun2Image,bowserRightRun3Image,bowserRightRun4Image;
    private static Image bowserLeftRun1Image,bowserLeftRun2Image,bowserLeftRun3Image,bowserLeftRun4Image;
    private static Image bowserLeftJumpImage,bowserRightJumpImage;
    private static Image bowserOnFloorLeftImage,bowserOnFloorRightImage;
    private static Image bowserJumpAttackUpImage,bowserJumpAttackDownImage;
    private static Image bowserRightFireBall1Image,bowserRightFireBall2Image,bowserRightFireBall3Image,bowserRightFireBall4Image;
    private static Image bowserLeftFireBall1Image,bowserLeftFireBall2Image,bowserLeftFireBall3Image,bowserLeftFireBall4Image;
    private static Image bowserCutSceneRight1Image,bowserCutSceneRight2Image,bowserCutSceneRight3Image,bowserCutSceneRight4Image;
    private static Image bowserCutSceneLeft1Image,bowserCutSceneLeft2Image,bowserCutSceneLeft3Image,bowserCutSceneLeft4Image;
    private static Image bowserNukingRight2Image,bowserNukingRight3Image,bowserNukingRight4Image;
    private static Image bowserNukingLeft2Image,bowserNukingLeft3Image,bowserNukingLeft4Image;
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
    private static Image heroFireGround1,heroFireGround2,heroFireGround3,heroFireGround4;
    private static Image heroFirePreImage;
    private static Image fireBallImage,fireBallTaleImage;
    private static Image BowserFireBallImage;
    private static Image bossfightFireGround1,bossfightFireGround2,bossfightFireGround3,bossfightFireGround4;
    private static Image nukeImage;
    private static Image swordImage;
    private static Image lightning1Image,lightning2Image,lightning3Image,lightning4Image;
    private static Image HPInnerImage,HPOuterImage;
    private static Image dizzyImage;
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

        ImageLoader.koopaRight1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Koopa/KoopaRight1.png"));
        ImageLoader.koopaRight2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Koopa/KoopaRight2.png"));

        ImageLoader.koopaLeft1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Koopa/KoopaLeft1.png"));
        ImageLoader.koopaLeft2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Koopa/KoopaLeft2.png"));

        ImageLoader.koopaShellImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Koopa/KoopaShell.png"));

        ImageLoader.spinyRight1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyRight1.png"));
        ImageLoader.spinyRight2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyRight2.png"));
        ImageLoader.spinyRight3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyRight3.png"));
        ImageLoader.spinyRight4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyRight4.png"));

        ImageLoader.spinyLeft1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyLeft1.png"));
        ImageLoader.spinyLeft2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyLeft2.png"));
        ImageLoader.spinyLeft3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyLeft3.png"));
        ImageLoader.spinyLeft4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyLeft4.png"));

        ImageLoader.spinyRightRun1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyRightRun1.png"));
        ImageLoader.spinyRightRun2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyRight1.png"));
        ImageLoader.spinyRightRun3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyRightRun3.png"));
        ImageLoader.spinyRightRun4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyRight1.png"));

        ImageLoader.spinyLeftRun1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyLeftRun1.png"));
        ImageLoader.spinyLeftRun2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyLeft1.png"));
        ImageLoader.spinyLeftRun3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyLeftRun3.png"));
        ImageLoader.spinyLeftRun4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Spiny/SpinyLeft1.png"));

        ImageLoader.bowserResting = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/WaitingBowser.png"));

        ImageLoader.bowserStandingLeft = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/StandingBowserLeft.png"));
        ImageLoader.bowserStandingRight = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/StandingBowserRight.png"));

        ImageLoader.bowserRightRun1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/RunningBowserLeft1.png"));
        ImageLoader.bowserRightRun2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/RunningBowserLeft2.png"));
        ImageLoader.bowserRightRun3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/RunningBowserLeft3.png"));
        ImageLoader.bowserRightRun4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/RunningBowserLeft4.png"));

        ImageLoader.bowserLeftRun1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/RunningBowserRight1.png"));
        ImageLoader.bowserLeftRun2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/RunningBowserRight2.png"));
        ImageLoader.bowserLeftRun3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/RunningBowserRight3.png"));
        ImageLoader.bowserLeftRun4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/RunningBowserRight4.png"));

        ImageLoader.bowserLeftJumpImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/JumpingBowserLeft.png"));
        ImageLoader.bowserRightJumpImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/JumpingBowserRight.png"));

        ImageLoader.bowserOnFloorLeftImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/OnFloorBowserLeft.png"));
        ImageLoader.bowserOnFloorRightImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/OnFloorBowserRight.png"));

        ImageLoader.bowserJumpAttackUpImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/JumpAttackingUpBowser.png"));
        ImageLoader.bowserJumpAttackDownImage = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/JumpAttackingDownBowser.png"));

        ImageLoader.bowserRightFireBall1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/FiringBowserRight1.png"));
        ImageLoader.bowserRightFireBall2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/FiringBowserRight2.png"));
        ImageLoader.bowserRightFireBall3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/FiringBowserRight3.png"));
        ImageLoader.bowserRightFireBall4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/FiringBowserRight4.png"));

        ImageLoader.bowserLeftFireBall1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/FiringBowserLeft1.png"));
        ImageLoader.bowserLeftFireBall2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/FiringBowserLeft2.png"));
        ImageLoader.bowserLeftFireBall3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/FiringBowserLeft3.png"));
        ImageLoader.bowserLeftFireBall4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/FiringBowserLeft4.png"));

        ImageLoader.bowserCutSceneRight1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/CutsceneBowserRight1.png"));
        ImageLoader.bowserCutSceneRight2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/CutsceneBowserRight2.png"));
        ImageLoader.bowserCutSceneRight3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/CutsceneBowserRight3.png"));
        ImageLoader.bowserCutSceneRight4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/CutsceneBowserRight10.png"));

        ImageLoader.bowserCutSceneLeft1Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/CutsceneBowserLeft1.png"));
        ImageLoader.bowserCutSceneLeft2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/CutsceneBowserLeft2.png"));
        ImageLoader.bowserCutSceneLeft3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/CutsceneBowserLeft3.png"));
        ImageLoader.bowserCutSceneLeft4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/CutsceneBowserLeft10.png"));

        ImageLoader.bowserNukingRight2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/NukingBowserRight2.png"));
        ImageLoader.bowserNukingRight3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/NukingBowserRight3.png"));
        ImageLoader.bowserNukingRight4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/NukingBowserRight4.png"));

        ImageLoader.bowserNukingLeft2Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/NukingBowserLeft2.png"));
        ImageLoader.bowserNukingLeft3Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/NukingBowserLeft3.png"));
        ImageLoader.bowserNukingLeft4Image = ImageIO.read(new File("src/Loading/Graphics/Characters/Bowser/NukingBowserLeft4.png"));

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
        ImageLoader.heroFireGround1 = ImageIO.read(new File("src/Loading/Graphics/Other/Fire/Ground1.png"));
        ImageLoader.heroFireGround2 = ImageIO.read(new File("src/Loading/Graphics/Other/Fire/Ground2.png"));
        ImageLoader.heroFireGround3 = ImageIO.read(new File("src/Loading/Graphics/Other/Fire/Ground3.png"));
        ImageLoader.heroFireGround4 = ImageIO.read(new File("src/Loading/Graphics/Other/Fire/Ground4.png"));
        ImageLoader.heroFirePreImage = ImageIO.read(new File("src/Loading/Graphics/Other/Fire/PreFireBall.png"));
        ImageLoader.fireBallImage = ImageIO.read(new File("src/Loading/Graphics/Other/Fire/FireBall.png"));
        ImageLoader.fireBallTaleImage = ImageIO.read(new File("src/Loading/Graphics/Other/Fire/FireBallTale.png"));
        ImageLoader.BowserFireBallImage = ImageIO.read(new File("src/Loading/Graphics/Other/Fire/BowserFireBall.png"));
        ImageLoader.bossfightFireGround1 = ImageIO.read(new File("src/Loading/Graphics/Other/Fire/Floor1.png"));
        ImageLoader.bossfightFireGround2 = ImageIO.read(new File("src/Loading/Graphics/Other/Fire/Floor2.png"));
        ImageLoader.bossfightFireGround3 = ImageIO.read(new File("src/Loading/Graphics/Other/Fire/Floor3.png"));
        ImageLoader.bossfightFireGround4 = ImageIO.read(new File("src/Loading/Graphics/Other/Fire/Floor4.png"));
        ImageLoader.nukeImage = ImageIO.read(new File("src/Loading/Graphics/Other/Nuke.png"));
        ImageLoader.swordImage = ImageIO.read(new File("src/Loading/Graphics/Other/Sword/Sword.png"));
        ImageLoader.lightning1Image = ImageIO.read(new File("src/Loading/Graphics/Other/Sword/Lightning1.png"));
        ImageLoader.lightning2Image = ImageIO.read(new File("src/Loading/Graphics/Other/Sword/Lightning2.png"));
        ImageLoader.lightning3Image = ImageIO.read(new File("src/Loading/Graphics/Other/Sword/Lightning3.png"));
        ImageLoader.lightning4Image = ImageIO.read(new File("src/Loading/Graphics/Other/Sword/Lightning4.png"));
        ImageLoader.HPInnerImage = ImageIO.read(new File("src/Loading/Graphics/Other/HP/HPInner.png"));
        ImageLoader.HPOuterImage = ImageIO.read(new File("src/Loading/Graphics/Other/HP/HPOuter.png"));
        ImageLoader.dizzyImage = ImageIO.read(new File("src/Loading/Graphics/Other/HP/DizzyStars.png"));
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
    public static Image[] getKoopaRightImages(){
        return new Image[]{koopaRight1Image,koopaRight2Image};
    }
    public static Image[] getKoopaLeftImages(){
        return new Image[]{koopaLeft1Image,koopaLeft2Image};
    }
    public static Image getKoopaShellImage() {return koopaShellImage;}
    public static Image[] getSpinyRightImages(){
        return new Image[]{spinyRight1Image,spinyRight2Image,spinyRight3Image,spinyRight4Image};
    }
    public static Image[] getSpinyLeftImages(){
        return new Image[]{spinyLeft1Image,spinyLeft2Image,spinyLeft3Image,spinyLeft4Image};
    }
    public static Image[] getSpinyRightRunImages(){
        return new Image[]{spinyRightRun1Image,spinyRightRun2Image,spinyRightRun3Image,spinyRightRun4Image};
    }
    public static Image[] getSpinyLeftRunImages(){
        return new Image[]{spinyLeftRun1Image,spinyLeftRun2Image,spinyLeftRun3Image,spinyLeftRun4Image};
    }
    public static Image getBowserResting() {
        return bowserResting;
    }
    public static Image getBowserStandingLeft() {
        return bowserStandingLeft;
    }
    public static Image getBowserStandingRight() {
        return bowserStandingRight;
    }
    public static Image[] getBowserRightRunImages(){
        return new Image[]{bowserRightRun1Image,bowserRightRun2Image,bowserRightRun3Image,bowserRightRun4Image};
    }
    public static Image[] getBowserLeftRunImages(){
        return new Image[]{bowserLeftRun1Image,bowserLeftRun2Image,bowserLeftRun3Image,bowserLeftRun4Image};
    }
    public static Image getBowserLeftJumpImage() {
        return bowserLeftJumpImage;
    }
    public static Image getBowserRightJumpImage() {
        return bowserRightJumpImage;
    }
    public static Image getBowserOnFloorLeftImage() {
        return bowserOnFloorLeftImage;
    }
    public static Image getBowserOnFloorRightImage() {
        return bowserOnFloorRightImage;
    }
    public static Image getBowserJumpAttackUpImage() {
        return bowserJumpAttackUpImage;
    }
    public static Image getBowserJumpAttackDownImage() {
        return bowserJumpAttackDownImage;
    }
    public static Image[] getBowserLeftFiringImage(){
        return new Image[]{bowserLeftFireBall1Image,bowserLeftFireBall2Image,bowserLeftFireBall3Image,bowserLeftFireBall4Image};
    }
    public static Image[] getBowserRightFiringImage(){
        return new Image[]{bowserRightFireBall1Image,bowserRightFireBall2Image,bowserRightFireBall3Image,bowserRightFireBall4Image};
    }
    public static Image[] getBowserCutSceneRightImage(){
        return new Image[]{bowserCutSceneRight1Image,bowserCutSceneRight2Image,bowserCutSceneRight3Image,bowserCutSceneRight3Image,bowserCutSceneRight3Image,bowserCutSceneRight3Image,bowserCutSceneRight3Image,bowserCutSceneRight3Image,bowserCutSceneRight3Image,bowserCutSceneRight4Image};
    }
    public static Image[] getBowserCutSceneLeftImage(){
        return new Image[]{bowserCutSceneLeft1Image,bowserCutSceneLeft2Image,bowserCutSceneLeft3Image,bowserCutSceneLeft3Image,bowserCutSceneLeft3Image,bowserCutSceneLeft3Image,bowserCutSceneLeft3Image,bowserCutSceneLeft3Image,bowserCutSceneLeft3Image,bowserCutSceneLeft4Image};
    }
    public static Image[] getBowserNukingRightImages(){
        return new Image[]{bowserNukingRight3Image,bowserNukingRight2Image,bowserNukingRight3Image,bowserNukingRight4Image};
    }
    public static Image[] getBowserNukingLeftImages(){
        return new Image[]{bowserNukingLeft3Image,bowserNukingLeft2Image,bowserNukingLeft3Image,bowserNukingLeft4Image};
    }
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
    public static Image[] getHeroFireGroundImages(){
        return new Image[]{heroFireGround1,heroFireGround2,heroFireGround3,heroFireGround4};
    }
    public static Image getHeroFirePreImage() {return heroFirePreImage;}
    public static Image getFireBallImage() {return fireBallImage;}
    public static Image getFireBallTaleImage() {return fireBallTaleImage;}
    public static Image getBowserFireBallImage() {
        return BowserFireBallImage;
    }
    public static Image[] getBossfightFireGroundImages(){
        return new Image[]{bossfightFireGround1,bossfightFireGround2,bossfightFireGround3,bossfightFireGround4};
    }
    public static Image getNukeImage() {
        return nukeImage;
    }
    public static Image getSwordImage() {return swordImage;}
    public static Image[] getLightningImages(){
        return new Image[]{lightning1Image,lightning2Image,lightning3Image,lightning4Image};
    }
    public static Image getHPInnerImage() {
        return HPInnerImage;
    }
    public static Image getHPOuterImage() {
        return HPOuterImage;
    }
    public static Image getDizzyImage() {
        return dizzyImage;
    }
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