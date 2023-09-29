package UserInterface;

import java.util.List;
import java.util.Scanner;
import Model.*;

public class ConsoleView implements View <Pet> {

    Scanner in;

    public ConsoleView() {
        in = new Scanner(System.in, "ibm866");
    }

    @Override
    public String getName() {
        System.out.print("Name: ");
        return in.nextLine();
    }

    @Override
    public String getBirthday() {
        System.out.print("Input birthday in format 'dd.mm.yyyy': ");
        return in.nextLine();
    }

    @Override
    public <T> void printItemList (List <T> list) {
        if (list.isEmpty())
            System.out.println("list empty");
        else {
            for (T item : list) {
                System.out.println(item);
            }
        }
    }

    @Override
    public <T> void printPetList (List <T> list) {
        System.out.println("\nPets:");
        printItemList(list);
        System.out.print("\n");
    }

    @Override
    public <T> void printCommandList (List <T> list) {
        System.out.println("\nCommands:");
        printItemList(list);
        System.out.print("\n");
    }
    
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
