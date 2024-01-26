package com.pong.objects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pong.game.GameScreen;
import com.pong.helpers.BodyHelper;
import com.pong.helpers.ContactType;
import com.pong.helpers.WorldConstants;

public abstract class Paddle {
	
	protected Body body;
	protected float x;
	protected float y;
	protected float speed;
	protected float velocityY;
	protected int width;
	protected int height;
	protected int score;
	protected Texture texture;
	protected GameScreen gamescreen;

	public Paddle(float x, float y, GameScreen gameScreen) {
		this.x = x;
		this.y = y;
		this.score = 0;
		this.speed = 18;
		this.width = 16;
		this.height = 64;
		this.texture = new Texture("Paddle.png");
		this.gamescreen = gameScreen;
		this.body = BodyHelper.createBody(x, y, width+10, height+20, false, 10000, gameScreen.getWorld(), ContactType.PLAYER );
		}

	public void update() {
		x = body.getPosition().x * WorldConstants.ppm - (width/2);
		y = body.getPosition().y * WorldConstants.ppm - (height/2);
		velocityY = 0;
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(texture, x, y ,width, height);
	}

	public void score() {
		this.score++;
	}
	@Override
	public String toString() {
		return "Paddle [gamescreen=" + gamescreen + "]";
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
