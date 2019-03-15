package classes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import world.Engine;
import world.GameLevel;
import world.SampleLevel;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameObject;
import factory.ObjectFactory;

public class main1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObjectFactory factory = new ObjectFactory();
		 List<GameObject> constantObjects = new ArrayList<>();
		 constantObjects.add(factory.createConstantObject("backGround", "circus.jpg", 1000, 1000));
		 constantObjects.add(factory.createConstantObject("shelf", "shelf2.png", 300, 69));
		 GameObject l=factory.createConstantObject("shelf", "shelf2.png", 300, 69);
		 l.setX(900-300);
		 constantObjects.add(l);
		 List<GameObject> control = new ArrayList<>();
		 GameLevel game = new SampleLevel();
		GameEngine.start("Very Simple Game in 99 Line of Code", new Engine(constantObjects,game,900, 800), Color.YELLOW);
	}

}
