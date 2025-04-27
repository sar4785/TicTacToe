/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;
import static java.lang.System.in;
import java.util.*;

/**
 *
 * @author COM04
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    static String[] board = new String[9];
    static String turn;  
    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random();

    
    static String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0: line = board[0] + board[1] + board[2]; break;
                case 1: line = board[3] + board[4] + board[5]; break;
                case 2: line = board[6] + board[7] + board[8]; break;
                case 3: line = board[0] + board[3] + board[6]; break;
                case 4: line = board[1] + board[4] + board[7]; break;
                case 5: line = board[2] + board[5] + board[8]; break;
                case 6: line = board[0] + board[4] + board[8]; break;
                case 7: line = board[2] + board[4] + board[6]; break;
            }
            if (line.equals("XXX")) return "X";
            else if (line.equals("OOO")) return "O";
        }

        for (int a = 0; a < 9; a++) {
            if (board[a].equals(String.valueOf(a+1))) {
                return null;
            }
        }
        return "draw";
    }
                    
  static void position(){  
        Random rand = new Random();
        int x,o;
        for(int i=0; i<9; i++){
            if(i%2==0){
                do{x=rand.nextInt(9);}while(board[x]!=null);
                    board[x]= "X";
                }else if(i%2==1){
                    do{o=rand.nextInt(9);}while(board[o]!=null);
                    board[o]= "O ";
                }
            printBoard();
        }
    }
    
    static void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|---|---|---|");
    }
    
    static void clearBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }
    }
    static void playerMove() {
        int numInput;
        while (true) {
            System.out.print("Enter a slot number (1-9) to place X: ");
            try {
                numInput = scanner.nextInt();
                if (numInput >= 1 && numInput <= 9) {
                    if (board[numInput-1].equals(String.valueOf(numInput))) {
                        board[numInput-1] = "X";
                        break;
                    } else {
                        System.out.println("Slot already taken; choose another.");
                    }
                } else {
                    System.out.println("Invalid input; enter number 1-9.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; enter number 1-9.");
                scanner.next(); // Clear wrong input
            }
        }
    }
    
    static void aiMove() {
          // 1. หาโอกาสชนะตัวเอง
    for (int i = 0; i < 9; i++) {
        if (board[i].equals(String.valueOf(i + 1))) {
            board[i] = "O"; // ลองวาง O ลงไปชั่วคราว
            if ("O".equals(checkWinner())) {
                System.out.println("AI placed O at position " + (i + 1) + " to WIN!");
                return;
            }
            board[i] = String.valueOf(i + 1); // คืนค่ากลับ
        }
    }
    
    // 2. ป้องกัน X ชนะ
    for (int i = 0; i < 9; i++) {
        if (board[i].equals(String.valueOf(i + 1))) {
            board[i] = "X"; // ลองวาง X ลงไปดูว่า X จะชนะไหม
            if ("X".equals(checkWinner())) {
                board[i] = "O"; // วาง O ทับเลยเพื่อกัน
                System.out.println("AI placed O at position " + (i + 1) + " to BLOCK!");
                return;
            }
            board[i] = String.valueOf(i + 1); // คืนค่ากลับ
        }
    }
    
    // 3. ถ้าไม่มีอะไร ทำ random move
    int aiMove;
    Random rand = new Random();
    while (true) {
        aiMove = rand.nextInt(9);
        if (board[aiMove].equals(String.valueOf(aiMove + 1))) {
            board[aiMove] = "O";
            System.out.println("AI placed O at random position " + (aiMove + 1));
            break;
        }
    }
}
    public static void main(String[] args) {
        clearBoard();
        printBoard();
        String winner = null;
        turn = "X";

        while (winner == null) {
            if (turn.equals("X")) {
                playerMove();
                turn = "O";
            } else {
                aiMove();
                turn = "X";
            }
            printBoard();
            winner = checkWinner();
        }

        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw!!!");
        } else {
            System.out.println("Congratulations! " + winner + "'s have won!");
        }
    }
}