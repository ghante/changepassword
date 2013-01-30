package com.trailblazers.freeriders.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ItemTypeTest {

    @Test
    public void shouldReturnListOfTypes(){
        ItemType itemType = new ItemType();
        List<String> expectedList = new ArrayList<String>();

        for (ItemType.TYPE type: ItemType.TYPE.values()){
            expectedList.add(type.toString());
        }

        List<String> foundList = itemType.getTypes();
        assertThat(foundList, is(equalTo(expectedList)));
    }
}

