package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.Item;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface ItemMapper {

    @Insert(
        "INSERT INTO item (description, name, price, type, quantity) " +
        "VALUES (#{description}, #{name}, #{price}, #{type}, #{quantity})"
    )
    @Options(keyProperty = "itemId", keyColumn = "item_id", useGeneratedKeys = true)
    void insert(Item item);

    @Select(
        "SELECT item_id as itemId, description, name, price, type, quantity " +
        "FROM item " +
        "WHERE item_id = #{itemId}"
    )
    Item get(Long itemId);

    // void delete(Item item)
    // List<Item> findAll()
    // List<Item> findItemWithNonZeroQuantity()
    // void update(Item item)
    // void reduceQuantityByOne(Item item)

}
