import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HttpsURLConnection;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String userInput = "";
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("User input keyword:");
		userInput = inputScanner.next();
		KeywordList keywords = new KeywordList();

		HashMap<String, String> webName = new HashMap<String, String>(new GoogleQuery(userInput).query());

		ArrayList<WebNode> nodeList = new ArrayList<WebNode>();

		HashMap<String, String> subPageName = new HashMap<String, String>();
		try {
			for(String title : webName.keySet()) {
//				String map = "";
				WebPage rootPage = new WebPage(webName.get(title), title);
				WebTree tree = new WebTree(rootPage);
		/*		subPageName = getSubpage(webName.get(title));

				if(subPageName != null) {
				for(String subTitle : subPageName.keySet()) {
					tree.root.addChild(new WebNode(new WebPage(subPageName.get(subTitle), subTitle)));
//					map+="    ("+ subTitle +","+ subPageName.get(subTitle)+")\n";  
				}
//				System.out.println("subpages of "+ title +":\n"+map);
				}
		*/
				tree.setPostOrderScore(keywords.getKeywordList());
			}
		}catch(Exception e1) {
			
		}
		
		
		
		Ranking ranking=new Ranking(nodeList);
		ranking.sort();
		nodeList=ranking.getList();
		for(int i=0;i<nodeList.size();i++){
			System .out.println("( "+nodeList.get(i).webPage.name+" , "+nodeList.get(i).webPage.url+" , "+nodeList.get(i).nodeScore+" )");
		}
		
	}
	
	public static HashMap<String, String> getSubpage(String url) throws IOException{
		try {
			Document doc = Jsoup.connect(url).get();
	        Elements links = doc.select("a");
	        String title="";
	        String spLinks="\n";
	        HashMap<String,String> subpages=new HashMap<String,String>();
	        int i=0;
	        for (Element link : links){
	        	if(link.attr("abs:href").startsWith(url)&&!link.attr("abs:href").equals(url)&&!link.attr("abs:href").startsWith(url+"#")
	        			&&!link.attr("abs:href").startsWith(url+"/#")&&!link.attr("abs:href").equals(url+"/")&&!link.attr("abs:href").endsWith("jpg")){
	        		//link.attr("title")!=null&&link.attr("abs:href").contains(url+"&")||link.attr("abs:href").contains(url+"/")
	        		title=link.attr("title");
	        		if(title==""){
	        			if(!link.text().isEmpty()){
	        				title=link.text();
	        				subpages.put(title,link.attr("abs:href"));
	        				spLinks+="              ("+title+","+link.attr("abs:href")+")\n";
	        			}
	        		}else{
	        			subpages.put(title,link.attr("abs:href"));
	        			spLinks+="              ("+title+","+link.attr("abs:href")+")\n";
	        		}
	        	}
	        }
		}catch(Exception e1) {
			
		}
        return null;
	}
}
