/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocktrader;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that holds an ArrayList of stock quotes.
 * @author Nate McCain
 * @author Dr. Zhang (Iterator Design Pattern implementation)
 */
public class StockList implements java.io.Serializable{
    /**
     * Constructs an empty list of stock quotes.
     * It is an ArrayList made up of StockQuotes.
     */
    public StockList()
    {
        this.stockQueue = new ArrayList<StockQuote>();
    }
    
    /**
     * Function that checks if the StockList is empty.
     * @return true if empty, false if not empty.
     */
    public boolean isListEmpty()
    {
        return this.stockQueue.isEmpty();
    }
    
    /**
     * Function that gets the size of the StockList.
     * @return integer value of the StockList's size.
     */
    public int getListSize()
    {
        return this.stockQueue.size();
    }
    
    /**
     * Function that returns a StockQuote from a specified index.
     * It is only used by other StockList functions.
     * @param index
     * @return StockQuote at index (if valid), or null if the index
     * is out of bounds.
     */
    private StockQuote returnStock(int index)
    {
        // If the index is out of bounds, return null.
        if (index >= this.getListSize())
        {
            return null;
        }
        else
        {
            return this.stockQueue.get(index);
        }
    }
    
    /**
     * A function that is only used by other StockList functions.
     * @param symbol of the stock you are looking for.
     * @return index of the stock's location, or -1 if not found.
     */
    private int locationOfStock(String symbol)
    {
        for (int i = 0; i < this.getListSize(); i++)
        {
            if (symbol.equalsIgnoreCase(this.returnStock(i).getSymbol()) == true)
            {
                return i;
            }
        }
        return -1;
    }
    
    /**
       Looks for a stock in the list by the stock's symbol.
       @param symbol the symbol of the stock being searched.
       @return stock if found, null if not.
     */
    public StockQuote searchStock(String symbol)
    {
        int index = this.locationOfStock(symbol);
        // The stock exists in this StockList, index indicates its location.
        if (index != -1)
        {
            StockQuote helper = this.stockQueue.get(index);
            return helper;
        }
        // The stock does not exist in this StockList, return the null StockQuote.
        else
        {
            return null;
        }
    }
    
    /**
     * Adds a stock to the list alphabetically (by stock symbol).
     * This method takes in a StockQuote and inserts it into the StockList.
     * If the stock is already in the StockList, then it replaces that stock.
     * @param stock that is to be added. 
     */
    public  void addStock(StockQuote stock)
    {
        // If there are no elements in the StockList
        if (this.isListEmpty() == true)
        {
            this.stockQueue.add(stock);
        }
        // If there are elements in the StockList
        else
        {
            // This makes sure every stock is listed alphabetically by symbol.
            String newStockSymbol = new String(stock.getSymbol());
            boolean foundPlace = false;
            int helperValue;
            for (int i = 0; i < this.getListSize() && foundPlace == false; i++)
            {
                helperValue = newStockSymbol.compareToIgnoreCase(this.stockQueue.get(i).getSymbol());
                if (helperValue < 0)
                {
                    this.stockQueue.add(i, stock);
                    foundPlace = true;
                }
                else if (helperValue == 0)
                {
                    // Replace the stock at this position.
                    this.stockQueue.set(i, stock);
                    foundPlace = true;
                }
                else
                {
                    // Do nothing.
                }
            }
            // If the new stock should be added to the end of the StockList
            if (foundPlace == false)
            {
                this.stockQueue.add(stock);
            }
        }
    }
    
    /**
     * This function attempts to add a StockQuote to the current StockList.
     * This function does not return the result of searching for the stock 
     * online.
     * @param stockSymbol to be searched online.
     * @throws IOException
     */
    public void addStock(String stockSymbol) throws IOException
    {
        String updater = yahooConnectForStockInfo.successfulStockFetch(stockSymbol);
        // If the stock quote fetched is valid
        if (!updater.equalsIgnoreCase("false"))
        {
            this.addStock(yahooConnectForStockInfo.parseStockValues(updater));
        }
    }
    
    /**
     * This function attempts to add a StockQuote to the current StockList.
     * @param stockSymbol to be searched online.
     * @return true if a valid StockQuote is found, false if not.
     * @throws IOException
     */
    public boolean addStockIfFound(String stockSymbol) throws IOException
    {
        String updater = yahooConnectForStockInfo.successfulStockFetch(stockSymbol);
        // If the stock quote fetched is valid
        if (!updater.equalsIgnoreCase("false"))
        {
            this.addStock(yahooConnectForStockInfo.parseStockValues(updater));
            return true;
        }
        // If an invalid stock quote is fetched
        else
        {
            return false;
        }
    }
    
    /**
     * Removes a specific stock from the list of stocks based on the stock symbol.
     * @param stockSymbol symbol of stock to be removed from the StockList.
     */
    public void removeStock(String stockSymbol)
    {
        int doesExistInList = this.locationOfStock(stockSymbol);
        if (doesExistInList != -1)
        {
            this.stockQueue.remove(doesExistInList);
        }
    }
    
    /**
     * Makes a call to removeStock(String stockSymbol) and passes
     * stock.getSymbol() as the parameter.
     * @param stock to be removed from the StockList.
     */
    public void removeStock(StockQuote stock)
    {
        this.removeStock(stock.getSymbol());
    }
    
    /**
     * Function that updates every stock in the StockList.
     * @throws IOException
     */
    public void updateStockList() throws IOException
    {
        String helperSymbol;
        String updater;
        for (int i = 0; i < this.getListSize(); i++)
        {
            helperSymbol = this.returnStock(i).getSymbol(); // The symbol for the stock to replace.
            updater = yahooConnectForStockInfo.successfulStockFetch(helperSymbol); // Get update on the stock.
            // If the updater retrieves a valid stock from yahoo finance
            if (!updater.equalsIgnoreCase("false"))
            {
                // Get the updated StockQuote
                StockQuote replacementStock = yahooConnectForStockInfo.parseStockValues(updater);
                this.addStock(replacementStock); // Put this in the StockList.
            }
        }
    }
    
    /**
     * Function that calls updateStockList() using the parameter "list" as the
     * calling object.
     * @param list, of StockList type.
     * @return updated list.
     * @throws IOException
     */
    public static StockList updateStockList(StockList list) throws IOException
    {
        list.updateStockList();
        return list;
    }
    
    /**
     * This private class implements the iterator design pattern for the StockList.
     * @author Dr. Zhang (from the class demonstration of the Iterator Design Pattern).
     */
    public static class StepThrough implements Iterator
    {
        private StockList copyOfStockList;
        private int index; // The index for iteration.
        
        /**
         * Constructor for the StepThrough class for iteration
         * of a StockList.
         * @param originalStockList the StockList to be iterated through.
         */
        StepThrough(StockList originalStockList)
        {
            copyOfStockList = originalStockList;
            index = 0;
        }
        
        /**
         * Function that checks to see if the iterator is at the end
         * of the StockList.
         * @return true if not at end of StockList, false if otherwise.
         */
        public boolean hasNext()
        {
            return (index < copyOfStockList.getListSize());
        }
        
        /**
         * Overriden function from the Iterator interface. It returns
         * the next object in the StockList, and then increments the 
         * iterator's index.
         * @return StockQuote at current index location.
         */
        @Override
        public Object next()
        {
            return copyOfStockList.returnStock(index++);
        }
    }
    
    /**
     * This is the iterator implementation that steps through an entire
     * StockList.
     * @return the result of the StepThrough of the current item.
     * @author Dr. Zhang (from the class demonstration of the Iterator Design Pattern).
     */
    public Iterator stepThrough()
    {
        return new StepThrough(this);
    }
    
    /**
     * This function is only used as a testing function. It makes a call
     * to StockQuote's print function for every StockQuote inside of 
     * StockList.
     */
    public void printList()
    {
        for (int i = 0; i < this.getListSize(); i++)
        {
            this.returnStock(i).printQuote();
        }
    }
    
    private ArrayList<StockQuote> stockQueue; // This holds all of the StockQuotes.
}