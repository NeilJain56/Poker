public class Player{

	//declare your fields here
	private double playerBalance;
	private Hand hand1;
	//initialize your fields in the constructor
	public Player(double balance){
		this.playerBalance = balance;
		hand1 = new Hand();
	}

	public void deal(Card c){
		this.hand1.addCard(c);
		
	}

	//Returns an array of Cards that the Player wishes to discard.
	//The game engine will call deal() on this player for each card
	//that exists in the return value. MS2 Instructions: Print the hand to
	//the terminal using System.out.println and ask the user which cards 
	//they would like to discard. The user will first the number of cards they
    //wish to discard, followed by the indices, one at a time, of
	//the card(s) they would like to discard, 
	//Return an array with the appropriate Card objects
	//that have been discarded, and remove the Card objects from this
	//player's hand. Use IO.readInt() for all inputs. In cases of error
	//re-ask the user for input.
	//
	// Example call to discard():
	//
	// This is your hand:
	// 0: Ace of Hearts
	// 1: 2 of Diamonds
	// 2: 5 of Hearts
	// 3: Jack of Spades
	// 4: Ace of Clubs
	// How many cards would you like to discard?
	// 2
	// 1
	// 2
	//
	// The resultant array will contain the 2 of Diamonds and the 5 of hearts in that order
	// This player's hand will now only have 3 cards
	/*
	public Card[] discard(){
		System.out.println("This is your hand:");
		this.hand1.printHand();
		System.out.println("How many cards would you like to discard?");
		int x=IO.readInt();
		Card[] arr = new Card[x];
		for(int i=0; i<x; i++) {
			int y = IO.readInt();
			arr[i]=this.hand1.getCard(y);
			this.hand1.removeCard(y);
		}
		return arr;
	}
	*/
	public Card[] discard() {
		
		if(this.hand1.hasStraight() || this.hand1.hasFlush() || this.hand1.hasFourOfAKind() || this.hand1.hasFullHouse()) {
			Card[] arr = new Card[0];
			return arr;
			
		}
		if(this.hand1.hasTriplet()) {
			this.hand1.sortByValue();
			if(this.hand1.getCard(1).getValue() == this.hand1.getCard(2).getValue()) {
				Card[] arr = new Card[1];
				arr[0]=this.hand1.getCard(3);
				this.hand1.removeCard(3);
				return arr;
			}
			else {
				Card[] arr = new Card[1];
				arr[0]=this.hand1.getCard(1);
				this.hand1.removeCard(1);
				return arr;
			}
		}
		if(this.hand1.numPairs()==2) {
			this.hand1.sortByValue();
			
				if(this.hand1.getCard(0).getValue()!=this.hand1.getCard(1).getValue()) {
					Card[] arr = new Card[1];
					arr[0]=this.hand1.getCard(0);
					this.hand1.removeCard(0);
					return arr;
				}
				if(this.hand1.getCard(3).getValue()!=this.hand1.getCard(4).getValue()) {
					Card[] arr = new Card[1];
					arr[0]=this.hand1.getCard(4);
					this.hand1.removeCard(4);
					return arr;
				}
				else {
					Card[] arr = new Card[1];
					arr[0]=this.hand1.getCard(2);
					this.hand1.removeCard(2);
					return arr;
				}
			}
		if(this.hand1.numPairs()==1) {
			int j=0;
			for(int i=0; i<4; i++) {
				if(this.hand1.getCard(i).getValue()==this.hand1.getCard(i+1).getValue()) {
					j=i;
				}
			}
			if(j==0) {
				Card[] arr = new Card[1];
				arr[0]=this.hand1.getCard(2);
				this.hand1.removeCard(2);
				return arr;
			}
			else {
				Card[] arr = new Card[1];
				arr[0]=this.hand1.getCard(0);
				this.hand1.removeCard(0);
				return arr;
			}
		}
		Card[] arr = new Card[2];
		arr[0]=this.hand1.getCard(2);
		arr[1]=this.hand1.getCard(3);
		this.hand1.removeCard(2);
		this.hand1.removeCard(3);
		return arr;
		}
		
	
	//Returns the amount that this player would like to wager, returns
	//-1.0 to fold hand. Any non zero wager should immediately be deducted
	//from this player's balance. This player's balance can never be below
	// 0 at any time. This player's wager must be >= to the parameter min
	// MS2 Instructions: Show the user the minimum bet via the terminal 
	//(System.out.println), and ask the user for their wager. Use
	//IO.readDouble() for input. In cases of error re-ask the user for 
	//input.
	// 
	// Example call to wager()
	//
	// How much do you want to wager?
	// 200
	//
	// This will result in this player's balance reduced by 200
	/*
	public double wager(double min){
		System.out.println("The minimum bet is: " + min);
		while(true) {
			System.out.println("How much do you want to wager?");
			double wager = IO.readDouble();
			if(wager==-1) {
				return -1;
			}
			else if(wager<min) {
				System.out.println("Please enter enough money");
			}
			else if((this.playerBalance - wager)<0){
				System.out.println("Not enough money in your bank");
			}
			else {
				this.playerBalance = this.playerBalance-wager;
				return wager;
			}
		}
	}
*/
	public double wager(double min) {
		if(min>playerBalance) return -1;
		if(this.hand1.hasStraight() || this.hand1.hasFlush() || this.hand1.hasFourOfAKind() || this.hand1.hasFullHouse()) {
			if (((min+10)*2)<playerBalance){
				return (min+10)*2;
			}
			else {
				return playerBalance;
			}
			
		}
		if(this.hand1.hasTriplet()) {
			if (((min+10)*1.5)<playerBalance){
				return (min+10)*1.5;
			}
			else {
				return playerBalance;
			}
			
		}
		if(this.hand1.numPairs()==2 || this.hand1.numPairs()==1) {
			return min+5;
		}
		if(min>0) {
		return -1;
		}
	return min;
	}
	//Returns this player's hand
	public Hand showHand(){
		return this.hand1;
	}

	//Returns this player's current balance
	public double getBalance(){
		return this.playerBalance;
	}

	//Increase player's balance by the amount specified in the parameter,
	//then reset player's hand in preparation for next round. Amount will
	//be 0 if player has lost hand
	public void winnings(double amount){
		this.playerBalance = this.playerBalance + amount;
		this.hand1.clear();
	}

}