public class Cell {
    
    private boolean isBomb = false;
    private Player owner;
    private boolean flagged = false;
    public boolean isBomb() {
        return isBomb;
    }

    public void setBombValue(boolean value) {
        isBomb = value;
    }

    public Player owner() {
        return owner;
    }

    public void setFlag(boolean value) {
        flagged = value;
    
    }
    public boolean isFlagged() {
        return flagged;
    }
    public void setOwner(Player player) {
        owner = player;
    }

    public boolean isOccupied() {
        return (owner != null);
    }

}