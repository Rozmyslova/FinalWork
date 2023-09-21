import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ShowAnimalsList {
    private int choise;

    public void showList() {
        do {
            System.out.printf("Вы хотите: \n");
            System.out.printf("1 - вывести список на экран; \n");
            System.out.printf("2 - сохранить в файл \n");
            System.out.printf("Ваш выбор: ");
            CorrectChoise ch = new CorrectChoise();
            choise = ch.correctChoise();
        } while (choise <= 0 | choise >= 3);

        var animals = new ArrayList<InfoAnimals>(AnimalsList.allAnimalsList());

        if (choise == 1) {
            show(animals);
            System.out.println();
        } else {
            inFile(animals);
        }
    }

    public void show(ArrayList<InfoAnimals> animals) {
        for (int i = 0; i < animals.size(); i++) {
           animals.get(i).info();
        }
    }

    public void inFile(ArrayList<InfoAnimals> animals) {
        File file = new File("animalsList.txt");
        try (PrintWriter out = new PrintWriter(file, StandardCharsets.UTF_8)) {
            for (int i = 0; i < animals.size(); i++) {
                out.println("Имя животного - " + animals.get(i).animalName);
                out.println("Дата рождения - " + animals.get(i).bday);
                out.println("Род - " + animals.get(i).genus);
                out.println("Выполняемые команды - " + animals.get(i).command);
                out.println();
            }
            System.out.println("Данные успешно записаны в файл");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
