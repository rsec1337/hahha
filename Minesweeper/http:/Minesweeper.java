import java.util.Scanner;
public class Minesweeper {
    static Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) {
            String playAgain = "yes";
            System.out.println("-------MiN3SW33P3R-------");
            while(playAgain.charAt(0) == 'y' || playAgain.charAt(0) == 'Y') {
                    System.out.println("\nEnter the length of the board (min dim = 8):");
                    int dim = userInput.nextInt();
                    System.out.println("Enter the no of mines:");
                    int mines = userInput.nextInt();
                    System.out.println("The dimensions of the board are " + dim + "x" + dim);
                    System.out.println("There are " + mines + " mines on the board");
                    int i, j, k, l;
                    char[][] solboard = new char[dim+2][dim+2];
                    int c = 0, p, q;
                    while(c < mines) {
                            p = 1 + (int) (Math.random() *dim);
                            q = 1 + (int) (Math.random() *dim);
                            if(solboard[p][q] != '*') {
                                    solboard[p][q] = '*';
                                    c++;
                            }
                    }
                    for(i = 1; i < dim +1; i++) {
                            for(j = 1; j < dim + 1; j++) {
                                    if(solboard[i][j] != '*') {
                                            solboard[i][j] = '0';
                                            for(k = -1; k < 2; k++) {
                                                    for(l = -1; l < 2; l++) {
                                                            if(solboard[i+k][j+l] == '*') {
                                                                    solboard[i][j]+=1;
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
                    char[][] playboard = new char[dim+2][dim+2];
                    for(i = 1; i < dim +1; i++) {
                            for(j = 1; j < dim + 1; j++) {
                                    playboard[i][j] = ' ';
                            }
                    }
                    int stats = 1, fc = 0, cfc = 0;
                    while (stats == 1) {
                            for(i = 1; i < dim +1; i++) {
                                    for(j = 1; j < dim + 1; j++) {
                                            System.out.print("|"+ playboard[i][j]);
                                    }
                                    System.out.println("|");
                            }
                            System.out.println("Your move:");
                            String move = userInput.next();
                            int coj = userInput.nextInt();
                            int coi = userInput.nextInt();
                            switch (move) {
                            case "tap":
                                    if(playboard[coi][coj] == ' ' || playboard[coi][coj] == '0') {
                                            switch (solboard[coi][coj]) {
                                            case '*':
                                                    System.out.println("\nYOU LOST !!!");
                                                    stats = 0;
                                                    break;
                                            case '0':
                                                    open(coi, coj, playboard, solboard);
                                                    for(i = 1; i < dim +1; i++) {
                                                            for(j = 1; j < dim + 1; j++) {
                                                                    if(playboard[i][j] == '0') {
                                                                            for(k = -1; k < 2; k++) {
                                                                                    for(l = -1; l < 2; l++) {
                                                                                            playboard[i+k][j+l] = solboard[i+k][j+l];
                                                                                    }
                                                                            }
                                                                    }
                                                            }
                                                    }
                                                    break;
                                            default:
                                                    playboard[coi][coj] = solboard[coi][coj];
                                            }
                                    }
                                    break;
                            case "flag":
                                    if(playboard[coi][coj] == ' ' && fc < mines) {
                                            playboard[coi][coj] = 'F';
                                            fc+=1;
                                            if(solboard[coi][coj] == '*') {
                                                    cfc+=1;
                                            }
                                            if(cfc == mines) {
                                                    System.out.println("\nYOU WON !!!");
                                                    stats = 0;
                                            }
                                    }
                                    else{
                                            System.out.println("Flags are limited. Unflag first.");
                                    }
                                    break;
                            case "unflag":
                                    if(playboard[coi][coj] == 'F') {
                                            playboard[coi][coj] = ' ';
                                            fc-=1;
                                            if(solboard[coi][coj] == '*') {
                                                    cfc-=1;
                                            }
                                    }
                                    break;
                            }
                    }
                    System.out.println("Here is the position of all mines --->");
                    for(i = 1; i < dim +1; i++) {
                            for(j = 1; j < dim + 1; j++) {
                                    System.out.print("|"+solboard[i][j]);
                            }
                            System.out.print("|\n");
                    }
                    System.out.println("WANT TO PLAY AGAIN ??? (yes or no)");
                    playAgain = userInput.next();
            }
    }
    public static void open(int coi, int coj, char[][] playboard, char[][] solboard) {
            int k, l;
            if (solboard[coi][coj] == '0' && playboard[coi][coj] == ' ')
            {
                    playboard[coi][coj] = '0';
                    for(k = -1; k < 2; k++) {
                            for(l = -1; l < 2; l++) {
                                    open(coi + k, coj + l, playboard,solboard);
                            }
                    }
            }
    }
}
