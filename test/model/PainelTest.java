package model;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PainelTest {

	private static Painel painel;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//painel = Painel.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@SuppressWarnings("static-access")
	@Test
	public void test() {
		assertEquals(Painel.getInstance(), painel.getInstance());
	}

}
