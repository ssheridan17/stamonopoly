import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

/** This is the animation panel for the whole game that includes all the graphics and designs */
public class AnimationMonopolyPanel extends JPanel{
	//Properties
	//-card variables
	BufferedImage monopolyboard;
	BufferedImage communitycard;
	BufferedImage chancecard;
	BufferedImage select1;
	BufferedImage winner;
	BufferedImage loser;
	//-chatbox
	/**This textfield (from javaswing) allows you to type and chat with other players in the game*/
	public JTextField textfield = new JTextField();
	Timer timer;
	//-chat history
	/**This text area (from javaswing) displays the chat history with other players throughout the game*/
	public JTextArea textarea = new JTextArea();
	/**The scroll in the text area allows you scroll through the chat history*/
	public JScrollPane scroll; 
	//-buy button variables
	/**This button allows you the option to buy the properties if possible*/
	public JButton buy;
	/**This button allows you to not buy the property and your turn passes*/
	public JButton dontbuy;
	//hotels/houses - aka classroom improvements and stem
	/**This button allows you to buy houses (classroom improvements) on the property if possible*/
	public JButton house;
	/**This button allows you to buy a hotel (upgrate to STEM) on the property if possible*/
	public JButton hotel;
	//-rolling die button
	/**This button allows you to roll the die and move around the board - there are two die*/
	public JButton rolldie;
	//-property variables
	/**This buttons allows you to view properties for the next page*/
	public JButton next;
	/**This button allows you to view properties for the page (in the back)*/
	public JButton back;
	//-fonts
	Font text = null;
	Font title = null;
	Font property = null;
	//-string
	String strColor = ""; 
	String strDice1 = "";
	String strDice2 = ""; 
	String strDiceSum = "";
	String strCard = "";
	String strCardinfo = "";
	String strPlayerTurn = "";
	String strDisplayLength = "";
	String strDisplayLength2 = "";
	String strDisplayLength3 = "";
	
	
	//- Community card / Chance card
	int intCardY = 145;
	
	//-money
	int intMoney = 1500;
	String strMoney = ""+intMoney;
	//-property variables
	boolean blnOwned = false;
	int intPropertyN = 0; 
	int intRent;
	int intHouse1; //these are the classroom improvements
	int intHouse2;
	int intHouse3;
	int intHotel;
	String strPropertyN = ""; 
	String strPropertyOwned = "";
	//-property colours
	String strColour = "";
	
	
	//-player variables
	int intYOUx =  650; 
	int intYOUy = 650;
	int intPlayerCount =0; 
	int intPlayer = 0; 
	int intTurn = 1; 
	String strPlayer = "";
	// indicate which colours were selected
	String strYourColor = ""; 
	boolean blnBlue;
	boolean blnGreen; 
	boolean blnYellow; 
	boolean blnRed; 	
	// Player one: 
	String strColor1 = "";  
	int intPlayerX1 = -50;
	int intPlayerY1 = -50;
	// Player 2:
	String strColor2 = "";
	int intPlayerX2 = -50; 
	int intPlayerY2 = -50;
	// Player 3: 
	String strColor3 = ""; 
	int intPlayerX3 = -50; 
	int intPlayerY3 = -50; 
	// Player 4: 
	String strColor4 = ""; 
	int intPlayerX4 = -50; 
	int intPlayerY4 = -50; 
	
	//Endgame
	boolean blnwinner;
	int intTimer = 1;
	
	//Methods
	/**Overriding JPanel paintComponent method in the Monopoly Animation Panel */
	public void paintComponent(Graphics g){
		g.setColor(new Color(192,225,175)); 
		g.fillRect(0,0,1280,720); 
		g.drawImage(monopolyboard,-260,-3,null);
		
		
		//DRAWING DIE
		g.setColor(Color.BLACK);
		g.fillRect(720,3,142,100);
		g.setFont(title);
		g.drawString("CHATBOX",1050,310);
		g.drawString("PROPERTIES",730,310);
		g.setColor(Color.WHITE);
		g.fillRect(727,36,60,60);
		g.fillRect(795,36,60,60);
	
		//status bar
		g.fillRect(867,3,407,99);
		
		//turn display
		g.fillRect(724,229,374,40);
		g.setFont(text);
		g.setColor(Color.BLACK);
		g.drawString("It's "+ strPlayerTurn+ "'s turn.",728,255);
		g.setColor(Color.WHITE);
		
		//cards
		g.fillRect(844,108,255,115);
		g.setFont(text);
		g.setColor(Color.BLACK);
		g.drawString (strCard,850,125);
		g.drawString(strDisplayLength,850,intCardY);
		g.drawString(strDisplayLength2,850,intCardY+15);
		g.drawString(strDisplayLength3,850,intCardY+30);
		
		g.setColor(Color.WHITE);
		
		//profile - icon
		g.fillRect(1104,165,170,105);
		
		//properties outline
		g.fillRect(725,331,282,315);
		if(blnOwned&&intPropertyN!=10){
			g.setFont(property);
			if(strColour.equalsIgnoreCase("br")){
				g.setColor(new Color(102, 86, 60));
			}
			else if(strColour.equalsIgnoreCase("red")){
				g.setColor(Color.RED);
			}
			else if(strColour.equalsIgnoreCase("u")){
				g.setColor(new Color(149, 153, 142));
			}
			else if(strColour.equalsIgnoreCase("rail")){
				g.setColor(Color.BLACK);
			}
			else if(strColour.equalsIgnoreCase("lb")){
				g.setColor(new Color(181, 206, 224));
			}
			else if(strColour.equalsIgnoreCase("pur")){
				g.setColor(new Color(171, 133, 181));
			}
			else if(strColour.equalsIgnoreCase("or")){
				g.setColor(new Color(224, 141, 8));
			}
			else if(strColour.equalsIgnoreCase("yel")){
				g.setColor(Color.YELLOW);
			}
			else if(strColour.equalsIgnoreCase("gre")){
				g.setColor(Color.GREEN);
			}
			else if(strColour.equalsIgnoreCase("db")){
				g.setColor(new Color(7, 16, 142));
			}
			else{
				g.setColor(Color.BLACK);
			}
			g.drawString(strPropertyOwned,730,353);
			g.setFont(text);
			g.setColor(Color.BLACK);
			g.drawString("Rent: $"+intRent,730,386);
			g.drawString("1 Improvement: $"+intHouse1,730,414);
			g.drawString("2 Improvements: $"+intHouse2,730,432);
			g.drawString("3 Improvements: $"+intHouse3,730,449);
			g.drawString("Upgrade to STEM: $"+intHotel,730,466);
			g.setColor(Color.WHITE);
		}
		
		
		// load the rolled number into the dice area
		g.setFont(title);
		g.setColor(Color.BLACK); 
		g.drawString(strDice1, 740,73); 
		g.drawString(strDice2, 810, 73);
		
		// Update the status bar: 
		g.setFont(text);
		g.drawString("You rolled a: " +strDiceSum, 875,20);
		g.drawString("You landed on: "+strPropertyN,875, 36);  
		
		//Icon header
		g.setFont(text);
		g.setColor(Color.BLACK); 
		g.drawString("ICON:",1110,200);
		g.drawString("MONEY: $" + strMoney,1110,260);
		
		if(strColor.equals("red1") || strYourColor.equals("red")){
			g.setColor(Color.red);
			strColor = "red"; 
			strYourColor = "red"; 
		}else if(strColor.equals("blue1") || strYourColor.equals("blue")){
			g.setColor(Color.BLUE); 
			strColor = "blue"; 
			strYourColor = "blue"; 
		}else if(strColor.equals("yellow1") || strYourColor.equals("yellow")){
			strColor = "yellow"; 
			strYourColor = "yellow"; 
			g.setColor(Color.YELLOW); 
		}else if(strColor.equals("green1") || strYourColor.equals("green")){
			strColor = "green"; 
			strYourColor = "green"; 
			g.setColor(Color.GREEN); 
		}
		
		
		// draw the profile box in:
		g.fillRect(1164,180,50,50); 
		

		// set up your character
		g.fillRect(intYOUx, intYOUy, 25,25);
		
		// Other peoples characters: 
		
		// Player one set up: 
		if(strColor1.equals("red")){
			g.setColor(Color.red);
		}else if(strColor1.equals("blue")){
			g.setColor(Color.BLUE); 
		}else if(strColor1.equals("yellow")){
			g.setColor(Color.YELLOW); 
		}else if(strColor1.equals("green")){
			g.setColor(Color.GREEN); 
		}
		
		g.fillRect(intPlayerX1, intPlayerY1, 25,25);
		
		// Player two set up: 
		if(strColor2.equals("red")){
			g.setColor(Color.red);
		}else if(strColor2.equals("blue")){
			g.setColor(Color.BLUE); 
		}else if(strColor2.equals("yellow")){
			g.setColor(Color.YELLOW); 
		}else if(strColor2.equals("green")){
			g.setColor(Color.GREEN); 
		}
		
		g.fillRect(intPlayerX2, intPlayerY2, 25,25);
		
		// Player three set up: 
		if(strColor3.equals("red")){
			g.setColor(Color.red);
		}else if(strColor3.equals("blue")){
			g.setColor(Color.BLUE); 
		}else if(strColor3.equals("yellow")){
			g.setColor(Color.YELLOW); 
		}else if(strColor3.equals("green")){
			g.setColor(Color.GREEN); 
		}
		
		g.fillRect(intPlayerX3, intPlayerY3, 25,25); 
		
		// Player four set up: 
		if(strColor4.equals("red")){
			g.setColor(Color.red);
		}else if(strColor4.equals("blue")){
			g.setColor(Color.BLUE); 
		}else if(strColor4.equals("yellow")){
			g.setColor(Color.YELLOW); 
		}else if(strColor4.equals("green")){
			g.setColor(Color.GREEN); 
		}
		
		g.fillRect(intPlayerX4, intPlayerY4, 25,25); 
		
		if (intTimer == 0 && blnwinner == true){
			g.setColor(new Color(192,225,175)); 
			g.fillRect(0,0,1280,720); 
			g.drawImage(winner,0,0,null);
		}else if(intTimer == 0 && blnwinner == false){
			g.setColor(new Color(192,225,175)); 
			g.fillRect(0,0,1280,720); 
			g.drawImage(loser,0,0,null);
		}
		
	}
	

	//Constructor
	/**Constructor for Monopoly Animation Panel*/
	public AnimationMonopolyPanel(){
		super();
		
		//CHAT FEATURE
		//-chatbox
		textfield.setLocation(1020,635);
		textfield.setSize(255,80);
		
		//-scroll
		scroll = new JScrollPane(textarea); 
		scroll.setLocation(1020,330);
		scroll.setSize(255,297);
		textarea.setEnabled(false);
		
		
		//buttons
		buy = new JButton("Buy");
		buy.setSize(120,60);
		buy.setLocation(720,105);
		
		dontbuy = new JButton("Don't buy");
		dontbuy.setSize(120,60);
		dontbuy.setLocation(720,166);
		
		house = new JButton("Buy Class Improvements");
		house.setSize(182,25);
		house.setLocation(1097,108);
		
		hotel = new JButton("Upgrade to STEM");
		hotel.setSize(182,25);
		hotel.setLocation(1097,135);
		
		rolldie = new JButton("Roll the die!");
		rolldie.setSize(130,20);
		rolldie.setLocation(727,10);
		
		next = new JButton("Next");
		next.setLocation(723,655);
		next.setSize(141,60);
		
		back = new JButton("Back");
		back.setLocation(867,655);
		back.setSize(141,60);
		
		//winner and loser images 
		try{
			monopolyboard = ImageIO.read(new File("MonopolyGameBoard.png"));
			winner = ImageIO.read(new File("winner.png"));
			loser = ImageIO.read(new File("loser.png"));
		}catch(IOException e){
			System.out.println("Unable to upload image");
		}
		
		//fixing fonts
		//-text font
		try{
			text = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("kabel.ttf")); 
			text = text.deriveFont(Font.PLAIN, 14);
		}
		catch(Exception e){
		}
		try{
			text = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("kabel.ttf")); 
			text = text.deriveFont(Font.PLAIN, 14);
		}
		catch(Exception e){
			System.out.println("Unable to load font file kabel.ttf. Setting default font"); 
		}
		//-title font
		try{
			title = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("kabel.ttf")); 
			title = text.deriveFont(Font.PLAIN, 30);
		}
		catch(Exception e){
		}
		try{
			title = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("kabel.ttf")); 
			title = text.deriveFont(Font.PLAIN, 30);
		}
		catch(Exception e){
			System.out.println("Unable to load font file kabel.ttf. Setting default font"); 
		}
		//-property title
		try{
			property = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("kabel.ttf")); 
			property = text.deriveFont(Font.PLAIN, 20);
		}
		catch(Exception e){
		}
		try{
			property = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("kabel.ttf")); 
			property = text.deriveFont(Font.PLAIN, 20);
		}
		catch(Exception e){
			System.out.println("Unable to load font file kabel.ttf. Setting default font"); 
		}
	}	
}
