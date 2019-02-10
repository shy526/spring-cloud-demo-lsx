package top.ccxh.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.ccxh.product.po.ProductCategory;

import java.util.List;

/**
 * 分类
 * @author ccxh
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {
    /**
     * 查询所需分类
     * @param categoryIds categoryId 集合
     * @return List<ProductCategory>
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryIds);
}