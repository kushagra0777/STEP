import java.util.Objects;
import java.util.UUID;

public class p1 {

    public static final class PetSpecies {
        private final String speciesName;
        private final String[] evolutionStages;
        private final int maxLifespan;
        private final String habitat;

        public PetSpecies(String speciesName, String[] evolutionStages, int maxLifespan, String habitat) {
            if (speciesName == null || speciesName.isBlank()) {
                throw new IllegalArgumentException("Species name cannot be null/blank");
            }
            if (evolutionStages == null || evolutionStages.length == 0) {
                throw new IllegalArgumentException("Species must have at least one evolution stage");
            }
            if (maxLifespan <= 0) {
                throw new IllegalArgumentException("Max lifespan must be positive");
            }
            if (habitat == null || habitat.isBlank()) {
                throw new IllegalArgumentException("Habitat cannot be null/blank");
            }

            this.speciesName = speciesName;
            this.evolutionStages = evolutionStages.clone();
            this.maxLifespan = maxLifespan;
            this.habitat = habitat;
        }

        public String getSpeciesName() { return speciesName; }
        public String[] getEvolutionStages() { return evolutionStages.clone(); }
        public int getMaxLifespan() { return maxLifespan; }
        public String getHabitat() { return habitat; }

        @Override
        public String toString() {
            return speciesName + " (Habitat: " + habitat + ", Lifespan: " + maxLifespan + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof PetSpecies)) return false;
            PetSpecies other = (PetSpecies) obj;
            return speciesName.equals(other.speciesName) && habitat.equals(other.habitat);
        }

        @Override
        public int hashCode() {
            return speciesName.hashCode() * 31 + habitat.hashCode();
        }
    }
    public static class VirtualPet {
        private final String petId;
        private final PetSpecies species;
        private final long birthTimestamp;

        private String petName;
        private int age;
        private int happiness;
        private int health;

        protected static final String[] DEFAULT_EVOLUTION_STAGES = {"Egg", "Child", "Teen", "Adult"};
        static final int MAX_HAPPINESS = 100;
        static final int MAX_HEALTH = 100;
        public static final String PET_SYSTEM_VERSION = "2.0";

        public VirtualPet() {
            this("Unnamed Pet", new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 100, "Generic"), 0, 50, 50);
        }

        public VirtualPet(String petName) {
            this(petName, new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 100, "Generic"), 0, 60, 60);
        }

        public VirtualPet(String petName, PetSpecies species) {
            this(petName, species, 0, 60, 60);
        }

        public VirtualPet(String petName, PetSpecies species, int age, int happiness, int health) {
            this.petId = generatePetId();
            this.species = Objects.requireNonNull(species, "Species cannot be null");
            this.birthTimestamp = System.currentTimeMillis();
            setPetName(petName);
            setAge(age);
            setHappiness(happiness);
            setHealth(health);
        }

        public String getPetId() { return petId; }
        public PetSpecies getSpecies() { return species; }
        public long getBirthTimestamp() { return birthTimestamp; }

        public String getPetName() { return petName; }
        public void setPetName(String petName) {
            if (petName == null || petName.isBlank()) {
                throw new IllegalArgumentException("Pet name cannot be blank");
            }
            this.petName = petName;
        }

        public int getAge() { return age; }
        public void setAge(int age) {
            if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
            this.age = age;
        }

        public int getHappiness() { return happiness; }
        public void setHappiness(int happiness) {
            this.happiness = validateStat(happiness, MAX_HAPPINESS);
        }

        public int getHealth() { return health; }
        public void setHealth(int health) {
            this.health = validateStat(health, MAX_HEALTH);
        }

        public void feedPet(String foodType) {
            int bonus = calculateFoodBonus(foodType);
            modifyHealth(bonus);
        }

        public void playWithPet(String gameType) {
            int effect = calculateGameEffect(gameType);
            modifyHappiness(effect);
        }

        protected int calculateFoodBonus(String foodType) {
            return foodType.equalsIgnoreCase("fruit") ? 10 : 5;
        }

        protected int calculateGameEffect(String gameType) {
            return gameType.equalsIgnoreCase("fetch") ? 15 : 8;
        }

        private void modifyHappiness(int delta) {
            happiness = Math.min(MAX_HAPPINESS, happiness + delta);
            updateEvolutionStage();
        }

        private void modifyHealth(int delta) {
            health = Math.min(MAX_HEALTH, health + delta);
        }

        private void updateEvolutionStage() {
        }

        private int validateStat(int value, int max) {
            if (value < 0) return 0;
            return Math.min(value, max);
        }

        private String generatePetId() {
            return UUID.randomUUID().toString();
        }

        String getInternalState() {
            return "[DEBUG] Pet: " + petName + ", H:" + happiness + ", He:" + health;
        }

        @Override
        public String toString() {
            return "VirtualPet{" + "id='" + petId + "', name='" + petName + "', species=" + species + ", age=" + age +
                    ", happiness=" + happiness + ", health=" + health + '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof VirtualPet)) return false;
            VirtualPet other = (VirtualPet) obj;
            return petId.equals(other.petId);
        }

        @Override
        public int hashCode() {
            return petId.hashCode();
        }
    }

    public static class DragonPet {
        private final String dragonType;
        private final String breathWeapon;
        private final VirtualPet basePet;

        public DragonPet(String petName, String dragonType, String breathWeapon) {
            this.basePet = new VirtualPet(petName, new PetSpecies("Dragon",
                    new String[]{"Hatchling", "Wyrmling", "Young Dragon", "Adult Dragon"}, 500, "Cave"), 0, 70, 80);
            this.dragonType = dragonType;
            this.breathWeapon = breathWeapon;
        }

        public String getDragonType() { return dragonType; }
        public String getBreathWeapon() { return breathWeapon; }
        public VirtualPet getBasePet() { return basePet; }
    }

    public static class RobotPet {
        private boolean needsCharging;
        private int batteryLevel;
        private final VirtualPet basePet;

        public RobotPet(String petName) {
            this.basePet = new VirtualPet(petName, new PetSpecies("Robot",
                    new String[]{"Prototype", "Mark I", "Mark II", "Advanced"}, 300, "Lab"), 0, 50, 90);
            this.needsCharging = false;
            this.batteryLevel = 100;
        }

        public boolean isNeedsCharging() { return needsCharging; }
        public int getBatteryLevel() { return batteryLevel; }

        public void charge() {
            batteryLevel = 100;
            needsCharging = false;
        }

        public void drainBattery(int amount) {
            batteryLevel -= amount;
            if (batteryLevel <= 20) needsCharging = true;
        }

        public VirtualPet getBasePet() { return basePet; }
    }

    public static void main(String[] args) {
        VirtualPet pet1 = new VirtualPet("Fluffy");
        System.out.println(pet1);
        pet1.feedPet("fruit");
        pet1.playWithPet("fetch");
        System.out.println("After actions: " + pet1.getInternalState());

        DragonPet draco = new DragonPet("Draco", "Fire Dragon", "Fire Breath");
        System.out.println(draco.getBasePet());
        System.out.println("Dragon type: " + draco.getDragonType() + ", Weapon: " + draco.getBreathWeapon());

        RobotPet robo = new RobotPet("RoboBuddy");
        System.out.println(robo.getBasePet());
        robo.drainBattery(85);
        System.out.println("Battery: " + robo.getBatteryLevel() + ", Needs charging: " + robo.isNeedsCharging());
        robo.charge();
        System.out.println("After charging → Battery: " + robo.getBatteryLevel());
    }
}
