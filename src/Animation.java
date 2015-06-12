import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Animation {
	private BufferedImage playerImageRight;	
	private BufferedImage playerImageLeft;	
	private BufferedImage platformImage;	
	private BufferedImage wallImage;	
	
	private Image backgroundTheme;
	
	public Animation(){
		
		//read player image
		try {
			playerImageRight = ImageIO.read(new File("res/0r.png"));
		} catch (IOException e) {}
		try {
			playerImageLeft = ImageIO.read(new File("res/0l.png"));
		} catch (IOException e) {}
		//read background image
		try {
			backgroundTheme = ImageIO.read(new File("res/background1.jpg"));
		} catch (IOException e) {}
		//platform image
		try {
			platformImage = ImageIO.read(new File("res/platform.png"));
		} catch (IOException e) {}
		//platform image
		try {
			wallImage = ImageIO.read(new File("res/wall3.png"));
		} catch (IOException e) {}
		
	}
	
	public BufferedImage getPlayerImageRight(){
		return playerImageRight;
	}	
	public BufferedImage getWallImage(){
		return wallImage;
	}
	public BufferedImage getPlayerImageLeft(){
		return playerImageLeft;
	}
	public BufferedImage getPlatformImage(){
		return platformImage;
	}
	public Image getBackgroundTheme(){
		return backgroundTheme;
	}
}
