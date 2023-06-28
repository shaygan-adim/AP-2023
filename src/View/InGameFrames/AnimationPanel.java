package View.InGameFrames;

import Loading.ImageLoader;
import Logic.PhysicsHandler;
import Model.Characters.Enemies.*;
import Model.Characters.Heroes.Hero;
import Model.Characters.Heroes.HeroMode;
import Model.Characters.Heroes.*;
import Model.Items.*;
import Model.Levels.Level;
import Model.Physics.Block;
import Model.Physics.BlockType;
import Model.Physics.Pipe;
import Model.Shots.FireBall;
import Model.Shots.Shot;
import Model.Shots.Sword;

import javax.swing.*;
import java.awt.*;

public class AnimationPanel extends JPanel {
    // Fields
    private final GameLoop gameLoop;
    private final PhysicsHandler physicsHandler;
    private final Level level;
    private final Game game;
    private final Image[] levelImage;
    private final JLabel coinLabel,heartLabel,scoreLabel,timeLabel,levelLabel,coinImage,heartImage,scoreImage,timeImage,summaryLabel;
    private final JButton nextLevelButton,playAgainButton,homeButton;
    private int iterator=0;

    // Constructor
    public AnimationPanel(Level level, Image[] levelImage, PhysicsHandler physicsHandler,Game game){
        this.level = level;
        this.physicsHandler = physicsHandler;
        this.coinLabel = game.getCoinLabel();
        this.heartLabel = game.getHeartLabel();
        this.scoreLabel = game.getScoreLabel();
        this.timeLabel = game.getTimeLabel();
        this.levelLabel = game.getLevelLabel();
        this.coinImage = game.getCoinImage();
        this.heartImage = game.getHeartImage();
        this.scoreImage = game.getScoreImage();
        this.timeImage = game.getTimeImage();
        this.summaryLabel = game.getSummaryLabel();
        this.homeButton = game.getHomeButton();
        this.nextLevelButton = game.getNextLevelButton();
        this.playAgainButton = game.getPlayAgainButton();
        this.game = game;

        this.levelImage = levelImage;
        gameLoop = new GameLoop(this);
        setSize(new Dimension(1280,800));
    }

    // Methods
    @Override
    protected void paintComponent(Graphics g){
        iterator++;
        super.paintComponent(g);

        if (this.level.getDone()==0){
            // Updating the labels
            this.coinLabel.setText(String.valueOf(this.level.getActivePart().getHeroes()[0].getCoin()));
            this.heartLabel.setText(String.valueOf(this.level.getActivePart().getHeroes()[0].getLives()));
            this.scoreLabel.setText(String.valueOf(this.level.getActivePart().getHeroes()[0].getScore()));
            this.timeLabel.setText(String.valueOf(this.level.getActivePart().getTime()-this.level.getActivePart().getStopwatch().passedTime()));
            this.levelLabel.setText("World "+(this.level.getId()+1)+"-"+(this.level.getActivePart().getId()+1));
            if (this.level.getActivePart().getTime()-this.level.getActivePart().getStopwatch().passedTime()<20){
                this.timeLabel.setForeground(Color.red);
            }
            else{
                this.timeLabel.setForeground(Color.BLACK);
            }

            int drawingInteger = -(int)level.getActivePart().getHeroes()[0].getX();
            if (-drawingInteger>4002){
                drawingInteger = -4002;
            }

            g.drawImage(ImageLoader.getLevelBackground(),0,0,1280,800,this);

            // Drawing the Pits
            for (int i = 0 ; i<level.getActivePart().getFloors().length-1 ; i++){
                g.drawImage(ImageLoader.getPitImage(),drawingInteger+150+level.getActivePart().getFloors()[i].getX()+level.getActivePart().getFloors()[i].getWidth(),level.getActivePart().getFloors()[0].getY(),level.getActivePart().getFloors()[i+1].getX()-level.getActivePart().getFloors()[i].getX()-level.getActivePart().getFloors()[i].getWidth(),level.getActivePart().getFloors()[0].getHeight(),this);
            }

            // Drawing the blocks
            for (Block block : this.level.getActivePart().getBlocks()){
                if ((block.getBlockType()== BlockType.COIN || block.getBlockType()==BlockType.COINS || block.getBlockType()==BlockType.SIMPLE) && block.isVisible())
                    g.drawImage(ImageLoader.getRegularBlock(),drawingInteger+block.getX()+150,block.getY(),block.getWidth(),block.getHeight(),this);
                if (block.getBlockType()==BlockType.EMPTY)
                    g.drawImage(ImageLoader.getEmptyBlock(),drawingInteger+block.getX()+150,block.getY(),block.getWidth(),block.getHeight(),this);
                if (block.getBlockType()==BlockType.QUESTION)
                    g.drawImage(ImageLoader.getItemBlock(),drawingInteger+block.getX()+150,block.getY(),block.getWidth(),block.getHeight(),this);
                if (block.getBlockType()==BlockType.SLIME)
                    g.drawImage(ImageLoader.getSlimeBlock(),drawingInteger+block.getX()+150,block.getY(),block.getWidth(),block.getHeight(),this);
            }

            // Drawing the pipes
            for (Pipe pipe : level.getActivePart().getPipes()){
                g.drawImage(ImageLoader.getPipeImage(),drawingInteger+pipe.getX()+150,pipe.getY(),pipe.getWidth(),pipe.getHeight(),this);
            }
            // Drawing the enemies
            for (Enemy enemy : level.getActivePart().getEnemies()){
                if (enemy.isVisible()) {
                    if (enemy instanceof Plant) {
                        g.drawImage(ImageLoader.getPlantImage(), (int) (enemy.getX() - (int) level.getActivePart().getHeroes()[0].getX() + 150), (int) enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
                    }
                    if (enemy instanceof Goomba) {
                        if (((Goomba) enemy).isDeadActivated()){
                            g.drawImage(ImageLoader.getGoombaDeadImage(), (int) (enemy.getX() - (int) level.getActivePart().getHeroes()[0].getX() + 150), (int) enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
                        }
                        else{
                            if (enemy.getVx()>0){
                                g.drawImage(ImageLoader.getGoombaRightImages()[((Goomba) enemy).getFrameNumber()], (int) (enemy.getX() - (int) level.getActivePart().getHeroes()[0].getX() + 150), (int) enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
                            }
                            else {
                                g.drawImage(ImageLoader.getGoombaLeftImages()[((Goomba) enemy).getFrameNumber()], (int) (enemy.getX() - (int) level.getActivePart().getHeroes()[0].getX() + 150), (int) enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
                            }
                            if (iterator%Goomba.getFrameDelay()==0){
                                ((Goomba) enemy).addFrame();
                            }
                        }
                    }
                    if (enemy instanceof Koopa){
                        if (((Koopa) enemy).isDeadActivated()){
                            g.drawImage(ImageLoader.getKoopaShellImage(), (int) (enemy.getX() - (int) level.getActivePart().getHeroes()[0].getX() + 150), (int) enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
                        }
                        else{
                            if (enemy.getVx()>0){
                                g.drawImage(ImageLoader.getKoopaRightImages()[((Koopa) enemy).getFrameNumber()], (int) (enemy.getX() - (int) level.getActivePart().getHeroes()[0].getX() + 150), (int) enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
                            }
                            else {
                                g.drawImage(ImageLoader.getKoopaLeftImages()[((Koopa) enemy).getFrameNumber()], (int) (enemy.getX() - (int) level.getActivePart().getHeroes()[0].getX() + 150), (int) enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
                            }
                            if (iterator%Koopa.getFrameDelay()==0){
                                ((Koopa) enemy).addFrame();
                            }
                        }
                    }
                    if (enemy instanceof Spiny){
                        if (((Spiny) enemy).isRunActivated()){
                            if (enemy.getVx()>0){
                                g.drawImage(ImageLoader.getSpinyRightRunImages()[((Spiny) enemy).getFrameNumber()], (int) (enemy.getX() - (int) level.getActivePart().getHeroes()[0].getX() + 150), (int) enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
                            }
                            else {
                                g.drawImage(ImageLoader.getSpinyLeftRunImages()[((Spiny) enemy).getFrameNumber()], (int) (enemy.getX() - (int) level.getActivePart().getHeroes()[0].getX() + 150), (int) enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
                            }
                            if (iterator%(Spiny.getFrameDelay()-8)==0){
                                ((Spiny) enemy).addFrame();
                            }
                        }
                        else {
                            if (enemy.getVx()>0){
                                g.drawImage(ImageLoader.getSpinyRightImages()[((Spiny) enemy).getFrameNumber()], (int) (enemy.getX() - (int) level.getActivePart().getHeroes()[0].getX() + 150), (int) enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
                            }
                            else {
                                g.drawImage(ImageLoader.getSpinyLeftImages()[((Spiny) enemy).getFrameNumber()], (int) (enemy.getX() - (int) level.getActivePart().getHeroes()[0].getX() + 150), (int) enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
                            }
                            if (iterator%Spiny.getFrameDelay()==0){
                                ((Spiny) enemy).addFrame();
                            }
                        }
                    }
                    if (enemy instanceof Bowser){
                        if (!((Bowser) enemy).isTriggered()){
                            g.drawImage(ImageLoader.getBowserResting(),(int)(enemy.getX() +drawingInteger + 150),(int) enemy.getY(),enemy.getWidth(),enemy.getHeight(),this);
                        }
                        else{
                            if (((Bowser) enemy).isGrabAttacking()){
                                if (!((Bowser) enemy).isGrabHero()){
                                    if (((Bowser) enemy).isToLeft()){
                                        g.drawImage(ImageLoader.getBowserLeftJumpImage(),(int)(enemy.getX() +drawingInteger + 150),(int) enemy.getY(),enemy.getWidth(),enemy.getHeight(),this);
                                    }
                                    else{
                                        g.drawImage(ImageLoader.getBowserRightJumpImage(),(int)(enemy.getX() +drawingInteger + 150),(int) enemy.getY(),enemy.getWidth(),enemy.getHeight(),this);
                                    }
                                }
                                else{
                                    if (((Bowser) enemy).isToLeft()){
                                        g.drawImage(ImageLoader.getBowserOnFloorLeftImage(),(int)(enemy.getX() +drawingInteger + 150),(int) enemy.getY(),enemy.getWidth(),enemy.getHeight(),this);
                                    }
                                    else{
                                        g.drawImage(ImageLoader.getBowserOnFloorRightImage(),(int)(enemy.getX() +drawingInteger + 150),(int) enemy.getY(),enemy.getWidth(),enemy.getHeight(),this);
                                    }
                                }
                            }
                            else if (((Bowser) enemy).isJumpAttacking()){
                                if (enemy.getVy()>0){
                                    g.drawImage(ImageLoader.getBowserJumpAttackUpImage(),(int)(enemy.getX() +drawingInteger + 150),(int) enemy.getY(),enemy.getWidth(),enemy.getHeight(),this);
                                }
                                else{
                                    g.drawImage(ImageLoader.getBowserJumpAttackDownImage(),(int)(enemy.getX() +drawingInteger + 150),(int) enemy.getY(),enemy.getWidth(),enemy.getHeight(),this);
                                }
                            }
                            else if (((Bowser) enemy).isFireBallAttacking()){
                                if (((Bowser) enemy).isToLeft()){
                                    g.drawImage(ImageLoader.getBowserLeftFiringImage()[((Bowser) enemy).getFiringFrameNumber()],(int)(enemy.getX() +drawingInteger + 150),(int) enemy.getY(),enemy.getWidth(),enemy.getHeight(),this);
                                }
                                else{
                                    g.drawImage(ImageLoader.getBowserRightFiringImage()[((Bowser) enemy).getFiringFrameNumber()],(int)(enemy.getX() +drawingInteger + 150),(int) enemy.getY(),enemy.getWidth(),enemy.getHeight(),this);
                                }
                                if (iterator%Bowser.getFrameDelay()*2==0){
                                    ((Bowser) enemy).addFiringFrame();
                                }
                            }
                            else{
                                g.drawImage(ImageLoader.getHPOuterImage(),(int)(enemy.getX() +drawingInteger + 150),(int) enemy.getY()-50,enemy.getWidth(),20,this);
                                g.drawImage(ImageLoader.getHPInnerImage(),(int)(enemy.getX() +drawingInteger + 150 + 38 ),(int) enemy.getY()-50+6,(int)(enemy.getWidth()*(double)212/254*enemy.getLives()/20),(int)((double)12/32*20),this);
                                if (((Bowser) enemy).isToLeft()){
                                    if (enemy.getVx()==0){
                                        g.drawImage(ImageLoader.getBowserStandingLeft(),(int)(enemy.getX() +drawingInteger + 150),(int) enemy.getY(),enemy.getWidth(),enemy.getHeight(),this);
                                    }
                                    else{
                                        g.drawImage(ImageLoader.getBowserLeftRunImages()[((Bowser) enemy).getRunningFrameNumber()],(int)(enemy.getX() +drawingInteger + 150),(int) enemy.getY(),enemy.getWidth(),enemy.getHeight(),this);
                                        if (iterator%Bowser.getFrameDelay()==0){
                                            ((Bowser) enemy).addRunningFrame();
                                        }
                                    }
                                }
                                else{
                                    if (enemy.getVx()==0){
                                        g.drawImage(ImageLoader.getBowserStandingRight(),(int)(enemy.getX() +drawingInteger + 150),(int) enemy.getY(),enemy.getWidth(),enemy.getHeight(),this);
                                    }
                                    else{
                                        g.drawImage(ImageLoader.getBowserRightRunImages()[((Bowser) enemy).getRunningFrameNumber()],(int)(enemy.getX() +drawingInteger + 150),(int) enemy.getY(),enemy.getWidth(),enemy.getHeight(),this);
                                        if (iterator%Bowser.getFrameDelay()==0){
                                            ((Bowser) enemy).addRunningFrame();
                                        }
                                    }
                                }
                            }
                        }
                        if (((Bowser) enemy).isDizzy()){
                            if (((Bowser) enemy).isToLeft()){
                                g.drawImage(ImageLoader.getDizzyImage(),(int)(enemy.getX() +drawingInteger + 150 + 20),(int)enemy.getY()-30,enemy.getWidth()/3,(int)((double)1081/2463*enemy.getWidth()/3),this);
                            }
                            else{
                                g.drawImage(ImageLoader.getDizzyImage(),(int)(enemy.getX() +drawingInteger + 150 + 2*enemy.getWidth()/3 - 20),(int)enemy.getY()-30,enemy.getWidth()/3,(int)((double)1081/2463*enemy.getWidth()/3),this);
                            }
                        }
                        if (((Bowser) enemy).getShot()!=null){
                            g.drawImage(ImageLoader.getBowserFireBallImage(),((Bowser) enemy).getShot().getX()+drawingInteger + 150,((Bowser) enemy).getShot().getY(),((Bowser) enemy).getShot().getWidth(),((Bowser) enemy).getShot().getHeight(),this);
                        }
                    }
                }
            }

            // Drawing the Items
            for (Item item : level.getActivePart().getItems()){
                if (item.isVisible()){
                    if (item instanceof Coin) {
                        g.drawImage(ImageLoader.getCoinInGameImage(),drawingInteger+150+(int)item.getCoordinates()[0],(int)item.getCoordinates()[1],item.getWidth(),item.getHeight(),this);
                    }
                    if (item instanceof Flower){
                        g.drawImage(ImageLoader.getFlowerImage(),drawingInteger+150+(int)item.getCoordinates()[0],(int)item.getCoordinates()[1],item.getWidth(),item.getHeight(),this);
                    }
                    if (item instanceof Mushroom){
                        g.drawImage(ImageLoader.getMushroomImage(),drawingInteger+150+(int)item.getCoordinates()[0],(int)item.getCoordinates()[1],item.getWidth(),item.getHeight(),this);
                    }
                    if (item instanceof Star){
                        g.drawImage(ImageLoader.getStarImage(),drawingInteger+150+(int)item.getCoordinates()[0],(int)item.getCoordinates()[1],item.getWidth(),item.getHeight(),this);
                    }
                }
            }

            // Drawing the heroes
            for (Hero hero : level.getActivePart().getHeroes()){
                if (hero.isVisible()){
                    if (!(hero.isTransitioning() && iterator%2==0)){
                        int X = 150;
                        if (hero.getX()>=4002){
                            X = 150-4002+(int) level.getActivePart().getHeroes()[0].getX();
                        }
                        if (hero.isSeating()){
                            if (hero instanceof Mario){
                                g.drawImage(ImageLoader.getMarioSeatImage(),X,(int)hero.getY(),hero.getWidth(),hero.getHeight(),this);
                            }
                        }
                        else{
                            if (hero.getVy()==0){
                                if (hero.getVx()==0){
                                    if (hero.isStandingOnSomething()){
                                        if (hero instanceof Mario){
                                            g.drawImage(ImageLoader.getMarioInGameImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(), this);
                                        }
                                        if (hero instanceof Luigi){
                                            g.drawImage(ImageLoader.getLuigiInGameImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                        if (hero instanceof Princess){
                                            g.drawImage(ImageLoader.getPrincessInGameImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                        if (hero instanceof Yoshi){
                                            g.drawImage(ImageLoader.getYoshiInGameImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                        if (hero instanceof Toad){
                                            g.drawImage(ImageLoader.getToadInGameImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                    }
                                    else{
                                        if (hero instanceof Mario){
                                            g.drawImage(ImageLoader.getMarioJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                        if (hero instanceof Luigi){
                                            g.drawImage(ImageLoader.getLuigiJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                        if (hero instanceof Princess){
                                            g.drawImage(ImageLoader.getPrincessJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                        if (hero instanceof Yoshi){
                                            if (level.getActivePart().getHeroes()[0].getVx()>=0){
                                                g.drawImage(ImageLoader.getYoshiJumpRightImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            }
                                            else{
                                                g.drawImage(ImageLoader.getYoshiJumpLeftImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            }
                                        }
                                        if (hero instanceof Toad){
                                            g.drawImage(ImageLoader.getToadJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                    }
                                }
                                else if (hero.getVx()>0){
                                    if (hero instanceof Mario){
                                        if (hero.isStandingOnSomething()){
                                            g.drawImage(ImageLoader.getMarioRightImages()[((Mario) hero).getFrameNumber()],X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            if (iterator%Mario.getFrameDelay()==0){
                                                ((Mario) hero).addFrame();
                                            }
                                        }
                                        else{
                                            g.drawImage(ImageLoader.getMarioJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                    }
                                    if (hero instanceof Luigi){
                                        if (hero.isStandingOnSomething()){
                                            g.drawImage(ImageLoader.getLuigiRightImages()[((Luigi) hero).getFrameNumber()],X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            if (iterator%Luigi.getFrameDelay()==0){
                                                ((Luigi) hero).addFrame();
                                            }
                                        }
                                        else{
                                            g.drawImage(ImageLoader.getLuigiJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                    }
                                    if (hero instanceof Princess){
                                        if (hero.isStandingOnSomething()){
                                            g.drawImage(ImageLoader.getPrincessRightImages()[((Princess) hero).getFrameNumber()],X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            if (iterator%Princess.getFrameDelay()==0){
                                                ((Princess) hero).addFrame();
                                            }
                                        }
                                        else{
                                            g.drawImage(ImageLoader.getPrincessJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                    }
                                    if (hero instanceof Yoshi){
                                        if (hero.isStandingOnSomething()){
                                            g.drawImage(ImageLoader.getYoshiRightImages()[((Yoshi) hero).getFrameNumber()],X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            if (iterator%Yoshi.getFrameDelay()==0){
                                                ((Yoshi) hero).addFrame();
                                            }
                                        }
                                        else{
                                            if (level.getActivePart().getHeroes()[0].getVx()>=0){
                                                g.drawImage(ImageLoader.getYoshiJumpRightImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            }
                                            else{
                                                g.drawImage(ImageLoader.getYoshiJumpLeftImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            }
                                        }
                                    }
                                    if (hero instanceof Toad){
                                        if (hero.isStandingOnSomething()){
                                            g.drawImage(ImageLoader.getToadRightImages()[((Toad) hero).getFrameNumber()],X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            if (iterator%Toad.getFrameDelay()==0){
                                                ((Toad) hero).addFrame();
                                            }
                                        }
                                        else{
                                            g.drawImage(ImageLoader.getToadJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                    }
                                }
                                else{
                                    if (hero instanceof Mario){
                                        if (hero.isStandingOnSomething()){
                                            g.drawImage(ImageLoader.getMarioLeftImages()[((Mario) hero).getFrameNumber()],X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            if (iterator%Mario.getFrameDelay()==0){
                                                ((Mario) hero).addFrame();
                                            }
                                        }
                                        else{
                                            g.drawImage(ImageLoader.getMarioJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                    }
                                    if (hero instanceof Luigi){
                                        if (hero.isStandingOnSomething()){
                                            g.drawImage(ImageLoader.getLuigiLeftImages()[((Luigi) hero).getFrameNumber()],X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            if (iterator%Luigi.getFrameDelay()==0){
                                                ((Luigi) hero).addFrame();
                                            }
                                        }
                                        else{
                                            g.drawImage(ImageLoader.getLuigiJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                    }
                                    if (hero instanceof Princess){
                                        if (hero.isStandingOnSomething()){
                                            g.drawImage(ImageLoader.getPrincessLeftImages()[((Princess) hero).getFrameNumber()],X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            if (iterator%Princess.getFrameDelay()==0){
                                                ((Princess) hero).addFrame();
                                            }
                                        }
                                        else{
                                            g.drawImage(ImageLoader.getPrincessJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                    }
                                    if (hero instanceof Yoshi){
                                        if (hero.isStandingOnSomething()){
                                            g.drawImage(ImageLoader.getYoshiLeftImages()[((Yoshi) hero).getFrameNumber()],X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            if (iterator%Yoshi.getFrameDelay()==0){
                                                ((Yoshi) hero).addFrame();
                                            }
                                        }
                                        else{
                                            if (level.getActivePart().getHeroes()[0].getVx()>=0){
                                                g.drawImage(ImageLoader.getYoshiJumpRightImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            }
                                            else{
                                                g.drawImage(ImageLoader.getYoshiJumpLeftImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            }
                                        }
                                    }
                                    if (hero instanceof Toad){
                                        if (hero.isStandingOnSomething()){
                                            g.drawImage(ImageLoader.getToadLeftImages()[((Toad) hero).getFrameNumber()],X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                            if (iterator%Toad.getFrameDelay()==0){
                                                ((Toad) hero).addFrame();
                                            }
                                        }
                                        else{
                                            g.drawImage(ImageLoader.getToadJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                        }
                                    }
                                }
                            }
                            else{
                                if (hero instanceof Mario){
                                    g.drawImage(ImageLoader.getMarioJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                }
                                if (hero instanceof Luigi){
                                    g.drawImage(ImageLoader.getLuigiJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                }
                                if (hero instanceof Princess){
                                    g.drawImage(ImageLoader.getPrincessJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                }
                                if (hero instanceof Yoshi){
                                    if (level.getActivePart().getHeroes()[0].getVx()>=0){
                                        g.drawImage(ImageLoader.getYoshiJumpRightImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                    }
                                    else{
                                        g.drawImage(ImageLoader.getYoshiJumpLeftImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                    }
                                }
                                if (hero instanceof Toad){
                                    g.drawImage(ImageLoader.getToadJumpImage(),X,(int)hero.getY(),hero.getWidth(), hero.getHeight(),this);
                                }
                            }
                        }
                        if (hero.getMode()== HeroMode.FIRE){
                            boolean draw = true;
                            for (Shot shot : hero.getShots()){
                                if (shot instanceof FireBall && shot.isVisible()){
                                    draw = false;
                                }
                            }
                            if (draw){
                                g.drawImage(ImageLoader.getHeroFirePreImage(),X-15,(int) hero.getY()-40,30,48,this);
                            }
                            if (hero.isStandingOnSomething()){
                                g.drawImage(ImageLoader.getHeroFireGroundImages()[hero.getFireFrameNumber()], X,(int) hero.getY()+hero.getHeight()-35,80,35,this);
                                if (iterator%Hero.getFireFrameDelay()==0){
                                    hero.addFireFrame();
                                }
                            }
                        }
                        for (Shot shot : hero.getShots()){
                            if (shot.isVisible()){
                                if (shot instanceof FireBall){
                                    g.drawImage(ImageLoader.getFireBallImage(),drawingInteger+150+shot.getX(),shot.getY(),shot.getWidth(), shot.getHeight(), this);
                                    if (shot.getVelocity()>0){
                                        g.drawImage(ImageLoader.getFireBallTaleImage(),drawingInteger+150+shot.getX()-100,shot.getY(), 100, shot.getHeight(), this);
                                    }
                                    else{
                                        g.drawImage(ImageLoader.getFireBallTaleImage(),drawingInteger+150+shot.getX()+shot.getWidth(),shot.getY(), 100, shot.getHeight(), this);
                                    }
                                }
                                if (shot instanceof Sword){
                                    g.drawImage(ImageLoader.getSwordImage(),drawingInteger+150+shot.getX(),shot.getY(),shot.getWidth(), shot.getHeight(), this);
                                }
                            }
                        }
                        if (hero.isDizzy()){
                            g.drawImage(ImageLoader.getDizzyImage(),drawingInteger+150+10+(int)hero.getX(),(int)hero.getY()-20,(int)(hero.getWidth()*(double)3/4),20,this);
                        }
                        if (hero.isShieldActivated()){
                            g.drawImage(ImageLoader.getShieldImage(),X-60,(int) hero.getY()-40,200,200,this);
                        }
                    }
                }
            }
            Hero myHero = level.getActivePart().getHeroes()[0];
            int X = 150;
            if (myHero.getX()>=4002){
                X = 150-4002+(int) myHero.getX();
            }
            if (myHero.isSwordActivated()){
                if (myHero.getStopwatchForLightning().passedTime()<800){
                    g.drawImage(ImageLoader.getLightningImages()[myHero.getLightningFrameNumber()], X+100,0,(int)((myHero.getY()+myHero.getHeight())*125/772),(int)myHero.getY()+myHero.getHeight()+10,this);
                    if (iterator%Hero.getLightningFrameDelay()==0){
                        myHero.addLightningFrame();
                    }
                }
            }
        }
        else if (this.level.getDone()==1){
            stopTheAnimation();

            this.homeButton.setVisible(true);
            this.playAgainButton.setVisible(true);
            this.nextLevelButton.setVisible(true);

            this.coinLabel.setText(String.valueOf(this.level.getTotalCoins()));
            this.heartLabel.setText(String.valueOf(this.level.getActivePart().getHeroes()[0].getLives()));
            this.scoreLabel.setText(String.valueOf(this.level.getActivePart().getHeroes()[0].getScore()));
            this.timeLabel.setText(String.valueOf(this.level.getTotalTime()));
            this.levelLabel.setText("Level "+(this.level.getId()+1));

            this.timeLabel.setForeground(Color.BLACK);
            this.summaryLabel.setVisible(true);


            heartImage.setBounds(150,390,100,100);
            heartLabel.setBounds(235,420,100,50);
            coinImage.setBounds(150,450,100,100);
            coinLabel.setBounds(235,480,150,50);
            scoreImage.setBounds(150,510,100,100);
            scoreLabel.setBounds(235,540,150,50);
            timeImage.setBounds(150,570,100,100);
            timeLabel.setBounds(235,600,150,50);
            levelLabel.setBounds(177,360,200,50);
            summaryLabel.setBounds(50,270,300,50);
            homeButton.setBounds(1020,450,80,30);
            playAgainButton.setBounds(1008,550,110,30);
            nextLevelButton.setBounds(1008,500,110,30);

            g.drawImage(ImageLoader.getPassedImage(),0,0,this);
        }
        else{
            stopTheAnimation();

            this.heartImage.setVisible(false);
            this.heartLabel.setVisible(false);

            this.homeButton.setVisible(true);
            this.playAgainButton.setVisible(true);

            this.coinLabel.setText(String.valueOf(this.level.getTotalCoins()));
            this.scoreLabel.setText(String.valueOf(this.level.getActivePart().getHeroes()[0].getScore()));
            this.timeLabel.setText(String.valueOf(this.level.getTotalTime()));
            this.levelLabel.setText("Level "+(this.level.getId()+1));

            this.timeLabel.setForeground(Color.BLACK);
            this.summaryLabel.setVisible(true);

            coinImage.setBounds(150,390,100,100);
            coinLabel.setBounds(235,420,150,50);
            scoreImage.setBounds(150,450,100,100);
            scoreLabel.setBounds(235,480,150,50);
            timeImage.setBounds(150,510,100,100);
            timeLabel.setBounds(235,540,150,50);
            levelLabel.setBounds(177,360,200,50);
            summaryLabel.setBounds(50,270,300,50);
            homeButton.setBounds(1020,500,80,30);
            playAgainButton.setBounds(1008,550,110,30);

            g.drawImage(ImageLoader.getGameOverImage(),0,0,this);
        }
    }
    public void startTheAnimation() {gameLoop.start();}
    public void stopTheAnimation() {gameLoop.stop();}
    public void update(){
        physicsHandler.updatePhysics();
        this.repaint();
    }
}
