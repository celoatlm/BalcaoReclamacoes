package control;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class FabricaUsuarioAtendenteTest {

	private FabricaUsuarioAtendente fabricaUsuarioAtendente;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@SuppressWarnings("static-access")
	@Test
	public void test() {
		
		assertEquals(FabricaUsuarioAtendente.getInstance(), fabricaUsuarioAtendente.getInstance());
		
	}

}
