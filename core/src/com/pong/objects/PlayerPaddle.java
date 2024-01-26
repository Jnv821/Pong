package com.pong.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.pong.game.GameScreen;

public class PlayerPaddle extends Paddle {

	public PlayerPaddle(float x, float y, GameScreen gameScreen) {
		super(x, y, gameScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	
	public void update() {
		super.update();
		
		
		if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
			velocityY = 1;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			velocityY = -1;
		}
		
		body.setLinearVelocity(0, velocityY * speed);
	}
}
