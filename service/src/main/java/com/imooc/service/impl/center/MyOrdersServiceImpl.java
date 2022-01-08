package com.imooc.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.OrdersMapperCustom;
import com.imooc.pojo.vo.MyOrdersVo;
import com.imooc.service.center.MyOrdersService;
import com.imooc.utils.PagedGridResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** @author afu */
@Service
public class MyOrdersServiceImpl implements MyOrdersService {

  @Autowired private OrdersMapperCustom ordersMapperCustom;

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
