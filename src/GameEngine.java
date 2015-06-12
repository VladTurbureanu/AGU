import java.awt.Rectangle;
import java.util.Timer;

public class GameEngine {

	private final double FPS = 60D;
	private PlayerController playerHandler;
	private LevelDesign level;
	private boolean running = true;
	private int count = 0;
	private 
	Timer t = new Timer();
	Timer t2 = new Timer();

	public GameEngine() {
		playerHandler = new PlayerController();
		level = new LevelDesign(playerHandler);

		while(running){
			try {
			    Thread.sleep(16);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {	    
				
			}
			render();
			updateGame();
		}
		
		//run();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / FPS;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;

			while (delta >= 1) {
				updateGame();
				delta -= 1;
				shouldRender = true;
			}
			if (shouldRender) {
				render();
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
			}
		}
	}

	public void render() {
		level.repaint();
	}

	public void updateGame() {
		boundarySensor();
		currentGraphics();
		playerHandler.move();
	}
	public void currentGraphics(){
		if(playerHandler.getxD() < 0){
			level.setPlayerImage( level.getAnimation().getPlayerImageLeft());
		}else if(playerHandler.getxD() > 0){
			level.setPlayerImage( level.getAnimation().getPlayerImageRight());
		}
	}

	public void boundarySensor() {		
		playerHandler.predictMove();	// Predict the position of the player for the next tick.			
		insideWindow();					//Check for player to be in game.		
		onPlatform(); 					//Check if player is on platform.
	}
	
	public void onPlatform(){
		int predictedGridCell = level.getPlatforms().getPlatformGrid()[(int)(playerHandler.getPredictedX()/100)][(int)(playerHandler.getPredictedY()/100)];
		if(predictedGridCell !=-1){
			playerHandler.getPlayerHitbox().y = level.getPlatforms().getPlatformsHitbox().get(predictedGridCell).y;
			playerHandler.setJump(false);
			playerHandler.setPseudoGravity(1);
			playerHandler.setJumpReleased(false);
		}
	}
	public void insideWindow(){
		// Check according to prediction for x and y coordinates
		if (playerHandler.getPredictedY() > level.HEIGHT * level.SCALE || playerHandler.getPredictedY() < 0) {
			playerHandler.setPseudoGravity(1);
			playerHandler.setJumpReleased(false);
		}
		if (playerHandler.getPredictedX() > level.WIDTH * level.SCALE || playerHandler.getPredictedX() < 0) {
			playerHandler.setXD(0);
		}
	}
}
