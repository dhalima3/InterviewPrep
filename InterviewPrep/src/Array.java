
public class Array {
	public static void main(String [] args){
		int[] stockPrices= {1,2,3,4,5,6,7};
		stockPrices(stockPrices);
	}
	
	public static int stockPrices(int[] stockPricesYesterday){
		int smallest = 0;
		int largest = 0;
		for (int i = 1; i < stockPricesYesterday.length; i++){
			if (stockPricesYesterday[i] < stockPricesYesterday[smallest]) smallest = i;
		}
		for (int i = 1; i < stockPricesYesterday.length; i++){
			if (stockPricesYesterday[i] > stockPricesYesterday[largest] && largest > smallest) largest = i;
		}
		int largestProfit = stockPricesYesterday[largest] - stockPricesYesterday[smallest];
		System.out.println("The most profitable days are buying on " + smallest + " and buying on " + largest);
		System.out.println("The largest profit is " + largestProfit);
		return largestProfit;
	}
}
