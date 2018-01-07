/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocktrader;
import java.util.NoSuchElementException;
/**
 * This was taken from the Iterator2 demonstration from class.
 * @author Dr. Zhang
 */
public interface Iterator {
    public Object next()throws NoSuchElementException;
    public boolean hasNext();
}
