package kr.kmooc.dataEngineering.homework2_3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;

public class MyLinkedList<E> implements List<E>, Queue<E> {

	private MyNode<E> first;
	private MyNode<E> last;
	private int size;

	public MyLinkedList() {
		first = null;
		last = null;
		size = 0;
	}

	public MyLinkedList(Collection<? extends E> c) {
		this();
		for (E value : c) {
			add(value);
		}
	}

	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean add(E e) {
		if (first == null) {
			// 비어 있을 때
			MyNode<E> newNode = new MyNode<E>(null, e, null);
			first = newNode;
			last = newNode;
			size++;
		} else {
			// 비어 있지 않을 때
			MyNode<E> newNode = new MyNode<E>(last, e, null);
			last.setNext(newNode);
			last = newNode;
			size++;
		}
		return true;
	}

	@Override
	public String toString() {
		if (size == 0) {
			return "[]";
		}
		String result = "[";
		result += first.getItem();
		MyNode<E> cursor = first.getNext();
		while (cursor != null) {
			result += ", " + cursor.getItem();
			cursor = cursor.getNext();
		}
		result += "]";
		return result;

	}

	@Override
	public boolean offer(E e) {
		if (first == null) {
			// 비어 있을 때
			MyNode<E> newNode = new MyNode<E>(null, e, null);
			first = newNode;
			last = newNode;
			size++;
		} else {
			// 비어 있지 않을 때
			MyNode<E> newNode = new MyNode<E>(last, e, null);
			last.setNext(newNode);
			last = newNode;
			size++;
		}
		return true;
	}

	@Override
	public E remove() {
		if (size == 0) {
			throw new NoSuchElementException();
		} else {
			return remove(0);
		}
	}

	@Override
	public E poll() {
		if (size == 0) {
			return null;
		} else {
			return remove(0);
		}
	}

	@Override
	public E element() {
		if (size == 0) {
			throw new NoSuchElementException();
		} else {
			return get(0);
		}
	}

	@Override
	public E peek() {
		if (size == 0) {
			return null;
		} else {
			return get(0);
		}
	}

	@Override
	public boolean contains(Object o) {
		if (size == 0)
			return false;
		MyNode<E> cursor = first;
		do {
			if (cursor.getItem().equals(o))
				return true;
			cursor = cursor.getNext();
		} while (cursor != null);
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new MyLinkedListListIterator<E>(this, 0);
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int idx = 0;
		for (E val : this) {
			array[idx++] = val;
		}
		return array;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	private MyNode<E> getNode(Object o) {

		int cIdx = 0;
		MyNode<E> cursor = first;
		do {
			if (cursor.getItem().equals(o))
				return cursor;
			cursor = cursor.getNext();
			cIdx++;
		} while (cursor != null);

		return null;
	}

	public MyNode<E> getNode(int index) {
		if (index >= size || index < 0)
			throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for lenght " + size);

		int cIdx = 0;
		MyNode<E> cursor = first;
		do {
			if (cIdx == index)
				return cursor;
			cursor = cursor.getNext();
			cIdx++;
		} while (cursor != null);
		return null;
	}

	@Override
	public boolean remove(Object o) {
		MyNode<E> nodeToRemove = getNode(o);
		if (nodeToRemove == null)
			return false;

		MyNode<E> next = nodeToRemove.getNext();
		MyNode<E> prev = nodeToRemove.getPrev();
		if (prev == null) {
			first = next;
		} else {
			prev.setNext(next);
		}
		if (next == null) {
			last = prev;
		} else {
			next.setPrev(prev);
		}
		size--;

		return true;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		while (size != 0)
			remove(0);

	}

	@Override
	public E get(int index) {
		if (index >= size || index < 0)
			throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for lenght " + size);

		int cIdx = 0;
		MyNode<E> cursor = first;
		do {
			if (cIdx == index)
				return cursor.getItem();
			cursor = cursor.getNext();
			cIdx++;
		} while (cursor != null);
		return null;
	}

	@Override
	public E set(int index, E element) {
		MyNode<E> node = getNode(index);
		E previousOne = node.getItem();
		node.setItem(element);
		return previousOne;
	}

	@Override
	public void add(int index, E element) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException();
		if (size == index) {
			// 가장 마지막에 추가
			add(element);
		} else {
			// 중간에 추가
			MyNode<E> next = getNode(index);
			MyNode<E> prev = next.getPrev();
			// 새로운 노드 추가
			MyNode<E> newNode = new MyNode<E>(prev, element, next);
			// prev의 참조 재조정
			if (prev != null) {
				// 처음 위치에 추가하는 것이 아님
				prev.setNext(newNode);
			} else {
				first = newNode;
			}
			next.setPrev(newNode);
			size++;
		}
	}

	@Override
	public E remove(int index) {

		MyNode<E> nodeToRemove = getNode(index);
		E element = nodeToRemove.getItem();
		MyNode<E> next = nodeToRemove.getNext();
		MyNode<E> prev = nodeToRemove.getPrev();
		if (prev == null) {
			first = next;
		} else {
			prev.setNext(next);
		}
		if (next == null) {
			last = prev;
		} else {
			next.setPrev(prev);
		}
		size--;

		return element;
	}

	@Override
	public int indexOf(Object o) {
		int cIdx = 0;
		MyNode<E> cursor = first;
		do {
			if (cursor.getItem().equals(o))
				return cIdx;
			cursor = cursor.getNext();
			cIdx++;
		} while (cursor != null);

		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {

		int cIdx = size - 1;
		MyNode<E> cursor = last;
		do {
			if (cursor.getItem().equals(o))
				return cIdx;
			cursor = cursor.getPrev();
			cIdx--;
		} while (cursor != null);

		return -1;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return new MyLinkedListListIterator<E>(this, 0);
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return new MyLinkedListListIterator<E>(this, index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}


	public static void main(String[] args) {

		LinkedList<Email> linkedList = new LinkedList<>();
		linkedList.add(new Email(5, 2));
		linkedList.add(new Email(0, 1));
		linkedList.add(new Email(3, 4));
		linkedList.sort(new Comparator<Email>() {
			@Override
			public int compare(Email o1, Email o2) {
				// TODO Auto-generated method stub
				if (o1.getFrom() > o2.getFrom())
					return +1;
				else if (o1.getFrom() == o2.getFrom())
					return 0;
				else
					return -1;
			}
		});
		System.out.println(linkedList);
	}

}
