/**
 * Name: Jiaqi Fan
 * ID: A12584051
 * Login: cs12sju
 */
package hw6;

import java.util.*;

/**
 * create a dheap
 * either a max heap or a min heap
 * @author Jiaqi Fan
 * @version 1.0
 * @since 05/03/2016
 * @param <T> the type that heap will hold
 */
class dHeap <T extends Comparable <? super T>> implements dHeapInterface<T> {
	private int size; //the size of the heap
	private boolean isMax; //Whether is max or min heap
	private int children; //Number of children
	T[] myarray; //the array for the heap
		

	 /** O-argument constructor. Creates and empty dHeap with 
	 *  initial capacity = 5, and is a 2-min-heap 
	 */ 
	@SuppressWarnings("unchecked")
	public dHeap()
	{
		isMax = false; //min heap
		myarray = (T[])new Comparable[5]; //create the array
		children = 2; //for binary heap
	}

	/** 
	 * Constructor to build a min or max dheap
	 * @param isMaxHeap  if true, this is a 2-max-heap, else a 2-min-heap 
	 * with initial size = 'capacity'
	 */ 
	@SuppressWarnings("unchecked")
	public dHeap(int capacity, boolean isMaxHeap)
	{
		isMax = isMaxHeap;//either max heap or min heap
		myarray = (T[])new Comparable[capacity]; //Initialize empty array
		children = 2; //for binary heap
	}

	/** 
	 * Constructor to build a with specified initial capacity and
	 * given number of children d. 
	 * @param capacity initial capacity of the heap.	
	 * @param isMaxHeap if true, this is a max-heap, else a min-heap 
	 * @param d number of children, 
	 * @exception if d is less than one, throw IllegalArgumentException();
	 */ 
	@SuppressWarnings("unchecked")
	public dHeap(int capacity, boolean isMaxHeap, int d) throws IllegalArgumentException
	{
		if(d < 1){ //check if the d is less than 1
			throw new IllegalArgumentException(); //if it is throw illegal argument exception
		}
		myarray = (T[])new Comparable[capacity]; //create array
		isMax = isMaxHeap; //either max or min heap
		children = d; //set the children
	}
	/**
	 * return the number of element stored in heap
	 * @return the number of element stored in heap
	 */
	public int size () { 
		return size;
	}
	/**
	 * add the specified element to the heap
	 * can not add null element to the heap
	 * @param Data
	 * @throws null pointer exception if the element that added is null
	 */
	@SuppressWarnings("unchecked")
	public void add (T data) throws NullPointerException{
		if(data == null){ //check for null pointer exception
			throw new NullPointerException();
		}
		if(size == myarray.length){ //if the array is not enough length
			T[] temp = (T[]) new Comparable[myarray.length]; //then double the array
			System.arraycopy(myarray, 0, temp, 0, myarray.length);
			myarray = (T[]) new Comparable[myarray.length*2];
			System.arraycopy(temp, 0, myarray, 0, temp.length);
		}
		myarray[size] = data; //add the element to the bottom
		int index = size;
		if(isMax == false){ //if the heap is min heap
			//check for the condition for all the parents and children
			//swap if the parents is larger than the child
			while(index > 0 && myarray[index].compareTo(myarray[findParent(index)]) < 0){
				swap(myarray, index, findParent(index));
				index = findParent(index);
			}
		}
		else{
			//chekc for the condition for all the parents and children
			//swap if the parents is less than the child
			while(index > 0 && myarray[index].compareTo(myarray[findParent(index)]) > 0){
				swap(myarray, index, findParent(index));
				index = findParent(index);
			}
		}
		size++;
	}
	/**
	 * Helper method for add method that find the parent index of the child
	 * find the parent index of the child element
	 * @param childindex
	 * @return the index of the parents
	 */
	private int findParent(int childindex){
		return ((childindex - 1) / children);
	}
	/**
	 * Helper method that swap the 2 elements in the heap
	 * swap the position of two elements
	 * @param T[] array the input array with elements
	 * @param iFrom the from index
	 * @param iTo to the index
	 */
	private void swap(T[] array, int iFrom, int iTo){
		T temp = array[iFrom]; //let temp hold the first element
		array[iFrom] = array[iTo]; //change the 2 elements
		array[iTo] = temp;
	}

	/**
	 * Removes and return the element stored on the heap
	 * if the heap is empty throw no such element exception
	 * @return the element that removed
	 * @throw No such element exception when heap is empty
	 */
	public T remove () throws NoSuchElementException{
		if(size == 0){ //check the heap is empty or not
			throw new NoSuchElementException();
		}
		T temp = myarray[0]; //store the head in the temp
		swap(myarray, 0, --size); //swap the last element to the top
		if(size > 0){
			trickleDown();
		}
		return temp;
	}
	/**
	 * Helper method that shift the new head to the correct position of the heap
	 */
	private void trickleDown(){
		int head = 0; //initizlize current to head which is top element
		int childIndex;//index of the children
		boolean doneSet = false;
		//check all children until met the requirement
		//from small to big to from big to small
		while(isChild(head) == false && doneSet == false){
			childIndex = compareChildren(head);
			if(isMax == false){ //process when it is a min heap
				if(myarray[head].compareTo(myarray[childIndex]) > 0){
					swap(myarray, head, childIndex); //swap the elements
					head = childIndex; //set the new head index
				}
				else{
					doneSet = true;
				}
			}
			else{ //process when it is a max heap
				if(myarray[head].compareTo(myarray[childIndex]) < 0){
					swap(myarray, head, childIndex);
					head = childIndex;
				}
				else{
					doneSet = true; //when finished trick down
				}
			}
		}
	}
	/**
	 * Helper method compareChildren
	 * compare all the children of current element
	 * return the one that is needed either max or min
	 * @return the index of the children
	 * @param the current position 
	 */
	private int compareChildren(int currIndex){
		int indexNow = getChildren(currIndex, 1); // get the first child index
		T childNow = myarray[indexNow]; //get the child of that index
		int childIndex = indexNow; //the index that need return of the correct child
		//loop through how many child are there
		for(int i = 1; i <= children && indexNow < size - 1; i++ ){
			indexNow = getChildren(currIndex, i);
			if(isMax == false){//this is for min heap
				//compare all the child and find the minimum child
				if(myarray[indexNow].compareTo(childNow) < 0){
					childNow = myarray[indexNow];
					childIndex = indexNow;
				}
			}
			else{ //this is for max heap
				//compare all the child and find the max
				if(myarray[indexNow].compareTo(childNow) > 0){
					childNow = myarray[indexNow];
					childIndex = indexNow;
				}
			}
		}
		return childIndex;
	}
	/**
	 * Helper method
	 * @param currIndex the index of current position
	 * @param child the children of the current element
	 * @return the children's index
	 * @throws IndexOutOfBound Exception when children is less than 1
	 * @throws NoSuchElementException if the current element is leaf
	 */
	private int getChildren(int currIndex, int child)
	throws IndexOutOfBoundsException, NoSuchElementException{
		//if the number of child is less than 1 or greater than children set
		//then there is no child to get
		if(child < 1 || child > children){
			throw new IndexOutOfBoundsException();
		}
		//if the the current position is a child then there is no child element
		if(isChild(currIndex) == true){
			throw new NoSuchElementException();
		}
		return (children * currIndex) + child;
		
	}
	/**
	 * determine if the current position is a child or not
	 * @param currIndex
	 * @return true if the element is child otherwise false
	 */
	private boolean isChild(int currIndex){
		return (currIndex >= size / children) && (currIndex < size);
	}
	
	/**
	 * Helper method for junit test
	 */
	public T getElement(int index){
		return myarray[index]; //return the element in the heap at thet index
	}

}
