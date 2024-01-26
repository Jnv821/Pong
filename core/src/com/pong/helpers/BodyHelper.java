package com.pong.helpers;

import com.badlogic.gdx.physics.box2d.World;

import  com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;



public class BodyHelper {

	public static Body createBody(float x, float y, int width, int height, boolean isStatic, float density, World world, ContactType contactType) {
		BodyDef bodyDefinition = new BodyDef();
		// Type of Objects and if they can move. AKA Static bodies, Kinematic or Dynamic.
		bodyDefinition.type = isStatic == false ? BodyDef.BodyType.DynamicBody : BodyDef.BodyType.StaticBody;
		bodyDefinition.position.set(x/WorldConstants.ppm,y/WorldConstants.ppm);
		bodyDefinition.fixedRotation = true;
		Body body = world.createBody(bodyDefinition);
		// Set collision Box to the center of our bodies.
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/2/WorldConstants.ppm, height/2/WorldConstants.ppm);
		
		// Defines physicial properties of the Body object
		FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = shape;
		fixtureDefinition.density = density;
		
		// Finish creating the fixture.
		body.createFixture(fixtureDefinition).setUserData(contactType);
		
		// Dispose shape as it was only needed for setup.
		shape.dispose();
		
		return body;
	}
}
