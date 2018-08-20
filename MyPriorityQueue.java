/**
 * Name: Jiaqi Fan
 * ID: A12584051
 * Login: cs12sju
 */
package hw6;
/**
 * MyPriorityQueue class that uses the dHeap class to provide the priority
 * queue operations add and poll defined in the standard java class
 * @author Jiaqi Fan
 * @version 1.0
 * @since 05/03/2016
 * @param <E>
 */
public class MyPriorityQueue<E extends Comparable<? super E>> {
	private dHeap<E> myheap; //the min-heap 
	private int size; //size of the queue
	/**
	 * ctor to initialize a queue with a binary heap with a user input size
	 * @param size the size the user input
	 * @throws IllegalArgumentException if the size is less than 1
	 */
	public MyPriorityQueue(int size) throws IllegalArgumentException {
		if(size < 1){ // if the size of queue is less than 1 throw illegal argument
			throw new IllegalArgumentException();
		}
		boolean minheap = false; //this is a min heap
		myheap = new dHeap<E>(size, minheap);
		this.size = 0;
	}
	
	/**
	 * method add that add a element into the heap
	 * @param e the element to add
	 * @throws throws nullpointerexception if null item is added
	 */
	public void add(E e) throws NullPointerException{
		if(e == null){ //check for adding null elements
			throw new NullPointerException();
		}
		myheap.add(e); //add the element
		size++;
	}
	/**
	 * method that retrives and remove the head of this queue
	 * @return the head element in the heap
	 */
	public E poll(){
		E head;
		if(size > 0){ //remove until size is 0
			head = myheap.remove();
			size--;
		}
		else{//if the size is empty the head is null
			head = null;
		}
		return head;
	}

}
