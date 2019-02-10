package top.ccxh.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ccxh.product.dao.ProductCategoryDao;
import top.ccxh.product.po.ProductCategory;
import top.ccxh.product.service.ProductCategoryService;

import java.util.List;

/**
 * 实现类
 * @author ccxh
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> findCategoryTypeIn(List<Integer> categoryTypes) {
        return productCategoryDao.findByCategoryTypeIn(categoryTypes);
    }
}
