package com.pong.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.pong.game.GameScreen;
import com.pong.game.Pong;
import com.pong.helpers.BodyHelper;
import com.pong.helpers.ContactType;
import com.pong.helpers.WorldConstants;

public class Wall {
	
	private Body body;
	private float x;
	private float y;
	private int width;
	private int height;
	private Texture texture;
	
	public Wall(float x, float y, GameScreen gameScreen) {
		this.x = x + Pong.INSTANCE.getScreenWidth() / 2;
		this.y = y;
		this.width = Pong.INSTANCE.getScreenWidth() * (int)WorldConstants.ppm/2;
		this.height = 64;
		
		this.texture = new Texture("Wall.png");
		
		this.body = BodyHelper.createBody(x, y,width, height, true, 0, gameScreen.getWorld(), ContactType.WALL);
 	}
	
	public void render(SpriteBatch batch) {
		batch.draw(texture, x - (width/2), y - (height/2), width, height);
	}
}
