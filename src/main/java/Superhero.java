public class Superhero {
    private String name;
    private String realName;
    private boolean isHuman;
    private int creationYear;
    private String strength;

    public Superhero(String name, String realName, boolean isHuman, int creationYear, String strength) {
        this.name = name;
        this.realName = realName;
        this.isHuman = isHuman;
        this.creationYear = creationYear;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public String getRealName() {
        return realName;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public String getStrength() {
        return strength;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setIsHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    // Add other getter and setter methods as needed
}
