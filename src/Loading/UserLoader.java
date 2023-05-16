package Loading;

import Model.User;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

public abstract class UserLoader {
    // Fields
    private static User[] users;

    // Methods
    public static void loadUsers() throws IOException {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader("src/Loading/Users/UsersData.json")) {
            // Convert the JSON string to a list of objects
            User[] users = gson.fromJson(fileReader, User[].class);
            UserLoader.users = users;
        }
    }

    // Getters
    public static User[] getUsers() {return users;}
}
