import java.util.List;

public class StaticEstimation {

	static int counter = 0;

	/**
	 * returns static estimation for current location of board
	 * for MidgameEndgame phase
	 * @param node
	 * @return update estimate of input node
	 */
	public Node MidgameEndgame(Node node)
	{
		Board board = node.board;
		int numWhitePieces = 0;
		int numBlackPieces = 0;
		int i=0;
		while(i<Board.boardSize)
		{
			if(board.array[i] == 'W')
				numWhitePieces++;
			if(board.array[i] == 'B')
			numBlackPieces++;
			i++;
		}
		if(numBlackPieces <= 2)
			node.estimate = 10000;
		else if(numWhitePieces <= 2)
			node.estimate = -10000;
		else
		{ 
			List<Board> list = new MoveGeneratorBlack().generateMovesMidgameEndgame(board);		
			int numBlackMoves = list.size();

			if(numBlackMoves == 0)
				node.estimate = 10000;
			else
				node.estimate = (1000*(numWhitePieces - numBlackPieces) - numBlackMoves);
		}

		counter++;
		return node;
	}	

	
	public Node Opening(Node node)
	{
		Board board = node.board;
		int numWhitePieces = 0;
		int numBlackPieces = 0;
		int i=0;
		while(i<Board.boardSize)
		{
			if(board.array[i] == 'W')
				numWhitePieces++;
			if(board.array[i] == 'B')
				numBlackPieces++;
			i++;
		}
	    node.estimate = (numWhitePieces - numBlackPieces);

	    counter++;
	    return node;
	}



}