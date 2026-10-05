import java.util.*;

public class TextGame {

    private static void newCommands(String[] commandlets, Game game) {

        if (commandlets.length < 2) {
            System.out.println("invalid command, type help for info");
            return;
        }

        switch (commandlets[1]) {
            case "player" -> {
                if (commandlets.length > 3) {
                    game.newPlayer(commandlets[2], commandlets[3]);
                } else {
                    System.out.println("invalid command, new player needs a name and a color");
                }
            }
            default -> {
                System.out.println("invalid command, type help for info");
            }
        }

    }

    private static void getCommands(String[] commandlets, Game game) {

        if (commandlets.length < 2) {
            System.out.println("invalid command, type help for info");
            return;
        }

        switch (commandlets[1]) {
            case "players" -> {

                Player[] players = game.players();
                String result = "The current available players are: ";

                for (int i = 0; i < players.length; i++) {
                    result += players[i].name();

                    if (i < players.length - 1) {
                        result += " and ";
                    }
                }

                System.out.println(result);

            }
            default -> {
                System.out.println("invalid command, type help for info");
            }
        }

    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Game game = new Game();

        while (true) { 
            
            System.out.print("TextGame> ");
            String command = sc.nextLine();

            String[] commandlets = command.split(" ");

            switch (commandlets[0]) {

                case "quit" -> {
                    return;
                }

                case "start" -> {
                    game.print();
                }

                case "get" -> {
                    getCommands(commandlets, game);
                }

                case "new" -> {
                    newCommands(commandlets, game);
                }

                default -> {
                    System.out.println("invalid command, type help for info");
                }

            }
        }
    }
}