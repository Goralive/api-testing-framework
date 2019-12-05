package com.socks.ui.test;

import com.socks.ui.CatalogPage;
import org.testng.annotations.Test;

public class ShoppingCartTest extends BaseUITest {

    @Test
    public void userCanAddItemToCardFromCatalog(){
        CatalogPage.open()
        .addItemByIndex(0)
        .goToCart();
    }
}
