package top.ccxh.product.service;

import top.ccxh.product.po.ProductCategory;

import java.util.List;

/**
 * 产品分类接口
 * @author ccxh
 */
public interface ProductCategoryService {
    /**
     * 根据集合返回相应 productCategory
     * @param categoryTypes categoryTypes
     * @return List<ProductCategory>
     */
    List<ProductCategory> findCategoryTypeIn(List<Integer> categoryTypes);
}
