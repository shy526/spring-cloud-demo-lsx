package top.ccxh.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.ccxh.orderservice.po.OrderMaster;

/**
 *
 * @author ccxh
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {

}
