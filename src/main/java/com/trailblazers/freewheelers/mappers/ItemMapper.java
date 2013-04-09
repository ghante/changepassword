package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.Item;
import org.apache.ibatis.annotations.*;

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

    @Delete(
        "DELETE FROM item WHERE item_id = #{itemId}"
    )
    void delete(Item item);

    @Update(
        "UPDATE item " +
        "SET description=#{description}, name=#{name}, price=#{price}, type=#{type}, quantity=#{quantity} " +
        "WHERE item_id=#{itemId}"
    )
    void update(Item item);

    // List<Item> findAll()
    // List<Item> findItemWithNonZeroQuantity()

    // void reduceQuantityByOne(Item item)

}
