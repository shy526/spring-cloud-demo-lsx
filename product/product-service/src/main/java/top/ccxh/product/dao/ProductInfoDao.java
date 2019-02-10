package top.ccxh.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.ccxh.product.po.ProductInfo;

import java.util.List;

/**
 * 产品详情操作
 * @author ccxh
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {
    /**
     * 根据productStatus 查询商品
     * @param productStatus 上下架状态
     * @return  List<ProductInfo>
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    /**
     * 根据productId集合返回相应的id
     * @param productIds productIds
     * @return List<ProductInfo>
     */
    List<ProductInfo> findByProductIdIn(List<String> productIds );
}
