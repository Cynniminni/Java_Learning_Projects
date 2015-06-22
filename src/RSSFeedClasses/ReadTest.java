/*
 * [June 21, 2015]
 * "RSS Feed Creator – A program which can read in text from other sources 
 * and put it in RSS or Atom news format for syndication."
 * 
 * Source: http://www.dreamincode.net/forums/topic/78802-martyr2s-mega-project-ideas-list/
 * Tutorial: http://www.vogella.com/tutorials/RSSFeed/article.html
 */

package RSSFeedClasses;

public class ReadTest {

	public static void main(String[] args) {
		//RSSFeedParser parser = new RSSFeedParser("http://www.vogella.com/article.rss");
		RSSFeedParser parser = new RSSFeedParser("http://yummygreentea.tumblr.com/rss");
		Feed feed = parser.readFeed();
		System.out.println(feed);
		
		for (FeedMessage message : feed.getMessages()) {
			System.out.println(message);
		}//end for
	}//end main
}//end class
