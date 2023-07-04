package Loading;

import Model.User;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class UserLoader {
    // Fields
    private static User[] users;

    // Methods
    public static void loadUsers() throws IOException {
        try  {
            String folderPath = "src/Loading/Users";
            File folder = new File(folderPath);
            File[] files = folder.listFiles();
            boolean notFound = true;

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        if (file.getName().equals("UsersData.ser")){
                            notFound = false;
                        }
                    }
                }
            }
            if (notFound){
                try {
                    FileOutputStream fileOut = new FileOutputStream("src/Loading/Users/UsersData.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    List<User> list = new ArrayList<>();
                    out.writeObject(list);
                    out.close();
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                ArrayList<User> loadedObj = null;
                try {
                    FileInputStream fileIn = new FileInputStream("src/Loading/Users/UsersData.ser");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    loadedObj = (ArrayList<User>) in.readObject();
                    in.close();
                    fileIn.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                users = loadedObj.toArray(new User[0]);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // Getters
    public static User[] getUsers() {
        return users;
    }
}
