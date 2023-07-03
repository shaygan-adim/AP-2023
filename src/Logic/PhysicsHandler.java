package Logic;

import Model.Characters.Enemies.*;
import Model.Characters.Heroes.Hero;
import Model.Characters.Heroes.HeroMode;
import Model.Items.*;
import Model.Levels.Level;
import Model.Levels.PartName;
import Model.Physics.Block;
import Model.Physics.BlockType;
import Model.Physics.Floor;
import Model.Physics.Pipe;
import Model.Shots.FireBall;
import Model.Shots.Nuke;
import Model.Shots.Shot;
import Model.Shots.Sword;
import Model.User;

import java.io.*;
import java.lang.management.BufferPoolMXBean;
import java.util.Random;

public class PhysicsHandler {
    // Fields
    private final Level level;
    private final User user;
    private static int g = -5;
    private static final double dt = 0.1D;
    private boolean changed;
    private boolean paused = false;

    // Constructor
    public PhysicsHandler(Level level, User user){
        this.level = level;
        this.user = user;
    }

    // Methods
    public void updatePhysics(){
        changed = false;
        // Physics of shots
        if (level.getActivePart().getHeroes()[0].isSwordActivated() && level.getActivePart().getHeroes()[0].getStopwatchForLightning().passedTime()>800){
            int x = (int)level.getActivePart().getHeroes()[0].getX()+20;
            int y = (int)level.getActivePart().getHeroes()[0].getY()+50;
            if (level.getActivePart().getHeroes()[0].getMode() == HeroMode.MINI){
                y = (int)level.getActivePart().getHeroes()[0].getY()+20;
                x = (int)level.getActivePart().getHeroes()[0].getX()+10;
            }
            level.getActivePart().getHeroes()[0].getShots().add(new Sword(new int[]{x,y}));
            level.getActivePart().getHeroes()[0].setSwordActivated(false);
        }
        for (Hero hero : level.getActivePart().getHeroes()){
            for (Shot shot : hero.getShots()){
                if (shot.isVisible()){
                    if (shot instanceof FireBall && shot.getStopwatch().passedTime()>FireBall.getPeriod()){
                        shot.setVisible(false);
                    }
                    if (shot instanceof Sword && shot.getX()<hero.getX()){
                        shot.setVisible(false);
                        hero.getStopwatchForCooldown().start();
                    }
                    if (shot.isVisible()){
                        if (shot instanceof Sword){
                            shot.setVelocity(shot.getVelocity()-Sword.getAcceleration()*dt);
                        }
                        shot.addToX((int) (shot.getVelocity()*dt));
                    }
                }
            }
        }
        for (Enemy enemy : level.getActivePart().getEnemies()){
            if (enemy instanceof Bowser){
                if (((Bowser) enemy).getShot()!=null){
                    ((Bowser) enemy).getShot().addToX((int)(((Bowser) enemy).getShot().getVelocity()*dt));
                }
                if (((Bowser) enemy).isNukeAppearance()){
                    ((Bowser) enemy).setNukeAppearance(false);
                    ((Bowser) enemy).setNuke(new Nuke((int)level.getActivePart().getHeroes()[0].getX()+level.getActivePart().getHeroes()[0].getWidth()/3));
                }
                if (((Bowser) enemy).getNuke()!=null){
                    ((Bowser) enemy).getNuke().setVelocity(((Bowser) enemy).getNuke().getVelocity()+g*dt);
                    ((Bowser) enemy).getNuke().addToY(-(int)(((Bowser) enemy).getNuke().getVelocity()*dt));
                }
            }
        }

        // Physics of Items
        for (Item item : this.level.getActivePart().getItems()){
            if (item.isVisible()){
                if (item instanceof Mushroom){
                    if (((Mushroom) item).getStopwatch().passedTime()>3 && !((Mushroom) item).isActivated()){
                        item.setVx(10);
                        item.setVy(10);
                        ((Mushroom) item).setActivated(true);
                    }
                }
                if (item instanceof Star){
                    if (((Star) item).getStopwatch().passedTime()>3 && !((Star) item).isActivated()){
                        ((Star) item).getStopwatch().start();
                        item.setVx(10);
                        item.setVy(10);
                        ((Star) item).setActivated(true);
                    }
                    if (((Star) item).getStopwatch().passedTime()>1 && ((Star) item).isActivated() && ((Star) item).isStandingOnSomething()){
                        ((Star) item).getStopwatch().start();
                        item.setVy(20);
                    }
                }
                item.addVy(g*dt);
                int n = 0;

                // Handling the physics of blocks
                for (Block block : level.getActivePart().getBlocks()) {
                    if (!(block.isVisible() && item.getY() - item.getVy() * dt >= block.getY() - item.getHeight() && item.getY() + item.getHeight() < block.getY() + block.getHeight() && item.getX() > block.getX() - item.getWidth() && item.getX() < block.getX() + block.getWidth())) {
                        n++;
                    }
                    else{
                        if (item instanceof Star){
                            ((Star) item).setStandingOnSomething(true);
                        }
                    }
                    if (item instanceof Mushroom || item instanceof Star){
                        if (block.isVisible() && item.getY()+item.getHeight()>block.getY() && item.getY()<block.getY()+block.getHeight() && item.getX()+ item.getWidth()+item.getVx()*dt>block.getX() && item.getX()<block.getX()){
                            item.setVx(-item.getVx());
                        }
                        if (block.isVisible() && item.getY()+item.getHeight()>block.getY() && item.getY()<block.getY()+block.getHeight() && item.getX()+item.getVx()*dt<block.getX()+ block.getWidth() && item.getX()+ item.getWidth()>block.getX()+block.getWidth()){
                            item.setVx(-item.getVx());
                        }
                    }
                }

                // Handling the physics of pipes
                for (Pipe pipe : level.getActivePart().getPipes()) {
                    if (!(item.getY() - item.getVy() * dt >= pipe.getY() - item.getHeight() && item.getY() + item.getHeight() < pipe.getY() + pipe.getHeight() && item.getX() > pipe.getX() - item.getWidth() && item.getX() < pipe.getX() + pipe.getWidth())) {
                        n++;
                    }
                    else{
                        if (item instanceof Star){
                            ((Star) item).setStandingOnSomething(true);
                        }
                    }
                    if (item instanceof Mushroom || item instanceof Star){
                        if (item.getY()+item.getHeight()>pipe.getY() && item.getY()<pipe.getY()+pipe.getHeight() && item.getX()+ item.getWidth()+item.getVx()*dt>pipe.getX() && item.getX()<pipe.getX()){
                            item.setVx(-item.getVx());
                        }
                        if (item.getY()+item.getHeight()>pipe.getY() && item.getY()<pipe.getY()+pipe.getHeight() && item.getX()+item.getVx()*dt<pipe.getX()+ pipe.getWidth() && item.getX()+ item.getWidth()>pipe.getX()+pipe.getWidth()){
                            item.setVx(-item.getVx());
                        }
                    }
                }

                // Handling the physics of floors
                int N = 0;
                for (Floor floor : level.getActivePart().getFloors()) {
                    if (!(item.getY() - item.getVy() * dt >= floor.getY() - item.getHeight() && item.getY() + item.getHeight() < floor.getY() + floor.getHeight() && item.getX() > floor.getX() - item.getWidth() && item.getX() < floor.getX() + floor.getWidth())) {
                        n++;
                        N++;
                    }
                    else{
                        if (item instanceof Star){
                            ((Star) item).setStandingOnSomething(true);
                        }
                    }
                    if (item instanceof Mushroom || item instanceof Star){
                        if (item.getY()+item.getHeight()>floor.getY() && item.getY()<floor.getY()+floor.getHeight() && item.getX()+ item.getWidth()+item.getVx()*dt>floor.getX() && item.getX()<floor.getX()){
                            item.setVx(-item.getVx());
                        }
                        if (item.getY()+item.getHeight()>floor.getY() && item.getY()<floor.getY()+floor.getHeight() && item.getX()+item.getVx()*dt<floor.getX()+ floor.getWidth() && item.getX()+ item.getWidth()>floor.getX()+floor.getWidth()){
                            item.setVx(-item.getVx());
                        }
                    }
                }
                if (N==this.level.getActivePart().getFloors().length && item.getY()+item.getHeight()/2>this.level.getActivePart().getFloors()[0].getY()){
                    item.setVisible(false);
                }
                if (!(n==level.getActivePart().getFloors().length+ level.getActivePart().getBlocks().length+ level.getActivePart().getPipes().length)){
                    item.setVy(0);
                }
                else{
                    if (item instanceof Star){
                        ((Star) item).setStandingOnSomething(false);
                    }
                }
                if (item.getX()+item.getVx()*dt<150){
                    item.setVx(item.getVy()*-1);
                }
                item.addY(-item.getVy()*dt);
                item.addX(item.getVx()*dt);
                if (item.getX()<0){
                    item.setVisible(false);
                }
            }
        }

        //Physics of Enemies
        for (Enemy enemy : this.level.getActivePart().getEnemies()){
            if (enemy.isVisible() && !(enemy instanceof Plant)){
                if (enemy instanceof Bowser){
                    bossMechs((Bowser) enemy);
                }
                enemy.setStandingOnSomething(false);
                enemy.addVy(g*dt);
                if (enemy instanceof Spiny && !level.getActivePart().getHeroes()[0].isTransitioning()){
                    if (enemy.getY()+enemy.getHeight()>level.getActivePart().getHeroes()[0].getY() && enemy.getY()<level.getActivePart().getHeroes()[0].getY()+level.getActivePart().getHeroes()[0].getHeight()){
                        if (Math.pow(enemy.getX()+enemy.getWidth()/2-level.getActivePart().getHeroes()[0].getX()-level.getActivePart().getHeroes()[0].getWidth()/2,2)+Math.pow(enemy.getY()+enemy.getHeight()/2-level.getActivePart().getHeroes()[0].getY()-level.getActivePart().getHeroes()[0].getHeight()/2,2)<Math.pow(5*level.getActivePart().getBlocks()[0].getHeight(),2)){
                            if (!((Spiny) enemy).isRunActivated()){
                                if (enemy.getX()+enemy.getWidth()/2>level.getActivePart().getHeroes()[0].getX()+level.getActivePart().getHeroes()[0].getWidth()/2){
                                    enemy.setVx(-5);
                                }
                                else{
                                    enemy.setVx(5);
                                }
                            }
                            ((Spiny) enemy).setRunActivated(true);
                        }
                        else{
                            ((Spiny) enemy).setRunActivated(false);
                        }
                    }
                    else{
                        ((Spiny) enemy).setRunActivated(false);
                    }
                    if (((Spiny) enemy).isRunActivated()){
                        if (enemy.getVx()>0){
                            enemy.addVx(Spiny.getAcceleration()*dt);
                        }
                        else{
                            enemy.addVx(-Spiny.getAcceleration()*dt);
                        }
                    }
                    else{
                        if (enemy.getVx()>0){
                            enemy.setVx(2.5);
                        }
                        else{
                            enemy.setVx(-2.5);
                        }
                    }
                }
                if (enemy instanceof Spiny && level.getActivePart().getHeroes()[0].isTransitioning()){
                    ((Spiny) enemy).setRunActivated(false);
                    if (enemy.getVx()>0){
                        enemy.setVx(2.5);
                    }
                    else{
                        enemy.setVx(-2.5);
                    }
                }
                int n = 0;
                int nr = 0;
                int nl = 0;

                // Handling the physics of blocks
                for (Block block : level.getActivePart().getBlocks()) {
                    if (!(block.isVisible() && enemy.getY() - enemy.getVy() * dt >= block.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < block.getY() + block.getHeight() && enemy.getX() > block.getX() - enemy.getWidth() && enemy.getX() < block.getX() + block.getWidth())) {
                        n++;
                    }
                    else{
                        enemy.setStandingOnSomething(true);
                    }
                    if (!(block.isVisible() && enemy.getY() - enemy.getVy() * dt >= block.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < block.getY() + block.getHeight() && enemy.getX()+enemy.getWidth() > block.getX() - enemy.getWidth() && enemy.getX()+enemy.getWidth() < block.getX() + block.getWidth())) {
                        if (enemy.getVx()>0){
                            nr++;
                        }
                    }
                    if (!(block.isVisible() && enemy.getY() - enemy.getVy() * dt >= block.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < block.getY() + block.getHeight() && enemy.getX()-enemy.getWidth() > block.getX() - enemy.getWidth() && enemy.getX()-enemy.getWidth() < block.getX() + block.getWidth())) {
                        if (enemy.getVx()<0){
                            nl++;
                        }
                    }
                    if (block.isVisible() && enemy.getY()+enemy.getHeight()>block.getY() && enemy.getY()<block.getY()+block.getHeight() && enemy.getX()+ enemy.getWidth()+enemy.getVx()*dt>block.getX() && enemy.getX()<block.getX()){
                        enemy.setVx(-enemy.getVx());
                    }
                    if (block.isVisible() && enemy.getY()+enemy.getHeight()>block.getY() && enemy.getY()<block.getY()+block.getHeight() && enemy.getX()+enemy.getVx()*dt<block.getX()+ block.getWidth() && enemy.getX()+ enemy.getWidth()>block.getX()+block.getWidth()){
                        enemy.setVx(-enemy.getVx());
                    }
                }

                // Handling the physics of pipes
                for (Pipe pipe : level.getActivePart().getPipes()) {
                    if (!(enemy.getY() - enemy.getVy() * dt >= pipe.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < pipe.getY() + pipe.getHeight() && enemy.getX() > pipe.getX() - enemy.getWidth() && enemy.getX() < pipe.getX() + pipe.getWidth())) {
                        n++;
                    }
                    else{
                        enemy.setStandingOnSomething(true);
                    }
                    if (!(enemy.getY() - enemy.getVy() * dt >= pipe.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < pipe.getY() + pipe.getHeight() && enemy.getX()+enemy.getWidth() > pipe.getX() - enemy.getWidth() && enemy.getX()+enemy.getWidth() < pipe.getX() + pipe.getWidth())) {
                        if (enemy.getVx()>0){
                            nr++;
                        }
                    }
                    if (!(enemy.getY() - enemy.getVy() * dt >= pipe.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < pipe.getY() + pipe.getHeight() && enemy.getX()-enemy.getWidth() > pipe.getX() - enemy.getWidth() && enemy.getX()-enemy.getWidth() < pipe.getX() + pipe.getWidth())) {
                        if (enemy.getVx()<0){
                            nl++;
                        }
                    }
                    if (enemy.getY()+enemy.getHeight()>pipe.getY() && enemy.getY()<pipe.getY()+pipe.getHeight() && enemy.getX()+ enemy.getWidth()+enemy.getVx()*dt>pipe.getX() && enemy.getX()<pipe.getX()){
                        enemy.setVx(-enemy.getVx());
                    }
                    if (enemy.getY()+enemy.getHeight()>pipe.getY() && enemy.getY()<pipe.getY()+pipe.getHeight() && enemy.getX()+enemy.getVx()*dt<pipe.getX()+ pipe.getWidth() && enemy.getX()+ enemy.getWidth()>pipe.getX()+pipe.getWidth()){
                        enemy.setVx(-enemy.getVx());
                    }
                }

                // Handling the physics of floors
                int N = 0;
                for (Floor floor : level.getActivePart().getFloors()) {
                    if (!(enemy.getY() - enemy.getVy() * dt >= floor.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < floor.getY() + floor.getHeight() && enemy.getX() > floor.getX() - enemy.getWidth() && enemy.getX() < floor.getX() + floor.getWidth())) {
                        n++;
                        N++;
                    }
                    else{
                        enemy.setStandingOnSomething(true);
                        if (enemy instanceof Bowser){
                            if (((Bowser) enemy).isGrabAttacking() && !((Bowser) enemy).isGrabHero()){
                                ((Bowser) enemy).setGrabAttacking(false);
                                ((Bowser) enemy).getAttackReloadStopwatch().start();
                                enemy.setVx(0);
                            }
                            if (((Bowser) enemy).isJumpAttacking()){
                                ((Bowser) enemy).setJumpAttacking(false);
                                if (level.getActivePart().getHeroes()[0].isStandingOnSomething() && level.getActivePart().getHeroes()[0].getY()+level.getActivePart().getHeroes()[0].getHeight()>level.getActivePart().getFloors()[0].getY()-20){
                                    level.getActivePart().getHeroes()[0].setDizzy(true);
                                    level.getActivePart().getHeroes()[0].getStopwatchForDizzy().start();
                                }
                                ((Bowser) enemy).getAttackReloadStopwatch().start();
                            }
                        }
                    }
                    if (!(enemy.getY() - enemy.getVy() * dt >= floor.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < floor.getY() + floor.getHeight() && enemy.getX()+enemy.getWidth() > floor.getX() - enemy.getWidth() && enemy.getX()+enemy.getWidth() < floor.getX() + floor.getWidth())) {
                        if (enemy.getVx()>0){
                            nr++;
                        }
                    }
                    if (!(enemy.getY() - enemy.getVy() * dt >= floor.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < floor.getY() + floor.getHeight() && enemy.getX()-enemy.getWidth() > floor.getX() - enemy.getWidth() && enemy.getX()-enemy.getWidth() < floor.getX() + floor.getWidth())) {
                        if (enemy.getVx()<0){
                            nl++;
                        }
                    }
                    if (enemy.getY()+enemy.getHeight()>floor.getY() && enemy.getY()<floor.getY()+floor.getHeight() && enemy.getX()+ enemy.getWidth()+enemy.getVx()*dt>floor.getX() && enemy.getX()<floor.getX()){
                        enemy.setVx(-enemy.getVx());
                    }
                    if (enemy.getY()+enemy.getHeight()>floor.getY() && enemy.getY()<floor.getY()+floor.getHeight() && enemy.getX()+enemy.getVx()*dt<floor.getX()+ floor.getWidth() && enemy.getX()+ enemy.getWidth()>floor.getX()+floor.getWidth()){
                        enemy.setVx(-enemy.getVx());
                    }
                }
                if (N==this.level.getActivePart().getFloors().length && enemy.getY()+enemy.getHeight()/2>this.level.getActivePart().getFloors()[0].getY()){
                    enemy.setVisible(false);
                }
                if (!(n==level.getActivePart().getFloors().length+ level.getActivePart().getBlocks().length+ level.getActivePart().getPipes().length)){
                    enemy.setVy(0);
                }
                if (nr==level.getActivePart().getFloors().length+ level.getActivePart().getBlocks().length+ level.getActivePart().getPipes().length || nl==level.getActivePart().getFloors().length+ level.getActivePart().getBlocks().length+ level.getActivePart().getPipes().length){
                    if (enemy.isStandingOnSomething()){
                        enemy.setVx(-enemy.getVx());
                    }
                }

                enemy.addY(-enemy.getVy()*dt);
                if (enemy instanceof Bowser && ((Bowser) enemy).isTriggered()){
                    if (enemy.getX()+enemy.getWidth()+enemy.getVx()*dt>level.getActivePart().getHeroes()[0].getBossBoundries()[1]-level.getActivePart().getBlocks()[0].getWidth()*3){
                        enemy.setVx(0);
                    }
                    if (enemy.getX()+enemy.getVx()*dt<level.getActivePart().getHeroes()[0].getBossBoundries()[0]+level.getActivePart().getBlocks()[0].getWidth()*3){
                        enemy.setVx(0);
                    }
                }
                enemy.addX(enemy.getVx()*dt);
                if (enemy.getX()<0){
                    enemy.setVisible(false);
                }
            }
        }

        // Physics of Heroes
        for (Hero hero : level.getActivePart().getHeroes()){
            if (hero.getStopwatchForDizzy().passedTime()>3000 && hero.isDizzy()){
                hero.setDizzy(false);
            }
            if (hero.getStopwatchForTransitioning().passedTime()>2000){
                hero.setTransitioning(false);
            }
            if (this.level.getActivePart().getStopwatch().passedTime()>=this.level.getActivePart().getTime()){
                this.level.addTime((int)this.level.getActivePart().getStopwatch().passedTime());
                this.level.getActivePart().getStopwatch().start();
                die(hero);
            }
            int n = 0;

            if (!hero.isJumping()){
                hero.setVy(hero.getVy()+g*dt);
            }

            // Handling the physics of blocks
            for (Block block : level.getActivePart().getBlocks()){
                    if (block.isVisible() && hero.getY()-hero.getVy()*dt>=block.getY()-hero.getHeight() && hero.getY()+ hero.getHeight()<block.getY()+block.getHeight() && hero.getX()>block.getX()-hero.getWidth() && hero.getX()<block.getX()+ block.getWidth()) {
                        if (block.getBlockType() == BlockType.SLIME && Math.abs(hero.getVy())>5) {
                            n++;
                            if (Math.abs(hero.getVy())<45){
                                hero.setVy(-hero.getVy()*1.2);
                            }
                            else{
                                hero.setVy(-hero.getVy());
                            }
                        }
                        else{
                            hero.setStandingOnSomething(true);
                        }
                    }
                    else{
                        n++;
                    }
                    if (block.isVisible() && hero.getY()- hero.getVy()*dt<block.getY()+block.getHeight() && hero.getY()>=block.getY()-hero.getHeight() && hero.getX()>block.getX()-hero.getWidth() && hero.getX()<block.getX()+ block.getWidth()){
                        hero.setVy(0);
                        hero.setY(hero.getY()+5);
                        if (block.getBlockType()==BlockType.SIMPLE && hero.getMode()!=HeroMode.MINI){
                            hero.addScore(100);
                            block.setVisible(false);
                            changed=true;
                        }
                        if (block.getBlockType()==BlockType.COIN){
                            block.setBlockType(BlockType.SIMPLE);
                            block.getItemsInside()[0].setVisible(true);
                            level.getActivePart().addBlockCoin((Coin) block.getItemsInside()[0]);
                            hero.addScore(100);
                            changed=true;
                        }
                        if (block.getBlockType()==BlockType.COINS){
                            if (block.getItemsInside().length==1){
                                block.setBlockType(BlockType.EMPTY);
                            }
                            block.setItemsInside(new Item[block.getItemsInside().length-1]);
                            hero.addCoin();
                            hero.addScore(100);
                            changed=true;
                        }
                        if (block.getBlockType()==BlockType.QUESTION){
                            block.getItemsInside()[0].setVisible(true);
                            if (block.getItemsInside()[0] instanceof Mushroom){
                                ((Mushroom) block.getItemsInside()[0]).getStopwatch().start();
                            }
                            if (block.getItemsInside()[0] instanceof Star){
                                ((Star) block.getItemsInside()[0]).getStopwatch().start();
                            }
                            this.level.getActivePart().getItems().add(block.getItemsInside()[0]);
                            hero.addScore(25);
                            block.setBlockType(BlockType.EMPTY);
                            changed=true;
                        }

                    }
                    if (block.isVisible() && hero.getY()+hero.getHeight()>block.getY() && hero.getY()<block.getY()+block.getHeight() && hero.getX()+ hero.getWidth()+hero.getVx()*dt>block.getX() && hero.getX()<block.getX()){
                        hero.setVx(0);
                    }
                    if (block.isVisible() && hero.getY()+hero.getHeight()>block.getY() && hero.getY()<block.getY()+block.getHeight() && hero.getX()+hero.getVx()*dt<block.getX()+ block.getWidth() && hero.getX()+ hero.getWidth()>block.getX()+block.getWidth()){
                        hero.setVx(0);
                    }
            }

            // Handling the physics of pipes
            for (Pipe pipe : level.getActivePart().getPipes()){
                if (hero.getY()-hero.getVy()*dt>=pipe.getY()-hero.getHeight() && hero.getY()+ hero.getHeight()<pipe.getY()+pipe.getHeight() && hero.getX()>pipe.getX()-hero.getWidth() && hero.getX()<pipe.getX()+ pipe.getWidth()) {
                    hero.setStandingOnSomething(true);
                }
                else{
                    n++;
                }
                if (hero.getY()- hero.getVy()*dt<pipe.getY()+pipe.getHeight() && hero.getY()>=pipe.getY()-hero.getHeight() && hero.getX()>pipe.getX()-hero.getWidth() && hero.getX()<pipe.getX()+ pipe.getWidth()){
                    hero.setVy(0);
                }
                if (hero.getY()+hero.getHeight()>pipe.getY() && hero.getY()<pipe.getY()+pipe.getHeight() && hero.getX()+ hero.getWidth()+hero.getVx()*dt>pipe.getX() && hero.getX()<pipe.getX()){
                    hero.setVx(0);
                }
                if (hero.getY()+hero.getHeight()>pipe.getY() && hero.getY()<pipe.getY()+pipe.getHeight() && hero.getX()+hero.getVx()*dt<pipe.getX()+ pipe.getWidth() && hero.getX()+ hero.getWidth()>pipe.getX()+pipe.getWidth()){
                    hero.setVx(0);
                }
            }

            // Handling the physics of floors
            int N = 0;
            for (Floor floor : level.getActivePart().getFloors()){
                if (hero.getY()-hero.getVy()*dt>=floor.getY()-hero.getHeight() && hero.getX()>floor.getX()-hero.getWidth() && hero.getX()<floor.getX()+ floor.getWidth()) {
                    hero.setStandingOnSomething(true);
                }
                else{
                    n++;
                    N++;
                }
                if (!hero.isStandingOnSomething() && hero.getY()+hero.getHeight()>floor.getY() && hero.getY()<floor.getY()+floor.getHeight() && hero.getX()+ hero.getWidth()+hero.getVx()*dt>floor.getX() && hero.getX()<floor.getX()){
                    hero.setVx(0);
                }
                if (!hero.isStandingOnSomething() && hero.getY()+hero.getHeight()>floor.getY() && hero.getY()<floor.getY()+floor.getHeight() && hero.getX()+hero.getVx()*dt<floor.getX()+ floor.getWidth() && hero.getX()+ hero.getWidth()>floor.getX()+floor.getWidth()){
                    hero.setVx(0);
                }
            }
            if (N==this.level.getActivePart().getFloors().length && hero.getY()+hero.getHeight()/2>this.level.getActivePart().getFloors()[0].getY()){
                hero.setMode(HeroMode.MINI);
                die(hero);
            }
            if (n==level.getActivePart().getFloors().length+ level.getActivePart().getBlocks().length+ level.getActivePart().getPipes().length){
                hero.setStandingOnSomething(false);
                hero.setJumping(false);
            }
            else{
                if (!hero.isJumping()){
                    hero.setVy(0);
                }
            }
            hero.addY(-hero.getVy()*dt);
            if (hero.isBossTrapped() && (hero.getBossBoundries()[0]>hero.getX()+hero.getVx()*dt||hero.getX()+hero.getVx()*dt+hero.getWidth()>hero.getBossBoundries()[1])){
                hero.setVx(0);
            }
            if (hero.getX()+ hero.getVx()*dt>150) hero.addX(hero.getVx()*dt);
            else hero.setVx(0);
        }
        checkEnemies();
        shotsCollisionCheck();
        itemsCollisionCheck();
        updatePlants();
        updateActivePart();
//        if (changed){
//            if (level.getActivePart().getId()==0){
//                if (this.user.getActiveSlot()==1){
//                    this.user.setPart1(PartName.L1P1);
//                    this.user.setPartScore1(0);
//                    this.user.setPartCoin1(this.level.getActivePart().getHeroes()[0].getCoin());
//                    this.user.setPartHeart1(this.level.getActivePart().getHeroes()[0].getLives());
//                }
//                if (this.user.getActiveSlot()==2){
//                    this.user.setPart2(PartName.L1P1);
//                    this.user.setPartScore2(0);
//                    this.user.setPartCoin1(this.level.getActivePart().getHeroes()[0].getCoin());
//                    this.user.setPartHeart2(this.level.getActivePart().getHeroes()[0].getLives());
//                }
//                if (this.user.getActiveSlot()==3){
//                    this.user.setPart3(PartName.L1P1);
//                    this.user.setPartScore3(0);
//                    this.user.setPartCoin1(this.level.getActivePart().getHeroes()[0].getCoin());
//                    this.user.setPartHeart3(this.level.getActivePart().getHeroes()[0].getLives());
//                }
//                try {
//                    user.save();
//                } catch (Exception e) {}
//            }
//            else{
//                if (this.user.getActiveSlot()==1){
//                    this.user.setPart1(PartName.L1P2);
//                    this.user.setPartCoin1(this.level.getActivePart().getHeroes()[0].getCoin());
//                    this.user.setPartScore1(this.level.getActivePart().getHeroes()[0].getScore());
//                    this.user.setPartHeart1(this.level.getActivePart().getHeroes()[0].getLives());
//                }
//                if (this.user.getActiveSlot()==2){
//                    this.user.setPart2(PartName.L1P2);
//                    this.user.setPartCoin1(this.level.getActivePart().getHeroes()[0].getCoin());
//                    this.user.setPartScore2(this.level.getActivePart().getHeroes()[0].getScore());
//                    this.user.setPartHeart2(this.level.getActivePart().getHeroes()[0].getLives());
//                }
//                if (this.user.getActiveSlot()==3){
//                    this.user.setPart3(PartName.L1P2);
//                    this.user.setPartCoin1(this.level.getActivePart().getHeroes()[0].getCoin());
//                    this.user.setPartScore3(this.level.getActivePart().getHeroes()[0].getScore());
//                    this.user.setPartHeart3(this.level.getActivePart().getHeroes()[0].getLives());
//                }
//                try {
//                    user.save();
//                } catch (Exception e) {}
//            }
//        }
    }
    public void jump(){
        boolean beStill = false;
        for (Enemy enemy : level.getActivePart().getEnemies()){
            if (enemy instanceof Bowser){
                if (((Bowser) enemy).isPhase2Activated() && !((Bowser) enemy).isPhase2Fire()){
                    beStill=true;
                    stand();
                    stop();
                }
            }
        }
        if (!beStill){
            if (!level.getActivePart().getHeroes()[0].isSwordActivated()){
                if (level.getActivePart().getHeroes()[0].getVy()==0 && level.getActivePart().getHeroes()[0].isStandingOnSomething()){
                    if (level.getActivePart().getHeroes()[0].isDizzy()){
                        Random random = new Random();
                        if (random.nextDouble() < 0.5){
                            level.getActivePart().getHeroes()[0].setVx(-20);
                        }
                        else {
                            level.getActivePart().getHeroes()[0].setVx(20);
                        }
                    }
                    else{
                        level.getActivePart().getHeroes()[0].setY(level.getActivePart().getHeroes()[0].getY()-5);
                        level.getActivePart().getHeroes()[0].setVy(45);
                        level.getActivePart().getHeroes()[0].setJumping(true);
                    }
                }
            }
        }
    }
    public void right(){
        boolean beStill = false;
        for (Enemy enemy : level.getActivePart().getEnemies()){
            if (enemy instanceof Bowser){
                if (((Bowser) enemy).isPhase2Activated() && !((Bowser) enemy).isPhase2Fire()){
                    beStill=true;
                    stand();
                    stop();
                }
            }
        }
        if (!beStill){
            boolean dontDoAnyhting = false;
            for (Enemy enemy : level.getActivePart().getEnemies()){
                if (enemy instanceof Bowser){
                    if (((Bowser) enemy).isGrabHero()){
                        ((Bowser) enemy).setToLeft(false);
                        enemy.setVx(3);
                        ((Bowser) enemy).addRightTry();
                        dontDoAnyhting=true;
                    }
                    if (((Bowser) enemy).isDizzy() && !level.getActivePart().getHeroes()[0].isStandingOnSomething()){
                        dontDoAnyhting=true;
                    }
                }
            }
            if (!dontDoAnyhting){
                if (!level.getActivePart().getHeroes()[0].isSwordActivated()) {
                    if (level.getActivePart().getHeroes()[0].isDizzy()){
                        Random random = new Random();
                        if (random.nextDouble() < 0.5){
                            level.getActivePart().getHeroes()[0].setVx(-20);
                        }
                        else {
                            jump();
                        }
                    }
                    else{
                        level.getActivePart().getHeroes()[0].setVx(40);
                    }
                }
            }
        }
    }
    public void stop(){
        for (Enemy enemy : level.getActivePart().getEnemies()){
            if (enemy instanceof Bowser){
                if (((Bowser) enemy).isGrabHero()){
                    enemy.setVx(0);
                }
            }
        }
        level.getActivePart().getHeroes()[0].setVx(0);
    }
    public void left(){
        boolean beStill = false;
        for (Enemy enemy : level.getActivePart().getEnemies()){
            if (enemy instanceof Bowser){
                if (((Bowser) enemy).isPhase2Activated() && !((Bowser) enemy).isPhase2Fire()){
                    beStill=true;
                    stand();
                    stop();
                }
            }
        }
        if (!beStill){
            boolean dontDoAnyhting = false;
            for (Enemy enemy : level.getActivePart().getEnemies()){
                if (enemy instanceof Bowser){
                    if (((Bowser) enemy).isGrabHero()){
                        ((Bowser) enemy).setToLeft(true);
                        enemy.setVx(-3);
                        ((Bowser) enemy).addLeftTry();
                        dontDoAnyhting=true;
                    }
                    if (((Bowser) enemy).isDizzy() && !level.getActivePart().getHeroes()[0].isStandingOnSomething()){
                        dontDoAnyhting=true;
                    }
                }
            }
            if (!dontDoAnyhting){
                if (level.getActivePart().getHeroes()[0].isDizzy()){
                    Random random = new Random();
                    if (random.nextDouble() < 0.5){
                        level.getActivePart().getHeroes()[0].setVx(20);
                    }
                    else {
                        seat();
                    }
                }
                else{
                    level.getActivePart().getHeroes()[0].setVx(-40);
                }
            }
        }
    }
    public void seat(){
        boolean beStill = false;
        for (Enemy enemy : level.getActivePart().getEnemies()){
            if (enemy instanceof Bowser){
                if (((Bowser) enemy).isPhase2Activated() && !((Bowser) enemy).isPhase2Fire()){
                    beStill=true;
                    stand();
                    stop();
                }
            }
        }
        if (!beStill){
            if (level.getActivePart().getHeroes()[0].isDizzy()){
                Random random = new Random();
                if (random.nextDouble() < 0.5){
                    level.getActivePart().getHeroes()[0].setVx(-20);
                }
                else {
                    jump();
                }
            }
            else{
                if (!level.getActivePart().getHeroes()[0].isSwordActivated()) {
                    if (level.getActivePart().getHeroes()[0].isStandingOnSomething()) {
                        level.getActivePart().getHeroes()[0].setSeating(true);
                    }
                }
            }
        }
    }
    public void stand(){
        level.getActivePart().getHeroes()[0].setSeating(false);
    }
    public void shoot(boolean right){
        boolean beStill = false;
        for (Enemy enemy : level.getActivePart().getEnemies()){
            if (enemy instanceof Bowser){
                if (((Bowser) enemy).isPhase2Activated() && !((Bowser) enemy).isPhase2Fire()){
                    beStill=true;
                    stand();
                    stop();
                }
            }
        }
        if (!beStill){
            if (level.getActivePart().getHeroes()[0].isStandingOnSomething() && level.getActivePart().getHeroes()[0].getMode()==HeroMode.FIRE){
                boolean shoot = true;
                for (Shot shot : level.getActivePart().getHeroes()[0].getShots()){
                    if (shot instanceof FireBall && shot.isVisible()) shoot=false;
                }
                if (shoot){
                    if (level.getActivePart().getHeroes()[0].getStopwatchForFireball().passedTime()>1500){
                        if (right){
                            level.getActivePart().getHeroes()[0].getShots().add(new FireBall(new int[]{(int)level.getActivePart().getHeroes()[0].getX()+60,(int)level.getActivePart().getHeroes()[0].getY()+20},right));
                        }
                        else{
                            level.getActivePart().getHeroes()[0].getShots().add(new FireBall(new int[]{(int)level.getActivePart().getHeroes()[0].getX()-20,(int)level.getActivePart().getHeroes()[0].getY()+20},right));
                        }
                        level.getActivePart().getHeroes()[0].getStopwatchForFireball().start();
                    }
                }
            }
        }
    }
    public void swordOperation(){
        boolean beStill = false;
        for (Enemy enemy : level.getActivePart().getEnemies()){
            if (enemy instanceof Bowser){
                if (((Bowser) enemy).isPhase2Activated() && !((Bowser) enemy).isPhase2Fire()){
                    beStill=true;
                    stand();
                    stop();
                }
            }
        }
        if (!beStill){
            if (!level.getActivePart().getHeroes()[0].isSwordActivated() && level.getActivePart().getHeroes()[0].getStopwatchForCooldown().passedTime()>3000){
                if (level.getActivePart().getHeroes()[0].getCoin()>=3){
                    level.getActivePart().getHeroes()[0].addCoin(-3);
                    level.getActivePart().getHeroes()[0].setSwordActivated(true);
                }
            }
        }
    }
    public void shotsCollisionCheck(){
        for (Hero hero : this.level.getActivePart().getHeroes()){
            for (Shot shot : hero.getShots()){
                // Enemies
                for (Enemy enemy : level.getActivePart().getEnemies()){
                    if (enemy.isVisible() && shot.isVisible() && shot.getX()+ shot.getWidth()> enemy.getX() && shot.getX()< enemy.getX()+ enemy.getWidth() && shot.getY()+ shot.getHeight()> enemy.getY() && shot.getY()< enemy.getY()+ enemy.getHeight()){
                        if (!(enemy instanceof Bowser)){
                            enemy.setVisible(false);
                            if (enemy instanceof Goomba){
                                hero.addScore(100);
                            }
                            if (enemy instanceof Koopa){
                                hero.addScore(200);
                            }
                            if (enemy instanceof Plant){
                                hero.addScore(100);
                            }
                            if (enemy instanceof Spiny){
                                hero.addScore(300);
                            }
                        }
                        else{
                            if (shot instanceof FireBall){
                                if (enemy.getLives()>1){
                                    if (enemy.getLives()<=11 && !((Bowser) enemy).isPhase2Activated()){
                                        ((Bowser) enemy).setPhase2Activated(true);
                                        ((Bowser) enemy).setDizzy(false);
                                        ((Bowser) enemy).getAttackReloadStopwatch().start();
                                        stop();
                                        stand();
                                        hero.setMode(HeroMode.FIRE);
                                    }
                                    enemy.setLives(enemy.getLives()-1);
                                }
                                else{
                                    enemy.setVisible(false);
                                    hero.addCoin(50);
                                    hero.addScore(500);
                                    updateActivePart();
                                }
                            }
                            else{
                                if (enemy.getLives()>2){
                                    if (enemy.getLives()<=12 && !((Bowser) enemy).isPhase2Activated()){
                                        ((Bowser) enemy).setPhase2Activated(true);
                                        ((Bowser) enemy).setDizzy(false);
                                        ((Bowser) enemy).getAttackReloadStopwatch().start();
                                        stop();
                                        stand();
                                        hero.setMode(HeroMode.FIRE);
                                    }
                                    enemy.setLives(enemy.getLives()-2);
                                }
                                else{
                                    enemy.setVisible(false);
                                    hero.addCoin(50);
                                    hero.addScore(500);
                                    updateActivePart();
                                }
                            }
                            enemy.setVx(0);
                            enemy.setVy(0);
                        }
                        this.changed = true;
                        shot.setVisible(false);
                    }
                }
                // Blocks
                for (Block block : level.getActivePart().getBlocks()){
                    if (block.isVisible() && shot.isVisible() && shot.getX()+ shot.getWidth()> block.getX() && shot.getX()< block.getX()+ block.getWidth() && shot.getY()+ shot.getHeight()> block.getY() && shot.getY()< block.getY()+ block.getHeight()){
                        shot.setVisible(false);
                    }
                }
                // Pipes
                for (Pipe pipe : level.getActivePart().getPipes()){
                    if (shot.isVisible() && shot.getX()+ shot.getWidth()> pipe.getX() && shot.getX()< pipe.getX()+ pipe.getWidth() && shot.getY()+ shot.getHeight()> pipe.getY() && shot.getY()< pipe.getY()+ pipe.getHeight()){
                        shot.setVisible(false);
                    }
                }
            }
        }
        for (Enemy enemy : level.getActivePart().getEnemies()){
            if (enemy instanceof Bowser){
                if (((Bowser) enemy).getShot()!=null){
                    Shot shot = ((Bowser) enemy).getShot();
                    // Heroes
                    for (Hero hero : level.getActivePart().getHeroes()){
                        if (hero.isVisible() && shot.isVisible() && shot.getX()+ shot.getWidth()> hero.getX() && shot.getX()< hero.getX()+ hero.getWidth() && shot.getY()+ shot.getHeight()> hero.getY() && shot.getY()< hero.getY()+ hero.getHeight()){
                            ((Bowser) enemy).setShot(null);
                            die(hero);
                        }
                    }
                    // Blocks
                    for (Block block : level.getActivePart().getBlocks()){
                        if (block.isVisible() && shot.isVisible() && shot.getX()+ shot.getWidth()> block.getX() && shot.getX()< block.getX()+ block.getWidth() && shot.getY()+ shot.getHeight()> block.getY() && shot.getY()< block.getY()+ block.getHeight()){
                            ((Bowser) enemy).setShot(null);
                        }
                    }
                    // Pipes
                    for (Pipe pipe : level.getActivePart().getPipes()){
                        if (shot.isVisible() && shot.getX()+ shot.getWidth()> pipe.getX() && shot.getX()< pipe.getX()+ pipe.getWidth() && shot.getY()+ shot.getHeight()> pipe.getY() && shot.getY()< pipe.getY()+ pipe.getHeight()){
                            ((Bowser) enemy).setShot(null);
                        }
                    }
                }
                if (((Bowser) enemy).getNuke()!=null){
                    Nuke nuke = ((Bowser) enemy).getNuke();
                    Hero hero1 = level.getActivePart().getHeroes()[0];
                    // Heroes
                    for (Hero hero : level.getActivePart().getHeroes()){
                        if (hero.isVisible()  && nuke.getX()+ nuke.getWidth()> hero.getX() && nuke.getX()< hero.getX()+ hero.getWidth() && nuke.getY()+ nuke.getHeight()> hero.getY() && nuke.getY()< hero.getY()+ hero.getHeight()){
                            ((Bowser) enemy).setNuke(null);
                            ((Bowser) enemy).setNukeAppearance(false);
                            die(hero);
                        }
                    }
                    // Blocks
                    for (Block block : level.getActivePart().getBlocks()){
                        if (block.isVisible()  && nuke.getX()+ nuke.getWidth()> block.getX() && nuke.getX()< block.getX()+ block.getWidth() && nuke.getY()+ nuke.getHeight()> block.getY() && nuke.getY()< block.getY()+ block.getHeight()){
                            ((Bowser) enemy).setNuke(null);
                            if (Math.pow(nuke.getX()+nuke.getWidth()/2-hero1.getX()-hero1.getWidth()/2,2)+Math.pow(nuke.getY()+nuke.getHeight()/2-hero1.getY()-hero1.getHeight()/2,2)<=1){
                                ((Bowser) enemy).setNukeAppearance(false);
                                die(hero1);
                            }
                        }
                    }
                    // Floors
                    for (Floor floor : level.getActivePart().getFloors()){
                        if (nuke.getX()+ nuke.getWidth()> floor.getX() && nuke.getX()< floor.getX()+ floor.getWidth() && nuke.getY()+ nuke.getHeight()> floor.getY() && nuke.getY()< floor.getY()+ floor.getHeight()){
                            ((Bowser) enemy).setNuke(null);
                            if (Math.pow(nuke.getX()+nuke.getWidth()/2-hero1.getX()-hero1.getWidth()/2,2)+Math.pow(nuke.getY()+nuke.getHeight()/2-hero1.getY()-hero1.getHeight()/2,2)<=1){
                                ((Bowser) enemy).setNukeAppearance(false);


                                die(hero1);
                            }
                        }
                    }
                }
            }
        }
    }
    public void itemsCollisionCheck(){
        for (Hero hero : this.level.getActivePart().getHeroes()){
            if (hero.getStopwatchForShield().passedTime()>15){
                hero.setShieldActivated(false);
            }
            for (int i = 0; i< level.getActivePart().getItems().size() ; i++){
                if (hero.isVisible() && level.getActivePart().getItems().get(i).isVisible() && level.getActivePart().getItems().get(i).getX()+ level.getActivePart().getItems().get(i).getWidth()> hero.getX() && level.getActivePart().getItems().get(i).getX()< hero.getX()+ hero.getWidth() && level.getActivePart().getItems().get(i).getY()+ level.getActivePart().getItems().get(i).getHeight()> level.getActivePart().getHeroes()[0].getY() && level.getActivePart().getItems().get(i).getY()< level.getActivePart().getHeroes()[0].getY()+ level.getActivePart().getHeroes()[0].getHeight()){
                    level.getActivePart().getItems().get(i).setVisible(false);
                    if (level.getActivePart().getItems().get(i) instanceof Coin) {
                        hero.addCoin();
                    }
                    if (level.getActivePart().getItems().get(i) instanceof Flower) {
                        hero.addScore(50);
                        if (hero.getMode() == HeroMode.MINI){
                            hero.setMode(HeroMode.MEGA);
                        }
                        else if (hero.getMode() == HeroMode.MEGA){
                            hero.setMode(HeroMode.FIRE);
                        }
                    }
                    if (level.getActivePart().getItems().get(i) instanceof Mushroom){
                        hero.addScore(100);
                        if (hero.getMode() == HeroMode.MINI){
                            hero.setMode(HeroMode.MEGA);
                        }
                        else if (hero.getMode() == HeroMode.MEGA){
                            hero.setMode(HeroMode.FIRE);
                        }
                    }
                    if (level.getActivePart().getItems().get(i) instanceof Star){
                        hero.addScore(150);
                        if (hero.getMode() == HeroMode.MINI){
                            hero.setMode(HeroMode.MEGA);
                        }
                        else if (hero.getMode() == HeroMode.MEGA){
                            hero.setMode(HeroMode.FIRE);
                        }
                        hero.setShieldActivated(true);
                    }
                    this.changed = true;
                }
            }
        }
    }
    public void checkEnemies(){
        for (Hero hero : this.level.getActivePart().getHeroes()){
            if (!hero.isTransitioning()){
                for (int i = 0; i< level.getActivePart().getEnemies().length ; i++){
                    if (hero.isSwordActivated()){
                        if ((level.getActivePart().getEnemies()[i]).isVisible() && level.getActivePart().getEnemies()[i].getX()+ level.getActivePart().getEnemies()[i].getWidth()> hero.getX()+100 && level.getActivePart().getEnemies()[i].getX()< hero.getX()+100+(int)((hero.getY()+hero.getHeight())*125/772) && level.getActivePart().getEnemies()[i].getY()<hero.getY()+hero.getHeight()){
                            if (!(level.getActivePart().getEnemies()[i] instanceof Bowser)){
                                (level.getActivePart().getEnemies()[i]).setVisible(false);
                                if (level.getActivePart().getEnemies()[i] instanceof Goomba){
                                    hero.addScore(100);
                                }
                                if (level.getActivePart().getEnemies()[i] instanceof Koopa){
                                    hero.addScore(200);
                                }
                                if (level.getActivePart().getEnemies()[i] instanceof Plant){
                                    hero.addScore(100);
                                    ((Plant) level.getActivePart().getEnemies()[i]).setDead(true);
                                }
                                if (level.getActivePart().getEnemies()[i] instanceof Spiny){
                                    hero.addScore(300);
                                }
                            }
                        }
                    }
                    if (hero.isShieldActivated()){
                        if ((level.getActivePart().getEnemies()[i]).isVisible() && level.getActivePart().getEnemies()[i].getX()+ level.getActivePart().getEnemies()[i].getWidth()> hero.getX()-60 && level.getActivePart().getEnemies()[i].getX()< hero.getX()-60+ 200 && level.getActivePart().getEnemies()[i].getY()+ level.getActivePart().getEnemies()[i].getHeight()> hero.getY()-40 && level.getActivePart().getEnemies()[i].getY()< hero.getY()-40+ 200){
                            if (!(level.getActivePart().getEnemies()[i] instanceof Bowser)){
                                (level.getActivePart().getEnemies()[i]).setVisible(false);
                                if (level.getActivePart().getEnemies()[i] instanceof Goomba){
                                    hero.addScore(100);
                                }
                                if (level.getActivePart().getEnemies()[i] instanceof Koopa){
                                    hero.addScore(200);
                                }
                                if (level.getActivePart().getEnemies()[i] instanceof Plant){
                                    hero.addScore(100);
                                    ((Plant) level.getActivePart().getEnemies()[i]).setDead(true);
                                }
                                if (level.getActivePart().getEnemies()[i] instanceof Spiny){
                                    hero.addScore(300);
                                }
                            }
                            hero.setShieldActivated(false);
                        }
                    }
                    else{
                        if ((level.getActivePart().getEnemies()[i].isVisible()) && (level.getActivePart().getEnemies()[i]) instanceof Goomba && ((Goomba)level.getActivePart().getEnemies()[i]).isDeadActivated()){
                            if (((Goomba)level.getActivePart().getEnemies()[i]).getDeathStopwatch().passedTime()>400){
                                level.getActivePart().getEnemies()[i].setVisible(false);
                            }
                        }
                        if ((level.getActivePart().getEnemies()[i].isVisible()) && (level.getActivePart().getEnemies()[i]) instanceof Koopa && ((Koopa)level.getActivePart().getEnemies()[i]).isDeadActivated()){
                            if (((Koopa)level.getActivePart().getEnemies()[i]).getDeathStopwatch().passedTime()>850){
                                level.getActivePart().getEnemies()[i].setVx(0);
                            }
                            if (((Koopa)level.getActivePart().getEnemies()[i]).getDeathStopwatch().passedTime()>3500){
                                ((Koopa) level.getActivePart().getEnemies()[i]).setDeadActivated(false,true);
                            }
                        }
                        if ((level.getActivePart().getEnemies()[i]).isVisible() && level.getActivePart().getEnemies()[i].getX()+ level.getActivePart().getEnemies()[i].getWidth()> hero.getX() && level.getActivePart().getEnemies()[i].getX()< hero.getX()+ hero.getWidth() && level.getActivePart().getEnemies()[i].getY()+ level.getActivePart().getEnemies()[i].getHeight()> level.getActivePart().getHeroes()[0].getY() && level.getActivePart().getEnemies()[i].getY()< level.getActivePart().getHeroes()[0].getY()+ level.getActivePart().getHeroes()[0].getHeight()){
                            if (hero.getY()+ hero.getHeight()-10<level.getActivePart().getEnemies()[i].getY() && !(level.getActivePart().getEnemies()[i] instanceof Plant || level.getActivePart().getEnemies()[i] instanceof Spiny) && (hero.getY()-hero.getVy()*dt>=level.getActivePart().getEnemies()[i].getY()-hero.getHeight() && hero.getY()+ hero.getHeight()<level.getActivePart().getEnemies()[i].getY()+level.getActivePart().getEnemies()[i].getHeight() && hero.getX()>level.getActivePart().getEnemies()[i].getX()-hero.getWidth() && hero.getX()<level.getActivePart().getEnemies()[i].getX()+ level.getActivePart().getEnemies()[i].getWidth())) {
                                if (level.getActivePart().getEnemies()[i] instanceof Goomba){
                                    ((Goomba) level.getActivePart().getEnemies()[i]).getDeathStopwatch().start();
                                    ((Goomba) level.getActivePart().getEnemies()[i]).setDeadActivated(true);
                                    hero.addScore(100);
                                    hero.setVy(20);
                                }
                                if (level.getActivePart().getEnemies()[i] instanceof Koopa){
                                    if (((Koopa) level.getActivePart().getEnemies()[i]).isDeadActivated()){
                                        (level.getActivePart().getEnemies()[i]).setVisible(false);
                                        hero.addScore(200);
                                    }
                                    else{
                                        ((Koopa) level.getActivePart().getEnemies()[i]).setDeadActivated(true, hero.getX() + hero.getWidth() / 2 < level.getActivePart().getEnemies()[i].getX() + level.getActivePart().getEnemies()[i].getWidth() / 2);
                                    }
                                    hero.setVy(20);
                                }
                                if (level.getActivePart().getEnemies()[i] instanceof Bowser && !((Bowser) level.getActivePart().getEnemies()[i]).isGrabAttacking()){
                                    if (level.getActivePart().getEnemies()[i].getLives()>3){
                                        Bowser bowser = (Bowser) level.getActivePart().getEnemies()[i];
                                        if (level.getActivePart().getEnemies()[i].getLives()<=13 && !((Bowser) level.getActivePart().getEnemies()[i]).isPhase2Activated()){
                                            ((Bowser) level.getActivePart().getEnemies()[i]).setDizzy(false);
                                            ((Bowser) level.getActivePart().getEnemies()[i]).setPhase2Activated(true);
                                            ((Bowser) level.getActivePart().getEnemies()[i]).getAttackReloadStopwatch().start();
                                            stop();
                                            stand();
                                            hero.setMode(HeroMode.FIRE);
                                        }
                                        else{
                                            bowser.setDizzy(true);
                                            bowser.getDizzyStopwatch().start();
                                        }
                                        level.getActivePart().getEnemies()[i].setLives(level.getActivePart().getEnemies()[i].getLives()-3);
                                        if (hero.getX()+hero.getWidth()/2>bowser.getX()+ bowser.getWidth()/2){
                                            hero.setX(bowser.getX()+ bowser.getWidth()+10);
                                            hero.setVx(10);
                                        }
                                        else{
                                            hero.setX(bowser.getX()- hero.getWidth()-10);
                                            hero.setVx(-10);
                                        }
                                        bowser.setVx(0);
                                        bowser.setVy(0);
                                    }
                                    else{
                                        level.getActivePart().getEnemies()[i].setVisible(false);
                                        hero.addCoin(50);
                                        hero.addScore(500);
                                        updateActivePart();
                                    }
                                }
                                if (level.getActivePart().getEnemies()[i] instanceof Bowser && ((Bowser) level.getActivePart().getEnemies()[i]).isGrabAttacking()){
                                    Bowser bowser = (Bowser) level.getActivePart().getEnemies()[i];
                                    if (bowser.isGrabAttacking() && !bowser.isGrabHero()){
                                        hero.setVisible(false);
                                        bowser.getGrabAttackStopwatch().start();
                                        bowser.setGrabHero(true);
                                        bowser.getReloadStopwatches()[0].start();
                                        bowser.setVx(0);
                                    }
                                }
                            }
                            else{
                                if (level.getActivePart().getEnemies()[i] instanceof Bowser){
                                    Bowser bowser = (Bowser) level.getActivePart().getEnemies()[i];
                                    if (bowser.isGrabAttacking() && !bowser.isGrabHero()){
                                        hero.setVisible(false);
                                        bowser.getGrabAttackStopwatch().start();
                                        bowser.setGrabHero(true);
                                        bowser.setVx(0);
                                    }
                                    if (!bowser.isGrabAttacking()){
                                        die(hero);
                                    }
                                }
                                else{
                                    die(hero);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void updatePlants(){
        for (Enemy enemy : level.getActivePart().getEnemies()){
            if (enemy instanceof Plant && !((Plant) enemy).isDead()){
                if (((Plant) enemy).getStopwatch().passedTime()> ((Plant) enemy).getTimePeriod()){
                    ((Plant) enemy).getStopwatch().start();
                    if (enemy.isVisible()){
                        enemy.setVisible(false);
                    }
                    else{
                        enemy.setVisible(true);
                    }
                }
            }
        }
    }
    public void bossMechs(Bowser bowser){
        if (!bowser.isPhase2Activated() || bowser.isPhase2Fire()){
            Hero hero = level.getActivePart().getHeroes()[0];
            if ((!bowser.isDizzy() || bowser.getDizzyStopwatch().passedTime()>1000)){
                bowser.setDizzy(false);
                // Attacking
                if (bowser.isGrabHero() && bowser.getRunTries()[0]>=10 && bowser.getRunTries()[1]>=10){
                    hero.setVisible(true);
                    bowser.setGrabHero(false);
                    bowser.setGrabAttacking(false);
                    bowser.setVx(0);
                    bowser.getAttackReloadStopwatch().start();
                    if (bowser.isToLeft()){
                        hero.setVx(-15);
                        hero.setVy(15);
                        hero.setX(bowser.getX()-hero.getWidth()-10);
                    }
                    else{
                        hero.setVx(15);
                        hero.setVy(15);
                        hero.setX(bowser.getX()+bowser.getWidth()+10);
                    }
                    hero.setY(bowser.getY()-hero.getHeight()-10);
                }
                if (bowser.isGrabHero() && bowser.getGrabAttackStopwatch().passedTime()>3000){
                    hero.setVisible(true);
                    die(hero);
                    bowser.setGrabHero(false);
                    bowser.setGrabAttacking(false);
                }
                if (bowser.isStandingOnSomething() && !bowser.isGrabAttacking() && !bowser.isJumpAttacking() && !bowser.isFireBallAttacking() && !bowser.isNukeAttacking() && bowser.getAttackReloadStopwatch().passedTime()>1500 && bowser.isTriggered()){
                    if (hero.getX()+hero.getWidth()/2<bowser.getX()+bowser.getWidth()/2){
                        bowser.setToLeft(true);
                    }
                    else {
                        bowser.setToLeft(false);
                    }
                    // Nuke attack
                    if (bowser.isPhase2Fire() && new Random().nextDouble()<0.3){
                        if (bowser.getReloadStopwatches()[3].passedTime()>3500){
                            bowser.setNukeAttacking(true);
                            bowser.getReloadStopwatches()[3].start();
                        }
                    }
                    // Fireball attack
                    if (bowser.getReloadStopwatches()[2].passedTime()>2700){
                        if (!bowser.isNukeAttacking()){
                            if (bowser.isToLeft()){
                                if (Math.abs(bowser.getX()-hero.getX())>6*level.getActivePart().getBlocks()[0].getWidth()){
                                    bowser.setVx(0);
                                    bowser.setFireBallAttacking(true);
                                }
                            }
                            else {
                                if (Math.abs(bowser.getX()+bowser.getWidth()-hero.getX())>6*level.getActivePart().getBlocks()[0].getWidth()){
                                    bowser.setVx(0);
                                    bowser.setFireBallAttacking(true);
                                }
                            }
                            bowser.getReloadStopwatches()[2].start();
                        }
                    }
                    // Grab attack
                    if (bowser.getReloadStopwatches()[0].passedTime()>5000){
                        if (!bowser.isFireBallAttacking() && !bowser.isNukeAttacking()){
                            if (bowser.isToLeft()){
                                if (Math.abs(bowser.getX()-hero.getX())<3*level.getActivePart().getBlocks()[0].getWidth()){
                                    bowser.setGrabAttacking(true);
                                    bowser.setVy(35);
                                    bowser.setVx(-20);
                                }
                            }
                            else {
                                if (Math.abs(bowser.getX()+bowser.getWidth()-hero.getX())<3*level.getActivePart().getBlocks()[0].getWidth()){
                                    bowser.setGrabAttacking(true);
                                    bowser.setVy(35);
                                    bowser.setVx(20);
                                }
                            }
                            bowser.getReloadStopwatches()[0].start();
                        }
                    }
                    // Jump attack
                    if (bowser.getReloadStopwatches()[1].passedTime()>4500){
                        if (!bowser.isGrabAttacking() && !bowser.isFireBallAttacking() && !bowser.isNukeAttacking()){
                            if (hero.isStandingOnSomething() && bowser.isStandingOnSomething()){
                                bowser.setVx(0);
                                bowser.setJumpAttacking(true);
                                bowser.getReloadStopwatches()[1].start();
                                bowser.setVy(60);
                                bowser.addY(-100);
                            }
                        }
                    }
                }
                if (!bowser.isGrabAttacking() && !bowser.isJumpAttacking() && !bowser.isFireBallAttacking() && !bowser.isNukeAttacking()){
                    boolean swift = true;
                    if (bowser.isToLeft()){
                        if (Math.abs(bowser.getX()-hero.getX())<5*level.getActivePart().getBlocks()[0].getWidth()){
                            Random random = new Random();
                            double ran = random.nextDouble();
                            if (ran>Math.abs(bowser.getX()-hero.getX())/(5*level.getActivePart().getBlocks()[0].getWidth())){
                                swift = false;
                            }
                        }
                    }
                    else {
                        if (Math.abs(bowser.getX()+bowser.getWidth()-hero.getX())<7*level.getActivePart().getBlocks()[0].getWidth()){
                            Random random = new Random();
                            double ran = random.nextDouble();
                            if (ran>Math.abs(bowser.getX()+bowser.getWidth()-hero.getX())/(7*level.getActivePart().getBlocks()[0].getWidth())){
                                swift = false;
                            }
                        }
                    }
                    if (swift) {
                        for (Shot shot : hero.getShots()) {
                            if (shot instanceof FireBall){
                                int newX;
                                if (shot.getX() < bowser.getX()) {
                                    newX = (int) bowser.getX() - 150;
                                } else {
                                    newX = (int) bowser.getX() + bowser.getWidth() + 150;
                                }
                                if (bowser.isVisible() && shot.isVisible() && shot.getX() + shot.getWidth() > newX && shot.getX() < newX + bowser.getWidth() && shot.getY() + shot.getHeight() > bowser.getY() && shot.getY() < bowser.getY() + bowser.getHeight()) {
                                    if (bowser.getVy() == 0) {
                                        bowser.setVy(63);
                                    }
                                }
                            }
                        }
                    }
                    if (!bowser.isTriggered()&&-hero.getX()+bowser.getX()<600){
                        bowser.setTriggered(true);
                        hero.setBossTrapped(true);
                        hero.setBossBoundries(new int[]{(int)hero.getX(),(int)hero.getX()+1280-70});
                    }
                    if (bowser.isTriggered()){
                        if (hero.getX()+hero.getWidth()/2<bowser.getX()+bowser.getWidth()/2){
                            bowser.setToLeft(true);
                        }
                        else {
                            bowser.setToLeft(false);
                        }
                        if (bowser.isToLeft()){
                            if (Math.abs(hero.getX()-bowser.getX())>8*level.getActivePart().getBlocks()[0].getWidth()){
                                bowser.setVx(-20);
                                bowser.setRunning(true);
                            }
                        }
                        else{
                            if (Math.abs(hero.getX()-bowser.getX()- bowser.getWidth())>8*level.getActivePart().getBlocks()[0].getWidth()){
                                bowser.setVx(20);
                                bowser.setRunning(true);
                            }
                        }
                        if (bowser.isRunning()){
                            if (bowser.isToLeft()){
                                if (Math.abs(hero.getX()-bowser.getX())<=2.5*level.getActivePart().getBlocks()[0].getWidth()){
                                    bowser.setVx(0);
                                    bowser.setRunning(false);
                                }
                            }
                            else{
                                if (Math.abs(hero.getX()-bowser.getX()- bowser.getWidth())<=2.5*level.getActivePart().getBlocks()[0].getWidth()){
                                    bowser.setVx(0);
                                    bowser.setRunning(false);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void updateActivePart(){
        boolean bossIsDead = false;
        for(Enemy enemy : level.getActivePart().getEnemies()){
            if (enemy instanceof Bowser){
                if (!(enemy.isVisible())){
                    bossIsDead=true;
                }
            }
        }
       if (bossIsDead || (this.level.getActivePart().getHeroes()[0].getX()+this.level.getActivePart().getHeroes()[0].getWidth()/2>this.level.getActivePart().getFloors()[this.level.getActivePart().getFloors().length-1].getX()+this.level.getActivePart().getFloors()[this.level.getActivePart().getFloors().length-1].getWidth())){
           if (this.level.getActivePart().getId()==this.level.getParts().length-1){
               this.level.addTime((int)this.level.getActivePart().getStopwatch().passedTime());
               this.level.getActivePart().getHeroes()[0].addScore((this.level.getActivePart().getTime()-(int)this.level.getActivePart().getStopwatch().passedTime())*50);
               this.level.getActivePart().getHeroes()[0].addScore(this.level.getActivePart().getHeroes()[0].getLives()*150);
               this.user.setCoin(this.user.getCoin()+this.level.getActivePart().getHeroes()[0].getCoin());
               this.level.addCoin(this.level.getActivePart().getHeroes()[0].getCoin());
               if (this.user.getHighscore()<this.level.getActivePart().getHeroes()[0].getScore()){
                   this.user.setHighscore(this.level.getActivePart().getHeroes()[0].getScore());
               }
               user.getSavedLevels()[user.getActiveSlot()-1] = null;
               try {
                   this.user.save();
               } catch (Exception e) {}
               this.level.setDone(1);
           }
           else{
               this.level.addTime((int)this.level.getActivePart().getStopwatch().passedTime());
               this.level.getActivePart().getHeroes()[0].addScore((this.level.getActivePart().getTime()-(int)this.level.getActivePart().getStopwatch().passedTime())*50);
               this.level.getActivePart().getHeroes()[0].addScore(this.level.getActivePart().getHeroes()[0].getLives()*150);
               this.user.setCoin(this.user.getCoin()+this.level.getActivePart().getHeroes()[0].getCoin());
               this.level.addCoin(this.level.getActivePart().getHeroes()[0].getCoin());
               this.level.getActivePart().getHeroes()[0].emptyCoin();
               this.level.getActivePart().setFinalScore(this.level.getActivePart().getHeroes()[0].getScore());
               this.level.setActivePart(this.level.getParts()[this.level.getActivePart().getId()+1]);
               this.level.getActivePart().getHeroes()[0].setCoordinates(new double[]{150,200});
               this.level.getActivePart().getStopwatch().start();
               user.getSavedLevels()[user.getActiveSlot()-1] = this.level;
               this.level.getActivePart().getHeroes()[0].setVx(0);
               Bowser bowser = null;
               for (Enemy enemy : level.getActivePart().getEnemies()) {
                   if (enemy instanceof Bowser) bowser = (Bowser) enemy;
               }
               if (bowser!=null){
                   bowser.getAttackReloadStopwatch().pause();
                   bowser.getDizzyStopwatch().pause();
                   bowser.getGrabAttackStopwatch().pause();
                   if (bowser.getReloadStopwatches()[0]!=null){
                       bowser.getReloadStopwatches()[0].pause();
                       bowser.getReloadStopwatches()[1].pause();
                       bowser.getReloadStopwatches()[2].pause();
                       bowser.getReloadStopwatches()[3].pause();
                   }
               }
               level.getActivePart().getStopwatch().pause();
               level.getActivePart().getHeroes()[0].getStopwatchForFireball().pause();
               level.getActivePart().getHeroes()[0].getStopwatchForDizzy().pause();
               level.getActivePart().getHeroes()[0].getStopwatchForLightning().pause();
               level.getActivePart().getHeroes()[0].getStopwatchForCooldown().pause();
               level.getActivePart().getHeroes()[0].getStopwatchForShield().pause();
               level.getActivePart().getHeroes()[0].getStopwatchForTransitioning().pause();
               try {
                   this.user.save();
               } catch (Exception e) {}
           }
       }
    }
    public void die(Hero hero){
        hero.setDizzy(false);
        stand();
        if (hero.isShieldActivated()){
            hero.setShieldActivated(false);
        }
        if (hero.getMode()==HeroMode.FIRE){
            hero.setMode(HeroMode.MEGA);
            hero.setTransitioning(true);
        }
        else if (hero.getMode()==HeroMode.MEGA){
            hero.setMode(HeroMode.MINI);
            hero.setTransitioning(true);
        }
        else{
            if (hero.isBossTrapped()){
                for (Enemy enemy : level.getActivePart().getEnemies()){
                    if (enemy instanceof Bowser){
                        ((Bowser) enemy).setTriggered(false);
                        enemy.setX(((Bowser) enemy).getRestingX());
                        enemy.setY(((Bowser) enemy).getRestingY());
                    }
                }
            }
            hero.setBossTrapped(false);
            if (hero.getLives()>=2){
                hero.setLives(hero.getLives()-1);
                hero.setCoordinates(new double[]{150,200});
            }
            else{
                this.level.addTime((int)this.level.getActivePart().getStopwatch().passedTime());
                user.getSavedLevels()[user.getActiveSlot()-1]=null;
                try {
                    this.user.save();
                } catch (Exception e) {}
                this.level.setDone(2);
            }
            this.changed = true;
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

}
