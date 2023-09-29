package UserInterface;

import java.util.List;

public interface View <T>{
    
    String getName();
    String getBirthday();
    <U> void printItemList (List <U> list);
    <U> void printPetList (List <U> list);
    <U> void printCommandList (List <U> list);
    void showMessage (String s);

}
