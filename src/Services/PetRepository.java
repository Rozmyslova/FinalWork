package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.time.LocalDate;

import Model.*;


public class PetRepository {

    private final PetCreator petCreator;
    String dbUrl;
    String dbUsername;
    String dbPassword;
    private Connection dbConnect;

    public PetRepository(String dbUrl, String dbUsername, String dbPassword) throws ClassNotFoundException {
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.petCreator = new PetCreator();
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    ;

    public List<Pet> getAll() {
        List<Pet> farm = new ArrayList<>();
        Pet pet;
        try {
            Connection dbConnection = getConnection();
            Statement sqlSt = dbConnection.createStatement();
            String SQLstr = "SELECT Genus_id, Id, Name, Bday, Commands FROM Pets ORDER BY Id";
            ResultSet resultSet = sqlSt.executeQuery(SQLstr);
            while (resultSet.next()) {
                int type = resultSet.getInt(1);
                int id = resultSet.getInt(2);
                String name = resultSet.getString(3);
                LocalDate birthday = resultSet.getDate(4).toLocalDate();
                String commands = resultSet.getString(5);

                pet = petCreator.createPet(type, name, birthday, commands);
                pet.setPetId(id);
                farm.add(pet);
            }
            return farm;
        } catch (SQLException ex) {
            Logger.getLogger(PetRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Pet getById(int petId) {
        Pet pet = null;
        try {
            Connection dbConnection = getConnection();
            String SQLstr = "SELECT Genus_id, Id, Name, Bday, Commands FROM Pets WHERE Id = ?";
            PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
            prepSt.setInt(1, petId);
            ResultSet resultSet = prepSt.executeQuery();

            if (resultSet.next()) {

                int type = resultSet.getInt(1);
                int id = resultSet.getInt(2);
                String name = resultSet.getString(3);
                LocalDate birthday = resultSet.getDate(4).toLocalDate();
                String commands = resultSet.getString(5);

                pet = petCreator.createPet(type, name, birthday, commands);
                pet.setPetId(id);
            }
            return pet;
        } catch (SQLException ex) {
            Logger.getLogger(PetRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public int create(Pet pet) {
        int rows;
        try {
            Connection dbConnection = getConnection();
            String SQLstr = "INSERT INTO Pets (Name, Bday, Genus_id) SELECT ?, ?, (SELECT Id FROM PetTypes WHERE GenusName = ?)";
            PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
            prepSt.setString(1, pet.getName());
            prepSt.setDate(2, Date.valueOf(pet.getBirthdayDate()));
            prepSt.setString(3, pet.getClass().getSimpleName());

            rows = prepSt.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(PetRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void train(int id, String command) {
        try {
            Connection dbConnection = getConnection();
            Pet pet = getById(id);
            ArrayList<String> commands = pet.getCommands();
            commands.add(command);
            String newCommands = String.join(",", commands);
            String SQLstr = "UPDATE Pets SET Commands = ? WHERE Id = ?";
            PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
            prepSt.setString(1, newCommands);
            prepSt.setInt(2, id);

            prepSt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PetRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<String> getCommandsById(int petId) {
        Pet pet = getById(petId);
        return pet.getCommands();
    }

    public int update(Pet pet) {
        int rows;
        try {
            Connection dbConnection = getConnection();
            String SQLstr = "UPDATE Pets SET Name = ?, Bday = ? WHERE Id = ?";
            PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
            prepSt.setString(1, pet.getName());
            prepSt.setDate(2, Date.valueOf(pet.getBirthdayDate()));
            prepSt.setInt(3, pet.getPetId());

            rows = prepSt.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(PetRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void delete(int id) {
        try {
            Connection dbConnection = getConnection();
            String SQLstr = "DELETE FROM Pets WHERE Id = ?";
            PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
            prepSt.setInt(1, id);
            prepSt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PetRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        if (this.dbConnect == null) {
            this.dbConnect = DriverManager.getConnection(this.dbUrl, this.dbUsername, this.dbPassword);
        }
        return this.dbConnect;
    }

    @Override
    protected void finalize() {
        try {
            if (this.dbConnect != null) {
                this.dbConnect.close();
            }
        } catch (SQLException exc) {
            Logger.getLogger(PetRepository.class.getName()).log(Level.SEVERE, null, exc);
        }
    }
}
