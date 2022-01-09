package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.ItemsComments;
import java.util.Map;

/** @author afu */
public interface ItemsCommentsMapperCustom extends MyMapper<ItemsComments> {

  /**
   * 保存评价
   *
   * @param map 评价列表
   */
  void saveComments(Map<String, Object> map);

  //    public List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);

}
