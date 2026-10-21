public class MoveResult {
    private final boolean correct;
    private final Player winner;
    
    public MoveResult(boolean correct, Player winner) {
        this.correct = correct;
        this.winner = winner;
    } 

    public boolean correct() {
        return correct;
    }

    public Player winner() {
        return winner;
    }
}
