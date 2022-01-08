package com.imooc.service.impl.center;

import com.imooc.mapper.OrderItemsMapper;
import com.imooc.pojo.OrderItems;
import com.imooc.service.center.MyCommentsService;
import java.util.List;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/** @author afu */
@Service
public class MyCommentsServiceImpl implements MyCommentsService {
  @Autowired public OrderItemsMapper orderItemsMapper;
  @Autowired public Sid sid;

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  @Override
  public List<OrderItems> queryPendingComment(String orderId) {
    OrderItems query = new OrderItems();
    query.setOrderId(orderId);
    return orderItemsMapper.select(query);
  }
}
