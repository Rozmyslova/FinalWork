public class InfoAnimals {

    int id;
    String animalName;
    String bday;
    String genus;
    String command;

    public void info() {
        System.out.println();
        System.out.printf("Имя животного: %s \n", this.animalName);
        System.out.printf("Дата рождения: %s \n", this.bday);
        System.out.printf("Род животного: %s \n", this.genus);
        System.out.printf("Количество игрушек: %s \n", this.command);       
    }
}
