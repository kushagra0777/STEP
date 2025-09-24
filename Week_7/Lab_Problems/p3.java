class Character {
    void attack() { System.out.println("Character attacks"); }
}
class Warrior extends Character {
    void attack() { System.out.println("Warrior swings sword with high defense!"); }
}
class Mage extends Character {
    void attack() { System.out.println("Mage casts a powerful spell using mana!"); }
}
class Archer extends Character {
    void attack() { System.out.println("Archer shoots an arrow from long range!"); }
}
public class p3 {
    public static void main(String[] args) {
        Character[] army = { new Warrior(), new Mage(), new Archer() };
        for (Character c : army) c.attack();
    }
}
