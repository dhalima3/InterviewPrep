package Slalom;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class PokerHands {

	private static String suits = "cdhs";
	private static String[] ranks  = { "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};

	public static void main(String [] args) throws Exception{
		ArrayList<Card> hand = new ArrayList<Card>();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Scanner scan = new Scanner(reader.readLine());
		scan.useDelimiter(",");
		for (int i = 0; i < 5; i++){
			String temp = scan.next();
			Card card = new Card(temp.indexOf(1), temp.indexOf(0));
			hand.add(card);
		}
		value(hand);
	}
	
	public static void value(ArrayList<Card> hand){
	      if (flush(hand) && straight(hand)) System.out.println("flush");
	      else if (fourKind(hand)) System.out.println("four of a kind");
	      else if (fullHouse(hand)) System.out.println("full house");
	      else if (flush(hand)) System.out.println("flush");
	      else if (straight(hand)) System.out.println("straight");
	      else if (threeKind(hand)) System.out.println("three of a kind");
	      else if (twoPairs(hand)) System.out.println("two pairs");
	      else if (onePair(hand)) System.out.println("one pair");
	      else System.out.println("high card");
	}

	public static boolean flush(ArrayList<Card> hand){
		//Check to make sure it's a valid hand
		if (hand.size() != 5 || hand == null) return false;
		sortSuit(hand);
		if (hand.get(0).getSuit() == hand.get(4).getSuit()) return true;
		return false;
	}

	public static boolean straight(ArrayList<Card> hand){
		//Check to make sure it's a valid hand
		if (hand.size() != 5 || hand == null) return false;
		sortRank(hand);
		//Special case for Ace
		if (hand.get(4).getRank() == 14){
			boolean a = hand.get(0).getRank() == 2 && hand.get(1).getRank() == 3 && hand.get(2).getRank() == 4 && hand.get(3).getRank() == 5;
			boolean b = hand.get(0).getRank() == 10 && hand.get(1).getRank() == 11 && hand.get(2).getRank() == 12 && hand.get(3).getRank() == 13;
			return (a || b);
		} else {
			int test = hand.get(0).getRank() + 1;
			for (int i = 1; i < 5; i++){
				if (hand.get(i).getRank() != test) return false;
				test++;
			}
			return true;
		}
	}

	public static boolean fourKind(ArrayList<Card> hand){
		boolean a, b;
		//Check to make sure it's a valid hand
		if (hand.size() != 5 || hand == null) return false;
		sortRank(hand);
		//4 same cards + higher leftover card
		a = hand.get(0).getRank() == hand.get(1).getRank() && hand.get(1).getRank() == hand.get(2).getRank() && hand.get(2).getRank() == hand.get(3).getRank();
		//4 same cards + lower leftover card
		b = hand.get(1).getRank() == hand.get(2).getRank() && hand.get(2).getRank() == hand.get(3).getRank() && hand.get(3).getRank() == hand.get(4).getRank();
		return (a || b);
	}

	public static boolean fullHouse(ArrayList<Card> hand){
		boolean a, b;
		//Check to make sure it's a valid hand
		if (hand.size() != 5 || hand == null) return false;
		sortRank(hand);
		a = hand.get(0).getRank() == hand.get(1).getRank() && hand.get(1).getRank() == hand.get(2).getRank() && hand.get(3).getRank() == hand.get(4).getRank();
		b = hand.get(0).getRank() == hand.get(1).getRank() && hand.get(2).getRank() == hand.get(3).getRank() && hand.get(3).getRank() == hand.get(4).getRank();
		return (a || b);
	}

	public static boolean threeKind(ArrayList<Card> hand){
		boolean a, b, c;
		//Check to make sure it's a valid hand
		if (hand.size() != 5 || hand == null) return false;
		sortRank(hand);
		//check 3 3 3 1 2
		a = hand.get(0).getRank() == hand.get(1).getRank() && hand.get(1).getRank() == hand.get(2).getRank() && hand.get(3).getRank() != hand.get(0).getRank() && hand.get(4).getRank() != hand.get(0).getRank() && hand.get(3).getRank() != hand.get(4).getRank();
		//check 1 3 3 3 2
		b = hand.get(1).getRank() == hand.get(2).getRank() && hand.get(2).getRank() == hand.get(3).getRank() && hand.get(0).getRank() != hand.get(1).getRank() && hand.get(0).getRank() != hand.get(4).getRank() && hand.get(4).getRank() != hand.get(1).getRank();
		// check 1 2 3 3 3
		c = hand.get(3).getRank() == hand.get(4).getRank() && hand.get(2).getRank() == hand.get(3).getRank() && hand.get(2).getRank() != hand.get(0).getRank() && hand.get(1).getRank() != hand.get(2).getRank() && hand.get(0).getRank() != hand.get(1).getRank();
		return (a || b || c);
	}

	public static boolean twoPairs(ArrayList<Card> hand){
		boolean a, b, c;
		//Check to make sure it's a valid hand
		if (hand.size() != 5 || hand == null) return false;
		sortRank(hand);
		//check 1 1 2 2 3
		a = hand.get(0).getRank() == hand.get(1).getRank() && hand.get(2).getRank() == hand.get(3).getRank(); 
		//check 1 1 3 2 2
		b = hand.get(0).getRank() == hand.get(1).getRank() && hand.get(4).getRank() == hand.get(3).getRank();
		//check 3 1 1 2 2
		c = hand.get(1).getRank() == hand.get(2).getRank() && hand.get(4).getRank() == hand.get(3).getRank();
		return (a || b || c);
	}

	public static boolean onePair(ArrayList<Card> hand){
		boolean a, b, c, d;
		//Check to make sure it's a valid hand
		if (hand.size() != 5 || hand == null) return false;
		if (fourKind(hand) || fullHouse(hand) || threeKind(hand) || twoPairs(hand)) return false;
		sortRank(hand);
		//check 1 1 2 3 4
		a = hand.get(0).getRank() == hand.get(1).getRank();
		//check 2 1 1 3 4
		b = hand.get(1).getRank() == hand.get(2).getRank();
		//check 2 3 1 1 4
		c = hand.get(2).getRank() == hand.get(3).getRank();
		//check 2 3 4 1 1
		d = hand.get(3).getRank() == hand.get(4).getRank();
		return (a || b || c || d);
	}
	
	//Use selection sort to sort poker hand by suit
	public static void sortSuit(ArrayList<Card> hand){
		int i, j, min;
		for (i = 0; i < hand.size(); i++){
			min = i;
			for (j = i+1; j < hand.size(); j++){
				if (hand.get(j).getSuit() < hand.get(min).getSuit()){
					min = j;
				}
			}
			Card temp = hand.get(i);
			hand.set(i, hand.get(min));
			hand.set(min, temp);
		}
	}

	//Use selection sort to sort poker hand by rank
	public static void sortRank(ArrayList<Card> hand){
		int i, j, min;
		for (i = 0; i < hand.size(); i++){
			min = i;
			for (j = i+1; j < hand.size(); j++){
				if (hand.get(j).getRank() < hand.get(min).getRank()){
					min = j;
				}
			}
			Card temp = hand.get(i);
			hand.set(i, hand.get(min));
			hand.set(min, temp);
		}
	}
}

class Card{
	public static final int SPADE   = 4;
	public static final int HEART   = 3;
	public static final int CLUB    = 2;
	public static final int DIAMOND = 1;

	private static final String[] Suit = {"d", "c", "h", "s"};
	private static final String[] Rank = { "*", "*", "2", "3", "4",
		"5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

	private int cardSuit;
	private int cardRank;

	public Card( int suit, int rank ){
		if ( rank == 1 )
			cardRank = 14;
		else
			cardRank = rank;
		cardSuit = suit;
	}

	public int getSuit(){
		return cardSuit;         				      
	}

	public int getRank(){
		return cardRank;
	}
}