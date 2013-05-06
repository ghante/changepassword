package com.trailblazers.freewheelers.model;

import java.util.ArrayList;
import java.util.List;

public class ItemValidation {

    public static List<String> validate(Item item) {
        ArrayList errors = new ArrayList();

        if(item.getName() == null || item.getName().isEmpty()) {
            errors.add("Please enter Item Name");
        }

        if(item.getDescription() == null) {
            errors.add("Please enter Item Description");
        }

        if (item.getQuantity() == null) {
            errors.add("Please enter Item Quantity");
        }

        if(item.getType() == null || item.getType().isEmpty()) {
            errors.add("Please select Item Type");
        }

        if (item.getPrice() == null) {
            errors.add("Please enter Item Price");
        } else {
            if(item.getPrice().longValue() > 99999) {
                errors.add("must be less than or equal to 99999");
            }
        }


        return errors;
    }
}
