package test;

import org.junit.Test;


public class AnyTest {
	
	@Test
	public void test(){
		String s = new String ("TestStuff lala");
		Object o = s; 
		if ( o instanceof String) {
			System.out.println((String) o);
		}
	}

}
