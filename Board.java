public class Board {

	public char[] array;
	final static int boardSize = 23;
	final static int[][] neighbors = new int[][]{
			{1,3,8}, 	
			{0,2,4},  	
			{1,5,13}, 	
			{0,4,6,9}, 	
			{1,3,5}, 	
			{2,4,7,12}, 		
			{3,7,10}, 	
			{5,6,11}, 
			{0,9,20}, 	
			{3,8,10,17}, 	
			{6,9,14},
			{7,12,16}, 	
			{5,11,13,19}, 	
			{2,12,22},
			{10,15,17},		
			{14,16,18}, 	
			{11,15,19}, 	
			{9,14,18,20}, 	
			{15,17,19,21}, 	
			{12,16,18,22}, 
			{8,17,21},
			{18,20,22},
			{13,19,21}
		};

	public Board(String boardValues) {
		this.array = boardValues.toCharArray();
	}

	public Board(Board other) {
		this.array = other.array.clone();
	}

	public int[] neighbors(int j)
	{
		return neighbors[j];
	}
	public void swap()
	{
		int i=0;
		while(i<boardSize){
			if(array[i] == 'W')
				array[i] = 'B';
			else if(array[i] == 'B')
				array[i] = 'W';
			i++;
		}		
	}

	public boolean closeMill(int location) {

		char C = array[location];

		if(C != 'W')
			return false;
		if( C != 'B')
			return false;

		switch(location)
		{
			case 0:
				if((array[8] == C && array[20] == C) || (array[3] == C && array[6] == C) || (array[1] == C && array[2] == C))
					return true;
			break;
			case 1:
				if((array[0] == C && array[2] == C))
					return true;
			break;
			case 2:
				if((array[0] == C && array[1] == C) || (array[3] == C && array[7] == C) || (array[22] == C && array[13] == C))
					return true;
			break;
			case 3:
				if((array[0] == C && array[6] == C) || (array[4] == C && array[5] == C) || (array[9] == C && array[17] == C))
					return true;
			break;
			case 4:
				if((array[3]== C && array[5] == C))
					return true;
			break;
			case 5:
				if((array[3] == C && array[4] == C) || (array[2] == C && array[7] == C))
					return true;
			break;
			case 6:
				if((array[0] == C && array[3] == C) || (array[10] == C && array[14] == C))
					return true;
			break;
			case 7:
				if((array[2] == C && array[3] == C) || (array[11] == C && array[16] == C))
					return true;
			break;
			case 8:
				if((array[0] == C && array[20] == C) || (array[9] == C && array[10] == C))
					return true;
			break;
			case 9:
				if((array[8] == C && array[10] == C) || (array[3] == C && array[17] == C))
					return true;
			break;
			case 10:
				if((array[6] == C && array[14] == C) || (array[8] == C && array[9] == C))
					return true;
			break;
			case 11:
				if((array[12] == C && array[13] == C) || (array[7] == C && array[16] == C))
					return true;
			break;
			case 12:
				if((array[11] == C && array[13] == C) || (array[5] == C && array[19] == C))
					return true;
			break;
			case 13:
				if((array[11] == C && array[12] == C) || (array[2] == C && array[22] == C))
					return true;
			break;
			case 14:
				if((array[6] == C && array[10] == C) || (array[15] == C && array[16] == C) || (array[17] == C && array[20] == C))
					return true;
			break;
			case 15:
				if((array[14] == C && array[16] == C) ||  (array[18] == C && array[21] == C) )
					return true;
			break;
			case 16:
				if((array[7] == C && array[11] == C) || (array[17] == C && array[21] == C) || (array[14] == C && array[15] == C))
					return true;
			break;
			case 17:
				if((array[18] == C && array[19] == C) || (array[3] == C && array[9] == C) || (array[14] == C && array[20] == C))
					return true;
			break;
			case 18:
				if((array[17] == C && array[19] == C) || (array[15] == C && array[21] == C))
					return true;
			break;
			case 19:
				if((array[16] == C && array[22] == C) || (array[17] == C && array[18] == C) || (array[3] == C && array[12] == C))
					return true;
			break;
			case 20:
				if((array[0] == C && array[8] == C) || (array[21] == C && array[22] == C) || (array[14] == C && array[17] == C))
					return true;
			break;
			case 21:
				if((array[20] == C && array[22] == C) || (array[15] == C && array[18] == C))
					return true;
			break;
			case 22:
				if((array[20] == C && array[21] == C) || (array[2] == C && array[13] == C) || (array[16] == C && array[19] == C))
					return true;
			break;
		}

		return false;
	}

	
	@Override
	public String toString() {
		return new String(array);
	}


	public int countFreedom()
	{
		int noOfPossibleMoves=0;
		for(int i = 0; i < boardSize;i++){
			int[] neighborList = neighbors(i);

			for(int neighbor:neighborList){
				if(this.array[neighbor] == 'x')
					noOfPossibleMoves++;
			}
		}
		return noOfPossibleMoves;
	}

	public int countOpenMills()
	{
		int noOf2s=0;
		for(int i = 0; i < boardSize;i++){
			if(this.array[i] == 'x'){
				this.array[i] = 'W';
				if(closeMill(i))
					noOf2s++;
				this.array[i] = 'x';
			}
		}
		return noOf2s;
	}

	public int millBlocked()
	{
		int blocked = 0;
		for(int i = 0; i < boardSize;i++){
			if(closeMill(i)){
				if(this.array[i] == 'W')
					blocked++;
				else
					blocked--;
			}
		}
		return blocked;
	}

	public int countMills() {
		int millCount= 0;
		for(int i = 0; i < boardSize;i++){
			if(this.array[i] == 'W' && closeMill(i))
				millCount++;
		}
		return millCount/3;
	}

	public int countDoubleMorris() {

		int doubleMorris = 0;

			for(int i = 0; i < boardSize;i++){
				if(closeMill(i)){
					int[] neighborList = neighbors(i);

					for(int neighbor:neighborList){
						if(array[neighbor] == 'x'){
							array[i] = 'W';
							if(closeMill(i))
								doubleMorris++;
							array[i] = 'x';
						}
					}
				}
			}
			return doubleMorris;
		}
}