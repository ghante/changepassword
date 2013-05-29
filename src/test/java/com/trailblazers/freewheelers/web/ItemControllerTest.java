package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.ItemType;
import com.trailblazers.freewheelers.service.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ItemControllerTest {

    @Mock
    ItemService itemService;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void shouldReturnItemDetailsForDefaultView() throws Exception {

        ItemController itemController = new ItemController();
        itemController.itemService = itemService;

        ItemGrid itemGrid = mock(ItemGrid.class);
        when(itemService.findAll()).thenReturn(itemGrid);

        standaloneSetup(itemController)
                .build().perform(get("/item"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("itemGrid", itemGrid))
                .andExpect(model().attribute("itemTypes", ItemType.values()));

    }
}
