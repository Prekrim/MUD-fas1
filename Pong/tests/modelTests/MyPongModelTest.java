package modelTests;

import static org.junit.Assert.*;
import model.BarKey;
import model.MyPongModel;
import model.PongModel;

import org.junit.Test;

public class MyPongModelTest {

	@Test
	public void testCompute() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBarPos() {
		PongModel tester = new MyPongModel("Filip","Husein");
		assertEquals("Model's left BarKey's pos exists",true,tester.getBarPos(BarKey.LEFT) != 0);
	}

	@Test
	public void testGetBarHeight() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBallPos() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMessage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScore() {
		PongModel tester = new MyPongModel("Filip","Husein");
		assertEquals("Score is real", "", tester.getScore(BarKey.LEFT));
	}

	@Test
	public void testGetFieldSize() {
		fail("Not yet implemented");
	}

}
