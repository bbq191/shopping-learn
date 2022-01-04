package com.imooc.mapper;

import com.imooc.pojo.vo.ItemCommentVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/** @author afu */
public interface ItemsMapperCustom {

  /**
   * 组装商品评价 vo
   *
   * @param map 参数列表
   * @return 评价列表
   */
  List<ItemCommentVo> queryItemComments(@Param("paramsMap") Map<String, Object> map);
}
