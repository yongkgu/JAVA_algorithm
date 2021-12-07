package kr.kmooc.dataEngineering.homework1;

import java.util.Iterator;

public class MyArrayListIterator<E> implements Iterator<E> {

	private Object[] data;
	private int cursor;

	public MyArrayListIterator(Object[] data) {
		this.data = data;
		this.cursor = -1;
	}
	

	public boolean hasNext() {
		if(cursor + 1 < data.length)
			return true;
		else
			return false;
	}


	@Override
	public E next() {
		// TODO Auto-generated method stub
		return (E) data[++cursor];
	}

}
