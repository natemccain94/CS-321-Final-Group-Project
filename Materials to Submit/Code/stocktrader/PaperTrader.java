/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocktrader;

import java.io.IOException;


/**
 * This is a test driver for the classes put together by Nate McCain
 * and other credited sources.
 * @author Nate McCain
 * @author Dr. Zhang (Iterator Implementation)
 */
public class PaperTrader {
    public static void main(String[] args) throws IOException {
        String testOne = "pg"; // Proctor & Gamble
        String testTwo = "MsFT"; // Microsoft
        
        StockList myList = new StockList();
        myList.printList(); // This should not print anything.
        System.out.println(); // Print a newline.
        
        myList.addStock(testOne); // Add Proctor & Gamble to the StockList
        myList.printList(); // Print information about P&G
        System.out.println(); // Print a newline.
        
        myList.addStock(testTwo); // Add Microsoft to the StockList
        myList.printList(); // Print information about Microsoft and P&G
        System.out.println(); // Print a newline
        
        myList.addStock("BOEEE"); // This is not a real stock symbol.
        myList.printList(); // This should only print info about MSFT and PG
        System.out.println(); // Print a newline
        
        myList.removeStock(testTwo); // Remove Microsoft from StockList
        myList.printList(); // Only print information on P&G
        System.out.println(); // Print a newline
        
        myList.addStock(testOne); // Should just update P&G
        myList.printList(); // Only print information on P&G
        System.out.println(); // Print a newline
        
        myList.updateStockList(); // Should update P&G again.
        myList.printList(); // Only print information on P&G
        System.out.println();
        
        myList.addStock("AAPL"); // Add Apple to StockList
        myList.addStock("YHOO"); // Add Yahoo! to StockList
        myList.addStock("TEK"); // This is not a stock symbol
  
        // Iterator implementation.
        // From Dr. Zhang's demonstration of the Iterator design pattern.
        Iterator iterator = myList.stepThrough();
        while (iterator.hasNext())
        {
            // Print all of the stocks in the StockList
            StockQuote individual = (StockQuote)iterator.next();
            individual.printQuote();
            System.out.println();
        }
    }
}