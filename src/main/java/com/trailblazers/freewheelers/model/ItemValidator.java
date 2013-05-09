package com.trailblazers.freewheelers.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;


public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Item.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required", "Please enter Item Name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "price.required", "Please enter Item Price");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "quantity.required", "Please enter Item Quantity");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "type.required", "Please enter Item Type");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required", "Please enter Item Description");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "price.required", "Please enter Item Price");

        Item item = (Item) o;

        if((item.getPrice() != null) && item.getPrice().compareTo(BigDecimal.valueOf(100000.00)) != -1) {
            errors.rejectValue("price", "price.toohigh", "must be less than or equal to 99999");
        }
    }
}
