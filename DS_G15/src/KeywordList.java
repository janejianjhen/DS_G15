import java.util.ArrayList;

public class KeywordList {
private ArrayList<Keyword> lst;
	
	public KeywordList(){
		this.lst = new ArrayList<Keyword>();
    }
	
	public void add(Keyword keyword){
		lst.add(keyword);
//		System.out.println("Done");
    }
	
	//quick sort
	public void sort(){
		if(lst.size() == 0)
		{
			System.out.println("InvalidOperation");
		}
		else 
		{
			quickSort(0, lst.size()-1);
//			System.out.println("Done");
		}

	}
	
	
	private void quickSort(int leftbound, int rightbound){
		//1. implement quickSort algorithm
		int pivot = lst.get(rightbound).weight;
		int left = leftbound;
		int right = rightbound-1;
		while(left < right) {
			while(lst.get(left).weight <= pivot && left < right){
				left++;
			}
			while(lst.get(right).weight > pivot && right > left){
				right--;
			}
			if(left < right) {
				swap(left, right);
			}else {
				swap(left, pivot);
			}
		}
		if(leftbound < left-1) {
			quickSort(leftbound, left-1);
		}
		if(left+1 < rightbound) {
			quickSort(left+1,rightbound);
		}
	}

	
	
	private void swap(int aIndex, int bIndex){
		Keyword temp = lst.get(aIndex);
		lst.set(aIndex, lst.get(bIndex));
		lst.set(bIndex, temp);
	}
	
	public void output(){
		//TODO: write output and remove all element logic here...
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<lst.size();i++){
			Keyword k = lst.get(i);
			if(i>0)sb.append(" ");
			sb.append(k.toString());
		}
		
		System.out.println(sb.toString());	
	}
}
