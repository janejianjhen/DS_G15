
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class Website
 */
@WebServlet("/Website")
public class Website extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String userInput, filter;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Website() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		
		userInput = request.getParameter("keyword");
		filter = request.getParameter("filter");
	
		
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
			System.out.println(rel);    //印出相關關鍵字
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
		
		request.setAttribute("relate", rel);
		
		String[][] s = new String[nodeList.size()][2];
		request.setAttribute("query", s);
		int num = 0;
		for(int i=0;i<nodeList.size();i++) {
			String key = nodeList.get(i).webPage.name;
			String value_2 = nodeList.get(i).webPage.url;
			s[num][0] = key;
			s[num][1] = value_2;
			num++;
		}
		
		request.getRequestDispatcher("googleitem.jsp").forward(request, response); 
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
}
