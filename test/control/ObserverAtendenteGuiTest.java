package control;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ObserverAtendenteGuiTest {

	private static ObserverAtendenteGui observerAtendenteGui;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@SuppressWarnings("static-access")
	@Test
	public void test() {
		assertEquals(ObserverAtendenteGui.getInstance(), observerAtendenteGui.getInstance());
	}

}
