/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocktrader;

import java.util.ArrayList;
import stocktrader.StockQuote;

/**
 * Portfolio data structure that holds the stock quotes and pending orders 
 * of a User.
 * @author Shawn
 */
public class Portfolio implements java.io.Serializable{
    
    /**
     * Default constructor of Portfolio.
     */
    public Portfolio() {
        listOfStocks = new OwnedStock();
        orderArray = new ArrayList<Order>();
    }
    
    /**
     * Parameterized constructor that allows input of StockList and orders.
     * @param stocks List of stocks in portfolio and corresponding quantities
     * @param orders List of orders in portfolio.
     */
    public Portfolio(OwnedStock stocks, ArrayList<Order> orders) {
        
        listOfStocks = stocks;
        orderArray = orders;
    }
    
    /**
     * Add a stock to the Portfolio.
     * @param stock StockQuote to add to Portfolio.
     */
    public void addStock(StockQuote stock) {
        listOfStocks.addStock(stock);
    }
    
    /**
     * Remove a Stock from the Portfolio and return it.
     * @param stock StockQuote to be removed.
     * @return StockQuote that was removed.
     */
    public StockQuote removeStock(StockQuote stock) {
        

        //Stock to be deleted
        StockQuote delStock = listOfStocks.searchStock(stock.getSymbol());
        
        //Delete that stock
        listOfStocks.removeStock(stock);
        
        return delStock;
    }
    
    /**
     * Add a pending Order to this User's Portfolio.
     * @param order Order to add to the Portfolio.
     */
    public void addOrder(Order order) {
        //check to see if it is already in there, if so, update it.
        for (int i = 0; i < orderArray.size(); i++)
        {
            //name of the current order
            String currentOrderSymbol = order.getStockQuote().getSymbol();
            
            //Name of the order passed in
            String orderSymbol = orderArray.get(i).getStockQuote().getSymbol();
            
            //compare the current order's name to the name of the one passed in
            if (currentOrderSymbol.equals(orderSymbol))
            {
                //update the quantity
                orderArray.get(i).setQuantity(order.getQuantity());
                orderArray.get(i).setMaxPrice(order.getMaxPrice());
                orderArray.get(i).setMinPrice(order.getMinPrice());
                
                return;
            }
        }
        //otherwise add
        orderArray.add(order);
    }
    
    /**
     * Remove an Order from the User's portfolio.
     * @param order Order to remove.
     */
    public void removeOrder(Order order) {
        orderArray.remove(order);
    }
    
    /**
     * Get the list of pending Orders in the User's portfolio.
     * @return The current list of orders.
     */
    public ArrayList<Order> getOrders() {
        return orderArray;
    }
    
    /**
     * Update the list of Orders.
     * @param orders The updated list of Orders.
     */
    public void setOrders(ArrayList<Order> orders) {
        orderArray = orders;
    }
    
    /**
     * Gets the current list of stocks. Used for updating the PortfolioPanel.
     * @return The current list of owned stocks.
     */
    public OwnedStock getStocks()
    {
        return listOfStocks;
    }
    
    /**
     * Updates the list of owned stocks.
     * @param stocks The updated list of stocks.
     */
    public void setStocks(OwnedStock stocks)
    {
        listOfStocks = stocks;
    }

    private OwnedStock listOfStocks;//List of Stocks in Portfolio
    
    private ArrayList<Order> orderArray;//List of pending Orders in Portfolio
}
