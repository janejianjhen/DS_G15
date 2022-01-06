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

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String userInput = "";
		Scanner inputScanner = new Scanner(System.in);
		userInput = inputScanner.next();
		KeywordList keywords = new KeywordList();
		
		HashMap<String, String> webName = new HashMap<String, String>(new GoogleQuery(userInput).query());
		
		//ArrayList<WebNode> nodeList = new ArrayList<WebNode>();
		
		HashMap<String, String> subPageName = new HashMap<String, String>();
		
		for(String title : webName.keySet()) {
			WebPage rootPage = new WebPage(webName.get(title), title);
			WebTree tree = new WebTree(rootPage);
			//subPageName = getSubpage(webName.get(title));
			if(subPageName != null) {
				for(String subTitle : subPageName.keySet()) {
					tree.root.addChild(new WebNode(new WebPage(subPageName.get(subTitle), subTitle)));
				}
			}

			tree.setPostOrderScore(keywords.getKeywordList());
		}
		
	}
	
	public static HashMap<String, String> getSubpage(String url) throws IOException{
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
        return null;
	}
}
