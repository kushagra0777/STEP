abstract class MagicalStructure {
    String structureName, location;
    int magicPower;
    boolean isActive;

    public MagicalStructure(String structureName, int magicPower, String location, boolean isActive) {
        this.structureName = structureName;
        this.magicPower = magicPower;
        this.location = location;
        this.isActive = isActive;
    }

    public abstract void castMagicSpell();
}

class WizardTower extends MagicalStructure {
    int spellCapacity;
    String[] knownSpells;

    public WizardTower(String name, int magicPower, String location) {
        super(name, magicPower, location, true);
        this.spellCapacity = 5;
        this.knownSpells = new String[]{"Fireball"};
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " casts a wizard spell!");
    }
}

class EnchantedCastle extends MagicalStructure {
    int defenseRating;
    boolean hasDrawbridge;

    public EnchantedCastle(String name, int magicPower, String location, int defenseRating, boolean drawbridge) {
        super(name, magicPower, location, true);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = drawbridge;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " channels castle magic!");
    }
}

class MysticLibrary extends MagicalStructure {
    int bookCount;
    String ancientLanguage;

    public MysticLibrary(String name, int magicPower, String location, int bookCount, String language) {
        super(name, magicPower, location, true);
        this.bookCount = bookCount;
        this.ancientLanguage = language;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " whispers ancient knowledge!");
    }
}

class DragonLair extends MagicalStructure {
    String dragonType;
    int treasureValue;

    public DragonLair(String name, int magicPower, String location, String dragonType, int treasureValue) {
        super(name, magicPower, location, true);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " unleashes dragon fury!");
    }
}

class KingdomManager {
    public static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        return s1.isActive && s2.isActive;
    }

    public static String performMagicBattle(MagicalStructure attacker, MagicalStructure defender) {
        if (attacker.magicPower > defender.magicPower)
            return attacker.structureName + " wins!";
        else if (attacker.magicPower < defender.magicPower)
            return defender.structureName + " wins!";
        else
            return "It's a tie!";
    }

    public static int calculateKingdomMagicPower(MagicalStructure[] structures) {
        int total = 0;
        for (MagicalStructure s : structures) total += s.magicPower;
        return total;
    }
}

public class p2 {
    public static void main(String[] args) {
        WizardTower tower = new WizardTower("Arcane Spire", 80, "North");
        EnchantedCastle castle = new EnchantedCastle("Silver Keep", 70, "East", 100, true);
        MysticLibrary library = new MysticLibrary("Ancient Archive", 60, "West", 5000, "Elder Tongue");
        DragonLair lair = new DragonLair("Crimson Lair", 90, "South", "Fire Dragon", 1000);

        MagicalStructure[] structures = {tower, castle, library, lair};
        for (MagicalStructure s : structures) s.castMagicSpell();

        System.out.println("Battle: " + KingdomManager.performMagicBattle(tower, lair));
        System.out.println("Total Kingdom Power: " + KingdomManager.calculateKingdomMagicPower(structures));
    }
}
