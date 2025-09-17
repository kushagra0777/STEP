class Box {
    public void pack() {
        System.out.println("Packing the box.");
    }

    public void unpack() {
        System.out.println("Unpacking the box.");
    }
}

public class p6 extends Box {
    @Override
    public void pack() {
        super.pack();
        System.out.println("Adding ribbons and decorations.");
    }

    @Override
    public void unpack() {
        super.unpack();
        System.out.println("Removing wrapping paper and ribbons.");
    }

    public static void main(String[] args) {
        p6 gb = new p6();
        gb.pack();
        gb.unpack();
    }
}
