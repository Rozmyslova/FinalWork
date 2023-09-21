public class UImenu {

    private int choise;

    public void mainMenu() {
        System.out.printf("Добро пожаловать в наш реестр животных!\n");
        System.out.printf("Что вы хотите сделать? \n");
        System.out.printf("1 - вывести список животных из нашего реестра; \n");
        System.out.printf("2 - добавить животное; \n");
        System.out.printf("3 - увидеть список команд, которые выполняет животное; \n");
        System.out.printf("4 - внести новую команду \n");
        System.out.printf("Итак, выберите цифру - ");
        CorrectChoise ch = new CorrectChoise();
        choise = ch.correctChoise();
        if (choise == 1) {
            /*Client ourClient = new Client();
            ourClient.randomToys();*/
            System.out.printf("1 - вывести список животных из нашего реестра; \n");
        } else if (choise == 2) {
            System.out.printf("2 - добавить животное; \n");
        } else if (choise == 3) {
            System.out.printf("3 - увидеть список команд, которые выполняет животное; \n");
        } else {
            /*Employee workingTask = new Employee();
            workingTask.workingMoments();*/
            System.out.printf("4 - внести новую команду \n");
        }
    }
}
