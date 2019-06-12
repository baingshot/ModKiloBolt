package com.kilobolt.robotgame;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.kilobolt.framework.Screen;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();

		Assets.menu = g.newImage("backgroundmenu.png", ImageFormat.RGB565);
		Assets.background = g.newImage("backgroundmain.png", ImageFormat.RGB565);

		Assets.tiledirt = g.newImage("tiledirt.png", ImageFormat.RGB565);
		Assets.tilegrassTop = g.newImage("tilegrasstop.png", ImageFormat.RGB565);
		Assets.tilegrassBot = g.newImage("tilegrassbot.png", ImageFormat.RGB565);
		Assets.tilegrassLeft = g.newImage("tilegrassleft.png", ImageFormat.RGB565);
		Assets.tilegrassRight = g.newImage("tilegrassright.png", ImageFormat.RGB565);
		
		Assets.button = g.newImage("button.jpg", ImageFormat.RGB565);

		Assets.topolm =  g.newImage("topolM.png",ImageFormat.RGB565);
		Assets.rocketm = g.newImage("rocketm.png",ImageFormat.RGB565);
		Assets.rocketmright = g.newImage("rocketmright.png",ImageFormat.RGB565);

		Assets.ufo = g.newImage("ufo.png",ImageFormat.RGB565);
		Assets.wall = g.newImage("wall.png",ImageFormat.RGB565);
		Assets.army = g.newImage("army.png",ImageFormat.RGB565);
		Assets.dragon =g.newImage("dragon.png",ImageFormat.RGB565);
		Assets.explosion = g.newImage("explosion.png",ImageFormat.RGB565);

		game.setScreen(new MainMenuScreen(game));

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}
}