package nl.arthurvlug.browserhistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrowserHistory {
	private int maxSize;
	private Node firstNode;
	private Node lastNode;
	private Map<Character, Node> map = new HashMap<Character, Node>();

	public BrowserHistory(List<Character> characters, int maxSize) {
		this.maxSize = maxSize;
		for(Character character : characters) {
			visit(character);
		}
	}

	public void visit(Character character) {
		if(map.containsKey(character)) {
			putInFront(character);
			return;
		}
		
		// First make sure we have enough space to store the node
		if(map.size() == maxSize) {
			// We are full
			deleteLast();
		}
		insertFirst(character);
	}

	private void insertFirst(Character character) {
		// We don't have it yet: Add it as the first element
		Node newNode = new Node(character);
		if(firstNode == null) {
			// This is the first element: It is the first and last element at the same time now.
			lastNode = newNode;
		} else {
			Node oldFirstNode = firstNode;
			
			newNode.next = oldFirstNode;
			oldFirstNode.previous = newNode;
		}
		firstNode = newNode;
		map.put(character, newNode);
	}

	private void deleteLast() {
		// Detach the last node
		lastNode.previous.next = null;
		map.remove(lastNode);
		
		// Remove the last node
		lastNode = lastNode.previous;
		lastNode.next = null;
	}

	private void putInFront(Character character) {
		// We already have this item. Put it in front
		Node node = map.get(character);
		
		// Remove node from the list and reattach pointers
		if(node.previous != null) {
			node.previous.next = node.next;
		}
		if(node.next != null) {
			node.next.previous = node.previous;
		}
		
		// Prepend the node
		if(node != firstNode) {
			node.next = firstNode;
			firstNode.previous = node;
		}
		node.previous = null;
		
		// Set the first node to be our current node.
		firstNode = node;
	}

	public List<Character> getHistory() {
		List<Character> list = new ArrayList<Character>();
		Node node = firstNode;
		while(node != null) {
			list.add(node.getValue());
			node = node.next;
		}
		return list;
	}
}
