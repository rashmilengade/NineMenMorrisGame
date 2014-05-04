import java.util.ArrayList;
import java.util.List;

public class MoveGeneratorWhite  {


	/**
	 * Generate Moves of White during Opening Phase
	 * @param board
	 * @return List of board position
	 */
	public List<Board> generateMovesOpening(Board board)
	{
		 List<Board> list = new  ArrayList<Board>();
		 list = generateAdd(board);
		 return list;
	}

	/**
	 * Generate Moves of White during MidgameEndgame Phase
	 * @param board
	 * @return List of board position
	 */
	public List<Board> generateMovesMidgameEndgame(Board board)
	{
		 List<Board> list = new  ArrayList<Board>();
		 int numWhitePlaces = 0;
			int i=0;
			while(i<Board.boardSize) 
			{
				if(board.array[i] == 'W')
					numWhitePlaces++;
				i++;
			}
		 if(numWhitePlaces == 3)
			 list = generateHopping(board);
		 else
			 list = generateMove(board);

		 return list;
	}



	public List<Board> generateAdd(Board board)
	{
		 List<Board> list = new  ArrayList<Board>();

		 for(int i = 0; i < Board.boardSize; i++){
			 if(board.array[i] == 'x'){

				 Board newboard = new Board(board);
				 newboard.array[i] = 'W';

				 if(newboard.closeMill(i))
					 generateRemove(newboard,list);
				 else
					 list.add(newboard);	 
			 }
		 }

		 return list;
	}



	public List<Board> generateHopping(Board board)
	{
		 List<Board> list = new  ArrayList<Board>();

		 for(int i = 0; i < Board.boardSize; i++){

			 if(board.array[i] == 'W'){

				 for(int j = 0; j < Board.boardSize; j++){

					 if(board.array[j] == 'x'){

						 Board newboard = new Board(board);
						 newboard.array[i] = 'x';
						 newboard.array[j] = 'W';

						 if(newboard.closeMill(j))
							 generateRemove(newboard, list);
						 else
							 list.add(newboard); 
					 }
				 }
			 }
		 }

		 return list;
	}

	public List<Board> generateMove(Board board)
	{
		 List<Board> list = new  ArrayList<Board>();
		 for(int i = 0; i < Board.boardSize; i++)
		 {
			 if(board.array[i] == 'W'){
				 int[] neighbors = board.neighbors(i);
				 for(int j : neighbors){
					 if(board.array[j] == 'x')
					 {
						 Board newboard = new Board(board);
						 newboard.array[i] = 'x';
						 newboard.array[j] = 'W';
						 if(newboard.closeMill(j))
							 generateRemove(newboard, list);
						 else
							 list.add(newboard);
					 }
				 }
			 }
		 }

		 return list;
	}

	public List<Board> generateRemove(Board board, List<Board> list)
	{

		Boolean listUpdated = false;
		 for(int i = 0; i < Board.boardSize; i++)
		 {
			 if(board.array[i] == 'B' && !board.closeMill(i)){
				 Board newboard = new Board(board);
				 newboard.array[i] = 'x';
				 list.add(newboard);
				 listUpdated = true;
			 }
		 }

		 if(!listUpdated)
			 list.add(board);

		return list;
	}
}