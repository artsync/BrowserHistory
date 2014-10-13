package nl.arthurvlug.browserhistory;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class BrowserHistoryTest {
	@Test
	public void testNone() throws Exception {
		BrowserHistory history = new BrowserHistory(Collections.<Character> emptyList(), 3);
		assertEquals(Arrays.asList(), history.getHistory());
	}


	@Test
	public void testOne() throws Exception {
		BrowserHistory history = new BrowserHistory(Arrays.asList('a'), 3);
		assertEquals(Arrays.asList('a'), history.getHistory());
	}

	@Test
	public void testNormal() throws Exception {
		BrowserHistory history = new BrowserHistory(Arrays.asList('a', 'b', 'c'), 3);
		assertEquals(Arrays.asList('c', 'b', 'a'), history.getHistory());
	}
	
	@Test
	public void testHistoryFull() throws Exception {
		BrowserHistory history = new BrowserHistory(Arrays.asList('a', 'b', 'c'), 2);
		assertEquals(Arrays.asList('c', 'b'), history.getHistory());
	}
	
	@Test
	public void testSameElement() throws Exception {
		BrowserHistory history = new BrowserHistory(Arrays.asList('a', 'a'), 3);
		assertEquals(Arrays.asList('a'), history.getHistory());
	}
	
	@Test
	public void testSameElementInterleaved() throws Exception {
		BrowserHistory history = new BrowserHistory(Arrays.asList('a', 'b', 'a'), 3);
		assertEquals(Arrays.asList('a', 'b'), history.getHistory());
	}
	
	@Test
	public void testSameElementSizeTwo() throws Exception {
		BrowserHistory history = new BrowserHistory(Arrays.asList('a', 'b', 'b'), 3);
		assertEquals(Arrays.asList('b', 'a'), history.getHistory());
	}
	
	@Test
	public void testSameElementLots() throws Exception {
		BrowserHistory history = new BrowserHistory(Arrays.asList('a', 'b', 'a', 'c', 'a', 'b'), 3);
		assertEquals(Arrays.asList('b', 'a', 'c'), history.getHistory());
	}
}
