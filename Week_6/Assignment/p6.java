class Weather {
    String description;

    public Weather(String description) {
        this.description = description;
        System.out.println("Weather: " + description);
    }

    public void show() {
        System.out.println("General weather: " + description);
    }
}

class Storm extends Weather {
    public Storm(String description) {
        super(description);
        System.out.println("Storm created");
    }

    @Override
    public void show() {
        System.out.println("Stormy weather: " + description);
    }
}

class Thunderstorm extends Storm {
    public Thunderstorm(String description) {
        super(description);
        System.out.println("Thunderstorm created");
    }

    @Override
    public void show() {
        System.out.println("Thunderstorm: " + description);
    }
}

class Sunshine extends Weather {
    public Sunshine(String description) {
        super(description);
        System.out.println("Sunshine created");
    }

    @Override
    public void show() {
        System.out.println("Sunny weather: " + description);
    }
}

public class p6 {
    public static void main(String[] args) {
        Weather[] forecasts = {
            new Weather("Mild"),
            new Storm("Windy"),
            new Thunderstorm("Heavy Rain with Lightning"),
            new Sunshine("Bright and Warm")
        };

        for (Weather w : forecasts) {
            w.show();
        }
    }
}
