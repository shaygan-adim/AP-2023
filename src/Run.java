import Loading.ImageLoader;
import Loading.UserLoader;
import View.EntryFrames.FirstPage;

import java.io.IOException;

abstract public class Run {
    public static void main(String[] args) throws IOException {
        ImageLoader.load();
        UserLoader.loadUsers();
        new FirstPage();
    }
}
