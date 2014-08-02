package com.zhadan.crawler;

import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewzhadan on 7/19/14.
 */
public class LinkFinder implements Runnable {

    /**
     * Used fot statistics
     */
    private static final long t0 = System.nanoTime();
    private String url;
    private WebCrawler6 linkHandler;

    public LinkFinder(String url, WebCrawler6 handler) {
        this.url = url;
        this.linkHandler = handler;
    }

    @Override
    public void run() {
        getSimpleLinks(url);
    }

    private void getSimpleLinks(String url) {
        //if not already visited
        if (!linkHandler.visited(url)) {
            try {
                URL uriLink = new URL(url);
                Parser parser = new Parser(uriLink.openConnection());
                NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
                List<String> urls = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {
                    LinkTag extracted = (LinkTag) list.elementAt(i);

                    if (!extracted.getLink().isEmpty()
                            && !linkHandler.visited(extracted.getLink())) {

                        urls.add(extracted.getLink());
                    }

                }
                //we visited this url
                linkHandler.addVisited(url);

                if (linkHandler.size() == 150) {
                    System.out.println("Time to visit 1500 distinct links = " + (System.nanoTime() - t0));
                }

                for (String l : urls) {
                    linkHandler.queueLink(l);
                }

            } catch (Exception e) {
                //ignore all errors for now
            }
        }
    }
}
