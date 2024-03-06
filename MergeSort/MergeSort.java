public class MergeSort {

	private static int []myArray;
	public static void main(String[] args) {
		myArray = getArray();
		printArray(myArray);
		sort();
		System.out.println();
		printArray(myArray);
	}
	
	public static void sort(){
		int []tempArray = new int[myArray.length];
		mergeSort(tempArray,0,myArray.length-1);
	}
	
	public static void mergeSort(int []tempArray,int lowerIndex,int upperIndex){
		if(lowerIndex == upperIndex){
			return;
		}else{
			int midPt = (lowerIndex+upperIndex)/2;
			mergeSort(tempArray, lowerIndex, midPt);
			mergeSort(tempArray, midPt+1, upperIndex);
			merge(tempArray,lowerIndex,midPt+1,upperIndex);
		}
	}
	
	public static void merge(int []tempArray,int lowerIndexCursor,int higerIndex,int upperIndex){
		int tempIndex=0;
		int lowerIndex = lowerIndexCursor;
		int midIndex = higerIndex-1;
		int totalItems = upperIndex-lowerIndex+1;
      // While neither array is empty
		while(lowerIndex <= midIndex && higerIndex <= upperIndex){
      // Compare the items of the two arrays and copy the smaller item into to third temp array
			if(myArray[lowerIndex] < myArray[higerIndex]){
				tempArray[tempIndex++] = myArray[lowerIndex++];
			}else{
				tempArray[tempIndex++] = myArray[higerIndex++];
			}
		}
		
		while(lowerIndex <= midIndex){
			tempArray[tempIndex++] = myArray[lowerIndex++];
		}
		while(higerIndex <= upperIndex){
			tempArray[tempIndex++] = myArray[higerIndex++];
		}
		
		for(int i=0;i<totalItems;i++){
			myArray[lowerIndexCursor+i] = tempArray[i];
		}
	}
	
	public static void printArray(int []array){
		for(int i : array){
			System.out.print(i+" ");
		}
	}
	
   //populate array
	public static int[] getArray(){
		int size=1500;
		int []array = new int[size];
		int item = 0;
		for(int i=0;i<size;i++){
			item = (int)(Math.random()*100); 
			array[i] = item;
		}
		return array;
	}

}
