package com.twu.biblioteca.service;

import com.twu.biblioteca.controller.ItemManager;
import com.twu.biblioteca.controller.UserManager;
import com.twu.biblioteca.model.Item;
import com.twu.biblioteca.model.User;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by carwest on 15-8-2.
 */
public class Library {

    private final UserManager userManager;
    private final ItemManager itemManager;
    private Hashtable<Integer, String> relation;

    public Library(UserManager userManager, ItemManager itemManager) {
        this.userManager = userManager;
        this.itemManager = itemManager;
        relation = new Hashtable<>();
    }

    public Integer getUserCount() {
        return userManager.getUserCount();
    }

    public Integer getItemCount() {
        return itemManager.getItemCount();
    }

    public int getAvailableCountByType(Item.TYPES itemType) {
        return itemManager.getItemListByTypeAndCheckout(itemType, 0).size();
    }

    public boolean checkout(User user, Item item) {
        if ( !relation.containsKey(item.getId()) ) {
            relation.put(item.getId(), user.getUserName());
            item.checkout();
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<Item> getRentListByUserNameAndItemType(String userName, Item.TYPES itemType) {
        ArrayList<Item> items = new ArrayList<>();
        for ( Integer id : relation.keySet() ){
            String tmp = relation.get(id);
            if ( tmp.compareTo(userName) == 0){
                items.add(itemManager.getItemByIdAndType(id, itemType));
            }
        }
        return items;
    }

    public boolean unCheckout(User user, Item _item) {
        if ( relation.containsKey(_item.getId()) ){
            if ( relation.get(_item.getId()).compareTo( user.getUserName() ) == 0 ){
                _item.unCheckout();
                relation.remove(_item.getId());
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public ArrayList<Item> getListByItemType(Item.TYPES itemType) {
        return itemManager.getAvailableItemListByType(itemType);
    }
}
