/*
* HeapPriorityQueue.java
*
* An implementation of a minimum PriorityQueue using a heap.
* based on the implementation in "Data Structures and Algorithms
* in Java", by Goodrich and Tamassia
*
* This implementation will throw a Runtime, HeapEmptyException
*	if the heap is empty and removeLow is called.
*
* This implementation will throw a Runtime, HeapFullException
*	if the heap is full and insert is called.
*
*/
//rawtypes, unchecked
public class HeapPriorityQueue implements PriorityQueue {

	protected final static int DEFAULT_SIZE = 1000000;
	
	protected Comparable[] storage;
	protected int currentSize;

	/*
	 * Constructor that initializes the array to hold DEFAULT_SIZE elements
	 */
	public HeapPriorityQueue() {
		storage = new Comparable[DEFAULT_SIZE + 1];
		currentSize = 0;
		
		// if using a 1-based implementation, remember to allocate an 
		// extra space in the array since index 0 is not used. 
	}
	
	/*
	 * Constructor that initializes the array to hold size elements
	 */
	public HeapPriorityQueue(int size) {
		storage = new Comparable[size + 1];

		
		// if using a 1-based implementation, remember to allocate an 
		// extra space in the array since index 0 is not used. 
	}

	public void insert (Comparable element) throws HeapFullException {
		if(isFull()){
			throw new HeapFullException();
		}
		currentSize++;
		storage[currentSize] = element;

		bubbleUp(currentSize);

		
		// When inserting the first element, choose whether to use 
		// a 0-based on 1-based implementation. Whatever you choose,
		// make sure your implementation for the rest of the program
		// is consistent with this choice.		
    }
	
	public void bubbleUp(int index) {
		if(index > 1){
			if(storage[index/2].compareTo(storage[index]) > 0){
				swap(index/2 , index);
				bubbleUp(index/2);
			}
		}
	}

	public void swap(int father , int son){
		Comparable temp = storage[father];
		storage[father] = storage[son];
		storage[son] = temp;
	}

	public Comparable removeMin() throws HeapEmptyException {
		if(isEmpty()){
			throw new HeapEmptyException();
		}

		Comparable temp = storage[1];
		swap(1,currentSize);
		currentSize--;
		bubbleDown(1);
		return temp;
	}

	private void bubbleDown(int index) {
		if (!(index-1 >= (currentSize / 2) && index-1 <= currentSize)){
			int l_key = index*2; //left
			if (l_key < currentSize){
				if (storage[l_key+1].compareTo(storage[l_key]) < 0){
					l_key++;
				}
			}
			if (storage[l_key].compareTo(storage[index]) < 0) {
				swap(index, l_key);
				bubbleDown(l_key);
			}
		}
	}

	public boolean isEmpty(){

		
		return this.currentSize == 0; // so it compiles
	}
	
	public boolean isFull() {

		
		return (currentSize == DEFAULT_SIZE || currentSize == storage.length-1); // so it compiles
	}
	
	public int size () {

		
		return this.currentSize; // so it compiles
	}

	public String toString() {
		String s = "";
		String sep = "";
		// This implementation of toString assumes you 
		// are using a 1-based approach. Update the initial
		// and final value for i if using a 0-based
		for(int i=1; i<=currentSize; i++) {
			s += sep + storage[i];
			sep = " ";
		}
		return s;
	}
}
