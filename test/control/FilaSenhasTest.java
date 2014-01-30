package control;

import static org.junit.Assert.*;


import model.Senha;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FilaSenhasTest {

	private Senha senha;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
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
	public void testPegaPrimeira() {
		senha = new Senha(3, 0);
		FilaSenhas.getInstance().inserirSenha(new Senha(1, 1));
		FilaSenhas.getInstance().inserirSenha(new Senha(2, 1));
		FilaSenhas.getInstance().inserirSenha(senha);
	
		assertEquals(senha, FilaSenhas.getInstance().pegaPrimeira());
	}

}
