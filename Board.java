package test;

import java.util.ArrayList;

import test.Tile.Bag;

public class Board {
	private static final int NumOfRows=15;
	private static final int NumOfCols=15;
	Tile[][] Tiles;
	char[][] BonusBoard;
	
	private static Board board=null;
	public Board()
	{
		
		Tiles=new Tile[NumOfRows][NumOfCols];
		//RESET THE BOARD TO NULLS
		for(int i=0;i<NumOfRows;i++)
			for(int j=0;j<NumOfCols;j++)
				Tiles[i][j]=null;
		//INITIALIZING THE SCORE BOARD
		BonusBoard=new char[][] 
				{ 
			//R = TRIPLE WORD SCORE
	        //L = DOUBLE LETTER SCORE
	        //Y = DOUBLE WORD SCORE
	        //B = TRIPLE LETTER SCORE
	        //S = DOUBLE WORD SCORE
	        {'R', ' ', ' ', 'L', ' ', ' ', ' ', 'R', ' ', ' ', ' ', 'L', ' ', ' ', 'R'},
	        {' ', 'Y', ' ', ' ', ' ', 'B', ' ', ' ', ' ', 'B', ' ', ' ', ' ', 'Y', ' '},
	        {' ', ' ', 'Y', ' ', ' ', ' ', 'L', ' ', 'L', ' ', ' ', ' ', 'Y', ' ', ' '},
	        {'L', ' ', ' ', 'Y', ' ', ' ', ' ', 'L', ' ', ' ', ' ', 'Y', ' ', ' ', 'L'},
	        {' ', ' ', ' ', ' ', 'Y', ' ', ' ', ' ', ' ', ' ', 'Y', ' ', ' ', ' ', ' '},
	        {' ', 'B', ' ', ' ', ' ', 'B', ' ', ' ', ' ', 'B', ' ', ' ', ' ', 'B', ' '},
	        {' ', ' ', 'L', ' ', ' ', ' ', 'L', ' ', 'L', ' ', ' ', ' ', 'L', ' ', ' '},
	        {'R', ' ', ' ', 'L', ' ', ' ', ' ', 'S', ' ', ' ', ' ', 'L', ' ', ' ', 'R'},
	        {' ', ' ', 'L', ' ', ' ', ' ', 'L', ' ', 'L', ' ', ' ', ' ', 'L', ' ', ' '},
	        {' ', 'B', ' ', ' ', ' ', 'B', ' ', ' ', ' ', 'B', ' ', ' ', ' ', 'B', ' '},
	        {' ', ' ', ' ', ' ', 'Y', ' ', ' ', ' ', ' ', ' ', 'Y', ' ', ' ', ' ', ' '},
	        {'L', ' ', ' ', 'Y', ' ', ' ', ' ', 'L', ' ', ' ', ' ', 'Y', ' ', ' ', 'L'},
	        {' ', ' ', 'Y', ' ', ' ', ' ', 'L', ' ', 'L', ' ', ' ', ' ', 'Y', ' ', ' '},
	        {' ', 'Y', ' ', ' ', ' ', 'B', ' ', ' ', ' ', 'B', ' ', ' ', ' ', 'Y', ' '},
	        {'R', ' ', ' ', 'L', ' ', ' ', ' ', 'R', ' ', ' ', ' ', 'L', ' ', ' ', 'R'}
	};
	}
	
	Tile[][] getTiles() 
	{
		return Tiles.clone();
	}
	static Board getBoard()
	{
		if(board==null)
			board=new Board();
		return board;
	}

    public boolean NeedReplace(Word word) 
    {
        if (word.isVertical()) 
        {
            int i = word.getRow();
            for (Tile t : word.getTiles()) 
            {
                if (t == null)
                    if (Tiles[i][word.getCol()] == null)
                        return false;
                i++;
            }
        }
        if (!word.isVertical()) 
        {
            int i = word.getCol();
            for (Tile t : word.getTiles()) 
            {
                if (t == null)
                    if (Tiles[word.getRow()][i] == null)
                        return false;
                i++;
            }
        }
        return true;
    }

    public boolean boardLegal(Word w) 
    {
        //IF THE WORD JUST ONE LETTER
        if (w.tiles.length < 2)
            return false;
        //IF THE START OF THE WORD NOT ON THE BOARD
        if (w.row < 0 || w.row > 14 || w.col < 0 || w.col > 14)
            return false;
        if (!InsideTheBoard(w))
            return false;
        //CHECKING IF FIRST WORD ON STAR
        if (Tiles[7][7] == null)
        {
            if ((w.vertical && (w.col != 7 || (w.row + w.tiles.length <= 7) || w.row >= 8)) ||
                    (!w.vertical && (w.row != 7 || (w.col + w.tiles.length <= 7) || w.col >= 8)))
                return false;
            return true;
        }
        if (!HaveNeigbours(w))
            return false;
        if (!NeedReplace(w))
            return false;
        return true;
    }

	boolean InsideTheBoard(Word word)
	{
		if(word.isVertical())
		{
			if((word.getRow()+word.getTiles().length)>14)
				return false;
		}
		if(!word.isVertical())
		{
			if((word.getCol()+word.getTiles().length)>14)
				return false;
		}
		return true;
	}

	boolean HaveNeigbours(Word word)

	{
		if(word.isVertical())
		{
			if(word.getRow()>0)
				if(Tiles[word.getRow()-1][word.getCol()]!=null)
					return true;
			for(int i=0;i<word.getTiles().length;i++)
			{
				if(word.getCol()>0)
					if(Tiles[word.getRow()+i][word.getCol()-1]!=null)
						return true;
				if((word.getCol())<14)
					if(Tiles[word.getRow()+i][word.getCol()+1]!=null)
						return true;
			}
			if(word.getRow()+word.getTiles().length<14)
				if(Tiles[word.getRow()+1][word.getCol()]!=null)
					return true;
		}
		if(!word.isVertical())
			{
				if(word.getCol()!=0)
					if(Tiles[word.getRow()][word.getCol()-1]!=null)
						return true;
				for(int i=0;i<word.getTiles().length;i++)
				{
					if(word.getRow()>0)
						if(Tiles[word.getRow()-1][word.getCol()+i]!=null)
							return true;
					if(word.getRow()<14)
						if(Tiles[word.getRow()+1][word.getCol()+i]!=null)
							return true;
				}
				if(word.getCol()+word.getTiles().length<14)
					if(Tiles[word.getRow()][word.getCol()+1]!=null)
						return true;
			}
		return false;
	}

	boolean dictionaryLegal(Word word)

	{
		return true;
	}
	
	public ArrayList<Word> getWords(Word word) {

        ArrayList<Word> words = new ArrayList<>();

        if (word.isVertical()) 
        {
            int i = word.getRow();
            for (Tile t : word.getTiles()) 
            {
                int j = word.getCol();
                ArrayList<Tile> WordsList= new ArrayList<>();
                if (t != null)
                {
                    while (j > 0 && Tiles[i][j - 1] != null)
                        j--;
                    int tempCol = j;
                    while (j < 15 && Tiles[i][j] != null) 
                    {
                        WordsList.add(Tiles[i][j]);
                        j++;
                    }
                    Tile[] tempT = WordsList.toArray(new Tile[0]);
                    Word w = new Word(tempT, i, tempCol, false);
                    if (dictionaryLegal(w) && boardLegal(w))
                        words.add(w);
                }
                i++;
            }
        }
        if (!word.isVertical()) 
        {
            int i = word.getCol();
            for (Tile t : word.getTiles()) 
            {
                int j = word.getRow();
                ArrayList<Tile> WordsList= new ArrayList<>();
                if (t != null) 
                {
                    while (j > 0 && Tiles[j - 1][i] != null)
                        j--;
                    int tempRow = j;
                    while (j < 15 && Tiles[j][i] != null) 
                    {
                        WordsList.add(Tiles[j][i]);
                        j++;
                    }
                    Tile[] tempT = WordsList.toArray(new Tile[0]);
                    Word w = new Word(tempT, tempRow, i, true);
                    if (dictionaryLegal(w) && boardLegal(w))
                        words.add(w);
                }
                i++;
            }
        }

        //make the new word full word if there is nulls

        int i = 0;
        for (Tile t : word.getTiles()) 
        {
            if (word.isVertical()) 
            {
                if (t == null)
                    word.getTiles()[i] = Tiles[word.getRow() + i][word.getCol()];
                i++;
            }
            if (!word.isVertical()) 
            {
                if (t == null)
                    word.getTiles()[i] = Tiles[word.getRow()][word.getCol() + i];
                i++;
            }
        }
        words.add(0, word);
        return words;
    }

	public int getScore(Word word)
	{
		int TotalPoints=0;
		ArrayList<Word> AllWords=getWords(word);
		for(Word w:AllWords)
		{
			int i=0;
			int TotalWordBonus=1;
			int TotalWordScore=0;
			
			for(Tile t:w.getTiles())
			{
				if(w.isVertical())
				{
					switch(BonusBoard[w.getRow()+i][w.getCol()])
					{
					case 'R':
                        TotalWordScore += t.score;
                        TotalWordBonus *= 3;
                        break;

                    case 'Y':
                    case 'S':
                        TotalWordScore += t.score;
                        TotalWordBonus *= 2;
                        BonusBoard[7][7] = ' ';
                        break;

                    case 'B':
                        TotalWordScore += t.score * 3;
                        break;

                    case 'L':
                        TotalWordScore += t.score * 2;
                        break;

                    default:
                        TotalWordScore += t.score;
                        break;
                
					}
				}
				if(!w.isVertical())
				{
					switch(BonusBoard[w.getRow()][w.getCol()+i])
					{
					case 'R':
                        TotalWordScore += t.score;
                        TotalWordBonus *= 3;
                        break;

                    case 'Y':
                    case 'S':
                        TotalWordScore += t.score;
                        TotalWordBonus *= 2;
                        BonusBoard[7][7] = ' ';
                        break;

                    case 'B':
                        TotalWordScore += t.score * 3;
                        break;

                    case 'L':
                        TotalWordScore += t.score * 2;
                        break;

                    default:
                        TotalWordScore += t.score;
                        break;
                
					}
				}
				i++;
			}
			TotalPoints+=(TotalWordScore*TotalWordBonus);
		}
		
		return TotalPoints;
	}

	public int tryPlaceWord(Word word)
	{
		if(boardLegal(word)&&(dictionaryLegal(word)))
		{
			int i=0;
			
				for(Tile t:word.getTiles())
				{
					if(word.isVertical())
					{
						if(t!=null)
							Tiles[word.getRow()+i][word.getCol()]=t;
					}
					if(!word.isVertical())
					{
						if(t!=null)
							Tiles[word.getRow()][word.getCol()+i]=t;
					}
					i++;
				}
			return getScore(word);
		}
		return 0;
		
	}

	
	
}
	
	
	
	
	
