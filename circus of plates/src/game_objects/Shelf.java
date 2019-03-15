package game_objects;

import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Shelf extends AbstractObject {



	public Shelf( String path ,int width,int height) {
		super(width, height, path);
		this.setY(50);
	}

}
