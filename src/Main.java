import Controller.PetController;
import Services.PetRepository;
import UserInterface.ConsoleMenu;

import java.io.FileInputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        String url;
        String username;
        String password;
        try (FileInputStream fileInputStream = new FileInputStream("src/database.properties")) {

            props.load(fileInputStream);
            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");
        }

        PetRepository petRepo = new PetRepository(url, username, password);
        PetController controller = new PetController(petRepo);
        new ConsoleMenu (controller).run();
    }
}    