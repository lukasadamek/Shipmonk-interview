package com.shipmonk.sortedlinkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic sorted linked list implementation.
 * <p>
 * This list maintains its elements in ascending order according to their natural ordering
 * (as defined by the {@link Comparable} interface).
 * Duplicate values are allowed. 
 * Null values are not allowed.
 * All operations are non-thread-safe.
 *
 * @param <T> the type of elements in this list; must implement {@link Comparable}
 */
public class SortedLinkedList<T extends Comparable<T>> implements Iterable<T> {

	private Node<T> first;
	private Node<T> last;
	private int size = 0;

	public SortedLinkedList() {
	}

	/**
	 * Returns {@code true} if the list contains no elements.
	 *
	 * @return {@code true} if the list is empty; {@code false} otherwise
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the first (smallest) element in this list.
	 *
	 * @return the first element
	 * @throws NoSuchElementException if the list is empty
	 */
	public T getFirst() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		return first.getValue();
	}

	/**
	 * Returns the last (largest) element in this list.
	 *
	 * @return the last element
	 * @throws NoSuchElementException if the list is empty
	 */
	public T getLast() {
		if (last == null) {
			throw new NoSuchElementException();
		}
		return last.getValue();
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index index of the element to return (0-based)
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	public T get(int index) {
		if (first == null) {
			throw new IndexOutOfBoundsException();
		}
		int i = 0;
		Node<T> indexNode = first;
		do {
			if (i == index) {
				return indexNode.getValue();
			}
			indexNode = indexNode.getNext();
			i++;
		} while (indexNode != null);
		throw new IndexOutOfBoundsException();
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element.
	 *
	 * @param object the value to search for
	 * @return index of the value, or -1 if not found
	 * @throws IllegalArgumentException if {@code object} is {@code null}
	 */
	public int indexOf(T object) {
		if (object == null) {
			throw new IllegalArgumentException();
		}
		Node<T> current = first;
		int index = 0;
		while (current != null) {
			int cmp = object.compareTo(current.getValue());
			if (cmp == 0) {
				return index;
			} else if (cmp < 0) {
				return -1;
			}
			index++;
			current = current.getNext();
		}
		return -1;
	}

	/**
	 * Inserts the specified value into the list, preserving sorted order.
	 *
	 * @param value the value to insert
	 * @throws IllegalArgumentException if {@code value} is {@code null}
	 */
	public void add(T value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		size++;
		Node<T> newNode = new Node<>(value);
		if (first == null) {
			first = last = newNode;
			return;
		}
		if (value.compareTo(first.getValue()) < 0) {
			newNode.setNext(first);
			first = newNode;
			return;
		}
		Node<T> current = first;
		while (current.getNext() != null && value.compareTo(current.getNext().getValue()) >= 0) {
			current = current.getNext();
		}
		newNode.setNext(current.getNext());
		current.setNext(newNode);
		if (newNode.getNext() == null) {
			last = newNode;
		}
	}

	/**
	 * Removes the first occurrence of the specified value from the list.
	 *
	 * @param value the value to remove
	 * @return {@code true} if the element was removed; {@code false} otherwise
	 * @throws IllegalArgumentException if {@code value} is {@code null}
	 */
	public boolean remove(T value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		if (first == null) {
			return false;
		}
		if (first.getValue().equals(value)) {
			size--;
			first = first.getNext();
			if (first == null) {
				last = null;
			}
			return true;
		}
		Node<T> current = first;
		while (current.getNext() != null) {
			if (current.getNext().getValue().equals(value)) {
				size--;
				current.setNext(current.getNext().getNext());
				if (current.getNext() == null) {
					last = current;
				}
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	/**
	 * Returns {@code true} if this list contains the specified value.
	 *
	 * @param value the value to check
	 * @return {@code true} if the value is found; {@code false} otherwise
	 */
	public boolean contains(T value) {
		return indexOf(value) != -1;
	}

	/**
	 * Returns an iterator over the elements in this list in ascending order.
	 *
	 * @return an iterator over the elements in sorted order
	 */
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node<T> current = first;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				T val = current.getValue();
				current = current.getNext();
				return val;
			}
		};
	}
}