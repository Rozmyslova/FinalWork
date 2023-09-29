package Model;

import java.time.LocalDate;
import java.util.HashMap;

import Exceptions.InvalidDataException;

public class PetCreator {

    HashMap<Integer, Class<?>> petClasses = new HashMap<Integer, Class<?>>() {{
        put(1, Cat.class);
        put(2, Dog.class);
        put(3, Hamster.class);
    }};

    public Pet createPet(Integer type, String name, LocalDate date, String commands) {
        try {
            Class<?> class_ = petClasses.get(type);
            Pet pet = (Pet) class_.newInstance();
            pet.setName(name);
            pet.setBirthday(date);
            pet.setCommands(commands);
            return pet;
        } catch (InstantiationException | IllegalAccessException e) {
            String msg = String.format("Error creating pet; type: %s, name: %s, date: %s", type.toString(), name, date.toString());
            throw new InvalidDataException(msg);
        }
    }
}
