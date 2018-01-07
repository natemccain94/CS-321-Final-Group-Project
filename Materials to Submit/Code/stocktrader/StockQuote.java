/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocktrader;


/**
 * A class that holds all information given in a stock quote.
 * @author natemccain94
 */
public class StockQuote implements java.io.Serializable{
    
    /**
     * Constructor that is mainly used as a safety feature.
     * It alerts the program (or whatever method needs something
     * from it) that the StockQuote being fetched is invalid.
     */
    public StockQuote()
    {
        this.name = null;
        this.symbol = null;
        this.daysLow = 0;
        this.daysHigh = 0;
        this.yearLow = 0;
        this.yearHigh = 0;
        this.open = 0;
        this.realtime_price = 0;
        this.change_percent = 0;
        this.timeStamp = null;
    }
    
    /**
     * This is the constructor that demonstrates the valid way to initialize
     * a StockQuote object. It takes in all the information about a particular
     * stock quote and stores said information.
     * @param name the name of the company.
     * @param symbol the ticker symbol of the company.
     * @param daysLow the reported lowest price of the most recent day.
     * @param daysHigh the reported highest price of the most recent day.
     * @param yearLow the reported lowest price of the past year.
     * @param yearHigh the reported highest price of the past year.
     * @param open the reported opening price of the most recent day.
     * @param realtime_price the current price of the stock.
     * @param change_percent the current change in price of the stock.
     * @param timeStamp the time at which the stock quote was obtained.
     */
    public StockQuote(String name, String symbol, float daysLow, float daysHigh, float yearLow,
            float yearHigh, float open, float realtime_price, float change_percent, String timeStamp)
    {
        this.name = name;
        this.symbol = symbol;
        this.daysLow = daysLow;
        this.daysHigh = daysHigh;
        this.yearLow = yearLow;
        this.yearHigh = yearHigh;
        this.open = open;
        this.realtime_price = realtime_price;
        this.change_percent = change_percent;
        this.timeStamp = timeStamp;
    }
    
    /**
     * Updates the stock quote daysLow value.
     * @param newDaysLow 
     */
    public void updateDaysLow(float newDaysLow)
    {
        this.daysLow = newDaysLow;
    }
    
    /**
     * Updates the stock quote daysHigh value.
     * @param newDaysHigh 
     */
    public void updateDaysHigh(float newDaysHigh)
    {
        this.daysHigh = newDaysHigh;
    }
    
    /**
     * Updates the stock quote yearLow value.
     * @param newYearLow 
     */
    public void updateYearLow(float newYearLow)
    {
        this.yearLow = newYearLow;
    }
    
    /**
     * Update the stock quote yearHigh value.
     * @param newYearHigh 
     */
    public void updateYearHigh(float newYearHigh)
    {
        this.yearHigh = newYearHigh;
    }
    
    /**
     * Update the stock quote open value.
     * @param newOpen 
     */
    public void updateOpen(float newOpen)
    {
        this.open = newOpen;
    }
    
    /**
     * Update the stock quote realtime_price value.
     * @param new_realtime_price 
     */
    public void update_realtime_price(float new_realtime_price)
    {
        this.realtime_price = new_realtime_price;
    }
    
    /**
     * Update the stock quote change_percent value.
     * @param new_change_percent 
     */
    public void update_change_percent(float new_change_percent)
    {
        this.change_percent = new_change_percent;
    }
    
    /**
     * Update the stock quote timeStamp value.
     * @param newTimeStamp 
     */
    public void update_timeStamp(String newTimeStamp)
    {
        this.timeStamp = newTimeStamp;
    }
    
    /**
     * Get a stock quote name.
     * @return a copy of the stock's name.
     */
    public String getName()
    {
        return new String(this.name);
        //String copy = this.name;
        //return copy;
    }
    
    /**
     * Get the stock's symbol.
     * @return a copy of the stock's symbol.
     */
    public String getSymbol()
    {
        return new String(this.symbol);
        //String copy = this.symbol;
        //return copy;
    }
    
    /**
     * Get the daysLow value.
     * @return a copy of the daysLow value.
     */
    public float getDaysLow()
    {
        return new Float(this.daysLow);
        //float copy = this.daysLow;
        //return copy;
    }
    
    /**
     * Get the daysHigh value.
     * @return a copy of the daysHigh value.
     */
    public float getDaysHigh()
    {
        return new Float(this.daysHigh);
        //float copy = this.daysHigh;
        //return copy;
    }
    
    /**
     * Get the yearLow value.
     * @return a copy of the yearLow value.
     */
    public float getYearLow()
    {
        return new Float(this.yearLow);
        //float copy = this.yearLow;
        //return copy;
    }
    
    /**
     * Get the yearHigh value.
     * @return a copy of the yearHigh value.
     */
    public float getYearHigh()
    {
        return new Float(this.yearHigh);
        //float copy = this.yearHigh;
        //return copy;
    }
    
    /**
     * Get the open value.
     * @return a copy of the open value.
     */
    public float getOpen()
    {
        return new Float(this.open);
        //float copy = this.open;
        //return copy;
    }
    
    /**
     * Get the realtime_price value.
     * @return a copy of the realtime_price value.
     */
    public float get_realtime_price()
    {
        return new Float(this.realtime_price);
        //float copy = this.realtime_price;
        //return copy;
    }
    
    /**
     * Get the change_percent value.
     * @return a copy of the change_percent value.
     */
    public float get_change_percent()
    {
        return new Float(this.change_percent);
        //float copy = this.change_percent;
        //return copy;
    }
    
    /**
     * Get the timeStamp string.
     * @return a copy of the timeStamp string.
     */
    public String get_timeStamp()
    {
        return new String(this.timeStamp);
        //String copy = this.timeStamp;
        //return copy;
    }
    /**
     * This function is used only as a testing function. It prints out
     * every value currently held inside of a StockQuote object.
     */
    public void printQuote()
    {
        System.out.println("Name: " + this.getName());
        System.out.println("Symbol: " + this.getSymbol());
        System.out.println("Day's Low: " + String.valueOf(this.getDaysLow()));
        System.out.println("Day's High: " + String.valueOf(this.getDaysHigh()));       
        System.out.println("Year's Low: " + String.valueOf(this.getYearLow()));               
        System.out.println("Year's High: " + String.valueOf(this.getYearHigh()));    
        System.out.println("Most recent open value: " + String.valueOf(this.getOpen()));               
        System.out.println("Current Stock Price: " + String.valueOf(this.get_realtime_price()));               
        System.out.println("Percentage change in price: " + String.valueOf(this.get_change_percent()) + "%");               
        System.out.println("Time Stamp: " + this.get_timeStamp());
    }
   
    private final String name; // Name of the company
    private final String symbol; // Ticker symbol
    private float daysLow; // Lowest value the most recent trading day
    private float daysHigh; // Highest value the most recent trading day
    private float yearLow; // Lowest value in past year
    private float yearHigh; // Highest value in past year
    private float open; // Opening price on most recent trading day
    private float realtime_price; // Current price/value of the stock
    private float change_percent; // percentage change of the stock's value
    private String timeStamp; // Time stamp of when the stock data was retrieved.
}