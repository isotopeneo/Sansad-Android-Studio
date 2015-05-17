package com.jujitsutech.sansad.contenthandler;

import com.jujitsutech.sansad.bean.Headlines;
import com.jujitsutech.sansad.util.LoggerClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sujit Devkar on 24-02-2015.
 */
public class HeadlinesHandler {

    /*

    //Bing API news json result

    {
    "d": {
        "results": [
            {
                "__metadata": {
                    "uri": "https://api.datamarket.azure.com/Data.ashx/Bing/Search/News?Query='parliament'&Market='en-IN'&NewsSortBy='Date'&$skip=0&$top=1",
                    "type": "NewsResult"
                },
                "ID": "8684fa04-c6d8-4d77-b31a-60aea7fc602d",
                "Title": "Pain as petitions gather dust in Parliament",
                "Url": "http://www.monitor.co.ug/Magazines/PeoplePower/Pain-as-petitions-gather-dust-in-Parliament/-/689844/2718264/-/y8ycfn/-/index.html",
                "Source": "Daily Monitor",
                "Description": "Backlog. Of the 96 petitions that have been filed with the Ninth Parliament since 2012, only 19 have been handled, debated and adopted, writes Solomon Arinaitwe. Hamurwa Health Centre IV in Rubanda East County, Kabale District, serves a population of more ...",
                "Date": "2015-05-16T22:16:47Z"
            },

     */
    private static final String NYT_RESPONSE = "response";
    private static final String NYT_DOCS = "docs";
    private static final String NYT_HEADLINE = "headline";

    private static final String NYT_WEB_URL = "web_url";
    private static final String NYT_SNIPPET = "snippet";
    private static final String NYT_PRINT_HEADLINE = "print_headline";
    private static final String NYT_SOURCE = "source";
    private static final String NYT_PUB_DATE = "pub_date";

    // Bing API news keys
    private static final String BING_D = "d";
    private static final String BING_RESULTS = "results";
    private static final String BING_TITLE = "Title";
    private static final String BING_URL = "Url";
    private static final String BING_SOURCE = "Source";
    private static final String BING_DESCRIPTION = "Description";
    private static final String BING_DATE = "Date";

    public List<Headlines> parseContent(String input) {
        LoggerClass.log(" json is " + input);

        return parseBingAPINews(input);

        /*

        if (!input.equals("")) {
            try {
                JSONObject newsJSON = new JSONObject(input);
                JSONObject response = newsJSON.getJSONObject(NYT_RESPONSE);

                JSONArray newsArray = response.getJSONArray(NYT_DOCS);

                List<Headlines> headlines = new ArrayList<>();
                for (int i = 0; i < newsArray.length(); i++) {
                    try {
                        Headlines headline = new Headlines();

                        JSONObject newsItem = newsArray.getJSONObject(i);

                        headline.setWebUrl(newsItem.getString(NYT_WEB_URL));
                        headline.setSnippet(newsItem.getString(NYT_SNIPPET));
                        headline.setSource(newsItem.getString(NYT_SOURCE));
                        headline.setPubDate(newsItem.getString(NYT_PUB_DATE));

                        JSONObject headlineObject = newsItem.getJSONObject(NYT_HEADLINE);
                        headline.setHeadline(headlineObject.getString(NYT_PRINT_HEADLINE));

                        headlines.add(headline);
                    } catch (JSONException e) {
                        LoggerClass.log(e.toString());
                        continue;
                    }
                }
                return headlines;
            } catch (JSONException e) {
                LoggerClass.log(e.toString());
            }
        }

        return null;
        */
    }

    private List<Headlines> parseBingAPINews(String input) {
        if (!input.equals("")) {
            try {
                JSONObject newsJSON = new JSONObject(input);
                JSONArray newsArray = newsJSON.getJSONObject(BING_D).getJSONArray(BING_RESULTS);

                List<Headlines> headlines = new ArrayList<>();
                for (int i = 0; i < newsArray.length(); i++) {
                    try {

                        JSONObject newsItem = newsArray.getJSONObject(i);

                        Headlines headline = new Headlines();
                        headline.setWebUrl(newsItem.getString(BING_URL));
                        headline.setSnippet(newsItem.getString(BING_DESCRIPTION));
                        headline.setSource(newsItem.getString(BING_SOURCE));
                        headline.setPubDate(newsItem.getString(BING_DATE));
                        headline.setHeadline(newsItem.getString(BING_TITLE));

                        headlines.add(headline);
                    } catch (JSONException e) {
                        continue;
                    }
                }
                return headlines;
            } catch (JSONException e) {
                LoggerClass.log(e.toString());
            }
        }
        return null;
    }
}
