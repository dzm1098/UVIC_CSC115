public class A5Exercises {

	// PART 1

	/*
	 * Purpose: get a count of the number of elements in the array
	 * Parameters: int[] arr
	 * Returns: int - the number of elements
	 * Post-condition - the array contents are unchanged
	 */
	public static int countMultiples(int[] arr, int x) {
		if (arr.length == 0){
			return 0;
		}else{
			return countMultiplesRec(arr , arr.length - 1 , x);
		}

	}

	public static int countMultiplesRec(int[] arr, int i, int x){
		if (i < 0){
			return 0;
		}else if(arr[i]%x == 0){

			return 1 + countMultiplesRec(arr , i - 1 , x);
		}else{
			return 0 + countMultiplesRec(arr , i - 1 , x);
		}
	}
		
	/*
	 * Purpose: double all values in the given array
	 * Parameters: int[] array - the array to modify
	 * Returns: void - nothing
	 */
	public static void doubleAll(int[] array) {
		if (array.length > 0){
			doubleAllRec(array , array.length-1);
		}
		
	}

	public static void doubleAllRec(int[] array , int i){
		if (i < 0){
			return;
		}else{
			array[i] = array[i]*2;
			doubleAllRec(array , i-1);
		}
	}
	
	/*
	 * Purpose: get the minimum value found in the array
	 * Parameters: int[] array - the array to search
	 * Returns: int - minimum value found in the array
	 *                or -1 if the array is empty
	 * Post-condition - the array contents are unchanged
	 */
	public static int getMinimum(int[] array) {
		if (array.length == 0){
			return -1;
		}else{
			return getMinimumRec(array , array.length-1 , array[array.length-1]);
		}
	}

	public static int getMinimumRec(int[] array , int i , int min){
		if(i<0){
			return min;
		}else if ( i> 0 && min > array[i-1]){
			return getMinimumRec(array , i-1 , array[i-1]);
		}else{
			return getMinimumRec(array , i-1 , min);
		}
	}
	
	



	// PART II

	/*
	 * Purpose: get the total number of books in s
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: int - the total number of books
	 * Post-condition: s is not modified
	 */
	public static int totalBooks(Stack<Book> s) {
		if (s.isEmpty()){
			return 0;
		}
		return totalBooksRec(s);
	}

	public static int totalBooksRec(Stack<Book> s) {
		if (s.isEmpty()) {
			return 0;
		}
		Book top = s.pop();
		int sum1 = totalBooksRec(s);
		s.push(top);
		return 1+sum1;


	}


	/*
	 * Purpose: get the total number of pages of all 
	 *          books in the stack
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: int - the total number of pages
	 * Post-condition: s is not modified
	 */
	public static int totalPages(Stack<Book> s) {
		if(s.isEmpty()){
			return 0;
		}
		return totalPagesRec(s);
	}

	public static int totalPagesRec(Stack<Book> s){
		if (s.isEmpty()) {
			return 0;
		}
		Book top = s.pop();
		int pageSum = top.getPages();
		int sum1 = totalPagesRec(s);
		s.push(top);
		return pageSum + sum1;
	}
	
	/*
	 * Purpose: get the average number of pages of books in s
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: double - the average number of pages
	 *                   0.0 if there are no books in s
	 * Post-condition: s is not modified
	 */
	public static double averagePages(Stack<Book> s) {
		// You don't need to change this, if you have
		// completed the previous two exercises
		// correctly, it should pass all the tests
		if (s.isEmpty()) {
			return 0.0;
		} else {
			double sum = totalPages(s);
			int count = totalBooks(s);
			return sum/count;
		}
	}

	/*
	 * Purpose: determine whether toFind is contained in s
	 * Parameters: Stack<Book> s - the stack of books
	 *             Book toFind - the book to search for
	 * Returns: boolean - true if s contains toFind, false otherwise
	 * Post-condition: s is not modified
	 */
	public static boolean containsBook(Stack<Book> s, Book toFind) {
		if(s.isEmpty()){
			return false;
		}
		return containsBookRec(s ,toFind);

	}

	public static boolean containsBookRec(Stack<Book> s, Book toFind){
		if (s.isEmpty()){

			return false;
		}
		Book top = s.pop();
		if (top.equals(toFind)){
			s.push(top);

			return true;
		}
		boolean ans = containsBookRec(s,toFind);
		s.push(top);
		return ans;
	}


	/*
	 * Purpose: determine the books in s are stacked correctly
	 *          (ie. there is never a book stacked on top of 
	 *               another book with fewer pages)
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: boolean - true if books in s are stacked correctly
	 * Post-condition: s is not modified
	 */
	public static boolean stackedCorrectly(Stack<Book> s) {
		if(s.isEmpty()){
			return true;
		}
		return stackedCorrectlyRec(s , s.top());
	}

	public static boolean stackedCorrectlyRec(Stack<Book> s , Book cur){

		if(s.isEmpty()){

			return true;
		}
		Book nex = s.pop();

		if (cur.getPages() > nex.getPages() ){
			s.push(nex);
			return false;
		}

		boolean ans = stackedCorrectlyRec(s,cur);
		s.push(nex);
		return ans;
	}
}