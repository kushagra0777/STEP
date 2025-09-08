
import java.util.*;
class VirtualPet {
    final String petId;
    String petName, species;
    int age, happiness, health, stageIndex;
    static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder", "Ghost"};
    static int totalPetsCreated = 0;

    static String generatePetId() {
        return "PET-" + UUID.randomUUID().toString();
    }

    public VirtualPet(String petName, String species, int age, int happiness, int health, int stageIndex) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.stageIndex = stageIndex;
        totalPetsCreated++;
    }

    public VirtualPet() {
        this("Unknown", "Mysterious", 0, 50, 50, 0);
    }

    public VirtualPet(String petName) {
        this(petName, "Unknown", 1, 60, 60, 1);
    }

    public VirtualPet(String petName, String species) {
        this(petName, species, 3, 70, 70, 2);
    }

    public void feedPet() { if (!isDead()) health += 10; }
    public void playWithPet() { if (!isDead()) happiness += 10; }
    public void healPet() { if (!isDead()) health += 15; }

    public void simulateDay() {
        if (isDead()) return;
        age++;
        Random rnd = new Random();
        happiness -= rnd.nextInt(5);
        if (happiness < 0) happiness = 0;
        health -= rnd.nextInt(5);
        if (health <= 0) {
            health = 0;
            stageIndex = EVOLUTION_STAGES.length - 1;
            species = "Ghost";
            return;
        }
        evolvePet();
    }

    public void evolvePet() {
        if (isDead()) return;
        if (age > 2 && stageIndex < EVOLUTION_STAGES.length - 2) stageIndex++;
    }

    public boolean isDead() {
        return EVOLUTION_STAGES[stageIndex].equals("Ghost");
    }

    public String getPetStatus() {
        return String.format("[%s] %s (%s) - Age: %d, Happiness: %d, Health: %d, Stage: %s",
                petId, petName, species, age, happiness, health, EVOLUTION_STAGES[stageIndex]);
    }
}

public class p1 {
    public static void main(String[] args) {
        VirtualPet p1 = new VirtualPet();
        VirtualPet p2 = new VirtualPet("Luna");
        VirtualPet p3 = new VirtualPet("Max", "Dragon");
        VirtualPet p4 = new VirtualPet("Bella", "Fox", 5, 80, 90, 3);
        VirtualPet[] pets = {p1, p2, p3, p4};
        for (int day = 1; day <= 5; day++) {
            System.out.println("\n--- Day " + day + " ---");
            for (VirtualPet pet : pets) {
                pet.simulateDay();
                System.out.println(pet.getPetStatus());
            }
        }
        System.out.println("\nTotal Pets Created: " + VirtualPet.totalPetsCreated);
    }
}
