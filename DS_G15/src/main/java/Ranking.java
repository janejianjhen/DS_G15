import java.util.ArrayList;

public class Ranking
{
	
	private ArrayList<WebNode> ranking;
	
	public Ranking(ArrayList<WebNode> websites){
		this.ranking=websites;
	}
	
	public ArrayList<WebNode> getList(){
		return this.ranking;
	}
	
	public void add(WebNode node){
		ranking.add(node);
	}
	
	public void sort(){
		quickSort(0, ranking.size()-1);
	}
	
	public void quickSort(int leftbound, int rightbound)
	{
		if(leftbound<rightbound){
			int i=leftbound-1;
			for(int j=leftbound;j<rightbound;j++){
				if(ranking.get(j).nodeScore>=ranking.get(rightbound).nodeScore){
					i++;
					Swap(i,j);
				}
			}
			Swap(i+1,rightbound);
			quickSort(leftbound,i);
			quickSort(i+2,rightbound);
		}
  
	}
	
	public void Swap(int indexA, int indexB)
	{
		WebNode temp = ranking.get(indexA);
		ranking.set(indexA, ranking.get(indexB));
		ranking.set(indexB, temp);
	}
}
