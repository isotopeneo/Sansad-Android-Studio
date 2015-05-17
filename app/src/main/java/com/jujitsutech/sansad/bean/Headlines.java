package com.jujitsutech.sansad.bean;

/**
 * Created by Sujit Devkar on 24-02-2015.
 */
public class Headlines {

    /*
    Bing API news
    "ID": "8684fa04-c6d8-4d77-b31a-60aea7fc602d",
                "Title": "Pain as petitions gather dust in Parliament",
                "Url": "http://www.monitor.co.ug/Magazines/PeoplePower/Pain-as-petitions-gather-dust-in-Parliament/-/689844/2718264/-/y8ycfn/-/index.html",
                "Source": "Daily Monitor",
                "Description": "Backlog. Of the 96 petitions that have been filed with the Ninth Parliament since 2012, only 19 have been handled, debated and adopted, writes Solomon Arinaitwe. Hamurwa Health Centre IV in Rubanda East County, Kabale District, serves a population of more ...",
                "Date": "2015-05-16T22:16:47Z"
     */
    private String headline;
    private String webUrl;
    private String source;
    private String snippet;
    private String pubDate;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
