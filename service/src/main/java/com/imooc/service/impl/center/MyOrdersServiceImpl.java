package com.imooc.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.mapper.OrdersMapperCustom;
import com.imooc.pojo.OrderStatus;
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
  public boolean updateReceiveOrderStatus(String orderId) {

    return false;
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
