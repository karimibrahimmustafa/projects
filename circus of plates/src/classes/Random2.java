package classes;
import java.util.Random;

public class Random2 {


public static int generateRandomInteger(int min, int max) {
   
Random rand =new Random();
    Random generator = new Random(System.currentTimeMillis()); // see comments!
    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;

}}
