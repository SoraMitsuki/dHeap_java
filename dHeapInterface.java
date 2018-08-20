/**
 * Name: Jiaqi Fan
 * ID: A12584051
 * Login: cs12sju
 */
package hw6;

import java.util.*;

/**
 * the dheap interface for implementing dHeap
 * @author Jiaqi Fan
 * @version 1.0
 * @since 05/03/2016
 * @param <T> the type that will be stored in the heap.
 * type must implement comparable interface with itself.
 */
public interface dHeapInterface<T extends java.lang.Comparable<? super T>> {
	/**
	 * add the specified element to the heap
	 * can not add null element to the heap
	 * @param o
	 * @throws null pointer exception if the element that added is null
	 */
	public void add(T o) throws NullPointerException;
	/**
	 * remove and return the element stored in the heap.
	 * @return the element that removed
	 * @throws No such element exception if heap is empty
	 */
	public T remove () throws NoSuchElementException;
	/**
	 * return the number stored in the heap
	 * @return the number stored in the heap
	 */
	public int size ();

}
