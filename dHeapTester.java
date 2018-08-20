/**
 * Name: Jiaqi Fan
 * ID: A12584051
 * Login: cs12sju
 */
package hw6;

import java.util.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test dheap
 * @author Jiaqi Fan
 * @version 1.0
 * @since 05/03/2016
 */
public class dHeapTester {
	private dHeap idHeap; //create a dheap for min
	private dHeap adHeap; // one for max
	
	@Before
	public void setUp(){
		idHeap = new dHeap(5, false); //initialize it as a 2 argv
		adHeap = new dHeap(5, true);
	}
	
	/**
	 * Test dHeap ctor with 3 arguments with illegal argument exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCtor3argvIAE(){
		dHeap test1 = new dHeap(5, false, 0);
		//create a dheap with number of children is 0 
		fail("can not have 0 child"); //test will fail
		dHeap test2 = new dHeap(5, true, -1); //number of children is -1
		fail("can not have 0 child"); //fail the test
	}
	
	/**
	 * test dHeap ctor with no arguments
	 */
	@Test
	public void testCtor0argv(){
		dHeap test1 = new dHeap();
		test1.add(0); //test no argv ctor works
		//add and remove a item out of the list
		assertEquals(new Integer(0), test1.remove());
	}
	/**
	 * test dHeap ctor with 2 arguments
	 */
	@Test
	public void testCtor2argv(){
		idHeap.add(0); //test for ctor with 2 argvs
		assertEquals(new Integer(0), idHeap.remove());
		adHeap.add(0);
		assertEquals(new Integer(0), adHeap.remove());
	}
	/**
	 * test dHeap ctor with 3 arguments
	 */
	@Test
	public void testCtor3argv(){
		dHeap test1 = new dHeap(5,false,2);
		test1.add(0); //test 3 argv ctor with add and remove element
		assertEquals(new Integer(0), test1.remove());
		dHeap test2 = new dHeap(5,true,2);
		test2.add(0);
		assertEquals(new Integer(0), test2.remove());		
	}
	
	/**
	 * test add with 0 argv ctor with null pointer exception
	 */
	@Test(expected = NullPointerException.class)
	public void testAdd0argvNPE(){
		dHeap test1 = new dHeap();
		test1.add(null); //test 0 argv and add a null element
		fail("can not add null to heap"); //fail the test
	}
	/**
	 * test add with 2 argv ctor with null pointer exception
	 */
	@Test(expected = NullPointerException.class)
	public void testAdd2argvNPE(){
		idHeap.add(null); //test null pointer with 2 argv ctor
		fail("can not add null to heap");
		adHeap.add(null); //add null
		fail("can not add null to heap");
	}
	
	/**
	 * test add with 3 argv ctor with null pointer exception
	 */
	@Test(expected = NullPointerException.class)
	public void testAdd3argvNPE(){
		dHeap test1 = new dHeap(5,false,2);
		test1.add(null); //test with 3 argv ctor
		fail("can not add null to heap");
		dHeap test2 = new dHeap(5,false,2);
		test2.add(null);
		fail("can not add null to heap");
	}
	
	/**
	 * test add with 0 argv
	 */
	@Test
	public void testAdd0argv(){
		dHeap test1 = new dHeap();
		test1.add(45); //add few element to the heap
		test1.add(13);
		test1.add(2);
		test1.add(88); //while add i make the same structure
		test1.add(26); //on a piece of paper 
		test1.add(32);
		test1.add(26);
		assertEquals(2, test1.getElement(0)); //get the elemens from  
		assertEquals(26, test1.getElement(1)); //each index
		assertEquals(13, test1.getElement(2)); //see it match the result
		assertEquals(88, test1.getElement(3)); //that i wrote on my paper
		assertEquals(45, test1.getElement(4));
		assertEquals(32, test1.getElement(5));
		assertEquals(26, test1.getElement(6));
	}
	/**
	 * test add with 2 argv
	 */
	@Test
	public void testAdd2argv(){
		idHeap.add(45);
		idHeap.add(13);
		idHeap.add(2);
		idHeap.add(88); //do the samething for 2 argv ctor
		idHeap.add(26);
		idHeap.add(32);
		idHeap.add(26);
		assertEquals(2, idHeap.getElement(0));
		assertEquals(26, idHeap.getElement(1));
		assertEquals(13, idHeap.getElement(2));
		assertEquals(88, idHeap.getElement(3));
		assertEquals(45, idHeap.getElement(4));
		assertEquals(32, idHeap.getElement(5));
		assertEquals(26, idHeap.getElement(6));
		adHeap.add(34);
		adHeap.add(14);
		adHeap.add(64); //this is for max heap
		adHeap.add(74);
		adHeap.add(24);
		assertEquals(74, adHeap.getElement(0));
		assertEquals(64, adHeap.getElement(1));
		assertEquals(34, adHeap.getElement(2));
		assertEquals(14, adHeap.getElement(3));
		assertEquals(24, adHeap.getElement(4));
	}
	/**
	 * test add with 3 argv
	 */
	@Test
	public void testAdd3argv(){
		dHeap test1 = new dHeap(3,false,3);
		test1.add(45);
		test1.add(13);
		test1.add(2);
		test1.add(88); //test add for 3 argv ctor
		test1.add(26); //and it is min heap
		test1.add(32);
		test1.add(26);
		assertEquals(2, test1.getElement(0));
		assertEquals(26, test1.getElement(1));
		assertEquals(13, test1.getElement(2));
		assertEquals(88, test1.getElement(3));
		assertEquals(45, test1.getElement(4));
		assertEquals(32, test1.getElement(5));
		assertEquals(26, test1.getElement(6));
		dHeap test2 = new dHeap(3,true,3);
		test2.add(34);
		test2.add(14);
		test2.add(64);
		test2.add(74); //this is for 3 argv ctor
		test2.add(24);//it is max heap
		test2.add(25);
		assertEquals(74, test2.getElement(0));
		assertEquals(25, test2.getElement(1));
		assertEquals(34, test2.getElement(2));
		assertEquals(64, test2.getElement(3));
		assertEquals(14, test2.getElement(4));
		assertEquals(24, test2.getElement(5));
	}
	/**
	 * test remove with 0 argv and no such element
	 */
	@Test(expected = NoSuchElementException.class)
	public void testRemove0argvNSEE(){
		dHeap test1 = new dHeap(); //test remove with no such element
		test1.remove(); //for 0 argv ctor
		fail("the heap is empty");
	}
	/**
	 * test remove with 2 argv and no such element
	 */
	@Test(expected = NoSuchElementException.class)
	public void testRemove2argvNSEE(){
		dHeap test1 = new dHeap(5,true);
		test1.remove(); //test remove for 2 argv ctor
		fail("the heap is empty"); //remove a empty heap
		dHeap test2 = new dHeap(5,false); //test will fail
		test2.remove();
		fail("the heap is empty");
	}
	/**
	 * test remove with 3 argv and no such element
	 */
	@Test(expected = NoSuchElementException.class)
	public void testRemove3argvNSEE(){
		dHeap test1 = new dHeap(5,false,5);
		test1.remove();
		fail("the heap is empty"); //do the samething for 3 argv ctor
		dHeap test2 = new dHeap(5,true,5);
		test2.remove();
		fail("the heap is empty");
	}
	
	/**
	 * test remove with 0 argv
	 */
	@Test
	public void testRemove0argv(){
		dHeap test1 = new dHeap();
		test1.add(45);
		test1.add(13);
		test1.add(2);
		test1.add(88);
		test1.add(26); //add few element to the min heap
		test1.add(32); 
		test1.add(26);
		assertEquals(2, test1.remove());
		assertEquals(13, test1.remove()); //it will always remove the 
		assertEquals(26, test1.remove()); //smallest from the list
		assertEquals(26, test1.remove()); //in order
		assertEquals(32, test1.remove());
		assertEquals(45, test1.remove());
		assertEquals(88, test1.remove());
	}
	/**
	 * test remove with 2 argv
	 */
	@Test
	public void testRemove2argv(){
		idHeap.add(45);
		idHeap.add(13);
		idHeap.add(2);
		idHeap.add(88); //test remove for 2 argv ctor
		idHeap.add(26); //basic same as 0 ctor argv
		idHeap.add(32);
		idHeap.add(26);
		assertEquals(2, idHeap.remove());
		assertEquals(13, idHeap.remove());
		assertEquals(26, idHeap.remove());
		assertEquals(26, idHeap.remove());
		assertEquals(32, idHeap.remove());
		assertEquals(45, idHeap.remove());
		assertEquals(88, idHeap.remove());
		adHeap.add(45);
		adHeap.add(13);
		adHeap.add(2);
		adHeap.add(88);
		adHeap.add(26); //remove from max heap
		adHeap.add(32); 
		adHeap.add(26);
		assertEquals(88, adHeap.remove()); //will always remove the biggest
		assertEquals(45, adHeap.remove()); //from the heap
		assertEquals(32, adHeap.remove());
		assertEquals(26, adHeap.remove());
		assertEquals(26, adHeap.remove());
		assertEquals(13, adHeap.remove());
		assertEquals(2, adHeap.remove());
	}
	/**
	 * test remove with 3 argv
	 */
	@Test
	public void testRemove3argv(){
		dHeap test1 = new dHeap(3,false,3);
		test1.add(45);
		test1.add(13);
		test1.add(2);
		test1.add(88); //test with 3 argv ctor
		test1.add(26);
		test1.add(32);
		test1.add(26);
		assertEquals(2, test1.remove());
		assertEquals(13, test1.remove());
		assertEquals(26, test1.remove());
		assertEquals(26, test1.remove());
		assertEquals(32, test1.remove());
		assertEquals(45, test1.remove());
		assertEquals(88, test1.remove());
		dHeap test2 = new dHeap(3, true, 3);
		test2.add(45);
		test2.add(13);
		test2.add(2);
		test2.add(88);
		test2.add(26);
		test2.add(32); //basic samething for 0 argv and 2 argv ctor
		test2.add(26);
		assertEquals(88, test2.remove());
		assertEquals(45, test2.remove());
		assertEquals(32, test2.remove());
		assertEquals(26, test2.remove());
		assertEquals(26, test2.remove());
		assertEquals(2, test2.remove());
		assertEquals(13, test2.remove());
	}
}
