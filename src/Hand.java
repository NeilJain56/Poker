
/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */

import java.util.ArrayList;

public class Hand {

   private Card[] hand;   // The cards in the hand.
   private int count; 
   
   /**
    * Create a hand that is initially empty.
    */
   public Hand() {
      hand = new Card[5];
	  count = 0;
   }
   
   /**
    * Remove all cards from the hand, leaving it empty.
    */
   public void clear() {
      for(int i=0 ; i<hand.length; i++){ hand[i] = null; }
	  count = 0;
   }
   
   /**
    * Add a card to the hand.  It is added at the end of the current hand.
    * @param c the non-null card to be added.
    * @throws NullPointerException if the parameter c is null.
    */
   public void addCard(Card c) {
      
	  for(int i=0 ; i<hand.length; i++){ 
		if (hand[i] == null){
			hand[i] = c;
			count = count + 1;
			break;
		}
	 }

	  
   }
   
   /**
    * Remove a card from the hand, if present.
    * @param c the card to be removed.  If c is null or if the card is not in 
    * the hand, then nothing is done.
    */
   public void removeCard(Card c) {

	for(int i=0 ; i<hand.length; i++){ 
		if (hand[i].equals(c)){
			hand[i] = null;
			count = count-1;
		}
	}

   }
   
   /**
    * Remove the card in a specified position from the hand.
    * @param position the position of the card that is to be removed, where
    * positions are starting from zero.
    * @throws IllegalArgumentException if the position does not exist in
    * the hand, that is if the position is less than 0 or greater than
    * or equal to the number of cards in the hand.
    */
   public void removeCard(int position) {
		if (position < 0 || position >= hand.length)
			throw new IllegalArgumentException("Position does not exist in hand: " + position);
		hand[position] = null;
		count = count - 1;
	}	

   /**
    * Returns the number of cards in the hand.
    */
   public int getCardCount() {
      return count;
   }
   
   /**
    * Gets the card in a specified position in the hand.  (Note that this card
    * is not removed from the hand!)
    * @param position the position of the card that is to be returned
    * @throws IllegalArgumentException if position does not exist in the hand
    */
   public Card getCard(int position) {
      if (position < 0 || position >= hand.length)
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
       return hand[position];
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same suit are
    * grouped together, and within a suit the cards are sorted by value.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortBySuit() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;
	  
      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.
		 
         for (int i = nonnull+1; i < hand.length; i++) {
            Card c1 = hand[i];
			if (c1 != null){
				if ( c1.getSuit() < c.getSuit() ||
						(c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same value are
    * grouped together.  Cards with the same value are sorted by suit.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortByValue() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;
	  
      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.
		 
         for (int i = nonnull+1; i < hand.length; i++) {
            
			Card c1 = hand[i];
            if (c1 != null){
				if ( c1.getValue() < c.getValue() ||
						(c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }
   
   public void printHand(){
	   
	   for(int i=0; i<hand.length; i++){
		   if (hand[i] != null){
			   System.out.println(hand[i]);
		   }
	   }
	   System.out.println();
   }
   

   /******************************** Implement your methods here ****************************************/
   public int numPairs() {
	   sortByValue();
	   int num=0;
	   for(int i=0; i<hand.length-1;i++) {
		   if(hand[i].getValue()==hand[i+1].getValue()) {
			   num++;
		   }
	   }
	   if(hasTriplet()) {
		   num = num-1;
	   }
	   if(hasFourOfAKind()) {
		   num = num-2;
	   }
	   return num;
		   
	   
   }
   public boolean hasTriplet() {
	   sortByValue();
	   if(hasFourOfAKind()) {
		   return true;
	   }
	   for(int i=0; i<hand.length-2; i++) {
		   if(hand[i].getValue()==hand[i+1].getValue() && hand[i].getValue()==hand[i+2].getValue()) {
			   return true;
		   }
		   
	   }
	   return false;
	   
   }
   public boolean hasFourOfAKind() {
	   sortByValue();
	   for(int i=0; i<hand.length-3; i++) {
		   if(hand[i].getValue()==hand[i+1].getValue() && hand[i].getValue()==hand[i+2].getValue() && hand[i].getValue()==hand[i+3].getValue()) {
			   return true;
		   }
	   }
	   return false;
   }
   public boolean hasFlush() {
	   sortBySuit();
	   for(int i=0; i<hand.length-1;i++) {
		   if(hand[i].getSuit()!=hand[i+1].getSuit()) {
			   return false;
		   }
	   }
	   return true;
   }
   public boolean hasStraight() {
	   sortByValue();  
	  if(hand[0].getValue()==1 && hand[1].getValue()==10 && hand[2].getValue()==11 && hand[3].getValue()==12 && hand[4].getValue()==13) {
		  return true;
	  }
	  for(int i=0; i<hand.length-1; i++) {
		  if(hand[i].getValue()+1!=hand[i+1].getValue()) {
			  return false;
		  }
		  
	  }
	  return true;
   }
   public int aceFix(Card c) {
	   if(c.getValue()==1) {
		   return 14;
	   }
	   else {
		   return c.getValue();
	   }
   }
   public boolean hasFullHouse() {
	   if(hasTriplet() && numPairs()==2) {
		   return true;
	   }
	   return false;
	   
   }
   public Card highestValue() {
	   sortByValue();
	   if(hand[0].getValue()==1) return hand[0];
	   return hand[hand.length-1];
   }
   public Card highestDuplicate() {
	   sortByValue();
	   if(hand[0].getValue()==1 && hand[1].getValue()==1) return hand[0];
	   for(int i=hand.length-1;i>0;i--) {
		   if(hand[i].getValue()==hand[i-1].getValue()) {
			return hand[i];   
		   }
	   }
	   return null;
   }
   public int compareTo(Hand h) {
	   this.sortByValue();
	   h.sortByValue();
	   int[] hand1 = {0,0,0,0,0,0,0,0};
	   int[] hand2 = {0,0,0,0,0,0,0,0};
	  
	   if(this.hasStraight() && this.hasFlush()) hand1[0]=1;
	   if(this.hasFourOfAKind()==true) hand1[1]=1;
	   if(this.hasFullHouse()) hand1[2]=1;
	   if(this.hasFlush()) hand1[3]=1;
	   if(this.hasStraight()) hand1[4]=1;
	   if(this.hasTriplet()) hand1[5]=1;
	   if(this.numPairs()==2) hand1[6]=1;
	   if(this.numPairs()==1) hand1[7]=1;
	   
	   if(h.hasStraight() && h.hasFlush()) hand2[0]=1;
	   if(h.hasFourOfAKind()==true) hand2[1]=1;
	   if(h.hasFullHouse()) hand2[2]=1;
	   if(h.hasFlush()) hand2[3]=1;
	   if(h.hasStraight()) hand2[4]=1;
	   if(h.hasTriplet()) hand2[5]=1;
	   if(h.numPairs()==2) hand2[6]=1;
	   if(h.numPairs()==1) hand2[7]=1;
	   
	   
	   
		int high1;
		int high2;
	   
	   int one=0;
	   int two=0;

	   for(int i=0;i<hand1.length;i++) {
		   if(hand1[i]==1) {
			  one=i+1;
			  break;
		   }
	   }
	   for(int i=0; i<hand2.length;i++) {
		   if(hand2[i]==1) {
				  two=i+1;
				  break;
			   }
	   }
	   if(this.highestValue().getValue()==1) {
		   high1=14;
	   }
	   else {
		   high1=this.highestValue().getValue();
	   }
	   
	   if(h.highestValue().getValue()==1) {
		   high2=14;
	   }
	   else {
		   high2=h.highestValue().getValue();
	   }
	   
	   if(two==0 && one==0) {
		   if(high1>high2) return 1;    
		   if(high1<high2) return -1;
			   for(int z=hand.length-1;z>-1;z--) {
				   if(this.hand[z].getValue()>h.hand[z].getValue()) return 1;
				   if(this.hand[z].getValue()<h.hand[z].getValue()) return -1;
			   }
			   return 0;
		   
	   }
	   if(one==0) {
		   one=200;
	   }
	   if(two==0) {
		   two=200;
	   }
	   
	   if(one<two) {
		   return 1;
	   }
	   if(one>two) {
		   return -1;
	   }
	   
	   if(one==two) {
		   if(h.hasStraight() || this.hasFlush()) {
			   if(this.hand[0].getValue()==1 && this.hand[1].getValue()==2) {
				   high1=this.hand[hand.length-1].getValue();
			   }
			   if(h.hand[0].getValue()==1 && h.hand[1].getValue()==2) {
				   high2=h.hand[hand.length-1].getValue();
			   }
			   if(high1>high2) return 1;
			   if(high1<high2) return -1;
			   for(int z=hand.length-1;z>0;z--) {
				   if(this.hand[z].getValue()>h.hand[z].getValue()) return 1;
				   if(this.hand[z].getValue()<h.hand[z].getValue()) return -1;
			   }
			   
		   }
		   int j=0;
		   int m=0;
		   if(hasFourOfAKind()) {
			   for(int i=hand.length-3; i>0; i--) {
				   if(this.hand[i].getValue()==this.hand[i+1].getValue() && this.hand[i].getValue()==this.hand[i+2].getValue() && this.hand[i].getValue()==this.hand[i+3].getValue()) {
					   if(hand[i].getValue()==1) j=14;
					   else {
					   j =hand[i].getValue();
					   }
				   }
				   if(h.hand[i].getValue()==h.hand[i+1].getValue() && h.hand[i].getValue()==h.hand[i+2].getValue() && h.hand[i].getValue()==h.hand[i+3].getValue()) {
					   if(hand[i].getValue()==1) j=14;
					   else {
					   m =hand[i].getValue();
					   }
				   }
			   }
			   if(j>m) return 1;
			   if(j<m) return -1;
		   }
		   
		   int o=0;
		   int p=0;
		   if(this.hasTriplet() || this.numPairs()>0) { 
			   if(this.hasTriplet()) {
			   for(int i=hand.length-3; i>-1; i--) {
			   if(h.hand[i].getValue()==h.hand[i+1].getValue() && h.hand[i].getValue()==h.hand[i+2].getValue()) {
				   o=h.hand[i].getValue();
				   if(h.hand[i].getValue()==1) o=14;
				   
			   }
			   if(this.hand[i].getValue()==this.hand[i+1].getValue() && this.hand[i].getValue()==this.hand[i+2].getValue()) {
				   p=this.hand[i].getValue();
				   if(this.hand[i].getValue()==1) p=14;
				   
				 
			   }
			   
		   }
			   if(p>o) return 1;
			   if(p<o) return -1;
			   }
			   
			   int q=0;
			   int w=0;
			   int e;
			   int t;
			   if(this.highestDuplicate().getValue()==1) {
				   e=14;
			   }
			   else {
				   e=this.highestDuplicate().getValue();
			   }
			   
			   if(h.highestDuplicate().getValue()==1) {
				   t=14;
			   }
			   else {
				   t=h.highestDuplicate().getValue();
			   }
			   
			  if(e>t) return 1;
			  if(e<t) return -1;
			  
			  for(int i=0; i<hand.length-2; i++) {
				  if(this.hand[i]==this.hand[i+1]) {
					  q=this.hand[i].getValue();
				  }
			  }
			  
				  for(int i=0; i<hand.length-2; i++) {
					  if(h.hand[i]==h.hand[i+1]) {
						  w=h.hand[i].getValue();
					  }
					  
			  }
				 
		    if(q>w) return 1;
				 if(q<w) return -1;
				 for(int i=hand.length-1; i>-1;i--) {
					 if(this.hand[i].getValue()!=h.hand[i].getValue()) {
						 if(this.hand[i].getValue()>h.hand[i].getValue()) return 1;
						 if(this.hand[i].getValue()<h.hand[i].getValue()) return -1;
					 }
				 }
	   return 0;
   
		  
   }
		   
	   }
	   return 0;
	   }
		   
}