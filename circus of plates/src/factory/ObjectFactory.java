package factory;

import java.awt.Color;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import game_objects.BackGroud;
import game_objects.Clown;
import game_objects.Plate;
import game_objects.Plate2;
import game_objects.Shelf;

public class ObjectFactory {

	public GameObject createConstantObject(String type, String path, int width, int height) {
		if (type.equals("shelf")) {
			return new Shelf(path, width, height);
		} else if (type.equals("backGround")) {
			return new BackGroud(path, width, height);
		}
		return null;

	}

	public GameObject createClown(String path, World engine, int width, int height) {
		return new Clown(path, engine, width, height);
	}

	public GameObject createPlate(String path,Color color, int width, int height, World engine, GameObject shelf) {
		int c=0;
		c++;
		Plate v =new Plate(path, color, width, height, engine, shelf);;
		return v;
	}
	public GameObject createPlate2(String path,Color color, int width, int height, World engine, GameObject shelf,int x , int y) {
		int c=0;
		c++;
		Plate2 v =new Plate2(path, color, width, height, engine, shelf);
		v.setX(x);
		v.setY(y);
		return v;
	}
}
