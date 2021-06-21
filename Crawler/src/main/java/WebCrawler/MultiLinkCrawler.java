package WebCrawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class MultiLinkCrawler implements Runnable{

		private static final int MAX_DEPTH = 2;
		private Thread thread;
		private String first_link;
		private ArrayList<String> visitedLinks = new ArrayList<String>();
			
		public MultiLinkCrawler(String link, int num) {
			first_link = link;
			thread = new Thread(this);
			thread.start();
		}
		
		public void run() {
			crawl(1, first_link);
		}
		
		public void crawl(int level, String url) {
			try {
			if(level <= MAX_DEPTH) {				
				Connection conn = Jsoup.connect(url);
				Document doc =conn.get();
				if(conn.response().statusCode() == 200) {
					for(Element link: doc.select("a[href]")) {
						String next_link = link.absUrl("href");
						System.out.println("Links : "+url);	
						if(link.text().contentEquals("Climate"))
							System.out.println("text : " + link.text());
						visitedLinks.add(url);		
						if(visitedLinks.contains(next_link) == false) {
							 crawl(level++, next_link);
						}	
					
					}
				
				}
				
				}	
			} catch(IOException e) {
				System.err.println(e.getMessage());			
			}
		}	
				

		public Thread getThread() {
			return thread;
		}
	}

