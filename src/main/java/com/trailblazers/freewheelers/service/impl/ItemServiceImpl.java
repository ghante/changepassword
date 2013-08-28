package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.ItemMapper;
import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ItemValidation;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ServiceResult;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class ItemServiceImpl implements ItemService {

    private final SqlSession sqlSession;
    private final ItemMapper itemMapper;

    public ItemServiceImpl() {
        this(MyBatisUtil.getSqlSessionFactory().openSession());
    }

    protected ItemServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.itemMapper = sqlSession.getMapper(ItemMapper.class);
    }

    @Override
    public Item get(Long item_id) {
        return itemMapper.get(item_id);
    }

    @Override
    public Item getByName(String name) {
        return itemMapper.getByName(name);
    }

    @Override
    public void delete(Item item) {
        itemMapper.delete(item);
        sqlSession.commit();
    }

    @Override
    public List<Item> findAll() {
        sqlSession.clearCache();
        return itemMapper.findAll();
    }

    @Override
    public List<Item> getItemsWithNonZeroQuantity() {
        sqlSession.clearCache();
        return itemMapper.findAvailable();
    }

    @Override
    public void saveAll(List<Item> items) {
        for (Item item : items) {
            insertOrUpdate(item);
            sqlSession.commit();
        }
    }

    @Override
    public void refreshItemList(Item item) {
        List<Item> allItems = findAll();
        allItems.add(item);
    }

    @Override
    public void deleteItems(List<Item> items) {
        for (Item item : items) {
            delete(item);
        }
    }

    @Override
    public void decreaseQuantityByOne(Item item) {
        item.setQuantity(item.getQuantity() - 1);
        itemMapper.update(item);
        sqlSession.commit();
    }

    @Override
    public ServiceResult<Item> saveItem(Item item) {
        Map<String,String> errors = new ItemValidation().validate(item);

        if (errors.isEmpty()) {
            insertOrUpdate(item);
            sqlSession.commit();
        }
        return new ServiceResult<Item>(errors, item);
    }

    private void insertOrUpdate(Item item) {
        if (item.getItemId() == null) {
            itemMapper.insert(item);
        } else {
            itemMapper.update(item);
        }
    }
}
