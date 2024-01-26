package com.pong.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.pong.game.GameScreen;
import com.pong.game.Pong;
import com.pong.helpers.BodyHelper;
import com.pong.helpers.ContactType;
import com.pong.helpers.WorldConstants;

public class Ball {

	private final float initialSpeed = 10;
	private Body body;
	private float x;
	private float y;
	private float velocityY;
	private float velocityX;
	private float speed;
	private int width;
	private int height;
	private GameScreen gameScreen;
	private Texture texture;
	
	public Ball(GameScreen gameScreen) {
		this.x = Pong.INSTANCE.getScreenWidth()/2;
		this.y = Pong.INSTANCE.getScreenHeight()/2;
		this.speed = this.initialSpeed;
		
		this.texture = new Texture("Ball.png");
		this.gameScreen = gameScreen;
		this.width = 16;
		this.height = 16;
		
		this.velocityX = this.generateRandomDirection();
		this.velocityY = this.generateRandomDirection();
		
		this.body = BodyHelper.createBody(x, y, width, height, false, 0, gameScreen.getWorld(), ContactType.BALL);
	}
	
	private float generateRandomDirection() {
		
		return (Math.random() < 0.5f) ? 1f : -1f;
	}
	
	public void update() {
		x = body.getPosition().x * WorldConstants.ppm - (this.width/2);
		y = body.getPosition().y * WorldConstants.ppm - (this.height/2);
		
		this.body.setLinearVelocity(velocityX * speed, velocityY * speed);
		
		// Scoring
		if (x < 0) {
			gameScreen.getOponnentAI().score();
		
			this.reset();
		}
		if (x > Pong.INSTANCE.getScreenWidth()) {
			gameScreen.getPlayer().score();
			this.reset();
		}
	
	}
	
	public void reset() {
		this.velocityX = this.generateRandomDirection();
		this.velocityY = this.generateRandomDirection();
		this.speed = this.initialSpeed;
		
		this.body.setTransform(Pong.INSTANCE.getScreenWidth()/2/WorldConstants.ppm, Pong.INSTANCE.getScreenHeight()/2/WorldConstants.ppm, 0);
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(this.texture, x, y, width, height);
	}

	
	public void reverseVelocityX() {
		this.velocityX *=-1;
	}
	
	public void reverseVelocityY() {
		this.velocityY*=-1;
	}
	
	public void increaseSpeed() {
		this.speed *=1.05f;
	}
	
	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	
	
}
