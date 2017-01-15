// checks to see if int isMember of an array
// this is how I would implement it in erlang, a default function that takes
// needle haystack that calls another with all the state information for the actual recurse
public class IsMember {
	public static boolean isMember(int search, int[] arr){
		return isMember(search, arr, 0, arr.length);
	}
	// this is main recurse function
	private static boolean isMember(int search, int[] arr, int index, int length){
		// if past end of list say false
		if (index>=length) return false;
		// if current element matches search, then return found
		else if (arr[index]==search) return true;
		// move on to next element
		else return isMember(search,arr,index+1, length);
	}
    public static void main(String[] args){
    	int[] test= {1,2,3,4,5,6};
    	int[] test1= {1,2,3,4,5,7,8,9};
    	//test if it works
    	System.out.println(isMember(6,test));
    	System.out.println(isMember(6,test1));
    }
}
