class Tool {
    private String name;
    protected String material;
    public String usage;

    public Tool(String name, String material, String usage) {
        this.name = name;
        this.material = material;
        this.usage = usage;
    }

    public String getName() {
        return name;
    }
}

public class p2 extends Tool {
    public p2(String name, String material, String usage) {
        super(name, material, usage);
    }

    public void showAccess() {
        System.out.println("Private via getter: " + getName());
        System.out.println("Protected directly: " + material);
        System.out.println("Public directly: " + usage);
    }

    public static void main(String[] args) {
        p2 hammer = new p2("Hammer", "Steel", "Hitting nails");
        hammer.showAccess();
    }
}
