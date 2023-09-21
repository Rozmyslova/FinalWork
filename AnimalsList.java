import java.util.ArrayList;

public class AnimalsList {
    public static ArrayList<AnimalsList> allAnimalsList() {
        

        InfoAnimals pet1 = new AddToys();
        toy1.id = 1;
        toy1.toyName = "Кукла Барби";
        toy1.quantity = 0;

        AddToys toy2 = new AddToys();
        toy2.id = 2;
        toy2.toyName = "Грузовик";
        toy2.quantity = 7;

        AddToys toy3 = new AddToys();
        toy3.id = 3;
        toy3.toyName = "Робот";
        toy3.quantity = 6;

        AddToys toy4 = new AddToys();
        toy4.id = 4;
        toy4.toyName = "Плюшевый мишка";
        toy4.quantity = 5;

        AddToys toy5 = new AddToys();
        toy5.id = 5;
        toy5.toyName = "Дженга";
        toy5.quantity = 6;

        AddToys toy6 = new AddToys();
        toy6.id = 6;
        toy6.toyName = "Мяч";
        toy6.quantity = 15;
        
        ArrayList<AddToys> toys = new ArrayList<AddToys>();
        toys.add(toy1);
        toys.add(toy2);
        toys.add(toy3);
        toys.add(toy4);
        toys.add(toy5);
        toys.add(toy6);
        return (toys);
    } 
}
