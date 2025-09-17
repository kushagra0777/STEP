import java.util.Objects;

class Game {
    String title;

    public Game(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Game: " + title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game g = (Game) o;
        return Objects.equals(title, g.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}

public class p3 extends Game {
    int cards;

    public p3(String title, int cards) {
        super(title);
        this.cards = cards;
    }

    @Override
    public String toString() {
        return super.toString() + ", Card count: " + cards;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (!(o instanceof p3)) return false;
        p3 cg = (p3) o;
        return cards == cg.cards;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cards);
    }

    public static void main(String[] args) {
        p3 g1 = new p3("Poker", 52);
        p3 g2 = new p3("Poker", 52);
        System.out.println(g1);
        System.out.println(g1.equals(g2));
    }
}
