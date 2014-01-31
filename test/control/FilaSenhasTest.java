package control;

import static org.junit.Assert.*;

import java.util.ArrayList;


import model.Senha;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FilaSenhasTest {

	private static Senha senha;
	private static Senha s2;
	private static Senha s3;
	private static Senha s4;
	private static Senha s5;
	
	private FilaSenhas filaSenhas;
	private static ArrayList<Senha> senhas;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		senha = new Senha(3, 0);
		senhas = new ArrayList<>();
		
		s2 = new Senha(1,1);
		s3 = new Senha(2,1);
		s4 = new Senha(4,1);
		s5 = new Senha(5,0);
		
		senhas.add(s2);
		senhas.add(s3);
		senhas.add(s4);
		senhas.add(s5);
		senhas.add(senha);
		
		for(Senha s : senhas){
			FilaSenhas.getInstance().inserirSenha(s);
		}
		
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
		
		assertEquals(senha, FilaSenhas.getInstance().pegaPrimeira());
	}
	
	@SuppressWarnings("static-access")
	@Test 
	public void testGetInstance(){
		
		assertEquals(FilaSenhas.getInstance(), filaSenhas.getInstance());
		
	}
	
	@Test
	public void testFilaSenhas(){
		
		senhas.clear();
		
		senhas.add(s5);
		senhas.add(s2);
		senhas.add(s3);
		senhas.add(s4);
		
		for(Senha s : senhas){
			senha = s;
			testPegaPrimeira();
		}
			
	}
	
}
