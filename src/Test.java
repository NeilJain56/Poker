
public class Test {
	public static void main(String[] args)	{

	Hand newHand = new Hand();

	Hand otherHand = new Hand();

	//HAND 1

	Card Card1 = new Card(1, 1);

	Card Card2 = new Card(1, 1);

	Card Card3 = new Card(1, 2);

	Card Card4 = new Card(1, 1);

	Card Card5 = new Card(11, 1);

	//HAND 2

	Card Card6 = new Card(1, 1);

	Card Card7 = new Card(1, 0);

	Card Card8 = new Card(11, 0);

	Card Card9 = new Card(12, 0);

	Card Card10 = new Card(11, 0);

	newHand.addCard(Card1);

	newHand.addCard(Card2);

	newHand.addCard(Card3);

	newHand.addCard(Card4);

	newHand.addCard(Card5);

	otherHand.addCard(Card6);

	otherHand.addCard(Card7);

	otherHand.addCard(Card8);

	otherHand.addCard(Card9);

	otherHand.addCard(Card10);

	newHand.printHand();

	otherHand.printHand();

	//HAND 1

	System.out.println("Has Four Of A Kind: " + newHand.hasFourOfAKind());

	System.out.println("Has Three Of A Kind: " + newHand.hasTriplet());

	System.out.println("Number of Pairs: " + newHand.numPairs());

	System.out.println("Highest Value Card: " + newHand.highestValue());

	System.out.println("Has Straight: " + newHand.hasStraight());

	System.out.println("Has Flush: " + newHand.hasFlush());

	System.out.println("Has Full House: " + newHand.hasFullHouse());

	System.out.println("Highest Duplicate: " + newHand.highestDuplicate());

	System.out.println();

	System.out.println();

	//HAND 2
	System.out.println("");

	System.out.println("Has Four Of A Kind: " + otherHand.hasFourOfAKind());

	System.out.println("Has Three Of A Kind: " + otherHand.hasTriplet());

	System.out.println("Number of Pairs: " + otherHand.numPairs());

	System.out.println("Highest Value Card: " + otherHand.highestValue());

	System.out.println("Has Straight: " + otherHand.hasStraight());

	System.out.println("Has Flush: " + otherHand.hasFlush());

	System.out.println("Has Full House: " + otherHand.hasFullHouse());

	System.out.println("Highest Duplicate: " + otherHand.highestDuplicate());

	//COMPARE HANDS

	System.out.println("Did I Win: " + newHand.compareTo(otherHand));

	}

	}

