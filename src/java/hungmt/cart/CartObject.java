/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungmt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class CartObject implements Serializable{
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addBookToCart (String book) {
        //1. Checking existd cart items
        if (this.items == null) {
            this.items = new HashMap<>();
        } //items have exist
        //2.check exist book;
        int quantity = 1;
        if (this.items.containsKey(book)) {
            quantity = this.items.get(book) + 1;
        }
        //3. update items
        this.items.put (book, quantity);
    }
    public void removeBookFromCart (String book) {
        //1. Check exist items 
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(book)) {
            this.items.remove(book);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}
