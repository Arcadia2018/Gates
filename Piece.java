public abstract class Piece {
	
	public String color;
	public boolean hasMoved;
	
	/**
	 * Gets the color
	 * @return color of the piece
	 */
	public abstract String getColor();
	public abstract String oppColor();
	public abstract boolean pieceValue(boolean yourResult, boolean oppResult);
	/**
	 * Moves the piece 
	 * 
	 * @return true if the move was valid or not.
	 */
	public abstract int validateMove(Piece[][] board, int[][] refboard, int currentRow, int currentCol, int newRow, int newCol);

}
