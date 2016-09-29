import java.util.*;
import java.io.*;
import java.awt.Point;

public class Map {
	
	/**
	 * A 2D array of the map
	 */
	private char [][] map;
	
	/**
	 * 2D array keeping track of the revealed portions of the map
	 */
	private boolean [][] revealed;
	
	/**
	 * Constructor for the map
	 */
	public Map() {
		
		map = new char [5][5];
		revealed = new boolean [5][5];
	}
	
	/**
	 * Generates the map of the specified area
	 * @param areaNum the area to be generated
	 */
	public void generateArea (int areaNum) {
		
		try {
			
			Scanner read = new Scanner ( new File ("Area" + areaNum + ".txt") );
			int y = 0;
			
			while ( read.hasNext() ){
				
				String s = read.nextLine();
				String [] sp = s.split(" ");				
				for (int x = 0; x < 5; x++){
					map[x][y] = sp[x].charAt(0);		 
				}
				y++;
			
			}
			
			for (int j = 0 ; j < 5; j ++){
				for (int i = 0 ; i < 5; i ++){
					if ( map[i][j] == 's' || map[i][j] == 'c'){
						
						revealed[i][j] = true;
						
					} else{
						
						revealed[i][j] = false;
					}
				}
			}
			read.close();
		} catch (FileNotFoundException e) {
			
			System.out.println("File not found.");
			
		}
	}

	/**
	 * Gets the char at the specified Point
	 * @param p the Point to find
	 * @return the character at the Point
	 */
	public char getCharAtLoc( Point p ){
		
		return map[(int) p.getX()][(int) p.getY()];
	}
	
	/**
	 * Displays the map
	 * @param p the Point that the Player is located
	 */
	public void displayMap( Point p ){
		System.out.println(" ___________ ");
		for (int y = 0; y < 5; y++){
			for ( int x = 0; x < 5; x++){
				if (x == 0){
					System.out.print("| ");
				}
				
				if (revealed[x][y]){
					
					if ( x == (int)p.getX() && y == (int)p.getY()){
						System.out.print( "* ");

					}else{
						System.out.print( map[x][y] + " ");
					}
				}else{
					if ( x == (int)p.getX() && y == (int)p.getY()){
						System.out.print( "* ");

					}else{
						System.out.print( "? ");
					}
				}
			}
			System.out.println("|");
		}
		System.out.println(" ----------- ");
	}
	
	/**
	 * Finds the start location of the map
	 * @return the Point of the start
	 */
	public Point findStartLocation(){
		int x = -1;
		int y = -1;
		for (int j = 0; j < 5; j++ ){
			for (int i = 0; i < 5; i++){
				
				if ( map[i][j] == 's'){
					x = i;
					y = j;
				}
			}
		}
		Point p = new Point(x, y);
		
		return p;
	}
	
	/**
	 * Sets the reveal to true if the Player has traversed it
	 * @param p
	 */
	public void reveal(Point p){
		revealed[(int) p.getX()][(int) p.getY()] = true;
	}
	
	/**
	 * Removes an opponent once defeated by Player
	 * @param p the Point where the Opponent is to be removed
	 */
	public void removeOppAtLoc(Point p){
		map[(int)p.getX()][(int)p.getY()] = 'n';
	}
	
}
