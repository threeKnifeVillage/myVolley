package com.example.musically.volleystudy;

import java.util.AbstractList;
import java.util.List;

/**
 * <pre>
 *     author : wang lei
 *     e-mail : peter.wang@musical.ly
 *     time   : 2018/3/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MyVector<E> extends AbstractList<E> implements List<E> {

	private Object[] elementData;
	private transient int modCount;
	private int elementCount;

	protected int capacityIncrement;

	public MyVector(int initialCapacity, int capacityIncrement) {
		super();
		if (initialCapacity < 0) {
			new IllegalArgumentException("Illegal capacity " + initialCapacity);
		}

		this.elementData = new Object[initialCapacity];
		this.capacityIncrement = capacityIncrement;
	}

	public MyVector(int initialCapacity) {
		this(initialCapacity, 0);
	}

	public MyVector() {
		this(10);
	}

	@Override
	public E get(int index) {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

}
