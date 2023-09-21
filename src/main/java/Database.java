import java.util.ArrayList;

public abstract class Database {
    private ArrayList<Superhero> superheroes = new ArrayList<>();

    public void addSuperhero(Superhero superhero) {
        superheroes.add(superhero);
    }

    public ArrayList<Superhero> getAllSuperheroes() {
        return superheroes;
    }

    public ArrayList<Superhero> searchSuperheroes(String searchCriteria) {
        ArrayList<Superhero> searchResult = new ArrayList<>();
        for (Superhero superhero : superheroes) {
            if (superhero.getName().toLowerCase().contains(searchCriteria.toLowerCase())) {
                searchResult.add(superhero);
            }
        }
        return searchResult;
    }

    public Superhero searchSuperheroByName(String name) {
        for (Superhero superhero : superheroes) {
            if (superhero.getName().equalsIgnoreCase(name)) {
                return superhero;
            }
        }
        return null; // Return null if no match is found
    }

    public void removeSuperhero(String name) {

        ArrayList<Superhero> superheroesToRemove = new ArrayList<>();

        for (Superhero superheroInDatabase : superheroes) {
            if (superheroInDatabase.getName().trim().equalsIgnoreCase(name))
                superheroesToRemove.add(superheroInDatabase);
        }
        superheroes.removeAll(superheroesToRemove);


    }


    public abstract void deleteSuperhero(String name);
}

