package com.pong.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.pong.helpers.ContactType;

public class GameContactListener implements ContactListener{

	private GameScreen gameScreen;
	
	public GameContactListener(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture a = contact.getFixtureA();
		Fixture b = contact.getFixtureB();
		
		if(a == null || b == null) return;
		if(a.getUserData() == null || b.getUserData() == null) return;
		
		if(a.getUserData() == ContactType.BALL || b.getUserData() == ContactType.BALL) {
			if(a.getUserData() == ContactType.PLAYER || b.getUserData() == ContactType.PLAYER) {
				gameScreen.getBall().reverseVelocityX();
				gameScreen.getBall().increaseSpeed();
			}
			
			if(a.getUserData()== ContactType.WALL || b.getUserData() == ContactType.WALL) {
				gameScreen.getBall().reverseVelocityY();
				gameScreen.getBall().increaseSpeed();
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
