/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocktrader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * This class contains the methods for gathering information about a stock.
 * @author natemccain94
 */
public class yahooConnectForStockInfo implements java.io.Serializable{
    /**
     * This function tests to see if the String passed in contains a valid stock
     * quote (delimited by xml containers).
     * @param xmlString
     * @return false if the info in xmlString is not valid, true if it is valid.
     */
    public static boolean validStockFetch(String xmlString)
    {
        // Any time "<.../>" is found, it means the current xml file does not
        // have a valid stock quote fetched.
        int validityChecker;
        validityChecker = xmlString.indexOf("<Name/>");
        if (validityChecker != -1)
        {
            return false;
        }
        validityChecker = xmlString.indexOf("<Symbol/>");
        if (validityChecker != -1)
        {
            return false;
        }
        validityChecker = xmlString.indexOf("<DaysLow/>");
        if (validityChecker != -1)
        {
            return false;
        }
        validityChecker = xmlString.indexOf("<DaysHigh/>");
        if (validityChecker != -1)
        {
            return false;
        }
        validityChecker = xmlString.indexOf("<YearLow/>");
        if (validityChecker != -1)
        {
            return false;
        }
        validityChecker = xmlString.indexOf("<YearHigh/>");
        if (validityChecker != -1)
        {
            return false;
        }
        validityChecker = xmlString.indexOf("<LastTradePriceOnly/>");
        if (validityChecker != -1)
        {
            return false;
        }
        validityChecker = xmlString.indexOf("<Open/>");
        if (validityChecker != -1)
        {
            return false;
        }
        validityChecker = xmlString.indexOf("<ChangeinPercent/>");
        if (validityChecker != -1)
        {
            return false;
        }
        return true;
    }

    /**
     * This function makes a call to the Yahoo yql API. It then puts the resulting XML file
     * into a string and uses it as the parameter in a call to validStockFetch(). If the
     * validStockFetch() returns true, it will return the XML file stuff as a string. 
     * If validStockFetch() returns false, it will return the string "false".
     * @param stockTickerToRetrieve
     * @return XML file as a string (if the information is a valid stock quote),
     * the string "false" if it is not.
     * @throws MalformedURLException
     * @throws IOException 
     * @author Kocsen Chung (for the method of making a RESTful call from Java to YQL.
     * @author Nate McCain (modified Kocsen Chung's java file to fit the application's
     * needs).
     */
    public static String successfulStockFetch(String stockTickerToRetrieve) throws MalformedURLException, IOException
    {
        // The connection to Yahoo Finance from Java using a RESTful call and how
        // to retrieve the xml output was written by Kocsen Chung. It is available
        // to view in its original version at: https://gist.github.com/kocsenc/fd7febfda2f6eb8dffb4.
        
        // Most of the parts have been modified in one way or another.
        stockTickerToRetrieve = stockTickerToRetrieve.toUpperCase();
        
        String urlPartOne = "https://query.yahooapis.com/v1/public/yql?q=select%20Ask%2C%20Bid%2C%20DaysLow%2C%20DaysHigh%2C%20YearLow%2C%20YearHigh%2C%20LastTradePriceOnly%2C%20Name%2C%20Open%2C%20PreviousClose%2C%20ChangeinPercent%2C%20Symbol%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22";
        String urlPartTwo = "%22)&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        String searchURL = urlPartOne + stockTickerToRetrieve + urlPartTwo;
        
        // Create the URL and open the connection.
        URL yahooSearchURL = new URL(searchURL);
        HttpURLConnection connection = (HttpURLConnection) yahooSearchURL.openConnection();
        // Set the HTTP Request type method to GET.
        connection.setRequestMethod("GET");
        // Create a BufferedReader to read the response of the Http Request.
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String helpToPutResponseInStringForm;
        StringBuilder xmlStuff = new StringBuilder();
        // The while loop makes sure we get all information into one string.
        while ((helpToPutResponseInStringForm = input.readLine()) != null)
        {
            xmlStuff.append(helpToPutResponseInStringForm);
        }
        // Close the connection.
        input.close();
        // End of part that was written by Kocsen Chung (that we modified to make more specific).
        
        // Now we make a specific call to see if we received valid data, or if the ticker symbol
        // does not correspond with any company names.
        boolean isThisStockReal = validStockFetch(xmlStuff.toString());
        if (isThisStockReal == false)
        {
            return "false";
        }
        else
        {
            return xmlStuff.toString();
        }
        
    }
    
    /**
     * This function parses the xml file output and gets all of the necessary 
     * information to make a StockQuote object. It is only called if the 
     * validStockFetch() call returns true for the testing xml file.
     * @param xmlString, string representing an XML file.
     * @return StockQuote with information found inside the xml string file.
     */
    public static StockQuote parseStockValues(String xmlString)
    {
        // The information we need is between the delimiters:
        // "<descriptor>......</descriptor>"
        int startIndex = xmlString.indexOf("<Name>");
        int endIndex = xmlString.indexOf("</Name>");
        String stockName = xmlString.substring(startIndex + 6, endIndex);
        
        startIndex = xmlString.indexOf("<Symbol>");
        endIndex = xmlString.indexOf("</Symbol>");
        String stockSymbol = xmlString.substring(startIndex + 8, endIndex); 
        
        startIndex = xmlString.indexOf("<DaysLow>");
        endIndex = xmlString.indexOf("</DaysLow>");
        String stockDaysLow = xmlString.substring(startIndex + 9, endIndex);
        Float daysLow = Float.parseFloat(stockDaysLow);
        
        startIndex = xmlString.indexOf("<DaysHigh>");
        endIndex = xmlString.indexOf("</DaysHigh>");
        String stockDaysHigh = xmlString.substring(startIndex + 10, endIndex);
        Float daysHigh = Float.parseFloat(stockDaysHigh);
        
        startIndex = xmlString.indexOf("<YearLow>");
        endIndex = xmlString.indexOf("</YearLow>");
        String stockYearLow = xmlString.substring(startIndex + 9, endIndex);
        Float yearLow = Float.parseFloat(stockYearLow);
        
        startIndex = xmlString.indexOf("<YearHigh>");
        endIndex = xmlString.indexOf("</YearHigh>");
        String stockYearHigh = xmlString.substring(startIndex + 10, endIndex);
        Float yearHigh = Float.parseFloat(stockYearHigh);
        
        startIndex = xmlString.indexOf("<LastTradePriceOnly>");
        endIndex = xmlString.indexOf("</LastTradePriceOnly>");
        String stockPrice = xmlString.substring(startIndex + 20, endIndex);
        Float price = Float.parseFloat(stockPrice);
        
        startIndex = xmlString.indexOf("<Open>");
        endIndex = xmlString.indexOf("</Open>");
        String stockOpen = xmlString.substring(startIndex + 6, endIndex);
        Float open = Float.parseFloat(stockOpen);
        
        startIndex = xmlString.indexOf("<ChangeinPercent>");
        endIndex = xmlString.indexOf("</ChangeinPercent>");
        String stockChgInPercent = xmlString.substring(startIndex + 17, endIndex - 1);
        Float change_percent = Float.parseFloat(stockChgInPercent);
        
        // Get the timestamp from the system.
        String timeStamp = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss, zzzz").format(new Date());
        // Make a StockQuote
        StockQuote resultOfParse = new StockQuote(stockName, stockSymbol, daysLow, daysHigh, yearLow, yearHigh, open, price, change_percent, timeStamp);
        return resultOfParse;
    }
}
