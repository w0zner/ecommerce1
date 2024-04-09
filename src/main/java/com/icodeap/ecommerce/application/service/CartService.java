package com.icodeap.ecommerce.application.service;

import com.icodeap.ecommerce.domain.ItemCart;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartService {

    private List<ItemCart> itemCarts;
    private HashMap<Integer, ItemCart> itemCartHashMap;

    public CartService() {
        this.itemCarts = new ArrayList<>();
        this.itemCartHashMap = new HashMap<>();
    }

    public void addItemCart(Integer quantity, Integer idProduct, String nameProduct, BigDecimal price) {
        ItemCart item = new ItemCart(idProduct, nameProduct, quantity, price);
        itemCartHashMap.put(item.getIdProduct(), item);
        fillList();
    }

    public BigDecimal getTotalCart() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemCart itemCart: itemCarts) {
            total = total.add(itemCart.getTotalPriceItem());
        }
        return total;
    }

    public void removeItemCart(Integer idProduct){
        itemCartHashMap.remove(idProduct);
        fillList();
    }

    public void removeAllItemCart() {
        itemCartHashMap.clear();
        itemCarts.clear();
    }

    private void fillList() {
        itemCarts.clear();
        itemCartHashMap.forEach(((integer, itemCart) -> itemCarts.add(itemCart)));
    }

    //Para mirar por consola
    public List<ItemCart> getItemCarts(){
        return itemCarts;
    }
}
