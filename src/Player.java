import java.awt.Color;

public class Player {

    private final String name;
    private Color color;

    public Player(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String name() {
        return name;
    }

    public Color color() {
        return color;
    }

}