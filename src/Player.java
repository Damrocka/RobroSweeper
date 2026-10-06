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

    public String colorCode() {

        if (color.equals("red")) {
            return "\u001B[31m";
        }

        if (color.equals("green")) {
            return "\u001B[32m";
        }

        if (color.equals("blue")) {
            return "\u001B[34m";
        }

        return "";

    }
}