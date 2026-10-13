import java.util.*;

public class TextGame {

    private static ArrayList<Player> players = new ArrayList<>();

    private static Player findPlayer(String name) {
        for (Player player : players) {
            if (player.name().equals(name)) {
                return player;
            }
        }

        return null;
    }

    private static void newCommands(String[] commandlets) {

        if (commandlets.length < 2) {
            System.out.println("invalid command, type help for info");
            return;
        }

        switch (commandlets[1]) {
            case "player" -> {
                if (commandlets.length > 2) {

                    for (Player player : players) {
                        if (player.name().equals(commandlets[2])) {
                            System.out.println("That name is already in use.");
                            return;
                        }
                    }

                    players.add(new Player(commandlets[2]));

                } else {
                    System.out.println("invalid command, new player needs a name and a color");
                }
            }
            default -> {
                System.out.println("invalid command, type help for info");
            }
        }

    }

    private static void getCommands(String[] commandlets) {

        if (commandlets.length < 2) {
            System.out.println("invalid command, type help for info");
            return;
        }

        switch (commandlets[1]) {
            case "players" -> {

                Player[] playerArray = players.toArray(Player[]::new);
                String result = "The current available players are: ";

                for (int i = 0; i < playerArray.length; i++) {
                    result += playerArray[i].name();

                    if (i < playerArray.length - 1) {
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
                    if (commandlets.length >= 3) {

                        Player first = findPlayer(commandlets[1]);
                        Player second = findPlayer(commandlets[2]);
                        Float bombDensity = null;

                        if (commandlets[3] != null) {
                            try {
                                bombDensity = Float.valueOf(commandlets[3]);
                            } catch (NumberFormatException e) {
                                System.out.println("The 4th arg must be parseable as a float or left out");
                                return;
                            }
                        }

                        if (first == null) {
                            System.out.println("Couldnt find the player name %s".formatted(commandlets[1]));
                            return;
                        }

                        if (second == null) {
                            System.out.println("Couldnt find the player name %s".formatted(commandlets[2]));
                            return;
                        }

                        if (bombDensity != null) {
                            game.init(first, second, bombDensity);
                        } else {
                            game.init(first, second);
                        }

                    }
                }

                case "get" -> {
                    getCommands(commandlets);
                }

                case "new" -> {
                    newCommands(commandlets);
                }

                default -> {
                    System.out.println("invalid command, type help for info");
                }

            }
        }
    }
}