package classes;

import java.util.List;

public class Random {
public Object getRandom(List objects,int min ,int  max ){
	Random2 rand=new Random2();
	int x =rand.generateRandomInteger(min,max);
	return objects.get(x);
	
	
}
}
