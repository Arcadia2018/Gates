
public class XOR extends Piece{

	public String color;
	public boolean hasMoved;

	public XOR(String color){
		this.color = color;
		this.hasMoved = false;
	}

	@Override
	public int validateMove(Piece[][] board, int[][] refBoard, int currentRow, int currentCol, int newRow, int newCol) {

		if(Math.abs(newRow - currentRow) > 2 || Math.abs(newCol - currentCol) > 2){
			return 3;
		
		} else {
			if(hasMoved){
				return 3;
			}//initiate fight if piece is at new location
			else if ((board[newRow][newCol] != null) && !board[newRow][newCol].getColor().equals(this.getColor())){
				int yourVal = refBoard[currentRow][currentCol];
				int oppVal = refBoard[newRow][newCol];
				boolean yourResult = true;
				boolean oppResult = true;
				//convert 1 and 0 to True and False
				if (yourVal == 1){
					yourResult = true;
				}else if (yourVal == 0){
					yourResult = false;
				}if (oppVal == 1){
					oppResult = true;
				}else if (oppVal == 0){
					oppResult = false;
				}
				//update values for both player
				boolean yourFinResult = pieceValue(yourResult, oppResult);
				boolean oppFinResult = board[newRow][newCol].pieceValue(yourResult, oppResult);

				//figuring out what piece you are fighting
				if (yourFinResult ^ oppFinResult){//if different results
					if (yourFinResult)
						return 0;//you won, program can update the board
					else
						return 1;//you lost, your piece is removed
				}else
					return 2;//both lost, both pieces removed

			}
		}
		return 0;
	}

	public String getColor(){
		return this.color;
	}

	public String toString(){
		return " " + color.charAt(0) + "X";
	}

	public String oppColor(){

		if (this.color != null && this.color == "white")
			return "black";
		else if (this.color != null && this.color == "black")
			return "white";
		else
			return null;
	}
	public boolean pieceValue(boolean yourVal, boolean oppVal){
		if (yourVal ^ oppVal){
			return true;
		}
		else
			return false;
	}

}
