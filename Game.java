import java.util.Scanner;

public class Game {
    static Scanner scanner = new Scanner(System.in);
    private static char pTurn = 'X';
    private static String User;
    static Menu menu = new Menu(User);
    
    public static  void startGame(){
        System.out.println("Tell me your name!");
        User = scanner.next();
        while (true){
            Menu.setUser(User);
            menu.PrintMenu();
            int ch = scanner.nextInt();
            choice(ch);
        }
    }

    public static void choice(int n){
        if (n==1) {
            play(User);
        }
        else {
            System.out.println("Game closed");
            System.exit(0);
        }
    }

    private static void play(String FPl){
        String SPl = "Ai";
        Board board = new Board(FPl);
        int i=0;
        while (!board.absWin(board.getMatrix())){
            board.coutBoard();
            if ((i % 2 == 0 && pTurn == 'X') || (i % 2 == 1 && pTurn == 'O')) {

                board.turn(FPl, pTurn);
            } else {
                System.out.println("Ai:");
                board.bestMove(pTurn == 'X' ? 'O' : 'X');
            }
            if (i == 8) {

                menu.printResult("Draw");
                return;
            }

            i++;

        }
        i--;
        if ((i % 2 == 0 && pTurn == 'X') || (i % 2 == 1 && pTurn == 'O')) {
            menu.printResult(FPl + " win");
        } else {
            menu.printResult(SPl + " win");
        }
    }
}
