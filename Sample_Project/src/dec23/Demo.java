package dec23;

import org.testng.annotations.Test;

public class Demo {
@Test
	public static void main() {
		String a = "ABCD";
		String b = "XYZ";
		String c = "   ";
		System.out.println(a+c.concat(b));
	}

}
