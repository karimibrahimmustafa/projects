package eg.edu.alexu.csd.oop.calculator.cs30;

public class m {

	public static void main(String[] args) {
XCalculator instance =new XCalculator();
String result;
instance.input("3.5+4.35");
result = instance.getResult();
System.out.print(result);
	}

}
