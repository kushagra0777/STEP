class Artwork {
    void display() { System.out.println("General artwork"); }
}
class Painting extends Artwork {
    void paintingDetails() { System.out.println("Painting: brush techniques, color palettes, frame specs"); }
}
class Sculpture extends Artwork {
    void sculptureDetails() { System.out.println("Sculpture: material composition, dimensions, lighting"); }
}
class DigitalArt extends Artwork {
    void digitalDetails() { System.out.println("Digital Art: resolution, file formats, interactive elements"); }
}
class Photography extends Artwork {
    void photoDetails() { System.out.println("Photography: camera settings, editing, print specifications"); }
}
public class p5 {
    public static void main(String[] args) {
        Artwork a = new Sculpture();
        a.display();
        if (a instanceof Sculpture) ((Sculpture)a).sculptureDetails();
    }
}
