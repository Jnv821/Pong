package com.pong.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Pong extends Game {

	public static Pong INSTANCE;
	private int screenWidth;
	private int screenHeight;
	private OrthographicCamera orthographicCamera;

	public Pong() {
		INSTANCE = this;
	}

	@Override
	public void create() {
		this.screenWidth = Gdx.graphics.getWidth();
		this.screenHeight = Gdx.graphics.getHeight();
		this.orthographicCamera = new OrthographicCamera();
		this.orthographicCamera.setToOrtho(false, screenWidth, screenHeight);
		setScreen(new GameScreen(orthographicCamera));
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	@Override
	public void dispose () {

	}
}
