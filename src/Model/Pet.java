package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Pet {
    
    protected int petId;
    protected String name;
    protected LocalDate birthday;

    protected String commands;
    
    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getPetId() {
        return petId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthday(LocalDate date) {
        this.birthday = date;
    }

    public LocalDate getBirthdayDate(){
        return birthday;
    }

    public String getBirthday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(birthday);
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }
    public ArrayList<String> getCommands() {
        if (this.commands == null || this.commands.isEmpty())
            return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(this.commands.split("\\s*,\\s*")));
    }

    @Override
    public String toString() {
        return String.format("%d. %s: name: %s, birthday: %s ", getPetId(), getClass().getSimpleName(), name, getBirthday());
    }
}
