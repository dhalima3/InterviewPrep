
public class Strings {

	public static void main(String [] args){
		char[] charArr = {'a','b','c','d','e','f','g','h'};
		reverseCharAlt(charArr);
	}
	
	public static char[] reverseChar(char[] charArr){
		if (charArr.length < 2) return charArr;
		char temp;
		int length = charArr.length;
		for (int i = 0; i < length/2; i++){
			temp = charArr[i];
			charArr[i] = charArr[length-i-1];
			charArr[length-i-1] = temp;
		}
		System.out.println(charArr);
		return charArr;
	}
	
	public static char[] reverseCharAlt(char[] charArr){
		int leftPointer = 0;
		int rightPointer = charArr.length-1;
		while (leftPointer < rightPointer){
			char temp = charArr[leftPointer];
			charArr[leftPointer] = charArr[rightPointer];
			charArr[rightPointer] = temp;
			leftPointer++;
			rightPointer--;
		}
		System.out.println(charArr);
		return charArr;
	}
}
