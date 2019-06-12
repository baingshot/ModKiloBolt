package com.kilobolt.robotgame;

import com.kilobolt.framework.Image;
import com.kilobolt.framework.Music;
import com.kilobolt.framework.Sound;

public class Assets {
	
	public static Image  splash, menu, background;
	public static Image tiledirt, tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight;
	public static Image button;
	public static Music theme;
	public static Image topolm,rocketm, rocketmright, ufo, wall, army, dragon, explosion;
	
	public static void load(SampleGame sampleGame) {
		// TODO Auto-generated method stub
		theme = sampleGame.getAudio().createMusic("maintheme.mp3");
		theme.setLooping(true);
		theme.setVolume(0.85f);
		theme.play();
	}
	
}
