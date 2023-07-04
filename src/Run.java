import Loading.AudioLoader;
import Loading.ImageLoader;
import Loading.UserLoader;
import View.EntryFrames.FirstPage;

import javax.sound.sampled.*;
import java.io.IOException;

abstract public class Run {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ImageLoader.load();
        UserLoader.loadUsers();
        new FirstPage();
    }
}
