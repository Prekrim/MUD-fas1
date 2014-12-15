package modelTests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.BarKey;
import model.Input;
import model.Input.Dir;
import model.MyPongModel;
import model.PongModel;

import org.junit.Test;

public class MyPongModelTest {

	@Test
	public void testComputeBall() {
		PongModel tester = new MyPongModel("Filip","Husein");
		Set<Input> set = new HashSet<>();
		Input input = new Input(BarKey.LEFT, Dir.UP);
		set.add(input);
		long longlong = 10;
		Point ballPos = tester.getBallPos();
		for(int i = 0;i<100000;i++){
			tester.compute(set,longlong);
		}
		assertEquals("BallPos has changed", false , ballPos.y == (tester.getBallPos().y));
	}

	@Test
	public void testComputeBar() {
		PongModel tester = new MyPongModel("Filip","Husein");
		Set<Input> set = new HashSet<>();
		Input input = new Input(BarKey.LEFT, Dir.UP);
		set.add(input);
		long longlong = 1;
		int leftBarPos = tester.getBarPos(BarKey.LEFT);
		tester.compute(set, longlong);
		assertEquals("Left barPos has changed",true,leftBarPos != tester.getBarPos(BarKey.LEFT));
	}
	@Test
	public void testComputeScore() {
		PongModel tester = new MyPongModel("Filip","Husein");
		Set<Input> set = new HashSet<>();
		Input input = new Input(BarKey.LEFT, Dir.UP);
		set.add(input);
		long longtime = 10000;
		for(int i = 0;i<1000;i++){
			tester.compute(set,longtime);
		}
		assertEquals("Score has changed", true, !(tester.getScore(BarKey.LEFT).equals("0") && tester.getScore(BarKey.RIGHT).equals("0")));
		public void testComputeBall() {
			PongModel tester = new MyPongModel("Filip","Husein");
			Set<Input> set = new HashSet<>();
			Input input = new Input(BarKey.LEFT, Dir.UP);
			set.add(input);
			long longlong = 10;
			Point ballPos = tester.getBallPos();
			for(int i = 0;i<100000;i++){
				tester.compute(set,longlong);
			}
			assertEquals("BallPos has changed", false , ballPos.y == (tester.getBallPos().y));
		}

		@Test
		public void testComputeBar() {
			PongModel tester = new MyPongModel("Filip","Husein");
			Set<Input> set = new HashSet<>();
			Input input = new Input(BarKey.LEFT, Dir.UP);
			set.add(input);
			long longlong = 1;
			int leftBarPos = tester.getBarPos(BarKey.LEFT);
			tester.compute(set, longlong);
			assertEquals("Left barPos has changed",true,leftBarPos != tester.getBarPos(BarKey.LEFT));
		}
		@Test
		public void testComputeScore() {
			PongModel tester = new MyPongModel("Filip","Husein");
			Set<Input> set = new HashSet<>();
			Input input = new Input(BarKey.LEFT, Dir.UP);
			set.add(input);
			long longtime = 10000;
			for(int i = 0;i<1000;i++){
				tester.compute(set,longtime);
			}
			assertEquals("Score has changed", true, !(tester.getScore(BarKey.LEFT).equals("0") && tester.getScore(BarKey.RIGHT).equals("0")));

		}
		@Test
		public void testGetBarPos() {
			PongModel tester = new MyPongModel("Filip","Husein");
			assertEquals("Model's left BarKey's pos exists",true,tester.getBarPos(BarKey.LEFT) != 0);
		}

		@Test
		public void testGetBarHeight() {
			PongModel tester = new MyPongModel("Filip","Husein");
			assertEquals("Model's left BarKey's height exists",true,tester.getBarHeight(BarKey.LEFT) != 0);
		}

		@Test
		public void testGetBallPos() {
			PongModel tester = new MyPongModel("Filip","Husein");
			assertEquals("Model's getBallPos.x does not return 0",true,tester.getBallPos().x != 0);
		}

		@Test
		public void testGetMessage() {
			PongModel tester = new MyPongModel("Filip","Husein");
			assertEquals("Model's GetMessage returns a string",true, tester.getMessage() instanceof String);
		}

		@Test
		public void testGetScore() {
			PongModel tester = new MyPongModel("Filip","Husein");
			assertEquals("Score is real", "0", tester.getScore(BarKey.LEFT));
		}

		@Test
		public void testGetFieldSize() {
			PongModel tester = new MyPongModel("Filip","Husein");
			assertEquals("Model's GetMessage returns an int which is not 0",true, tester.getFieldSize().height != 0);
		}

	}

	}
	@Test
	public void testGetBarPos() {
		PongModel tester = new MyPongModel("Filip","Husein");
		assertEquals("Model's left BarKey's pos exists",true,tester.getBarPos(BarKey.LEFT) != 0);
	}

	@Test
	public void testGetBarHeight() {
		PongModel tester = new MyPongModel("Filip","Husein");
		assertEquals("Model's left BarKey's height exists",true,tester.getBarHeight(BarKey.LEFT) != 0);
	}

	@Test
	public void testGetBallPos() {
		PongModel tester = new MyPongModel("Filip","Husein");
		assertEquals("Model's getBallPos.x does not return 0",true,tester.getBallPos().x != 0);
	}

	@Test
	public void testGetMessage() {
		PongModel tester = new MyPongModel("Filip","Husein");
		assertEquals("Model's GetMessage returns a string",true, tester.getMessage() instanceof String);
	}

	@Test
	public void testGetScore() {
		PongModel tester = new MyPongModel("Filip","Husein");
		assertEquals("Score is real", "0", tester.getScore(BarKey.LEFT));
	}

	@Test
	public void testGetFieldSize() {
		PongModel tester = new MyPongModel("Filip","Husein");
		assertEquals("Model's GetMessage returns an int which is not 0",true, tester.getFieldSize().height != 0);
	}

}
