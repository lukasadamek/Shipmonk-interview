package com.shipmonk.sortedlinkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortedLinkedListTest {
	
	private SortedLinkedList<Integer> sortedLinkedList;
	
	@BeforeEach
	private void beforeEach() {
		sortedLinkedList = new SortedLinkedList<>();
	}
	
	@Test
	public void testEmptyList() {
		assertTrue(sortedLinkedList.isEmpty());
		assertEquals(0, sortedLinkedList.size());
		assertThrows(NoSuchElementException.class, () -> {
			sortedLinkedList.getFirst();
	    });
		assertThrows(NoSuchElementException.class, () -> {
			sortedLinkedList.getLast();
	    });
	}
	
	@Test
	public void testAddNull() {	
		assertThrows(IllegalArgumentException.class, () -> {
			sortedLinkedList.add(null);
	    });
	}
	
	@Test
	public void testRemoveNull() {	
		assertThrows(IllegalArgumentException.class, () -> {
			sortedLinkedList.remove(null);
	    });
	}
	
	@Test
	public void testContainsNull() {	
		assertThrows(IllegalArgumentException.class, () -> {
			sortedLinkedList.contains(null);
	    });
	}
	
	@Test
	public void testIndexOfNull() {	
		assertThrows(IllegalArgumentException.class, () -> {
			sortedLinkedList.indexOf(null);
	    });
	}
	
	@Test
	public void testAdd() {			
		sortedLinkedList.add(5);	
		Iterator<Integer> iter = sortedLinkedList.iterator();
		assertEquals(5, iter.next());
		assertFalse(iter.hasNext());
		assertEquals(5, sortedLinkedList.getFirst());
		assertEquals(5, sortedLinkedList.getLast());
		assertFalse(sortedLinkedList.isEmpty());
		assertEquals(1, sortedLinkedList.size());
		
		sortedLinkedList.add(1);
		iter = sortedLinkedList.iterator();
		assertEquals(1, iter.next());
		assertEquals(5, iter.next());
		assertFalse(iter.hasNext());
		assertEquals(1, sortedLinkedList.getFirst());
		assertEquals(5, sortedLinkedList.getLast());
		assertEquals(2, sortedLinkedList.size());
		
		sortedLinkedList.add(3);
		iter = sortedLinkedList.iterator();
		assertEquals(1, iter.next());
		assertEquals(3, iter.next());
		assertEquals(5, iter.next());
		assertFalse(iter.hasNext());
		assertEquals(1, sortedLinkedList.getFirst());
		assertEquals(5, sortedLinkedList.getLast());
		assertEquals(3, sortedLinkedList.size());
		
		sortedLinkedList.add(7);
		iter = sortedLinkedList.iterator();
		assertEquals(1, iter.next());
		assertEquals(3, iter.next());
		assertEquals(5, iter.next());
		assertEquals(7, iter.next());
		assertFalse(iter.hasNext());
		assertEquals(1, sortedLinkedList.getFirst());
		assertEquals(7, sortedLinkedList.getLast());
		assertEquals(4, sortedLinkedList.size());
		
		
	}
	
	@Test
	public void testRemove() {			
		sortedLinkedList.add(7);
		sortedLinkedList.add(5);
		sortedLinkedList.add(3);
		sortedLinkedList.add(1);
		
		assertFalse(sortedLinkedList.remove(2));
		assertEquals(1, sortedLinkedList.getFirst());
		assertEquals(7, sortedLinkedList.getLast());
		assertEquals(4, sortedLinkedList.size());
		
		Iterator<Integer> iter = sortedLinkedList.iterator();
		iter = sortedLinkedList.iterator();
		assertTrue(sortedLinkedList.remove(5));
		assertEquals(1, iter.next());
		assertEquals(3, iter.next());
		assertEquals(7, iter.next());
		assertFalse(iter.hasNext());
		assertEquals(1, sortedLinkedList.getFirst());
		assertEquals(7, sortedLinkedList.getLast());
		assertEquals(3, sortedLinkedList.size());
		
		assertTrue(sortedLinkedList.remove(7));
		iter = sortedLinkedList.iterator();
		assertEquals(1, iter.next());
		assertEquals(3, iter.next());
		assertFalse(iter.hasNext());
		assertEquals(1, sortedLinkedList.getFirst());
		assertEquals(3, sortedLinkedList.getLast());
		assertEquals(2, sortedLinkedList.size());
		
		assertTrue(sortedLinkedList.remove(1));
		iter = sortedLinkedList.iterator();
		assertEquals(3, iter.next());
		assertFalse(iter.hasNext());
		assertEquals(3, sortedLinkedList.getFirst());
		assertEquals(3, sortedLinkedList.getLast());
		assertEquals(1, sortedLinkedList.size());
		
		assertTrue(sortedLinkedList.remove(3));
		iter = sortedLinkedList.iterator();
		assertFalse(iter.hasNext());
		assertEquals(0, sortedLinkedList.size());
		assertThrows(NoSuchElementException.class, () -> {
			sortedLinkedList.getFirst();
	    });
		assertThrows(NoSuchElementException.class, () -> {
			sortedLinkedList.getLast();
	    });
	}
	
	@Test
	public void testGet() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			sortedLinkedList.get(-1);
	    });
		assertThrows(IndexOutOfBoundsException.class, () -> {
			sortedLinkedList.get(0);
	    });
		
		sortedLinkedList.add(5);
		assertEquals(5, sortedLinkedList.get(0));
		assertThrows(IndexOutOfBoundsException.class, () -> {
			sortedLinkedList.get(1);
	    });
		
		sortedLinkedList.add(3);
		assertEquals(3, sortedLinkedList.get(0));
		assertEquals(5, sortedLinkedList.get(1));
		assertThrows(IndexOutOfBoundsException.class, () -> {
			sortedLinkedList.get(2);
	    });
		assertThrows(IndexOutOfBoundsException.class, () -> {
			sortedLinkedList.get(-1);
	    });
	}
	
	@Test
	public void testIndexOf() {
		assertEquals(-1, sortedLinkedList.indexOf(1));
		
		sortedLinkedList.add(5);
		assertEquals(0, sortedLinkedList.indexOf(5));
		assertEquals(-1, sortedLinkedList.indexOf(1));
		
		sortedLinkedList.add(3);
		assertEquals(0, sortedLinkedList.indexOf(3));
		assertEquals(1, sortedLinkedList.indexOf(5));
		assertEquals(-1, sortedLinkedList.indexOf(1));
	}
	
	@Test
	public void testContains() {
		assertFalse(sortedLinkedList.contains(1));
		
		sortedLinkedList.add(5);
		assertTrue(sortedLinkedList.contains(5));
		assertFalse(sortedLinkedList.contains(1));
		
		sortedLinkedList.add(3);
		assertTrue(sortedLinkedList.contains(3));
		assertTrue(sortedLinkedList.contains(5));
		assertFalse(sortedLinkedList.contains(1));
	}
}
