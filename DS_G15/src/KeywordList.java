import java.util.ArrayList;

public class KeywordList  {
	private ArrayList<Keyword> lst;
 
	public KeywordList(String filter){
		this.lst = new ArrayList<Keyword>();
		add(new Keyword(filter, 10));
		add(new Keyword("BTS"+filter, 20));
		add(new Keyword("���u�֦~��"+filter, 20));
		
	}
	
	public ArrayList<Keyword> getList(){
		return lst;
	}
	
	public void add(Keyword keyword){
		lst.add(keyword);
    }
}
