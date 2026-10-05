public class Player {

    private final String name;
    private final String color;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String name() {
        return name;
    }

    public String color() {
        return color;
    }
}