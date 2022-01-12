/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Main{
	public static void main(String[] args) throws IOException, NullPointerException, InterruptedException 
	{

		//String userInput = request.getParameter("keyword");
		
		String userInput= Website.userInput;
		//Scanner in=new Scanner(System.in);
		//System.out.println("User input keyword:");
		//userInput+=in.next();
		
		//System.out.println("Filter keyword:");
		String filter= Website.filter;
		//filter+=in.next();
		//System.out.print(filter);
		
		//String filter = request.getParameter("filter");
		

		
		KeywordList keywords = new KeywordList(filter);
		for(int i=0;i<keywords.getList().size();i++){
			//System.out.println(keywords.getList().get(i).toString());
		}
		
		HashMap<String,String> webName=new GoogleQuery(userInput).query();
		ArrayList<String> rel=new GoogleQuery(userInput).relateKeyword();
		ArrayList<WebNode> nodeList=new ArrayList<WebNode>();
		
		HashMap<String,String> subPageMap=new HashMap<String,String>();
		try{
			int value = 0;
			ConsoleProgressBar cp = new ConsoleProgressBar(20, '#', webName.size());
			for(String title:webName.keySet()){
				value += 1;
				cp.show(value);
				Thread.sleep(100);
				WebPage rootPage = new WebPage(webName.get(title), title);
				WebTree tree = new WebTree(rootPage);
				subPageMap=getSubpage(webName.get(title), filter); //Name, url
				if(subPageMap!=null){
					for(String subPageTitle : subPageMap.keySet()){ 
						WebNode subNode = new WebNode(new WebPage(subPageMap.get(subPageTitle),subPageTitle));
						//subNode.setNodeScore(keywords);
						tree.root.addChild(subNode);
						//nodeList.add(subNode);
						
						HashMap<String,String> subsubPageMap=new HashMap<String,String>();
						subsubPageMap=getSubpage(subPageMap.get(subPageTitle), filter);
						if(subsubPageMap!=null) {
							for(String subsubPageTitle : subsubPageMap.keySet()){ 
								WebNode subsubNode = new WebNode(new WebPage(subsubPageMap.get(subsubPageTitle),subsubPageTitle));
								subNode.addChild(subsubNode);
							}
						}
					}
				}
				tree.setPostOrderScore(keywords);
				nodeList.add(tree.root);
				tree.eularPrintTree();
			}
			Ranking ranking=new Ranking(nodeList);
			ranking.sort();
			nodeList=ranking.getList();
			for(int i=0;i<nodeList.size();i++){
				System.out.println("( "+nodeList.get(i).webPage.name+" , "+nodeList.get(i).webPage.url+" , "+nodeList.get(i).nodeScore+" )");
			}
			
		}
		catch (NullPointerException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String,String> getSubpage(String url, String filter) throws IOException
	{
		try {
			Document doc = Jsoup.connect(url).get();
	        Elements links = doc.select("a");
	        String title="";
	        HashMap<String,String> subpages=new HashMap<String,String>();
	        for (Element link : links){
	        	
	        	if(link.attr("title").indexOf(filter, 0)!= -1)
	        	{
	        		title=link.attr("title");
	        		//System.out.print("   sub: " +link.attr("title") + link.attr("abs:href"));
	        		if(title==""){
	        			if(!link.text().isEmpty()){
	        				title=link.text();
	        				subpages.put(title,link.attr("abs:href"));
	        			}
	        		}
	        		else{
	        			subpages.put(title,link.attr("abs:href"));
	        		}
	            }
	        }
	        return subpages;
	    }
		catch (IOException ex) {
			return null;
	    }
	}
	  
}*/
