package top.ccxh.product.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ccxh.product.common.enums.ProductInfoStatusEnum;
import top.ccxh.product.common.vo.InOrderStockeVo;
import top.ccxh.product.dao.ProductInfoDao;
import top.ccxh.product.exception.ProductException;
import top.ccxh.product.po.ProductInfo;
import top.ccxh.product.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品实现类
 *
 * @author ccxh
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public List<ProductInfo> findUpProductInfo() {
        return productInfoDao.findByProductStatus(ProductInfoStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findProductInfoByProductIdIn(List<String> productIds) {
        return productInfoDao.findByProductIdIn(productIds);
    }

    @Override
    public List<ProductInfo> decreaseStockProcess(List<InOrderStockeVo> inOrderStockeVos) {
        List<String> collect = inOrderStockeVos.stream().map(InOrderStockeVo::getProductId).collect(Collectors.toList());
        List<ProductInfo> productInfoList = this.findProductInfoByProductIdIn(collect);
        ProductException.assertTrue(collect.size() == productInfoList.size(), "找不到对应的商品");
        for (InOrderStockeVo orderStockeVo : inOrderStockeVos) {
            for (ProductInfo productInfo : productInfoList) {
                if (orderStockeVo.getProductId().equals(productInfo.getProductId())) {
                    ProductException.assertTrue(productInfo.getProductStatus().equals(ProductInfoStatusEnum.UP.getCode()), "商品已经下架:" + productInfo.getProductId());
                    int i = productInfo.getProductStock() - orderStockeVo.getProductQuantity();
                    ProductException.assertTrue(i>=0, "库存不足:" + productInfo.getProductId());
                    productInfo.setProductStock(i);
                    productInfoDao.save(productInfo);
                }
            }
        }
        return productInfoList;
    }
}
