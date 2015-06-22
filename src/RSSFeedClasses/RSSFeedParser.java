/*
 * [June 21, 2015]
 * "RSS Feed Creator – A program which can read in text from other sources 
 * and put it in RSS or Atom news format for syndication."
 * 
 * Source: http://www.dreamincode.net/forums/topic/78802-martyr2s-mega-project-ideas-list/
 * Tutorial: http://www.vogella.com/tutorials/RSSFeed/article.html
 */

package RSSFeedClasses;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public class RSSFeedParser {

	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String LANGUAGE = "language";
	static final String COPYRIGHT = "copyright";
	static final String LINK = "link";
	static final String AUTHOR = "author";
	static final String ITEM = "item";
	static final String PUB_DATE = "pubDate";
	static final String GUID = "guid";
	
	final URL url;
	
	public RSSFeedParser(String feedURL) {//constructor
		try {
			//initialize url here
			this.url = new URL(feedURL);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Feed readFeed() {
		Feed feed = null;
		
		try {
			boolean isFeedHeader = true;
			
			//initialize headers to empty strings
			String description = "";
			String title = "";
			String link = "";
			String language = "";
			String copyright = "";
			String author = "";
			String pubdate = "";
			String guid = "";
			
			//setup xml reader
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			
			//read xml document
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				
				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName().getLocalPart();
					
					switch (localPart) {
					case ITEM://create a feed of RSS messages
						if (isFeedHeader) {
							isFeedHeader = false;
							feed = new Feed(title, link, description, language, copyright, pubdate);
							event = eventReader.nextEvent();
						}//end inner if
						break;
					case TITLE:
						title = getCharacterData(event, eventReader);
						break;
					case DESCRIPTION:
						description = getCharacterData(event, eventReader);
						break;
					case LINK:
						link = getCharacterData(event, eventReader);
						break;
					case GUID:
						guid = getCharacterData(event, eventReader);
					case LANGUAGE:
						language = getCharacterData(event, eventReader);
						break;
					case AUTHOR:
						author = getCharacterData(event, eventReader);
						break;
					case PUB_DATE:
						pubdate = getCharacterData(event, eventReader);
						break;
					case COPYRIGHT:
						copyright = getCharacterData(event, eventReader);
						break;
					}//end switch
				} else if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart() == ITEM) {
						FeedMessage message = new FeedMessage();
						message.setAuthor(author);
						message.setDescription(description);
						message.setGuid(guid);
						message.setLink(link);
						message.setTitle(title);
						feed.getMessages().add(message);
						event = eventReader.nextEvent();
						continue;
					}//end inner if
				}//end outer if
			}//end while
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}//end catch
		
		return feed;
	}
	
	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) 
		throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		
		return result;
	}
	
	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}//end try
	}
}//end class
