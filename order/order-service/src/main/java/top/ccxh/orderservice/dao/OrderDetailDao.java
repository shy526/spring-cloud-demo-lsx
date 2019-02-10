package top.ccxh.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.ccxh.orderservice.po.OrderDetail;

/**
 *
 * @author ccxh
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {
}
