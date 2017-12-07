import java.io.IOException;
import java.util.Scanner;
public class Chess {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Board gameBoard = new Board();
        String color = "white";

        boolean drawAvailable = false;

        while(true){

            System.out.println(gameBoard);

            System.out.println(color + " make a move: ");
            Scanner sc = new Scanner(System.in);
            String move = sc.nextLine();

            if(drawAvailable){
                if(move.contains("draw")){
                    System.out.println("The game is a draw.");
                    return;
                }else{
                    drawAvailable = false;
                }
            }

            if(move.contains("resign")){
                System.out.println(color + " resigns");
                System.out.println(colorToggle(color) + " wins the game!");
                return;
            }

            try {
                gameBoard.performMove(move, color, true, gameBoard.refBoard);
            } catch (IOException e) {
                // Ask for user input again
                System.out.println("Invalid input!");
                continue;
            }

            Piece[][] oldBoard = gameBoard.board.clone();

            if(!gameBoard.canAnyPieceMakeAnyMove(colorToggle(color))){

            }

            gameBoard.board = oldBoard;

            color = colorToggle(color);

        }

    }

    public static String colorToggle(String color){
        if(color.equals("white")){
            return "black";
        }

        return "white";
    }

}
