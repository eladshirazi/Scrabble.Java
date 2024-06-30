package test;

import java.util.Objects;

public class Tile {
	
 final char letter;
 final int score;
 final static int[] LimitOfLetter= {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
private Tile(char letter, int score) {
	super();
	this.letter = letter;
	this.score = score;
}
@Override
public int hashCode() {
	return Objects.hash(letter, score);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Tile other = (Tile) obj;
	return letter == other.letter && score == other.score;
}

public static class Bag
{
	int[] AmountOfLetter= {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
	Tile[] bag = new Tile[26];
	private static Bag Sak=null;
private Bag() {
	bag[0]=new Tile('A',1);
	bag[1]=new Tile('B',3);
	bag[2]=new Tile('C',3);
	bag[3]=new Tile('D',2);
	bag[4]=new Tile('E',1);
	bag[5]=new Tile('F',4);
	bag[6]=new Tile('G',2);
	bag[7]=new Tile('H',4);
	bag[8]=new Tile('I',1);
	bag[9]=new Tile('J',8);
	bag[10]=new Tile('K',5);
	bag[11]=new Tile('L',1);
	bag[12]=new Tile('M',3);
	bag[13]=new Tile('N',1);
	bag[14]=new Tile('O',1);
	bag[15]=new Tile('P',3);
	bag[16]=new Tile('Q',10);
	bag[17]=new Tile('R',1);
	bag[18]=new Tile('S',1);
	bag[19]=new Tile('T',1);
	bag[20]=new Tile('U',1);
	bag[21]=new Tile('V',4);
	bag[22]=new Tile('W',4);
	bag[23]=new Tile('X',8);
	bag[24]=new Tile('Y',4);
	bag[25]=new Tile('Z',10);
}
	
Tile getRand() 
{
	boolean Empty=true;
	for(int i=0;i<26;i++)
	{
		if(AmountOfLetter[i]!=0)
		{
			Empty=false;
			break;
		}
	}
	if(Empty)
		return null;
	int x=(int) (Math.random()*25);
	while(AmountOfLetter[x]==0)
		 x=(int) Math.random();
	AmountOfLetter[x]--;
	return bag[x];
}
	
Tile getTile(char ch) 
{
	
	for(int i=0;i<26;i++)
	{
		if((bag[i].letter==ch)&&(AmountOfLetter[i]>0))
		{
			AmountOfLetter[i]--;
			return bag[i];
		}	
	}
	return null;	
}

void put(Tile tl)
{
	for(int i=0;i<26;i++)
	{
		if((bag[i].letter==tl.letter)&&(LimitOfLetter[i]>AmountOfLetter[i]))
		{
		AmountOfLetter[i]++;
		break;
		}
	}
}

int size()
{
	int counter=0;
	for(int i=0;i<26;i++)
	{
		if(AmountOfLetter[i]!=0)
		{
			counter+=AmountOfLetter[i];
		}
	}
			return counter;
}

int[] getQuantities()
{
	return this.AmountOfLetter.clone();
}

public static Bag getBag()
{
	if(Sak==null)
		Sak=new Bag();
	return Sak;
}




}
}

	
