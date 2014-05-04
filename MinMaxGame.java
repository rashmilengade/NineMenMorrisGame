import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

public class MinMaxGame {

	static int maximumDepth;
	static StaticEstimation estimation = new StaticEstimation();
	static  MoveGeneratorWhite moveGenWhite = new MoveGeneratorWhite();
	static MoveGeneratorBlack moveGenBlack = new MoveGeneratorBlack();

	public static void main(String[] args)  {

		String positions = null;

		try {
			BufferedReader input = new BufferedReader(new InputStreamReader( new FileInputStream(args[0])));
			String value = input.readLine();
			String [] values = value.split(",");
			positions = values[0];
			input.close();
		    maximumDepth = Integer.parseInt(values[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}

		MinMaxGame game = new MinMaxGame();
		StaticEstimation.counter = 0;

		Board board = new Board(positions);
		Node node = game.MaxMin(new Node(board), 0);

		System.out.println("Board Position: "+ node.board);
		System.out.println("Positions evaluated by static estimation: "+ StaticEstimation.counter);
		System.out.println("MINIMAX estimate: " +node.estimate);

		try {
			PrintWriter output = new PrintWriter(new FileWriter(new File(args[1]), false));
			output.print( node.board);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * MaxMin Plays Move for White i.e Max during MidgameEndgame Phase
	 * @param node
	 * @param depth
	 * @return
	 */
	public Node MaxMin(Node node,int depth)
	{
		if(depth == maximumDepth){
			return estimation.MidgameEndgame(node);
		}

		Node tempMax = new Node(node.board, Integer.MIN_VALUE);

		List<Board> list = moveGenWhite.generateMovesMidgameEndgame(node.board);

		for(Board possibleMove : list){
			Node childNode = new Node(possibleMove);
			Node temp = MinMax(childNode,depth+1);
			if(temp.estimate > tempMax.estimate){
				tempMax.estimate=temp.estimate;
				tempMax.board = childNode.board;
			}
		}

		if(list.size() == 0)
			return estimation.Opening(node);

		return tempMax;
	}

	/**
	 * MinMax Plays Move for Black i.e Min during MidgameEndgame Phase
	 * @param node
	 * @param depth
	 * @return
	 */
	public Node MinMax(Node node,int depth)
	{
		if(depth == maximumDepth){
			return estimation.MidgameEndgame(node);
		}

		Node tempMin = new Node(null, Integer.MAX_VALUE);

		List<Board> list = moveGenBlack.generateMovesMidgameEndgame(node.board);

		for(Board possibleMove : list){
			Node childNode = new Node(possibleMove);
			Node temp = MaxMin(childNode,depth+1);
			if(temp.estimate < tempMin.estimate){
				tempMin.estimate=temp.estimate;
				tempMin.board = childNode.board;
			}
		}

		if(list.size() == 0)
			return estimation.Opening(node);

		return tempMin;
	}



}