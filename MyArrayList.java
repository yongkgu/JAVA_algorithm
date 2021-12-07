package kr.kmooc.dataEngineering.homework1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


public class MyArrayList<E> implements List <E>{
	
		// 데이터 추상화
		private Object[] data;

		public MyArrayList() {
			data = new Object[0];
		}

		public String toString() {
			String result = "[";
			for (Object val : data) {
				result += " " + val + " ";
			}
			result += "]";
			return result;
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return data.length;
		}

		@Override
		public boolean isEmpty() {
			if (data == null || data.length == 0)
				return true;
			return false;
		}

		@Override
		public boolean contains(Object o) {
			for (Object value : data) {
				if (value.equals(o))
					return true;
			}
			return false;
		}

		@Override
		public Iterator<E> iterator() {
			
			return new MyArrayListListIterator<E>(data,0);
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return data;
		}

		@Override
		public <T> T[] toArray(T[] a) {
			if(a.length < data.length) {
				a = (T[]) Array.newInstance(a.getClass().componentType(), data.length);
			}
			for(int i=0; i<data.length ; i++) {
				a[i] = (T) data[i];
			}
			return a;
		}

		@Override
		public boolean add(E e) {

			// 1. 현재보다 크기가 한개 더 큰 배열 생성 newData
			Object[] newData = new Object[data.length + 1];

			for (int i = 0; i < data.length; i++) {
				newData[i] = data[i];
			}

			newData[newData.length - 1] = e;

			data = newData;

			return true;
		}

		@Override
		public boolean remove(Object o) {
			int ri = indexOf(o);
			if(ri == -1)
				return false;
			Object[] newData = new Object[data.length-1];
			
			for(int i=0; i<data.length;i++) {
				if(i==ri)
					break;
				newData[i] = data[i];
			}
			
			for(int i = ri+1;i <data.length;i++) {
				newData[i-1] = data[i];
			}
			data = newData;
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
			// TODO Auto-generated method stub
			data = new Object[0];
		}

		@Override
		public E get(int index) {
			if (index >= data.length || index < 0)
				throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for lenght " + data.length);

			return (E) data[index];
		}

		@Override
		public E set(int index, E element) {
			if (index >= data.length || index < 0)
				throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for lenght " + data.length);
			
			E previous = (E) data[index];
			data[index] = element;
			
			return previous;
		}

		@Override
		public void add(int index, E element) {
			// TODO Auto-generated method stub
			if (index > data.length)
				throw new IndexOutOfBoundsException();
			// 기존의 data를 index 전 까지 newData 복사
			Object[] newData = new Object[data.length + 1];

			// 기존의 data를 index 전 까지 newData 복사
			for (int i = 0; i < data.length; i++) {
				if (i == index)
					break;
				newData[i] = data[i];
			}

			// index위치에 element 삽입
			newData[index] = element;

			// index 다음 위치부터 다시 복사해서 붙여넣기
			for (int i = index; i < data.length; i++) {
				newData[i + 1] = data[i];
			}
			data = newData;

		}

		@Override
		public E remove(int index) {
			
			if (index >= data.length || index < 0)
				throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for lenght " + data.length);
			 
			E previous = (E) data[index];
			
			Object[] newData = new Object[data.length-1];
			
			for(int i=0; i<data.length;i++) {
				if(i==index)
					break;
				newData[i] = data[i];
			}
			
			for(int i = index+1;i <data.length;i++) {
				newData[i-1] = data[i];
			}
			data = newData;
			return previous;

		}

		@Override
		public int indexOf(Object o) {
			for (int i = 0; i < data.length; i++) {
				if (data[i].equals(o))
					return i;
			}
			return -1;
		}

		@Override
		public int lastIndexOf(Object o) {
			for (int i = data.length - 1; i >= 0; i--) {
				if (data[i].equals(o))
					return i;
			}
			return -1;
		}

		@Override
		public ListIterator<E> listIterator() {
	
			return new MyArrayListListIterator<E>(data, 0);
		}

		@Override
		public ListIterator<E> listIterator(int index) {
		
			return new MyArrayListListIterator<E>(data, index);
		}

		@Override
		public List<E> subList(int fromIndex, int toIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		
			
		public static void main(String[] args) {

			LinkedList<Integer> linkedList = new LinkedList<>();
			ArrayList<Integer> arrayList = new ArrayList<>();
			linkedList.add(1);
			linkedList.add(2);
			linkedList.add(3);
			linkedList.add(4);
			linkedList.add(5);
			linkedList.add(6);
			Iterator<Integer> it = linkedList.iterator();
			while (it.hasNext()) {
				Integer numb = it.next();
				if (numb % 2 == 0)
					arrayList.add(numb);
			}
			System.out.println(arrayList);
		}
	}

	

