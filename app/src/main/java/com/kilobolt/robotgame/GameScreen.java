package com.kilobolt.robotgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.graphics.Color;
import android.graphics.Paint;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Screen;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;

	// Variable Setup

	private static Background bg1, bg2;
	public static TopolM topolmOB;
	public static Ufo ufoOB;
	public static Wall wallOB;
	public static Army armyOB;
	public static Dragon dragonOB;
	public static boolean win =false;

	private Image topolm,  rocketm , rocketmright, ufo, wall, army, dragon, explosion;

	private ArrayList<Tile> tilearray = new ArrayList<Tile>();

	Paint paint, paint2;

	public GameScreen(Game game) {
		super(game);

		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		topolm = Assets.topolm;
		rocketm = Assets.rocketm;
		rocketmright = Assets.rocketmright;
		ufo = Assets.ufo;
		wall = Assets.wall;
		army = Assets.army;
		dragon = Assets.dragon;
		explosion = Assets.explosion;


		topolmOB = new TopolM();
		ufoOB = new Ufo();
		wallOB = new Wall();
		armyOB = new Army();
		dragonOB = new Dragon();

		loadMap();

		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);

	}

	private void loadMap() {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		Scanner scanner = new Scanner(SampleGame.map);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			// no more lines to read
			if (line == null) {
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());

			}
		}
		height = lines.size();

		for (int j = 0; j < 12; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {

				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tilearray.add(t);
				}

			}
		}

	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {

		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

		int len = touchEvents.size();
		for (int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN)
			{

				if (inBounds(event, 0, 350, 65, 65)) {
					topolmOB.shoot();
				}

				if (event.x > 250 && (topolmOB.isReadyToFire()==false) && (topolmOB.getRocket().isTargetselected()==false)) {
					topolmOB.getRocket().setTargetselected(true);
					topolmOB.getRocket().setDirection(event.x, event.y);
				}

			}

			if (event.type == TouchEvent.TOUCH_UP)
			{

				if (inBounds(event, 0, 0, 35, 35)) {
					pause();
				}

			}

		}

		armyOB.update();
		dragonOB.update();

		ufoOB.update();

		if(ufoOB.isReadyToFire())
		{
			int lives = wallOB.getLives();
			lives--;
			wallOB.setLives(lives);
			wallOB.setFired(50);
		}

		if( topolmOB.isReadyToFire() == false )
		{
			Rocket rocketOB = topolmOB.getRocket();
			if(rocketOB.isVisible() == true ) {
				rocketOB.update();
				if((rocketOB.getX()+ rocketmright.getWidth()> ufoOB.getCenterX() && rocketOB.getX()+rocketmright.getWidth()< ufoOB.getCenterX()+ufo.getWidth() ) && (rocketOB.getY()>ufoOB.getCenterY() && rocketOB.getY() < ufoOB.getCenterY()+ufo.getHeight()) ){
					ufoOB.setHit(true);
					ufoOB.setFired(50);
					int lives = ufoOB.getLives();
					lives--;
					ufoOB.setLives(lives);
				}
			}
			else
			{
				topolmOB.setReadyToFire(true);
			}
		}


		updateTiles();
		bg1.update();
		bg2.update();

		if( ufoOB.getLives() == -1) {
			state = GameState.GameOver;
			win = true;
		}

		if( wallOB.getLives() == -1) {
			state = GameState.GameOver;
			win = false;
		}

	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 35, 35)) {
						resume();
					}
				}

				if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					goToMenu();
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 800, 480)) {
					nullify();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}

	}



	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
		g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());
		paintTiles(g);

		if( topolmOB.isReadyToFire() == false ) {
			Rocket rocketOB = topolmOB.getRocket();
			if( ufoOB.isHit() )
			{
				rocketOB.setVisible(false);
				ufoOB.setHit(false);
			}

			if(rocketOB.isVisible()) {
				if (rocketOB.isTargetselected() == false) {
					g.drawImage(rocketm, rocketOB.getX(), rocketOB.getY());
				} else {
					g.drawImage(rocketmright, rocketOB.getX(), rocketOB.getY());
				}
			}
		}
		if( ufoOB.getFired()>0 )
		{
			g.drawImage(explosion, ufoOB.getCenterX(), ufoOB.getCenterY());
			int fired = ufoOB.getFired();
			ufoOB.setFired(--fired);
		}

		if( wallOB.getFired() > 0)
		{
			if(wallOB.getFired() == 50) ufoOB.setReadyToFire(false);
			g.drawLine(ufoOB.getCenterX() + (int) (ufo.getWidth() / 2), ufoOB.getCenterY() + (int) (ufo.getHeight() / 2), wallOB.getCenterX() + (int) (ufo.getWidth() / 2), wallOB.getCenterY() + (int) (wall.getHeight() / 2), Color.GREEN);
			g.drawImage(explosion, wallOB.getCenterX(), wallOB.getCenterY());
			int fired = wallOB.getFired();
			wallOB.setFired(--fired);

		}

		g.drawImage(topolm, topolmOB.getCenterX(), topolmOB.getCenterY());
		g.drawImage(ufo, ufoOB.getCenterX(), ufoOB.getCenterY());
		g.drawImage(wall, wallOB.getCenterX(), wallOB.getCenterY());
		g.drawImage(army, armyOB.getCenterX(), armyOB.getCenterY());
		g.drawImage(dragon, dragonOB.getCenterX(), dragonOB.getCenterY());

		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();

	}

	private void updateTiles() {

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}

	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			if (t.type != 0) {
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
			}
		}
	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start.", 400, 240, paint);

	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.button, 0, 350, 0, 65, 65, 65);
		g.drawImage(Assets.button, 0, 0, 0, 195, 35, 35);

	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 400, 165, paint2);
		g.drawString("Menu", 400, 360, paint2);

	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		if( win == true)
		{
			g.drawRect(0, 0, 1281, 801, Color.BLACK);
			g.drawString("YOU WIN!!!", 400, 240, paint2);
			g.drawString("Tap to return.", 400, 290, paint);
		}
		if( win == false) {
			g.drawRect(0, 0, 1281, 801, Color.BLACK);
			g.drawString("GAME OVER.", 400, 240, paint2);
			g.drawString("Tap to return.", 400, 290, paint);
		}

	}

	private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	private void nullify() {

		paint = null;
		bg1 = null;
		bg2 = null;

		topolmOB = null;
		ufoOB = null;
		wallOB = null;
		armyOB = null;
		dragonOB = null;

		System.gc();

	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game));

	}

	public static Background getBg1() {
		// TODO Auto-generated method stub
		return bg1;
	}

	public static Background getBg2() {
		// TODO Auto-generated method stub
		return bg2;
	}

}