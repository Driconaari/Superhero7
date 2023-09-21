import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    private Database database;

    @BeforeEach
    void setUp() {
        // Initialize a new Database instance before each test
        database = new TestDatabase();
    }

    @Test
    void testGetAllSuperheroes() {
        // Test whether getting all superheroes returns an empty list initially
        ArrayList<Superhero> superheroes = database.getAllSuperheroes();
        assertTrue(superheroes.isEmpty());
    }

    @Test
    void testSearchSuperheroesEmptyResult() {
        // Test whether searching for superheroes with an empty database returns an empty list
        ArrayList<Superhero> searchResults = database.searchSuperheroes("Superman");
        assertTrue(searchResults.isEmpty());
    }

    @Test
    void testSearchSuperheroByName() {
        Superhero superhero = new Superhero("Spider-Man", "Peter Parker", true, 1962, "Net-Slyngning");

        // Test whether searching for a superhero by name returns the correct superhero
        database.addSuperhero(superhero);

        Superhero foundSuperhero = database.searchSuperheroByName("Spider-Man");
        assertNotNull(foundSuperhero);
        assertEquals("Spider-Man", foundSuperhero.getName());
    }

    @Test
    void testAddSuperhero() {
        Superhero superhero = new Superhero("Superman", "Clark Kent", true, 1938, "Super Styrke");

        // Test whether the superhero is successfully added
        database.addSuperhero(superhero);

        ArrayList<Superhero> superheroes = database.getAllSuperheroes();
        assertTrue(superheroes.contains(superhero));
    }

    @Test
    void testDeleteSuperhero() {
        // Add superheroes to the database
        Superhero superhero1 = new Superhero("Superman", "Clark Kent", true, 1938, "Super Strength");
        Superhero superhero2 = new Superhero("Spider-Man", "Peter Parker", true, 1962, "Web-Slinging");

        database.addSuperhero(superhero1);
        database.addSuperhero(superhero2);

        // Get the initial number of superheroes
        int beforeDelete = database.getAllSuperheroes().size();

        // Delete a superhero by name
        database.deleteSuperhero("Superman");

        // Get the number of superheroes after deletion
        int afterDelete = database.getAllSuperheroes().size();

        // Check if the number has decreased by 1
        assertEquals(beforeDelete - 1, afterDelete);

        // Check that the deleted superhero is no longer in the database
        assertNull(database.searchSuperheroByName("Superman"));
    }

    // Concrete subclass of Database for testing
    private class TestDatabase extends Database {
        @Override
        public void deleteSuperhero(String name) {
            // Simulate the deletion by removing the superhero from the list
            getAllSuperheroes().removeIf(superhero -> superhero.getName().equals(name));
        }
    }
}
