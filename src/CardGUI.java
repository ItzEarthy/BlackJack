package defaul;

public class Card {
		
	int number;
	
	String shape;
	boolean cardUsed=false;
	String symbol;
	String name;
	int id;
	
	public Card(int n, String s,int i) {
		this.number=n;
		this.shape=s;
		this.id=i;
		//checks to assign value and name to card
		if(number<11) {
			symbol=Integer.toString(number);
			name =Integer.toString(number);
		}else if(number==11) {
			symbol="J";
			name ="Jack";
		}else if(number==12) {
			symbol="Q";
			name="Queen";
			
		}else if(number==13) {
			symbol="K";
			name="King";
		}else {
			symbol ="A";
			name ="Ace";
		}
		
		System.out.println("Card "+name+" of "+shape+" was created, id:"+this.id);
	}
}
