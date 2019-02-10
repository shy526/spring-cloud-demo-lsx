package top.ccxh.product.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import top.ccxh.product.client.controller.ProductController;
import top.ccxh.product.common.vo.*;
import top.ccxh.product.po.ProductCategory;
import top.ccxh.product.po.ProductInfo;
import top.ccxh.product.service.ProductCategoryService;
import top.ccxh.product.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ccxh
 */
@RestController
public class ProductControllerImpl implements ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @Override
    public Result<List<ProductVo>> list() {
        List<ProductInfo> upProductInfo = productService.findUpProductInfo();
        List<Integer> collect = upProductInfo.stream()
                .map(ProductInfo::getCategoryType).collect(Collectors.toList());
        List<ProductCategory> productCategories = productCategoryService.findCategoryTypeIn(collect);

        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory item : productCategories) {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(item, productVo);
            List<ProductInfoVo> productInfo = new ArrayList<>();
            productVo.setProductInfoVos(productInfo);
            for (ProductInfo pItem : upProductInfo) {
                if (item.getCategoryType().equals(pItem.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(pItem, productInfoVo);
                    productInfo.add(productInfoVo);
                }
            }
            productVoList.add(productVo);
        }
        return Result.succeed(productVoList);

    }

    @Override
    public Result<List<OutPutProductVo>> forkOrder(List<String> productIds) {
        List<ProductInfo> productInfos = productService.findProductInfoByProductIdIn(productIds);
        List<OutPutProductVo> outPutProductVos = tailorProductInfo(productInfos);
        return Result.succeed(outPutProductVos);
    }

    @Override
    public   Result<List<OutPutProductVo>> decreaseStockProcess(List<InOrderStockeVo> inOrderStockeVos) {
        List<ProductInfo> productInfos = productService.decreaseStockProcess(inOrderStockeVos);
        List<OutPutProductVo> outPutProductVos = tailorProductInfo(productInfos);
        return Result.succeed(outPutProductVos);
    }

    /**
     * 裁剪 ProductInfo
     * @param productInfos
     * @return
     */
    private List<OutPutProductVo> tailorProductInfo(List<ProductInfo> productInfos) {
        ArrayList<OutPutProductVo> outPutProductVos = new ArrayList<OutPutProductVo>();
        for (ProductInfo productInfo : productInfos) {
            OutPutProductVo outPutProductVo = new OutPutProductVo();
            BeanUtils.copyProperties(productInfo, outPutProductVo);
            outPutProductVos.add(outPutProductVo);

        }
        return outPutProductVos;
    }
}
