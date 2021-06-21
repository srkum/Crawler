package WebCrawler;

import java.util.ArrayList;

public class Crawler {

	public static void main(String[] args) {
		ArrayList<MultiLinkCrawler> bfs = new ArrayList<>();
		bfs.add(new MultiLinkCrawler("https://edition.cnn.com/",1));
		bfs.add(new MultiLinkCrawler("https://www.bbc.com/news",2));
		bfs.add(new MultiLinkCrawler("https://www.washingtonpost.com/",3));
		for(MultiLinkCrawler m: bfs) {
			try {
				m.getThread().join();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
}
