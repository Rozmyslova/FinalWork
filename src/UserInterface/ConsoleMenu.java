package UserInterface;

import java.util.*;

import Controller.*;
import Exceptions.InvalidDataException;


public class ConsoleMenu {

    PetController petController;

    public ConsoleMenu(PetController controller) {
        this.petController = controller;
    }

    public void run() {

        try (Scanner in = new Scanner(System.in, "ibm866"); Counter count = new Counter()) {
            LinkedHashMap<String, String> mainMenuMap = new LinkedHashMap<String, String>() {{
                put("0", "Exit");
                put("1", "List of all pets");
                put("2", "Add new pet");
                put("3", "Change pet details");
                put("4", "Pet commands");
                put("5", "Training");
                put("6", "Delete entry");
            }};
            boolean runCheck = true;
            int id;
            while (runCheck) {
                mainMenuMap.forEach((key, value) -> System.out.println(key + " - " + value));
                String key = in.next();
                switch (key) {
                    case "1":
                        petController.getAllPet();
                        break;
                    case "2":
                        addNewPet(in, count);
                        break;
                    case "3":
                        changePetDetails(in);
                        break;
                    case "4":
                        getPetCommands(in);
                        break;
                    case "5":
                        id = menuChoicePet(in);
                        if (id != 0)
                            menuTrainPet(id, in);
                        break;
                    case "6":
                        id = menuChoicePet(in);
                        if (id != 0)
                            petController.delete(id);
                        break;
                    case "0":
                        runCheck = false;
                        break;
                    default:
                        System.out.println("not valid option");
                        break;
                }
            }
        }
    }

    private void addNewPet(Scanner in, Counter count) {
        int type = menuChoice(in);
        if (type != 0) {
            try {
                petController.createPet(type);
                count.add();
                System.out.println("ОК");
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void changePetDetails(Scanner in) {
        int id;
        while (true) {
            id = menuChoicePet(in);
            if (id != 0)
                try {
                    petController.updatePet(id);
                } catch (InvalidDataException e) {
                    System.out.println(e.getMessage());
                }
            else
                break;
        }
    }

    private void getPetCommands(Scanner in) {
        int id;
        while (true) {
            id = menuChoicePet(in);
            if (id != 0)
                petController.getCommands(id);
            else
                break;
        }
    }
    private int menuChoice(Scanner in) {
        LinkedHashMap<String, String> choicePetMap = new LinkedHashMap<String, String>() {{
            put("0", "return to the main menu");
            put("1", "Cat");
            put("2", "Dog");
            put("3", "Hamster");
        }};
        Set<String> choiceKeys = choicePetMap.keySet();
        System.out.println("What pet add:");
        choicePetMap.forEach((key, value) -> System.out.println(key + " - " + value));

        while (true) {
            String key = in.next();
            if (choicePetMap.containsKey(key)) {
                return Integer.parseInt(key);
            } else {
                System.out.println("There is no such option, enter a number from the list:" + choiceKeys); // Arrays.toString(stack.toArray()) // String.join(",", yourIterable);
            }
        }
    }

    private int menuChoicePet(Scanner in) {
        System.out.println("\nEnter the pet number, 0 to return to the main menu: ");
        while (true) {
            int id = in.nextInt();
            in.nextLine();
            if (id == 0)
                return id;
            if (petController.getById(id) == null) {
                System.out.println("No pet with this number, try again, 0 to return to the main menu:");
            } else
                return id;

        }
    }

    private void menuTrainPet(int petId, Scanner in) {
        while (true) {
            System.out.println("Enter command, 0 to return to the main menu.: ");
            String command = in.nextLine();
            if (command.equals("0"))
                return;
            if (petController.trainPet(petId, command))
                System.out.println("train done!");
        }
    }
}
