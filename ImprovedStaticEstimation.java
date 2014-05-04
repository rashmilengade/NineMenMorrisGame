import java.util.List;


public class ImprovedStaticEstimation {
	
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
				node.estimate = Integer.MAX_VALUE-1;
			else if(numWhitePieces <= 2)
				node.estimate = Integer.MIN_VALUE+1;
			else
			{ 
				List<Board> list = new MoveGeneratorBlack().generateMovesMidgameEndgame(board);		
				int numBlackMoves = list.size();

				if(numBlackMoves == 0)
					node.estimate = Integer.MAX_VALUE-1;
				else
				{
			        int placeDiff = numWhitePieces - numBlackPieces;
			        int millNum         = board.countMills();
			        int openMillNum     = board.countOpenMills();
			        int doubleMorrisNum = board.countDoubleMorris();

					node.estimate = (placeDiff*1000 + numBlackMoves*(-5) + millNum*100 + openMillNum*150 + doubleMorrisNum*300);
			        //node.estimate = (placeDiff*50 + numBlackMoves*(-10)  + openMillNum*150 + doubleMorrisNum*300);
			        //node.estimate = (1000*(numWhitePlaces - numBlackPlaces) - numBlackMoves);
				}	
			}

			counter++;
			return node;
		}	

		/**
		 * returns static estimation for current location of board
		 * for opening phase
		 * @param node
		 * @return update estimate of input node
		 */
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

			int noOfPossibleMoves= board.countFreedom();

			int noOf2s=board.countOpenMills();

			int placeDiff = numWhitePieces - numBlackPieces;

			
		    //node.estimate = (noOfPossibleMoves*10 + noOf2s*50 + placeDiff*100 + millBlocked*200);
			node.estimate = (placeDiff*100 + noOfPossibleMoves*5 + noOf2s*500 );
			//node.estimate = (50*placeDiff +noOfPossibleMoves);

		    counter++;
		    return node;
		}



	}

