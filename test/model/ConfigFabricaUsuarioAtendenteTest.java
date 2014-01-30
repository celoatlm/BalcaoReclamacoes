package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConfigFabricaUsuarioAtendenteTest {

	
	private static ConfigFabricaUsuarioAtendente configFabricaUsuarioAtendente;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		configFabricaUsuarioAtendente = new ConfigFabricaUsuarioAtendente();
		configFabricaUsuarioAtendente.setQuantidadeMinimaReclamacao("1");
		configFabricaUsuarioAtendente.setQuantidadeMaximaReclamacao("10");
		configFabricaUsuarioAtendente.setTempoMaximoReclamacao("10");
		configFabricaUsuarioAtendente.setTempoMinimoReclamacao("1");
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
	public void testMaximoSetColldownUsuario() {
		
		configFabricaUsuarioAtendente.setColldownUsuario("10001");
		assertEquals(null,configFabricaUsuarioAtendente.getColldownUsuario());
		
	}
	@Test
	public void testMinimoSetColldownUsuario() {
		
		configFabricaUsuarioAtendente.setColldownUsuario("199");
		assertEquals(null,configFabricaUsuarioAtendente.getColldownUsuario());
		
	}

	@Test
	public void testSetQuantidadeMaximaReclamacao() {
		
		configFabricaUsuarioAtendente.setQuantidadeMaximaReclamacao("11");
		assertEquals("10",configFabricaUsuarioAtendente.getQuantidadeMaximaReclamacao());
	}

	@Test
	public void testSetQuantidadeMinimaReclamacao() {

		configFabricaUsuarioAtendente.setQuantidadeMinimaReclamacao("0");
		assertEquals("1",configFabricaUsuarioAtendente.getQuantidadeMinimaReclamacao());
	}

	@Test
	public void testSetTempoMaximoReclamacao() {
		configFabricaUsuarioAtendente.setTempoMaximoReclamacao("11");
		assertEquals("10", configFabricaUsuarioAtendente.getTempoMaximoReclamacao());
	}

	@Test
	public void testSetTempoMinimoReclamacao() {
		configFabricaUsuarioAtendente.setTempoMinimoReclamacao("0");
		assertEquals("1", configFabricaUsuarioAtendente.getTempoMinimoReclamacao());
	}

}
