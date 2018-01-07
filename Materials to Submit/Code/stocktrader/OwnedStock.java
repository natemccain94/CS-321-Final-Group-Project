/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocktrader;

import java.util.Map;
import java.util.HashMap;
import stocktrader.StockList;
import stocktrader.StockQuote;

/**
 * A subclass of StockList that has quantities associated with each stock.
 * @author Shawn
 */
public class OwnedStock extends StockList implements java.io.Serializable{
    
    /**
     * A list of owned stocks.
     */
    public OwnedStock() 
    {
        stockQuantities = new HashMap<String, Integer>();
        
    }
    
    /**
     * Get the quantity of a particular stock.
     * @param stockname Stock to check.
     * @return Integer quantity of the stock.
     */
    public Integer getQuantity(String stockname) {
        return stockQuantities.get(stockname);
    }
    
    /**
     * Set the quantity of the given stock.
     * @param stock Stock to set the quantity of.
     * @param quantity Number of stock.
     */
    public void setQuantity(StockQuote stock, Integer quantity)
    {
        stockQuantities.put(stock.getName(), quantity);
    }
    
    private Map<String, Integer> stockQuantities;
}

