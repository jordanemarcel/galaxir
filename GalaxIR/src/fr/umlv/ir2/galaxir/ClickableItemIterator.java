package fr.umlv.ir2.galaxir;

import java.util.ArrayList;
import java.util.Iterator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ClickableItemIterator implements Iterator<ClickableItem> {
	private Iterator<ClickableItem> clickableItemIterator;
	private ClickableItem next = null;
	
	public ClickableItemIterator(ArrayList<ClickableItem> mouseList) {
		this.clickableItemIterator = mouseList.iterator();
	}
	
	@Override
	public boolean hasNext() {
		return clickableItemIterator.hasNext();
	}

	@Override
	public ClickableItem next() {
		next = clickableItemIterator.next();
		return next;
	}

	@Override
	public void remove() {
		throw new NotImplementedException();
	}

}
