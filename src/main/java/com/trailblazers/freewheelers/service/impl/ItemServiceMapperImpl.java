package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.ItemMapper;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.persistence.MyBatisUtil;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.web.ItemCommand;
import com.trailblazers.freewheelers.web.ItemGrid;
import org.apache.ibatis.session.SqlSession;

public class ItemServiceMapperImpl implements ItemService {

    private final SqlSession sqlSession;
    private final ItemMapper itemMapper;

    public ItemServiceMapperImpl() {
        sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        itemMapper = sqlSession.getMapper(ItemMapper.class);
    }

    @Override
    public Item get(Long item_id) {
        return itemMapper.get(item_id);
    }

    @Override
    public void save(ItemCommand itemCommand) {
        save(itemCommand.toItem());
    }

    @Override
    public void delete(Item item) {
        itemMapper.delete(item);
        sqlSession.commit();
    }

    @Override
    public ItemGrid findAll() {
        return new ItemGrid(itemMapper.findAll());
    }

    @Override
    public ItemGrid getItemsWithNonZeroQuantity() {
        sqlSession.clearCache();
        return new ItemGrid(itemMapper.findAvailable());
    }

    @Override
    public void saveAll(ItemGrid itemGrid) {
        for (Item item : itemGrid.getItem()) {
            save(item);
        }
    }

    @Override
    public void refreshItemList(ItemGrid itemGrid) {
        ItemGrid allItems = findAll();
        allItems.getItemMap().putAll(itemGrid.getItemMap());
        itemGrid.setItemMap(allItems.getItemMap());
    }

    @Override
    public void deleteItems(ItemGrid itemGrid) {
        for (Item item : itemGrid.getItem()) {
            delete(item);
        }
    }

    @Override
    public void decreaseQuantityByOne(Item item) {
        item.setQuantity(item.getQuantity() - 1);
        itemMapper.update(item);
        sqlSession.commit();
    }

    private void save(Item item) {
        if (item.getItemId() == null) {
            itemMapper.insert(item);
        } else {
            itemMapper.update(item);
        }
        sqlSession.commit();
    }
}
