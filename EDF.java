/**
 * Name: Jiaqi Fan
 * ID: A12584051
 * Login: cs12sju
 */
package hw6;
import java.io.*;
import java.util.Scanner;
/**
 * earliest deadline first using priority queue
 * @author Jiaqi Fan
 * @version 1.0
 * @since 05/03/2016
 */
public class EDF {
	//case expression must be constant expression
	private static final String scheduleA = "schedule";
	private static final String runA = "run";
	//action status of current time
	private static final int readyT = 0;
	private static final int startT = 1;
	private static final int endT = 2;
	private static final int durationT = 3;
	/**
	 * prints the 3 different status
	 * @param choice which choice of printing
	 * @param current_time current time of application
	 * @param r record need to process
	 */
	public static void print(int choice,long current_time, Record r)
	{
		if(choice==1)
			System.out.println( current_time + ": adding " +
	                r.toString() );
		else if(choice==2)
			 System.out.println( current_time + ": busy with " +
	                 r.toString() );
		else
			System.out.println( current_time +  ": done with " +
	                r.toString( current_time ) );
	}

	public static void main(String[] args) {
		if(args.length != 1)//check for the file is valid or not
		{
			 System.err.println("Incorrect number of arguments: "+args.length);
			 System.err.println("java hw6.EDF <input-file>");
			 System.exit(1); 
		}
		File file = new File(args[0]); //get a file from argv
		MyPriorityQueue<Record> queue = new MyPriorityQueue<Record>(10);
		long current_time=0; //keep track of current time
		try{
			Scanner readfile = new Scanner(file); //create a scanner to read file
			while(readfile.hasNext()){
				//split the words by space
				String[] words = readfile.nextLine().split(" ");
				//check for the action should take
				switch(words[readyT]){
				case scheduleA: //case schedule
					schedule(queue, current_time, words);
					break;
				case runA: //case run
					long time = Integer.parseInt(words[startT]);
					current_time = run(queue, current_time, time);
					break;
				default:
					System.err.println("Program Failed to Run");
					break;
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Failed to open "+file);
			System.exit(1);
		}
		System.exit(0);
		
	}
	/**
	 * create a record object and add it to queue
	 * @param myqueue my queue
	 * @param currTime time will show on console
	 * @param words words in the file
	 */
	private static void schedule(MyPriorityQueue<Record> myqueue, 
									long currTime, String[] words){
		//obtain record from words
		String process = words[startT];
		long deadline = Integer.parseInt(words[endT]);
		long duration = Integer.parseInt(words[durationT]);
		Record myrecord = new Record(process, deadline, duration);
		print(startT, currTime, myrecord); //print the action on console
		myqueue.add(myrecord); //add record to priority queue
	}
	/**
	 * give a queue the current time and a time for execution, execute each time
	 * in the queue order and add duration items to the current time.
	 * @param myqueue the queue needed hold items
	 * @param currTime current time of application
	 * @param time time to stop
	 */
	private static long run(MyPriorityQueue<Record> myqueue, long currTime, long time){
		Record myrecord = myqueue.poll(); //pull the smallest element in the queue
		print(endT, currTime, myrecord); //print the status
		while(myrecord != null){ //loop until the record is empty
			if(currTime + myrecord.GetDuration() > time){ //check if execution the next record
				Record temp = new Record(myrecord, myrecord.GetDuration() - (time - currTime));
				//swap the time
				currTime = time;
				print(startT, currTime, temp); //print the status
				myqueue.add(temp);
				break;
			}
			currTime = currTime + myrecord.GetDuration();
			print(durationT,currTime,myrecord); //print the status
			myrecord = myqueue.poll(); //remove the element again from new head
			print(endT,currTime, myrecord); //print the status again
		}
		currTime = time;
		return currTime;
	}

}
