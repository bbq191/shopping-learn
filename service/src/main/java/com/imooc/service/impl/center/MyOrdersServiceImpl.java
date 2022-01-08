package com.imooc.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.YesOrNo;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.mapper.OrdersMapper;
import com.imooc.mapper.OrdersMapperCustom;
import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.Orders;
import com.imooc.pojo.vo.MyOrdersVo;
import com.imooc.service.center.MyOrdersService;
import com.imooc.utils.PagedGridResult;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/** @author afu */
@Service
public class MyOrdersServiceImpl implements MyOrdersService {

  @Autowired private OrdersMapperCustom ordersMapperCustom;
  @Autowired private OrderStatusMapper orderStatusMapper;
  @Autowired private OrdersMapper ordersMapper;

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  @Override
  public PagedGridResult queryMyOrders(
      String userId, Integer orderStatus, Integer page, Integer pageSize) {
    Map<String, Object> map = new HashMap<>(20);
    map.put("userId", userId);
    if (orderStatus != null) {
      map.put("orderStatus", orderStatus);
    }
    PageHelper.startPage(page, pageSize);
    List<MyOrdersVo> list = ordersMapperCustom.queryMyOrders(map);
    return setterPageGrid(list, page);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  @Override
  public void updateDeliverOrderStatus(String orderId) {
    OrderStatus updateOrder = new OrderStatus();
    updateOrder.setOrderStatus(OrderStatusEnum.WAIT_RECEIVE.type);
    updateOrder.setDeliverTime(new Date());

    Example example = new Example(OrderStatus.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("orderId", orderId);
    criteria.andEqualTo("orderStatus", OrderStatusEnum.WAIT_DELIVER.type);

    orderStatusMapper.updateByExampleSelective(updateOrder, example);
  }

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  @Override
  public Orders queryMyOrder(String userId, String orderId) {
    Orders orders = new Orders();
    orders.setUserId(userId);
    orders.setId(orderId);
    orders.setIsDelete(YesOrNo.NO.type);

    return ordersMapper.selectOne(orders);
  }

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  @Override
  public boolean updateReceiveOrderStatus(String orderId) {
    OrderStatus updateOrder = new OrderStatus();
    updateOrder.setOrderStatus(OrderStatusEnum.SUCCESS.type);
    updateOrder.setSuccessTime(new Date());

    Example example = new Example(OrderStatus.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("orderId", orderId);
    criteria.andEqualTo("orderStatus", OrderStatusEnum.WAIT_RECEIVE.type);

    int result = orderStatusMapper.updateByExampleSelective(updateOrder, example);

    return result == 1;
  }

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  @Override
  public boolean deleteOrder(String userId, String orderId) {
    Orders updateOrder = new Orders();
    updateOrder.setIsDelete(YesOrNo.YES.type);
    updateOrder.setUpdatedTime(new Date());

    Example example = new Example(Orders.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("id", orderId);
    criteria.andEqualTo("userId", userId);

    int result = ordersMapper.updateByExampleSelective(updateOrder, example);

    return result == 1;
  }

  /**
   * 分页列表查询
   *
   * @param list 对象列表
   * @param page 第几页
   * @return 分页后对象列表
   */
  private PagedGridResult setterPageGrid(List<?> list, Integer page) {
    PageInfo<?> pageList = new PageInfo<>(list);
    PagedGridResult grid = new PagedGridResult();
    grid.setPage(page);
    grid.setRows(list);
    grid.setTotal(pageList.getPages());
    grid.setRecords(pageList.getTotal());
    return grid;
  }
}
