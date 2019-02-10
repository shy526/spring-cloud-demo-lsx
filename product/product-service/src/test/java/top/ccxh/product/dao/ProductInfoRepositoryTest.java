package top.ccxh.product.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ccxh.product.ProductApplicationTests;
import top.ccxh.product.po.ProductInfo;

import java.util.List;

public class ProductInfoRepositoryTest extends ProductApplicationTests {
    @Autowired(required = false)
    private ProductInfoDao productInfoDao;
    @Test
    public void findByProductStatus(){
        List<ProductInfo> products = productInfoDao.findByProductStatus(0);
        Assert.assertTrue(products.size()>0);
    }

}