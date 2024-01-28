package com.pong.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.pong.helpers.WorldConstants;
import com.pong.objects.Ball;
import com.pong.objects.OponnentPaddle;
import com.pong.objects.PlayerPaddle;
import com.pong.objects.Wall;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

// Debug imports

import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class GameScreen extends ScreenAdapter{

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private World world; // Stores 2d bodies for box2D collision management
	private Box2DDebugRenderer box2DDebugRenderer;
	private GameContactListener gameContactListener;
	
	// Game Objects
	private PlayerPaddle player;
	private OponnentPaddle oponnentAI;
	private Ball ball;
	private Wall [] walls = new Wall[2];
	private TextureRegion[] scores;
	
	public GameScreen(OrthographicCamera camera) {
		super();
		
		this.camera = camera;
		
		this.camera.position.set(new Vector3(Pong.INSTANCE.getScreenWidth()/2, Pong.INSTANCE.getScreenHeight()/2, 0));
		
		this.batch = new SpriteBatch();
		
		this.world = new World(new Vector2(0,0), false);
	
		this.box2DDebugRenderer = new Box2DDebugRenderer();
		
		this.gameContactListener = new GameContactListener(this);
		this.world.setContactListener(gameContactListener);
		
		// Game Objects
		this.player = new PlayerPaddle(16, Pong.INSTANCE.getScreenHeight()/2, this);
		this.ball = new Ball(this);
		this.oponnentAI = new OponnentPaddle(Pong.INSTANCE.getScreenWidth()-16, Pong.INSTANCE.getScreenHeight()/2, this);
		
		this.walls[0] = new Wall(0,32,this);
		this.walls[1] = new Wall(0,Pong.INSTANCE.getScreenHeight()-32, this);
		this.scores = loadTextureSprite("Scores.png", 10);
	}
	
	
	public void update() {
		world.step(1/60f, 6, 2);
		
		this.camera.update();
		this.player.update();
		this.ball.update();
		this.oponnentAI.update();
		
		batch.setProjectionMatrix(this.camera.combined); // Synchornizes Camera with World
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		};
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
			this.ball.reset();
		}
	}
	
	@Override
	public void render(float delta) {
		// Updating objects
		this.update();
		
		// Clearing Screen
		Gdx.gl.glClearColor(0.15f,0.15f,0.15f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Drawing - Redrawing Sprites as needed
		batch.begin();
		this.player.render(batch);
		this.ball.render(batch);
		this.oponnentAI.render(batch);
		this.walls[0].render(batch);
		this.walls[1].render(batch);
		drawScore(batch, player.getScore(), 64, Pong.INSTANCE.getScreenHeight()-64, 64, 64);
		drawScore(batch, oponnentAI.getScore(),Pong.INSTANCE.getScreenWidth()-128, Pong.INSTANCE.getScreenHeight()-64, 64, 64);
		batch.end();
		//this.box2DDebugRenderer.render(getWorld(), camera.combined.scl(WorldConstants.ppm));
	}

	private TextureRegion[] loadTextureSprite(String filename, int columns) {
		Texture texture = new Texture(filename);
		return TextureRegion.split(texture, texture.getWidth()/columns, texture.getHeight())[0];
	}

	private void drawScore(SpriteBatch batch, int score, float x, float y, float width, float height) {
		if(score < 10) {
			batch.draw(scores[score], x, y, width, height);
		} else {
			batch.draw(scores[ Integer.parseInt((""+score).substring(0,1))],x,y,width,height);
			batch.draw(scores[ Integer.parseInt((""+score).substring(1,2))],x+64,y,width,height); // 16 is the px of column
		}
	}
	
	public World getWorld() {
		return world;
	}


	public void setWorld(World world) {
		this.world = world;
	}

	// test
	@Override
	public String toString() {
		return "GameScreen [oponnentAI=" + oponnentAI + "]";
	}


	public Ball getBall() {
		return ball;
	}


	public void setBall(Ball ball) {
		this.ball = ball;
	}


	public PlayerPaddle getPlayer() {
		return player;
	}


	public void setPlayer(PlayerPaddle player) {
		this.player = player;
	}


	public OponnentPaddle getOponnentAI() {
		return oponnentAI;
	}


	public void setOponnentAI(OponnentPaddle oponnentAI) {
		this.oponnentAI = oponnentAI;
	}


	public Wall[] getWalls() {
		return walls;
	}


	public void setWalls(Wall[] walls) {
		this.walls = walls;
	}
	
	
	
}
