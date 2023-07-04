package Loading;

import javax.sound.sampled.*;
import java.io.File;

abstract public class AudioLoader {
    public static Clip getJumpSound() {
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/jump.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getItemSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/item.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getSwordSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/sword.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getDeathSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/death.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getSectionSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/section.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getRunSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/run.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getSlimeSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/slime.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getShieldExplosionSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/shield-explosion.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getHeroFireballSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/hero-fireball.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getThemeSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/theme.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getLightningSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/lightning.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getBlockSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/block.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getEnemyDeathSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/enemy-death.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getBossfightSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/bossfight.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getRoarSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/roar.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getFireSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/fire.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getBowserFireballSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/bowser-fireball.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getNukeSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/nuke.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getWinSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/win.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getGameOverSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/game-over.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getButtonSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/button.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
    public static Clip getErrorSound(){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Loading/Audios/error.wav").toURI().toURL());
            Clip sound = AudioSystem.getClip();
            sound.open(stream);
            return sound;
        }catch (Exception e){}
        return null;
    }
}
