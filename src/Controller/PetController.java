package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Model.*;
import Services.PetRepository;
import UserInterface.*;

public class PetController {
    private final PetRepository petRepository;
    private final PetCreator petCreator;
    private final View<Pet> view;
    private final Validator validator;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
        this.petCreator = new PetCreator();
        this.view = new ConsoleView();
        this.validator = new Validator();
    }

    public void createPet(int type) {

        String[] data = new String[]{view.getName(), view.getBirthday()};
        validator.validate(data);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthday = LocalDate.parse(data[1], formatter);
        try {
            int res = petRepository.create(petCreator.createPet(type, data[0], birthday, null));
            view.showMessage(String.format("%d entry added", res));
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }

    }

    public void updatePet(int id) {

        Pet pet = getById(id);
        String[] data = new String[]{view.getName(), view.getBirthday()};

        validator.validate(data);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthday = LocalDate.parse(data[1], formatter);
        pet.setName(data[0]);
        pet.setBirthday(birthday);
        try {
            int res = petRepository.update(pet);
            view.showMessage(String.format("%d запись изменена \n", res));
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }

    }

    public void getAllPet() {
        try {
            view.printPetList(petRepository.getAll());
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }

    public boolean trainPet(int id, String command) {
        try {

            if (petRepository.getCommandsById(id).contains(command))
                view.showMessage("pet already know this command");
            else {
                    petRepository.train(id, command);
                    view.showMessage("command has been learned");
                    return true;
            }
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
        return false;
    }

    public Pet getById(int id) {
        try {
            return petRepository.getById(id);
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
        return null;
    }

    public void delete(int id) {
        try {
            petRepository.delete(id);
            view.showMessage("entry deleted");
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }

    public void getCommands(int id) {
        try {
            view.printCommandList(petRepository.getCommandsById(id));
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }
}
