import java.util.ArrayList;

public class Ranking {
		private ArrayList<WebNode> ranking;
		public Ranking(ArrayList<WebNode> websites){
	        this.ranking=websites;
		}
		public ArrayList<WebNode> getList(){
			return this.ranking;
		}
		public void add(WebNode node)	{
			ranking.add(node);
		}
		public void sort(){
			quickSort(0, ranking.size()-1);
		}
		public void quickSort(int leftbound, int rightbound){
			if(leftbound < rightbound){
				int i = leftbound-1;
				for(int j=leftbound ; j<rightbound ; j++){
					if(ranking.get(j).nodeScore>=ranking.get(rightbound).nodeScore){
						i++;
						swap(i,j);
					}
				}
				swap(i+1,rightbound);
				quickSort(leftbound,i);
				quickSort(i+2,rightbound);
			}
	  
		}
		public void swap(int aIndex, int bIndex){
			WebNode temp = ranking.get(aIndex);
			ranking.set(aIndex, ranking.get(bIndex));
			ranking.set(bIndex, temp);
		}
		public ArrayList<WebNode> output(){
			//  String retVal="";
			//  for(int i=0;i<ranking.size();i++)
			//  {
			//   String a="( "+ranking.get(i).webPage.name+" , "+ranking.get(i).webPage.url+" , "+ranking.get(i).nodeScore+" )";
			//   retVal+=a+"\n";
			//  }
			//  return retVal;
			return ranking;
		}
}
