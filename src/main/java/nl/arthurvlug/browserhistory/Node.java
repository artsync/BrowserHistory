package nl.arthurvlug.browserhistory;

import lombok.Getter;

public class Node {
	@Getter
	private Character value;
	public Node previous;
	public Node next;
	
	public Node(Character value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Node " + value.toString();
	}
}
