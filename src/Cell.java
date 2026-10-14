public class Cell {
    
    private boolean isBomb = false;
    private Player owner;

    public boolean isBomb() {
        return isBomb;
    }

    public void setBombValue(boolean value) {
        isBomb = value;
    }

    public Player owner() {
        return owner;
    }

    public void setOwner(Player player) {
        owner = player;
    }

    public boolean isOccupied() {
        return (owner != null);
    }

}