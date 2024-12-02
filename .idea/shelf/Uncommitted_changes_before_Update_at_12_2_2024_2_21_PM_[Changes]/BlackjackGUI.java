package defaul;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class BlackjackGUI extends JFrame{
	//dimensions of window screen aka resolution of window
	
	int aW=1200;
	int aH=800;
	
	//background color
	
	Color colorBackground = new Color(53,101,77);//poker green
	Color colorButton =new Color(0,0,0 );
	Color colorWhite=new Color(255,255,255);
	
	//button creation for code
	JButton bHit=new JButton();
	JButton bStay=new JButton();
	JButton bdoubledown=new JButton();
	JButton bYes=new JButton();
	JButton bNo=new JButton();
	JButton bChips=new JButton(); 
	
	
	//grid spots where cards will be
	int gridX= 50;
	int gridY=50;
	int gridW=900;
	int gridH=400;
	
	//card dimensions and spacing
	int cardSpacing=10;
	int cardTW = gridW/6;
	int cardTH =gridH/2;
	int cardAW =cardTW-2*cardSpacing;
	int cardAH=cardTH-2*cardSpacing;
	
	//total and hit stay grid postions of button and dimensions
	int hsX = gridX+ gridW + 50;
	int hsY = gridY;
	int hsW = 180;
	int hsH = 400;
	
	
	//Word fonts
	Font fontButton = new Font ("Forte", Font.PLAIN, 30);//sets font as well as size of text
	
	//yes or no to play again position
	int pmX=hsX;
	int pmY=hsY+hsH+50;
	int pmW=hsW;
	int pmH =200;
	
	//Array list that contains full Deck
	ArrayList<Card> deckCards=new ArrayList<Card>();
	ArrayList<Card> playerCards=new ArrayList<Card>();
	ArrayList<Card> dealerCards=new ArrayList<Card>();
	
	
	//random number generator for the cards to be dealt/given 
	int rand =new Random().nextInt(52);
	public BlackjackGUI() {
		this.setSize(aW+6, aH+29);//gives pixels to make sure nothing moves or gets covered
		this.setTitle("GROUP 3's BLACKJACK TABLE");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		table TABLE =new table();
		this.setContentPane(TABLE);
		this.setLayout(null);//cancel default layout that prevents change in dimensions of buttons and stuff
		
		
		//spot where all buttons get declared and set dimensions
		ActHit aHit=new ActHit();
		 bHit.addActionListener(aHit);//makes button hit
		bHit.setBounds(hsX+35,hsY+30,110,80);
		bHit.setBackground(colorButton);
		bHit.setFont(fontButton);
		bHit.setText("Hit");
		TABLE.add(bHit);
		
		ActStay aStay=new ActStay();
		 bStay.addActionListener(aStay);//makes button stay
		bStay.setBounds(hsX+35,hsY+120,120,80);
		bStay.setBackground(colorButton);
		bStay.setFont(fontButton);
		bStay.setText("STAY");
		TABLE.add(bStay);
		
		Actdoubledown adoubledown=new Actdoubledown();
		 bdoubledown.addActionListener(adoubledown);//makes button double down
		bdoubledown.setBounds(hsX+20,hsY+210,140,80);
		bdoubledown.setBackground(colorButton);
		bdoubledown.setFont(fontButton);
		bdoubledown.setText("Double");
		TABLE.add(bdoubledown);
		
		ActYes aYes=new ActYes();
		 bYes.addActionListener(aYes);//makes button yes
		bYes.setBounds(pmX+5,pmY+110,90,80);
		bYes.setBackground(colorButton);
		bYes.setFont(fontButton);
		bYes.setText("Yes");
		TABLE.add(bYes);
		
		ActNo aNo=new ActNo();
		 bNo.addActionListener(aNo);//makes button No
		bNo.setBounds(pmX+100,pmY+110,80,80);
		bNo.setBackground(colorButton);
		bNo.setFont(fontButton);
		bNo.setText("NO");
		TABLE.add(bNo);
		
		ActChips aChips=new ActChips();
		 bChips.addActionListener(aChips);//makes button for user to add chips
		bChips.setBounds(hsX+35,hsY+300,120,80);
		bChips.setBackground(colorWhite);
		bChips.setFont(fontButton);
		bChips.setText("Chips");
		TABLE.add(bChips);
		
		
		//filling up array list for cards
		String shapeS1;
		int id_setter=0;
		for(int s=0;s<4;s++) {
			if(s==0) {
				shapeS1="Spades";
				
			}else if(s==1) {
				shapeS1="Hearts";
			}
			else if(s==2) {
				shapeS1="Diamonds";
			}else {
				shapeS1="Clubs";
			}
			for(int i=2;i<15;i++) {
			
				deckCards.add(new Card(i,shapeS1, id_setter));
				id_setter++;
			}
		}
		
		rand=new Random().nextInt(52);
		playerCards.add(deckCards.get(rand));//gets card from all cards array list into players hand
		deckCards.get(rand).cardUsed=true;
		
		rand=new Random().nextInt(52);
		while(true) {
			if(deckCards.get(rand).cardUsed==false) {//if card hasnt been used yet
				dealerCards.add(deckCards.get(rand));
				deckCards.get(rand).cardUsed=true;
				break;
			}else {
				rand=new Random().nextInt(52);
			}
		}

		rand=new Random().nextInt(52);
		while(true) {
			if(deckCards.get(rand).cardUsed==false) {//if card hasnt been used yet
				playerCards.add(deckCards.get(rand));
				deckCards.get(rand).cardUsed=true;
				break;
			}else {
				rand=new Random().nextInt(52);
			}
		}

		rand=new Random().nextInt(52);
		while(true) {
			if(deckCards.get(rand).cardUsed==false) {//if card hasnt been used yet
				dealerCards.add(deckCards.get(rand));
				deckCards.get(rand).cardUsed=true;
				break;
			}else {
				rand=new Random().nextInt(52);
			}
		}
		
		for(Card c : playerCards) {//will loop through elements of array list
			System.out.println("player has the card "+c.name+" of "+c.shape);
		}
		for(Card c : dealerCards) {//will loop through elements of array list
			System.out.println("Dealer has the card "+c.name+" of "+c.shape);
		}
	}
	
	public class table extends JPanel{
		
		public void paintComponent(Graphics g ) {
			
			g.setColor(colorBackground);
		
			g.fillRect(0,0, aW, aH);
			
			//temporary grid painting
			g.setColor(Color.black);
			g.drawRect(gridX, gridY, gridW, gridH);
			//temp does log borders painting
			g.drawRect(gridX, gridY+gridH+50, gridW, 500);
			
			//temp chips and hit-stay message + button grid
			g.drawRect(hsX, hsY, hsW, hsH);
			//temporary play again grid
			g.drawRect(pmX, pmY, pmW, pmH);
			
			
			for(int i=0; i<6;i++) {
				g.drawRect(gridX+i*cardTW+cardSpacing, gridY+cardSpacing,cardAW, cardAH);//creates border spacing for where players cards will be
				//
				g.drawRect(gridX+i*cardTW+cardSpacing, gridY+cardSpacing+cardTH,cardAW, cardAH);//creates same thing for dealer
			}
		}
	}
	
	//buttons
	public class ActHit implements ActionListener{//only seen during players turn
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Player has choosen to Hit"); 
		}
	}
	
	public class ActStay implements ActionListener{//only seen during players turn
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Player has choosen to stay"); 
		}
	}
	
	public class Actdoubledown implements ActionListener{//only seen during players turn and clicked once
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Player has choosen to double down"); 
		}
	}
	
	public class ActYes implements ActionListener{//will only be visible when asked to play again
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Player has decided to continue"); 
		}
	}
	
	public class ActNo implements ActionListener{//only visible when asked to play again
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Player has decided to stop"); 
	}
}
	
	public class ActChips implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Player has choosen to add chips"); 
	}
}
	
}
