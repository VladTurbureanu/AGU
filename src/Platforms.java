import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;


public class Platforms {
	
	private int w = 80,h= 20;
	private final int MAX_COLUMN_PLATFORMS = 9;
	private final int MAX_ROW_PLATFORMS = 7;
	private ArrayList<Rectangle> platformsHitbox;
	private int[][] platformGrid;
	private int index;
	
	public int getW(){
		return w;
	}
	public int getH(){
		return h;
	}
	
	public Platforms(){
		platformsHitbox = new ArrayList<Rectangle>();
		platformGrid = new int[MAX_COLUMN_PLATFORMS][MAX_ROW_PLATFORMS];
		//initialize the grid with -1s
		for (int i = 0; i < MAX_COLUMN_PLATFORMS; i++) {
			for(int j = 0; j <MAX_ROW_PLATFORMS;j++){
				platformGrid[i][j] = -1;
			}
		}
		index = 0;
	}
	public ArrayList<Rectangle> getPlatformsHitbox(){
		return platformsHitbox;
	}
	public int[][]  getPlatformGrid(){
		return platformGrid;
	}
	
	//different types of platforms, each has an argument for placement 
	public void addCloud(int xPlacement, int yPlacement){
		platformsHitbox.add(index, new Rectangle(xPlacement*100,yPlacement*100,w,h));
		addToGrid(xPlacement,yPlacement);
		//TODO animation goes here 
	}
	public void addObstacle(int xPlacement, int yPlacement){
		platformsHitbox.add( new Rectangle(xPlacement*100,yPlacement*100,w,h));
		addToGrid(xPlacement,yPlacement);
		//TODO animation goes here 
	}
	public void addWall(int xPlacement, int yPlacement){
		platformsHitbox.add( new Rectangle(xPlacement*100 - h,yPlacement*100-w,h,w));		
		//TODO animation goes here 
	}
	
	public void addToGrid(int xPlacement, int yPlacement){
		platformGrid[xPlacement][yPlacement] = index;
		index++;
	}
}
