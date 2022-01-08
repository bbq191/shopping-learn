package com.imooc.service.center;

import com.imooc.pojo.OrderItems;
import java.util.List;

/** @author afu */
public interface MyCommentsService {

  /**
   * 根据订单id查询关联的商品
   *
   * @param orderId 订单编号
   * @return 评价列表
   */
  List<OrderItems> queryPendingComment(String orderId);

  /**
   * 保存用户的评论
   *
   * @param orderId 订单编号
   * @param userId 用户编号
   * @param commentList
   */
  //    void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList);

  /**
   * 我的评价查询 分页
   *
   * @param userId
   * @param page
   * @param pageSize
   * @return
   */
  //    PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);
}
