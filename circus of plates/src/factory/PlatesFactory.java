package factory;

import java.awt.Color;
import java.util.HashMap;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import game_objects.Plate;

public class PlatesFactory {

	private static final HashMap<String, Plate> shapesMap = new HashMap<>();
	private static ObjectFactory factory= new ObjectFactory();

	public static GameObject getShape(String path, Color color, World engine, GameObject shelf) {
		Plate shape = shapesMap.get(path + "," + color);

		if (shape == null||shapesMap.size()<15) {
			shape = (Plate) factory.createPlate(path,color, 45, 24, engine, shelf);
			shapesMap.put(path + "," + color, shape);
		}

		return shape;
	}
}
