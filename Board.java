import java.io.IOException;
import java.util.Scanner;

public class Board {

    public Piece[][] board = new Piece[8][8];
    public int[][] refBoard = new int[8][8];

    public Board(){
        this.initialize();
    }

    private void initialize(){
    	int placeOne = 0;
        for(int x = 0; x<board.length; x++){
            for(int y = 0; y<board[0].length; y++){
                board[x][y] = null;
                if (placeOne % 2 == 0) {
                	refBoard[x][y] = 1;
                }
                placeOne++;
            }
            placeOne++;
        }
        //NOR
        board[1][2] = new NOR("white");
        board[1][4] = new NOR("white");
        board[1][6] = new NOR("white");
        board[6][1] = new NOR("black");
        board[6][3] = new NOR("black");
        board[6][5] = new NOR("black");

        //AND
        board[1][1] = new AND("white");
        board[1][3] = new AND("white");
        board[1][5] = new AND("white");
        board[6][2] = new AND("black");
        board[6][4] = new AND("black");
        board[6][6] = new AND("black");

        //XNOR
        board[0][3] = new XNOR("white");
        board[0][5] = new XNOR("white");
        board[7][2] = new XNOR("black");
        board[7][4] = new XNOR("black");

        //XOR
        board[0][2] = new XOR("white");
        board[0][4] = new XOR("white");
        board[7][3] = new XOR("black");
        board[7][5] = new XOR("black");

        //NAND
        board[0][6] = new NAND("white");
        board[7][1] = new NAND("black");

        //OR
        board[0][1] = new OR("white");
        board[7][6] = new OR("black");

    }

    /**
     * Performs the move, and modifies the actual board
     *
     * @throws IOException
     */
    public void performMove(String move, String color, boolean actuallyMove, int[][] refboard) throws IOException{
        int[] moveArray = parseInput(move);
        //System.out.println(board[moveArray[0]][moveArray[1]]);

        if(board[moveArray[0]][moveArray[1]] == null){
            throw new IOException();
        }

        if(!board[moveArray[0]][moveArray[1]].getColor().equals(color)){
            throw new IOException();
        }

        if(board[moveArray[2]][moveArray[3]] != null){
            if(board[moveArray[2]][moveArray[3]].getColor().equals(color)){
                throw new IOException();
            }
        }
        int result = board[moveArray[0]][moveArray[1]].validateMove(board, refboard, moveArray[0], moveArray[1], moveArray[2], moveArray[3]);
        if(result == 0){
            //This means the move was valid

            if(actuallyMove){
                //Switch the two spots on the board because the move was valid
                board[moveArray[2]][moveArray[3]] = board[moveArray[0]][moveArray[1]];
                board[moveArray[0]][moveArray[1]] = null;
            }

        }else if(result == 1){
        	//code for losing piece
        	if (actuallyMove){
        		board[moveArray[0]][moveArray[1]] = null;
        	}

        }else if(result == 2){
        	//code for deleting both pieces
        	if (actuallyMove){
        		board[moveArray[2]][moveArray[3]] = null;
        		board[moveArray[0]][moveArray[1]] = null;
        	}
    	}else
        	throw new IOException();
    }

        //Rules dealing with pawns

    public static int[] parseInput(String move){

    	while ((move.length() != 5) && !(move.charAt(2) == (' '))) {
    		System.out.println("That's not a valid move. Try again.");
    		Scanner kb = new Scanner(System.in);
    		move = kb.nextLine();
    	}

        int[] returnArray = new int[4];

        String[] split = move.split(" ");
        returnArray[1] = charToInt(Character.toLowerCase(split[0].charAt(0)));
        returnArray[0] = Integer.parseInt(move.charAt(1) + "") - 1;

        returnArray[3] = charToInt(Character.toLowerCase(split[1].charAt(0)));
        returnArray[2] = Integer.parseInt(split[1].charAt(1) + "") - 1;
        return returnArray;

    }

    /**
     * Returns an integer corresponding to the user input
     */
    public static int charToInt(char ch){
        switch(ch){
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            default: return 8;
        }
    }

    /**
     * Checks to see if any moves are possible. If not, then it is either a checkmate or stalemate, depending on whether or not anyone is currently in check.
     * @return
     */
    public boolean canAnyPieceMakeAnyMove(String color){

        Piece[][] oldBoard = board.clone();

        for(int x = 0; x<board.length; x++){
            for(int y = 0; y<board[0].length; y++){
                //Check this piece against every other piece...
                for(int w = 0; w<board.length; w++){
                    for(int z = 0; z<board[0].length; z++){
                        try{
                            if(board[x][y] != null){
                                if(board[x][y].getColor().equals(color)){
                                    //System.out.println(coordinatesToMoveString(x, y, w, z));
                                    performMove(coordinatesToMoveString(x, y, w, z), board[x][y].getColor(), false, refBoard);
                                    board = oldBoard;
                                    return true;
                                }
                            }
                            board = oldBoard;
                        } catch(Exception e){
                            board = oldBoard;
                        }
                    }
                }
            }
        }

        board = oldBoard;
        return false;
    }

    private String coordinatesToMoveString(int row, int col, int newRow, int newCol){

        String returnString = "";

        switch(col){
            case 0: returnString += 'a'; break;
            case 1: returnString += 'b'; break;
            case 2: returnString += 'c'; break;
            case 3: returnString += 'd'; break;
            case 4: returnString += 'e'; break;
            case 5: returnString += 'f'; break;
            case 6: returnString += 'g'; break;
            case 7: returnString += 'h'; break;
            default: returnString += 'a'; break;
        }

        int addInt = row + 1;

        returnString += addInt + "";

        returnString += " ";

        switch(newCol){
            case 0: returnString += 'a'; break;
            case 1: returnString += 'b'; break;
            case 2: returnString += 'c'; break;
            case 3: returnString += 'd'; break;
            case 4: returnString += 'e'; break;
            case 5: returnString += 'f'; break;
            case 6: returnString += 'g'; break;
            case 7: returnString += 'h'; break;
            default: returnString += 'a'; break;
        }

        addInt = newRow + 1;

        returnString += addInt + "";
        //System.out.println(row + " " + col + " " + newRow + " " + newCol + " " + returnString);
        return returnString;
    }

    public String toString(){
        String string = "";
        int fileCount = 0;
        for(Piece[] pieces: board){
            int rankCount = 0;
            for(Piece piece: pieces){
                if(piece==null){
                    if(fileCount%2 == 0){
                        if(rankCount%2 == 0){
                            string += "###";
                        }else{
                            string += "   ";
                        }
                    }else{
                        if(rankCount%2 == 0){
                            string += "   ";
                        }else{
                            string += "###";
                        }
                    }
                }else{
                    string += piece;
                }
                string += " ";
                rankCount++;
            }
            fileCount++;
            string += "\n";
        }

        String reverseString = "";

        reverseString += "   a   b   c   d   e   f   g   h \n";
        String[] stringSplit = string.split("\n");
        for(int x = stringSplit.length-1; x >= 0; x--){
            reverseString += x+1 + " " + stringSplit[x] + "\n";
        }

        return reverseString;
    }

}
