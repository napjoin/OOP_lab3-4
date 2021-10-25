import java.util.Random;
import java.util.Scanner;

public class Board implements Interface{
    private Character[][] matrix;
    private Character[][] board;
    private String FPl;

    public Character[][] boardConv(Character[][] matrix){
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                board[i*2][j*2]=matrix[i][j];
            }
        }
        return board;
    }

    public Board (String FPl){
        this.FPl = FPl;
        matrix = creatEmptyMatrix();
    }

    public void coutBoard(){
        board = createBoard();
        board = boardConv(matrix);
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public Character[][] getMatrix(){
        return matrix;
    }

    private Character[][] createBoard () {
        Character[][] b = new Character[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++){
                if (i%2==0 && j%2==0) b[i][j]=' ';
                if (i%2==1) b[i][j]='-';
                if (j%2==1) b[i][j]='|';
            }
        }
        return b;
    }

    private Character[][] creatEmptyMatrix () {
        Character [][] m = new Character [3][3];
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                m[i][j]=' ';
            }
        }
        return m;
    }

    private boolean horWin (Character[][] matrix){
        boolean check = false;
        for (int i=0; i<3; i++){
            if (matrix[i][0]==matrix[i][1] && matrix[i][1]==matrix[i][2] && matrix[i][0]!=' '){
                check = true;
                break;
            }
        }
        return check;
    }

    private boolean werWin (Character[][] matrix){
        boolean check = false;
        for (int i=0; i<3; i++){
            if (matrix[0][i]==matrix[1][i] && matrix[1][i]==matrix[2][i] && matrix[0][i]!=' '){
                check = true;
                break;
            }
        }
        return check;
    }

    private boolean diaWin (Character[][] matrix){
        boolean check = false;

        if (matrix[0][0]==matrix[1][1] && matrix[1][1]==matrix[2][2] && matrix[0][0]!=' '){
            check = true;
        }
        else if (matrix[0][2]==matrix[1][1] && matrix[1][1]==matrix[2][0] && matrix[1][1]!=' '){
            check = true;
        }
        return check;
    }

    public boolean absWin (Character[][] matrix){
        if (horWin(matrix) || werWin(matrix) || diaWin(matrix)) {
            return true;
        }
        else return false;
    }

    public boolean  absDraw (Character[][] matrix){
        boolean ch = true;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (matrix[i][j] == ' ') ch = false;
            }
        }
        return ch;
    }

    public void turn(String player, char s){
        Scanner scan = new Scanner(System.in);
        System.out.println(player + ": ");
        int x = scan.nextInt();
        int y = scan.nextInt();
        x--;
        y--;

        if (x<0 || x>2 || y<0 || y>2){
            System.out.println("Incorrect input");
            turn(player, s);
        }
        else {
            matrix[x][y] = s;
        }
    }

    public void bestMove (char symb){
        int[] bestM = new int[2];
        int maScore = -100;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (matrix[i][j]== ' ') {
                    Character[][] nM = new Character[3][3];
                    for (int a=0; a<3; a++){
                        for (int b=0; b<3; b++){
                            nM[a][b]=matrix[a][b];
                        }
                    }
                    int score = minimax (nM, 0, symb);
                    if (score > maScore) {
                        maScore = score;
                        bestM[0] = i;
                        bestM[1] = j;
                    }
                }
            }
        }
        matrix [bestM[0]][bestM[1]] = symb;
    }

    private int minimax(Character[][] matrix, int depth, Character symb){
        boolean ynWin = absWin(matrix);
        boolean ynDraw = absDraw(matrix);
        if (ynWin){
            if (depth % 2 == 0){

                return 1;
            }
            else {

                return -1;
            }
        }
        else if (ynDraw) {

            return 0;
        }
        int maSc = -1000000;
        int miSc = 1000000;
        symb = (symb == 'O')? 'X' : 'O';
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (matrix[i][j]==' ') {
                    Character[][] nM = new Character[3][3];
                    for (int a=0; a<3; a++){
                        for (int b=0; b<3; b++){
                            nM[a][b]=matrix[a][b];
                        }
                    }
                    nM [i][j]=symb;
                    int score = minimax(nM, depth + 1, symb);
                    Random random = new Random();
                    if (depth % 2 == 1){
                        if(maSc != Math.max(maSc,score)){
                            if(random.nextInt(10) < 8){
                                maSc = Math.max(maSc,score);
                            }
                        }
                    //maSc = Math.max(maSc, score);}
                    else {
                            if (miSc != Math.min(miSc, score)) {
                                if (random.nextInt(10) < 8) {
                                    maSc = Math.min(miSc, score);
                                }
                            }
                        }
                    }
                   //miSc = Math.min(miSc, score);}
                }
            }
        }
        int res = depth % 2 == 1 ? maSc : miSc;

        return res;
    }
}
