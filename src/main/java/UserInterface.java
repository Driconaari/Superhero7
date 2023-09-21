import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class UserInterface {
    private Database database;
    private Controller controller;
    private Scanner scanner;

    public UserInterface() {
        database = new Database() {
            @Override
            public void deleteSuperhero(String name) {

            }
        };
        controller = new Controller(database);
        scanner = new Scanner(System.in);
    }


    private void removeSuperhero(String name) {
        ArrayList<Superhero> superheroesToRemove = new ArrayList<>();

        for (Superhero superheroInDatabase : database.getAllSuperheroes()) {
            if (superheroInDatabase.getName().trim().equalsIgnoreCase(name))
                superheroesToRemove.add(superheroInDatabase);
        }

        database.getAllSuperheroes().removeAll(superheroesToRemove);
        System.out.println("Superheroes removed!");

    }

        public void run () {
            boolean running = true;

            while (running) {
                // Display menu
                System.out.println("Menu:");
                System.out.println("1. Add Superhero");
                System.out.println("2. View All Superheroes");
                System.out.println("3. Search for Superhero");
                System.out.println("4. Edit Superhero");
                System.out.println("5. Remove Superhero");
                System.out.println("6. Exit");

                int choice;
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid menu choice.");
                    scanner.nextLine(); // Consume invalid input
                    continue; // Skip the rest of the loop and show the menu again
                }

                switch (choice) {
                    case 1:
                        // Add Superhero
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Real Name: ");
                        String realName = scanner.nextLine();
                        boolean isHuman;
                        try {
                            System.out.print("Is Human (true/false): ");
                            isHuman = scanner.nextBoolean();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input for 'Is Human.' Please enter 'true' or 'false'.");
                            scanner.nextLine(); // Consume invalid input
                            break; // Exit the case
                        }
                        int creationYear;
                        try {
                            System.out.print("Creation Year: ");
                            creationYear = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input for 'Creation Year.' Please enter a valid integer.");
                            scanner.nextLine(); // Consume invalid input
                            break; // Exit the case
                        }
                        System.out.print("Strength: ");
                        String strength = scanner.nextLine();

                        controller.addSuperhero(name, realName, isHuman, creationYear, strength);
                        System.out.println("Superhero added!");
                        break;

                    case 2:
                        ArrayList<Superhero> superheroList = controller.getAllSuperheroes();
                        if (superheroList.isEmpty()) {
                            System.out.println("There are no superheroes in the database.");
                        } else {
                            System.out.println("List of Superheroes:");
                            for (int i = 0; i < superheroList.size(); i++) {
                                Superhero superhero = superheroList.get(i);
                                System.out.println((i + 1) + ". " + superhero.getName());
                            }
                            // Prompt the user to select a superhero to edit
                            System.out.print("Enter the number of the superhero to edit: ");
                            int editChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character

                            // Now, you have the superhero to edit
                            // Proceed to editing (case 5)
                            Superhero superheroToEdit;
                            if (editChoice > 0 && editChoice <= superheroList.size())
                                superheroToEdit = superheroList.get(editChoice - 1);
                            else {
                                System.out.println("Invalid choice. Please select a valid superhero to edit.");
                                break;  // Exit the case
                            }
                        }
                        break;

                    case 3:
                        // Search for Superhero
                        System.out.print("Enter search criteria: ");
                        String searchCriteria = scanner.nextLine();
                        ArrayList<Superhero> searchResults = controller.searchSuperheroes(searchCriteria);

                        if (searchResults.isEmpty()) {
                            System.out.println("No matching superheroes found.");
                        } else {
                            System.out.println("Matching superheroes:");
                            for (Superhero superhero : searchResults) {
                                System.out.println("Name: " + superhero.getName());
                                System.out.println("Real Name: " + superhero.getRealName());
                                System.out.println("Is Human: " + superhero.isHuman());
                                System.out.println("Creation Year: " + superhero.getCreationYear());
                                System.out.println("Strength: " + superhero.getStrength());
                                System.out.println();
                            }
                        }
                        break;

                    case 4:
                        // Edit Superhero
                        System.out.print("Enter the name of the superhero to edit: ");
                        String editName = scanner.nextLine();
                        Superhero superheroToEdit = controller.searchSuperheroByName(editName);

                        if (superheroToEdit != null) {
                            // Prompt the user for new values
                            System.out.print("New real name: ");
                            String newRealName = scanner.nextLine();
                            boolean newIsHuman;
                            try {
                                System.out.print("Is human (true/false): ");
                                String isHumanInput = scanner.nextLine();
                                newIsHuman = Boolean.parseBoolean(isHumanInput);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid input for 'Is Human.' Please enter 'true' or 'false'.");
                                break; // Exit the case
                            }
                            int newCreationYear;
                            try {
                                System.out.print("New creation year: ");
                                newCreationYear = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input for 'Creation Year.' Please enter a valid integer.");
                                scanner.nextLine(); // Consume invalid input
                                break; // Exit the case
                            }
                            System.out.print("New strength: ");
                            String newStrength = scanner.nextLine();

                            // Attempt to edit the superhero
                            boolean editSuccess = controller.editSuperhero(
                                    editName,
                                    newRealName,
                                    newIsHuman,
                                    newCreationYear,
                                    newStrength
                            );

                            if (editSuccess) {
                                System.out.println("Superhero successfully edited.");
                            } else {
                                System.out.println("No matching superhero found for editing.");
                            }
                        } else {
                            System.out.println("No matching superhero found.");
                        }
                        break;


                    case 5:
                        // Remove Superhero
                        System.out.print("Enter the name of the superhero to remove: ");
                        String removeName = scanner.nextLine();
                        removeSuperhero(removeName);
                        break;


                    case 6:
                        running = false;
                        System.out.println("Goodbye!");
                        break;


                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }

