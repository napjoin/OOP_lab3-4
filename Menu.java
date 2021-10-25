public class Menu {
    static String User = "player";

    public static void setUser(String nameOfUser) {
        Menu.User = nameOfUser;
    }

    public Menu(String nameOfUser) {
        this.User = nameOfUser;
    }

    void PrintMenu(){
        System.out.println(User + ", welcome to TicTacToe");
        System.out.println("1. Start");
        System.out.println("2. Exit");
    }

    void printResult(String res){
        for(int i = 0;i < res.length() + 2;i++){
            System.out.print('-');
        }
        System.out.println();
        System.out.println('|' + res + '|');

        for(int i = 0;i < res.length() + 2;i++){
            System.out.print('-');
        }
        System.out.println();
    }
}
