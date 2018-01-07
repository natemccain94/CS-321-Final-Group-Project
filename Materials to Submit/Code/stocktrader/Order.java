
package stocktrader;

import stocktrader.StockQuote;

/**
 *The Order class is meant used by the portfolio class to add and remove values
 * and prices associated with creating a new order such as min price, max price,
 * and quantity to purchase.
 * @author Gray-1
 */

public class Order implements java.io.Serializable{
 float minPrice = 0;
    float maxPrice = 0;
    float Quantity = 0;
    StockQuote stock = new StockQuote();
    boolean isBuy = false;
    
    /**
     * A pending order for a stock with an associated quantity and price range values.
     * @param min Min price.
     * @param max Max price.
     * @param quant Quantity to order.
     * @param orderStock Which stock to order.
     * @param buy Whether this is a buy or sell order.
     */
    public Order (float min, float max, float quant, StockQuote orderStock, boolean buy)
    {
        minPrice = min;
        maxPrice = max;
        Quantity = quant;
        stock = orderStock;
        isBuy = buy;
    }
    
    /**
     *This method gets a stock quote from StockQuote.
     * @return The StockQuote associated with this Order.
     */
    public StockQuote getStockQuote()
    {
        
        return stock;
    }
    // this method returns a stock from StockQuote

    /**
     *This sets the current stock quote for the order.
     * @param stock The new version of this Order's associated StockQuote.
     */
    public void setStockQuote(StockQuote newStock)
    {
        
        stock = newStock;
    }
    // this aquires the stock to be returned in getStockQuote

    /**
     *This method determines if the current order is slated to be bought.
     */
    public void setBuy()
    {
       
        if(isBuy)
        {
            isBuy = true;
        }
        else 
        {
            isBuy = false;
        }
        
    }
    // this controlls the state of isBuy, true or false

    /**
     *This method returns the minimum price to be paid for the order.
     * @return The current value of minPrice.
     */
    public float getMinPrice()
    {
        
        return minPrice;
    }
    // this returns the minimum price for a stock

    /**
     *This gets the minimum price to be used in getMinPrice.
     * @param min The new value of minPrice.
     */
    public void setMinPrice(float min)
    {
        
        minPrice = min;
    }
    // this finds the minimum price for a stock

    /**
     *This method determines the maximum price for the order.
     * @return The current value of maxPrice.
     */
    public float getMaxPrice()
    {
        
        return maxPrice;
    }
    // this returns the max price for a stock

    /**
     *This method sets the maximum price to be used in getMaxPrice.
     * @param max The new value for maxPrice.
     */
    public void setMaxPrice(float max)
    {
       
        maxPrice = max;
    }
    // this finds the max price for a stock

    /**
     *This method gets the quantity to be used in getQuantity.
     * @param quantity The quantity to set.
     */
    public void setQuantity(float quantity)
    {
       
        Quantity = quantity; 
    }
    // this aquires the quantity of a stock one wishes to buy

    /**
     *This method determines the quantity of stock for the current order.
     * @return Returns the quantity.
     */
    public float getQuantity()
    {
        return Quantity;
    }
    // this returns the quantity from setQuantity

    /**
     *This method returns the results of the setBuy method, determining if
     * the order is a buy order or not.
     * @return Returns true if this is a buy order.
     */
    public boolean Buy()
    {
        
        return isBuy;
    }
    // this returns the value of isBuy
}
