import java.io.IOException;
import java.util.ArrayList;

//calculate each webpage's weighted score
public class WebPage{
	public String url;
	public String name;
	public WordCounter counter;
	public double score;
 
	public WebPage(String url,String name){
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url); 
	}
 
	public void setScore(KeywordList keywords) throws IOException{
		score = 0;
		for(int i=0;i<keywords.getList().size();i++){   
			score += counter.countKeyword(keywords.getList().get(i).name)* keywords.getList().get(i).weight; 
		}
	}
 
}
