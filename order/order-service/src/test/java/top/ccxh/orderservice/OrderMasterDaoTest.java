package top.ccxh.orderservice;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ccxh.orderservice.dao.OrderMasterDao;
import top.ccxh.orderservice.po.OrderMaster;

import java.math.BigDecimal;

public class OrderMasterDaoTest extends OrderServiceApplicationTests {
    @Autowired
    private OrderMasterDao dao;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("1886131241241");
        orderMaster.setBuyerAddress("慕课网总部");
        orderMaster.setBuyerOpenid("1101110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(0);

        OrderMaster result = dao.save(orderMaster);
        Assert.assertTrue(result != null);
    }

}
