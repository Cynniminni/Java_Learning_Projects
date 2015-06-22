/*
 * [June 21, 2015]
 * "RSS Feed Creator – A program which can read in text from other sources 
 * and put it in RSS or Atom news format for syndication."
 * 
 * Source: http://www.dreamincode.net/forums/topic/78802-martyr2s-mega-project-ideas-list/
 * Tutorial: http://www.vogella.com/tutorials/RSSFeed/article.html
 */

package RSSFeedClasses;

import java.util.ArrayList;
import java.util.List;

//this class stores an RSS feed
public class Feed {
	final String	title,
					link,
					description,
					language,
					copyright,
					pubDate;
	
	final List<FeedMessage> entries = new ArrayList<FeedMessage>();
	
	public Feed(String title, String link, String desc, String lang, String copyright, String pubDate) {
		//initialize final class variables here
		this.title = title;
		this.link = link;
		this.description = desc;
		this.language = lang;
		this.copyright = copyright;
		this.pubDate = pubDate;
	}
	
	public List<FeedMessage> getMessages() {
		return entries;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getLink() {
		return link;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getCopyright() {
		return copyright;
	}
	
	public String getPubDate() {
		return pubDate;
	}
	
	@Override
	public String toString() {
		return "Feed [copyright=" + copyright 
				+ ", description=" + description
				+ ", language=" + language
				+ ", link=" + link
				+ ", pubDate=" + pubDate
				+ ", title=" + title
				+ "]";
	}
}//end class
