package example;

import java.util.Arrays;
import java.util.List;

/***
 * This class should compile and execute if you use java 8
 * @author LeonBornemann
 *
 */
public class Java8Example {

	public static void main(String[] args) {
		List<String> stringList = Arrays.asList("1234","14","12");
		//Lambda action!! :)
		stringList.forEach(e -> myMethod(e));
	}

	private static void myMethod(String e) {
		System.out.println(Integer.parseInt(e)*10);
	}
}
