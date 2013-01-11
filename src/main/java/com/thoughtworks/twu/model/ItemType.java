package com.thoughtworks.twu.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemType {


    public enum TYPE {
        FRAME,
        ACCESSORIES
    }

    public List getTypes() {
        List<String> types = new ArrayList<String>();
        for (TYPE type: TYPE.values()){
        types.add(type.toString());
        }
        return types;
    }
}
