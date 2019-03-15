package world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import classes.GetColor;
import classes.Random;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import factory.ObjectFactory;
import factory.PlatesFactory;
import factory.Player_StateFactory;
import game_objects.GetPlates;
import game_strategy.State;

public class Engine implements World {

	private List<GameObject> constantObjects = new ArrayList<>();
	private List<GameObject> movableObjects = new ArrayList<>();
	private List<GameObject> controlableObjects = new ArrayList<>();
	private GameLevel level;
	private int controlledSpeed;
	private int score;
	private int time;
	private int Height;
	private int Width;
	private int platesCollected;
	private int sameColorPlates;
	private State gameMode;
	private Player_StateFactory stateFactory;
	private ObjectFactory objectFactory;
	private PlatesFactory shapeFactory = new PlatesFactory();
	private Random Rand = new Random();
	private long startTime = System.currentTimeMillis();
	private long endTime;
	private int turn =0;
	public Engine(List<GameObject> constant , GameLevel level ,int Width , int Height) {
		this.constantObjects = constant;
		this.level = level;
		controlledSpeed = 3;
		this.score = 0;
		this.time = 0;
		this.Width = Width;
		this.Height =Height;
		this.platesCollected = 0;
		this.sameColorPlates = 0;
		stateFactory = new Player_StateFactory();
		objectFactory = new ObjectFactory();
		shapeFactory  = new PlatesFactory();
		this.controlableObjects.add(objectFactory.createClown("d.png", this, 150, 201));
		this.controlableObjects.add(objectFactory.createPlate2("n22.png",Color.red,50,50,this,null,controlableObjects.get(0).getX()-5-45+controlableObjects.get(0).getWidth(),controlableObjects.get(0).getY()-24));
		this.controlableObjects.add(objectFactory.createPlate2("n22.png",Color.red,50,50,this,null,controlableObjects.get(0).getX()-5,controlableObjects.get(0).getY()-24));
		this.gameMode = stateFactory.changeState("start");
	}
	
	@Override
	public List<GameObject> getConstantObjects() {
		return constantObjects;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		return movableObjects;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		return controlableObjects;
	}

	@Override
	public int getWidth() {
		return Width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return Height;
	}

	@Override
	public boolean refresh() {
		if (turn ==0){
			
			
		}
		int num1=controlableObjects.size();
		if(turn == 800)
		{GameObject shelf = 	(GameObject) Rand.getRandom(constantObjects, 1, constantObjects.size()-1);
		Color color =(Color) Rand.getRandom(level.getColors(), 0, level.getColors().size()-1);
		GameObject newShape =shapeFactory.getShape("n22.png",color,this,shelf);
		movableObjects.add(newShape);
		turn =1;
		}
		int i=0;
		 Map<GameObject, GameObject> myMap = new ConcurrentHashMap<GameObject, GameObject>();
while( i <movableObjects.size()-1){
	
	myMap.put(movableObjects.get(i),movableObjects.get(i) );
	i++;
}
		 Iterator iterator = myMap.keySet().iterator(); 
			
		 turn ++;
	        while (iterator.hasNext()) {
	          GameObject x=(GameObject) (iterator.next()); 
	          x.setX(x.getX()+1);
	          x.setY(x.getY()+1);
	        }
	        if(controlableObjects.size()!=num1){
	        	GetPlates m = new GetPlates();
	        	List <GameObject> h=m.last(controlableObjects);
	        	if(h.size()>2){
	        		GetColor b =new GetColor();
	        		String n1 =b.getColor(h.get(0).getSpriteImages()[0]);
	        		String n2 =b.getColor(h.get(1).getSpriteImages()[0]);
	        		String n3 =b.getColor(h.get(2).getSpriteImages()[0]);
if(n1.equals(n3)&&n1.equals(n2)){
	
	score++;
	controlableObjects.remove(h.get(0));
	controlableObjects.remove(h.get(1));
	controlableObjects.remove(h.get(2));

}
	        		
	        	}
	        }
	         endTime =System.nanoTime() / 1000000;
	        time= (int) (endTime-startTime);
	        if(time==60)
		return false;
	        
	        return true;
		
	}

	@Override
	public String getStatus() {
		return "Score :"+score+"Time :"+time;
	}

	@Override
	public int getSpeed() {
		return level.getSpeed();
	}

	@Override
	public int getControlSpeed() {
		return controlledSpeed;
	}

}
