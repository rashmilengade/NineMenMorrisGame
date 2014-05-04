import java.util.ArrayList;
import java.util.List;


public class MoveGeneratorBlack {

	static MoveGeneratorWhite moveGeneratorWhite = new MoveGeneratorWhite();

	/**
	 * Generate Moves of White during Opening Phase
	 * @param board
	 * @return List of board position
	 */

	public List<Board> generateMovesOpening(Board board)
	{
		 List<Board> list = new  ArrayList<Board>();

		 Board tempboard = new Board(board);
		 tempboard.swap();

		 list = moveGeneratorWhite.generateMovesOpening(tempboard);

		 for(Board b : list)
			 b.swap();

		 return list;
	}

	/**
	 * Generate Moves of Black during MidgameEndgame Phase
	 * @param board
	 * @return List of board position
	 */
	public List<Board> generateMovesMidgameEndgame(Board board)
	{
		 List<Board> list = new  ArrayList<Board>();

		 Board tempboard = new Board(board);
		 tempboard.swap();

		 list = moveGeneratorWhite.generateMovesMidgameEndgame(tempboard);

		 for(Board b : list)
			 b.swap();

		 return list;
	}
}
