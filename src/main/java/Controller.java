import java.util.ArrayList;

public class Controller {
    private Database db;

    public Controller(Database db) {
        this.db = db;
    }

    public void addSuperhero(String name, String realName, boolean isHuman, int creationYear, String strength) {
        Superhero superhero = new Superhero(name, realName, isHuman, creationYear, strength);
        db.addSuperhero(superhero);
    }

    public ArrayList<Superhero> getAllSuperheroes() {
        return db.getAllSuperheroes();
    }

    public Superhero searchSuperheroByName(String name) {
        ArrayList<Superhero> superheroes = db.getAllSuperheroes();
        for (Superhero superhero : superheroes) {
            if (superhero.getName().equalsIgnoreCase(name)) {
                return superhero;
            }
        }
        return null; // Returner null, hvis ingen match blev fundet
    }

    public ArrayList<Superhero> searchSuperheroes(String searchCriteria) {
        return db.searchSuperheroes(searchCriteria);
    }


    public boolean editSuperhero(String name, String newRealName, boolean newIsHuman, int newCreationYear, String newStrength) {
        Superhero superhero = db.searchSuperheroByName(name);
        if (superhero != null) {
            superhero.setRealName(newRealName);
            superhero.setIsHuman(newIsHuman);
            superhero.setCreationYear(newCreationYear);
            superhero.setStrength(newStrength);
            return true; // Successful edit
        }
        return false; // Superhero not found
    }

}



