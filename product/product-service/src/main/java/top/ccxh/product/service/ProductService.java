package top.ccxh.product.service;

import org.omg.CORBA.INTERNAL;
import top.ccxh.product.common.vo.InOrderStockeVo;
import top.ccxh.product.common.vo.OutPutProductVo;
import top.ccxh.product.common.vo.ProductInfoVo;
import top.ccxh.product.po.ProductInfo;

import java.util.List;

/**
 * 产品接口
 * @author ccxh
 */
public interface ProductService {
    /**
     * 查询所有上架商品
     *
     * @return List<ProductInfo>
     */
    List<ProductInfo> findUpProductInfo();

    /**
     * 根据productId 集合 返回对应产品
     * @param productIds productIds
     * @return List<ProductInfo>
     */
    List<ProductInfo> findProductInfoByProductIdIn(List<String> productIds);

    /**
     * 加减库存
     * @param inOrderStockeVos 入方向
     * @return
     */
    List<ProductInfo> decreaseStockProcess(List<InOrderStockeVo> inOrderStockeVos);
}
