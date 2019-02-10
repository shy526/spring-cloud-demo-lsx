package top.ccxh.product.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ccxh.product.ProductApplicationTests;
import top.ccxh.product.po.ProductCategory;

import java.util.Arrays;
import java.util.List;

public class ProductCategoryDaoTest extends ProductApplicationTests {

    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Test
    public  void findByCategoryIdIn(){
        List<ProductCategory> byCategoryTypeIn = productCategoryDao.findByCategoryTypeIn(Arrays.asList(11, 22));

        Assert.assertTrue(byCategoryTypeIn.size()>0);
    }

}