package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SenhaTest {
	
	private static Senha s1;
	private static Senha s2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		s1 = new Senha(1,1);
		s2 = new Senha(2,0);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		assertEquals(1, s1.compareTo(s2));
		
		
	}

}
