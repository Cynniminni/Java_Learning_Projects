/*
 * [June 21, 2015]
 * "RSS Feed Creator – A program which can read in text from other sources 
 * and put it in RSS or Atom news format for syndication."
 * 
 * Source: http://www.dreamincode.net/forums/topic/78802-martyr2s-mega-project-ideas-list/
 * Tutorial: http://www.vogella.com/tutorials/RSSFeed/article.html
 */

package RSSFeedClasses;

//this class represents an RSS message
public class FeedMessage {
	String title;
	String description;
	String link;
	String author;
	String guid;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getGuid() {
		return guid;
	}
	
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	@Override
	public String toString() {
		return "FeedMessage [title=" + title
				+ ", description=" + description
				+ ", link=" + link
				+ ", author=" + author
				+ ", guid=" + guid
				+ "]";
	}
}
