import java.util.*;

public class p2 {

    public static final class KingdomConfig {
        private final String kingdomName;
        private final int foundingYear;
        private final String[] allowedStructureTypes;
        private final Map<String, Integer> resourceLimits; 

        private KingdomConfig(String kingdomName, int foundingYear, String[] allowedStructureTypes, Map<String, Integer> resourceLimits) {
            if (kingdomName == null || kingdomName.isBlank()) {
                throw new IllegalArgumentException("kingdomName cannot be null/blank");
            }
            if (foundingYear <= 0) {
                throw new IllegalArgumentException("foundingYear must be positive");
            }
            if (allowedStructureTypes == null || allowedStructureTypes.length == 0) {
                throw new IllegalArgumentException("allowedStructureTypes must be non-empty");
            }
            if (resourceLimits == null) {
                throw new IllegalArgumentException("resourceLimits cannot be null");
            }

            this.kingdomName = kingdomName;
            this.foundingYear = foundingYear;
            this.allowedStructureTypes = allowedStructureTypes.clone();
            this.resourceLimits = Collections.unmodifiableMap(new HashMap<>(resourceLimits));
        }

        public static KingdomConfig createDefaultKingdom() {
            String[] types = {"WizardTower", "EnchantedCastle", "MysticLibrary", "DragonLair"};
            Map<String, Integer> limits = new HashMap<>();
            limits.put("gold", 100000);
            limits.put("mana", 10000);
            limits.put("stone", 50000);
            return new KingdomConfig("EldenRealm", 1024, types, limits);
        }

        public static KingdomConfig createFromTemplate(String type) {
            if (type == null) type = "default";
            switch (type.toLowerCase(Locale.ROOT)) {
                case "fortress":
                    return new KingdomConfig("FortressHold", 900, new String[]{"EnchantedCastle", "MysticLibrary"},
                            Map.of("gold", 200000, "stone", 200000, "mana", 20000));
                case "arcane":
                    return new KingdomConfig("Arcana", 1100, new String[]{"WizardTower", "MysticLibrary"},
                            Map.of("gold", 50000, "stone", 20000, "mana", 100000));
                default:
                    return createDefaultKingdom();
            }
        }
        public String getKingdomName() { return kingdomName; }
        public int getFoundingYear() { return foundingYear; }
        public String[] getAllowedStructureTypes() { return allowedStructureTypes.clone(); }
        public Map<String, Integer> getResourceLimits() { return new HashMap<>(resourceLimits); }

        @Override
        public String toString() {
            return "KingdomConfig{" +
                    "kingdomName='" + kingdomName + '\'' +
                    ", foundingYear=" + foundingYear +
                    ", allowedStructureTypes=" + Arrays.toString(allowedStructureTypes) +
                    ", resourceLimits=" + resourceLimits +
                    '}';
        }
        public boolean equals(Object o) {
            if (!(o instanceof KingdomConfig)) return false;
            KingdomConfig k = (KingdomConfig) o;
            return kingdomName.equals(k.kingdomName)
                    && foundingYear == k.foundingYear
                    && Arrays.equals(allowedStructureTypes, k.allowedStructureTypes)
                    && resourceLimits.equals(k.resourceLimits);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(kingdomName, foundingYear, resourceLimits);
            result = 31 * result + Arrays.hashCode(allowedStructureTypes);
            return result;
        }
    }
    public static class MagicalStructure {
        private final String structureId;
        private final long constructionTimestamp;
        private final String structureName;
        private final String location;
        private int magicPower;
        private boolean isActive;
        private String currentMaintainer;
        static final int MIN_MAGIC_POWER = 0;
        static final int MAX_MAGIC_POWER = 1000;
        public static final String MAGIC_SYSTEM_VERSION = "3.0";
        public MagicalStructure(String name, String location) {
            this(name, location, 100);
        }

        public MagicalStructure(String name, String location, int power) {
            this(name, location, power, false); 
        }

        public MagicalStructure(String name, String location, int power, boolean active) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("structureName cannot be null/blank");
            }
            if (location == null || location.isBlank()) {
                throw new IllegalArgumentException("location cannot be null/blank");
            }
            if (power < MIN_MAGIC_POWER || power > MAX_MAGIC_POWER) {
                throw new IllegalArgumentException("power must be in [" + MIN_MAGIC_POWER + "," + MAX_MAGIC_POWER + "]");
            }

            this.structureId = UUID.randomUUID().toString();
            this.constructionTimestamp = System.currentTimeMillis();
            this.structureName = name;
            this.location = location;
            this.magicPower = power;
            this.isActive = active;
            this.currentMaintainer = "none";
        }
        public String getStructureId() { return structureId; }
        public long getConstructionTimestamp() { return constructionTimestamp; }
        public String getStructureName() { return structureName; }
        public String getLocation() { return location; }

        public int getMagicPower() { return magicPower; }
        public void setMagicPower(int magicPower) {
            if (magicPower < MIN_MAGIC_POWER || magicPower > MAX_MAGIC_POWER) {
                throw new IllegalArgumentException("magicPower out of range");
            }
            this.magicPower = magicPower;
        }

        public boolean isActive() { return isActive; }
        public void setActive(boolean active) { this.isActive = active; }

        public String getCurrentMaintainer() { return currentMaintainer; }
        public void setCurrentMaintainer(String currentMaintainer) {
            if (currentMaintainer == null) currentMaintainer = "none";
            this.currentMaintainer = currentMaintainer;
        }
        int increasePower(int delta) {
            int prev = this.magicPower;
            this.magicPower = Math.min(MAX_MAGIC_POWER, this.magicPower + delta);
            return this.magicPower - prev;
        }

        int decreasePower(int delta) {
            int prev = this.magicPower;
            this.magicPower = Math.max(MIN_MAGIC_POWER, this.magicPower - delta);
            return prev - this.magicPower;
        }

        @Override
        public String toString() {
            return "MagicalStructure{" +
                    "id='" + structureId + '\'' +
                    ", name='" + structureName + '\'' +
                    ", location='" + location + '\'' +
                    ", magicPower=" + magicPower +
                    ", isActive=" + isActive +
                    ", maintainer='" + currentMaintainer + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof MagicalStructure)) return false;
            MagicalStructure m = (MagicalStructure) o;
            return structureId.equals(m.structureId);
        }

        @Override
        public int hashCode() {
            return structureId.hashCode();
        }
    }
    public static class WizardTower {
        private final MagicalStructure core;
        private final int maxSpellCapacity;
        private final List<String> knownSpells; 
        private String currentWizard;

        public WizardTower(String location) {
            this("Unnamed Tower", location, 50, Arrays.asList("Spark"), "Apprentice");
        }
        public WizardTower(String name, String location, int capacity, List<String> initialSpells) {
            this(name, location, capacity, initialSpells, "Apprentice");
        }
        public WizardTower(String name, String location, int capacity, List<String> initialSpells, String wizard) {
            this.core = new MagicalStructure(name, location, Math.min(500, Math.max(50, capacity * 2)), true);
            if (capacity < 0) throw new IllegalArgumentException("capacity cannot be negative");
            this.maxSpellCapacity = capacity;
            this.knownSpells = new ArrayList<>(initialSpells == null ? Collections.emptyList() : initialSpells);
            this.currentWizard = (wizard == null || wizard.isBlank()) ? "none" : wizard;
        }

        public MagicalStructure getCore() { return core; }
        public int getMaxSpellCapacity() { return maxSpellCapacity; }
        public List<String> getKnownSpells() { return Collections.unmodifiableList(knownSpells); }

        // JavaBean pattern: add/remove spells with validation
        public void learnSpell(String spell) {
            if (spell == null || spell.isBlank()) throw new IllegalArgumentException("spell invalid");
            if (knownSpells.size() < maxSpellCapacity) knownSpells.add(spell);
            else throw new IllegalStateException("Spell capacity reached");
        }

        public boolean forgetSpell(String spell) { return knownSpells.remove(spell); }

        public String getCurrentWizard() { return currentWizard; }
        public void setCurrentWizard(String currentWizard) {
            this.currentWizard = (currentWizard == null || currentWizard.isBlank()) ? "none" : currentWizard;
        }

        @Override
        public String toString() {
            return "WizardTower{" + core + ", maxSpellCapacity=" + maxSpellCapacity + ", knownSpells=" + knownSpells + ", currentWizard='" + currentWizard + "'}";
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof WizardTower)) return false;
            WizardTower w = (WizardTower) o;
            return core.equals(w.core);
        }

        @Override
        public int hashCode() { return core.hashCode(); }
    }
    public static class EnchantedCastle {
        private final MagicalStructure core;
        private final String castleType;
        private int defenseRating;
        private boolean hasDrawbridge;

        public EnchantedCastle(String location) {
            this("Fort", location, "Simple Fort", 100, false);
        }
        public EnchantedCastle(String name, String location, String castleType) {
            this(name, location, castleType, "Royal Castle".equalsIgnoreCase(castleType) ? 400 : 200, "Royal Castle".equalsIgnoreCase(castleType));
        }

        public EnchantedCastle(String name, String location, String castleType, int defenseRating, boolean hasDrawbridge) {
            this.core = new MagicalStructure(name, location, Math.min(MagicalStructure.MAX_MAGIC_POWER, defenseRating * 2), true);
            this.castleType = (castleType == null || castleType.isBlank()) ? "Unknown Castle" : castleType;
            this.defenseRating = Math.max(0, defenseRating);
            this.hasDrawbridge = hasDrawbridge;
        }

        public MagicalStructure getCore() { return core; }
        public String getCastleType() { return castleType; }
        public int getDefenseRating() { return defenseRating; }
        public void setDefenseRating(int defenseRating) { this.defenseRating = Math.max(0, defenseRating); }

        public boolean hasDrawbridge() { return hasDrawbridge; }
        public void setHasDrawbridge(boolean hasDrawbridge) { this.hasDrawbridge = hasDrawbridge; }

        @Override
        public String toString() {
            return "EnchantedCastle{" + core + ", castleType='" + castleType + "', defenseRating=" + defenseRating + ", hasDrawbridge=" + hasDrawbridge + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof EnchantedCastle)) return false;
            EnchantedCastle e = (EnchantedCastle) o;
            return core.equals(e.core);
        }

        @Override
        public int hashCode() { return core.hashCode(); }
    }

    public static class MysticLibrary {
        private final MagicalStructure core;
        private final Map<String, String> bookCollection; 
        private int knowledgeLevel;

        public MysticLibrary(String location) {
            this("Local Library", location, Map.of("Intro to Runes", "Basics of runic inscriptions"), 10);
        }

        public MysticLibrary(String name, String location, Map<String, String> collection) {
            this(name, location, collection, Math.min(100, (collection == null ? 0 : collection.size() * 5)));
        }

        public MysticLibrary(String name, String location, Map<String, String> collection, int knowledgeLevel) {
            this.core = new MagicalStructure(name, location, Math.min(MagicalStructure.MAX_MAGIC_POWER, knowledgeLevel * 3), true);
            this.bookCollection = new HashMap<>(collection == null ? Collections.emptyMap() : collection);
            this.knowledgeLevel = Math.max(0, knowledgeLevel);
        }

        public MagicalStructure getCore() { return core; }
        public Map<String, String> getBookCollection() { return Collections.unmodifiableMap(bookCollection); }
        public int getKnowledgeLevel() { return knowledgeLevel; }
        public void setKnowledgeLevel(int knowledgeLevel) { this.knowledgeLevel = Math.max(0, knowledgeLevel); }

        public void addBook(String title, String description) {
            if (title == null || title.isBlank()) throw new IllegalArgumentException("title invalid");
            bookCollection.put(title, description == null ? "" : description);
        }

        public boolean removeBook(String title) { return bookCollection.remove(title) != null; }

        @Override
        public String toString() {
            return "MysticLibrary{" + core + ", knowledgeLevel=" + knowledgeLevel + ", books=" + bookCollection.keySet() + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof MysticLibrary)) return false;
            MysticLibrary m = (MysticLibrary) o;
            return core.equals(m.core);
        }

        @Override
        public int hashCode() { return core.hashCode(); }
    }

    public static class DragonLair {
        private final MagicalStructure core;
        private final String dragonType;
        private final long treasureValue;
        private final int territorialRadius;

        public DragonLair(String location, String dragonType) {
            this("Dragon Lair", location, dragonType,
                    "Gold".equalsIgnoreCase(dragonType) ? 1_000_000L : 250_000L,
                    "Gold".equalsIgnoreCase(dragonType) ? 1000 : 300);
        }

        public DragonLair(String name, String location, String dragonType, long treasureValue, int territorialRadius) {
            int basePower = (int) Math.min(MagicalStructure.MAX_MAGIC_POWER, Math.max(200, territorialRadius / 1 + (int)Math.min(500, treasureValue / 2000)));
            this.core = new MagicalStructure(name, location, basePower, true);
            this.dragonType = (dragonType == null || dragonType.isBlank()) ? "UnknownDragon" : dragonType;
            this.treasureValue = Math.max(0, treasureValue);
            this.territorialRadius = Math.max(0, territorialRadius);
        }

        public MagicalStructure getCore() { return core; }
        public String getDragonType() { return dragonType; }
        public long getTreasureValue() { return treasureValue; }
        public int getTerritorialRadius() { return territorialRadius; }

        @Override
        public String toString() {
            return "DragonLair{" + core + ", dragonType='" + dragonType + "', treasureValue=" + treasureValue + ", territorialRadius=" + territorialRadius + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof DragonLair)) return false;
            DragonLair d = (DragonLair) o;
            return core.equals(d.core);
        }

        @Override
        public int hashCode() { return core.hashCode(); }
    }
    public static class KingdomManager {
        private final List<Object> structures;
        private final KingdomConfig config;

        public KingdomManager(KingdomConfig config) {
            this.config = Objects.requireNonNull(config, "config cannot be null");
            this.structures = new ArrayList<>();
        }

        public void addStructure(Object structure) {
            if (structure == null) throw new IllegalArgumentException("structure cannot be null");
            String category = determineStructureCategory(structure);
            if (category == null) throw new IllegalArgumentException("Unsupported structure type: " + structure.getClass().getSimpleName());
            this.structures.add(structure);
        }

        public List<Object> getStructures() {
            return Collections.unmodifiableList(structures);
        }
        public static boolean canStructuresInteract(Object s1, Object s2) {
            if (s1 == null || s2 == null) return false;
            if (s1 instanceof WizardTower && s2 instanceof MysticLibrary) return true;
            if (s2 instanceof WizardTower && s1 instanceof MysticLibrary) return true;

            if (s1 instanceof DragonLair && s2 instanceof WizardTower) return false;
            if (s2 instanceof DragonLair && s1 instanceof WizardTower) return false;

            if (s1 instanceof EnchantedCastle || s2 instanceof EnchantedCastle) return true;

            return s1.getClass().equals(s2.getClass());
        }

        public static String performMagicBattle(Object attacker, Object defender) {
            if (attacker == null || defender == null) return "Invalid combatants";

            MagicalStructure aCore = extractCore(attacker);
            MagicalStructure dCore = extractCore(defender);

            if (aCore == null || dCore == null) return "Non-combatant involved";

            int aPower = aCore.getMagicPower();
            int dPower = dCore.getMagicPower();
            if (attacker instanceof DragonLair) aPower += ((DragonLair) attacker).getTerritorialRadius() / 10;
            if (defender instanceof EnchantedCastle) dPower += ((EnchantedCastle) defender).getDefenseRating();
            if (aPower == dPower) {
                aCore.decreasePower(10);
                dCore.decreasePower(10);
                return "Tie: both sides weakened";
            } else if (aPower > dPower) {
                int damage = Math.min(aPower - dPower, dCore.getMagicPower());
                dCore.decreasePower(damage);
                aCore.increasePower(Math.min(MagicalStructure.MAX_MAGIC_POWER - aCore.getMagicPower(), damage / 5));
                return "Attacker wins: defender loses " + damage + " magic power";
            } else {
                int damage = Math.min(dPower - aPower, aCore.getMagicPower());
                aCore.decreasePower(damage);
                dCore.increasePower(Math.min(MagicalStructure.MAX_MAGIC_POWER - dCore.getMagicPower(), damage / 5));
                return "Defender wins: attacker loses " + damage + " magic power";
            }
        }

        public static int calculateKingdomPower(Object[] items) {
            if (items == null) return 0;
            int total = 0;
            for (Object o : items) {
                MagicalStructure core = extractCore(o);
                if (core != null) total += core.getMagicPower();
                else {
                    if (o instanceof WizardTower) total += ((WizardTower) o).getKnownSpells().size() * 5;
                }
            }
            return total;
        }

        private String determineStructureCategory(Object structure) {
            if (structure instanceof WizardTower) return "WizardTower";
            if (structure instanceof EnchantedCastle) return "EnchantedCastle";
            if (structure instanceof MysticLibrary) return "MysticLibrary";
            if (structure instanceof DragonLair) return "DragonLair";
            return null;
        }

        private static MagicalStructure extractCore(Object o) {
            if (o instanceof WizardTower) return ((WizardTower) o).getCore();
            if (o instanceof EnchantedCastle) return ((EnchantedCastle) o).getCore();
            if (o instanceof MysticLibrary) return ((MysticLibrary) o).getCore();
            if (o instanceof DragonLair) return ((DragonLair) o).getCore();
            if (o instanceof MagicalStructure) return (MagicalStructure) o;
            return null;
        }

        @Override
        public String toString() {
            return "KingdomManager{" +
                    "config=" + config +
                    ", structures=" + structures.size() +
                    '}';
        }
    }

    public static void main(String[] args) {
        KingdomConfig config = KingdomConfig.createFromTemplate("arcane");
        System.out.println("Config: " + config);
        KingdomManager manager = new KingdomManager(config);
        WizardTower tower = new WizardTower("Merlyn Tower", "North Ridge", 20, Arrays.asList("Spark", "Shield"), "Merlyn");
        EnchantedCastle castle = new EnchantedCastle("Kingshold", "Central Plains", "Royal Castle");
        MysticLibrary library = new MysticLibrary("Grand Library", "East Quarter", Map.of("Grimoire of Ages", "Ancient spells"), 80);
        DragonLair lair = new DragonLair("Dragon's Keep", "Dark Mountains", "Gold", 2_000_000L, 1200);

        manager.addStructure(tower);
        manager.addStructure(castle);
        manager.addStructure(library);
        manager.addStructure(lair);
        System.out.println("\nStructures registered:");
        for (Object s : manager.getStructures()) System.out.println(" - " + s);

        System.out.println("\nCan tower and library interact? " + KingdomManager.canStructuresInteract(tower, library));
        System.out.println("Can lair and tower interact? " + KingdomManager.canStructuresInteract(lair, tower));
        System.out.println("Can castle and lair interact? " + KingdomManager.canStructuresInteract(castle, lair));

        System.out.println("\nBattle result (lair attacks castle): " + KingdomManager.performMagicBattle(lair, castle));
        System.out.println("After battle stats:");
        System.out.println(" Lair core: " + lair.getCore());
        System.out.println(" Castle core: " + castle.getCore());

        Object[] arr = manager.getStructures().toArray();
        System.out.println("\nTotal kingdom magic power: " + KingdomManager.calculateKingdomPower(arr));
    }
}