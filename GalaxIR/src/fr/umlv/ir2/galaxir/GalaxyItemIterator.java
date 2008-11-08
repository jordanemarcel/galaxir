package fr.umlv.ir2.galaxir;

import java.util.ArrayList;
import java.util.Iterator;

public class GalaxyItemIterator<E> implements Iterator<E> {
	private Iterator<E> galaxyItemIterator;
	private ArrayList<E> galaxyItem;
	private E next = null;
	
	public GalaxyItemIterator(ArrayList<E> galaxyItem) {
		this.galaxyItem = galaxyItem;
		this.galaxyItemIterator = galaxyItem.iterator();
	}
	
	@Override
	public boolean hasNext() {
		return galaxyItemIterator.hasNext();
	}

	@Override
	public E next() {
		next = galaxyItemIterator.next();
		return next;
	}

	@Override
	public void remove() {
		if(next==null)
			throw new IllegalStateException();
		galaxyItem.remove(next);
	}

}
