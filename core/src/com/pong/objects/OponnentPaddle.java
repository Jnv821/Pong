package com.pong.objects;

import com.pong.game.GameScreen;

public class OponnentPaddle extends Paddle {

	public OponnentPaddle(float x, float y, GameScreen gameScreen) {
		super(x, y, gameScreen);
	}

	@Override
	public void update() {
		super.update();
		
		Ball ball = this.gamescreen.getBall();

		if(ball.getY() + 10 > y && ball.getY() - 10 > y ) velocityY = 1;
		if(ball.getY() + 10 < y && ball.getY() - 10 < y) velocityY = -1;
		
		this.body.setLinearVelocity(0, velocityY * speed);
	}
	
}
