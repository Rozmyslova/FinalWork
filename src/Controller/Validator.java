package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import Exceptions.InvalidDataException;

public class Validator {

    public void validate (String [] data){

        StringBuilder sb = new StringBuilder();
        boolean flag = true;

        for (int i=0; i < data.length; i++){
            try{
                if (i==0) 
                    isValidName(data[i]);
                if (i==1)
                    isValidDate(data[i]);
                
            } catch (InvalidDataException e){
                sb.append ("\n");
                sb.append(e.getMessage());
                flag = false;
            }
        }
        if (!flag){
            throw new InvalidDataException(sb.toString());
        } 
    }

    private void isValidName (String name){
        int maxLength = 20;
        if (name.isEmpty())
            throw new InvalidDataException("invalid name, empty");
        if (name.length() > maxLength)
            throw new InvalidDataException("invalid name, length name must be less than" + maxLength);
    }
    
    private void isValidDate (String birthday)  {
        LocalDate date;
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            date = LocalDate.parse(birthday, formatter);

        } catch (DateTimeParseException e) {
            throw new InvalidDataException("invalid date format");
        }
    }
}
