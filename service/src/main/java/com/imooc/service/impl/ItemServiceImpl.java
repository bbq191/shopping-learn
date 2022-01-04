package com.imooc.service.impl;

import com.imooc.enums.CommentLevel;
import com.imooc.mapper.ItemsCommentsMapper;
import com.imooc.mapper.ItemsImgMapper;
import com.imooc.mapper.ItemsMapper;
import com.imooc.mapper.ItemsParamMapper;
import com.imooc.mapper.ItemsSpecMapper;
import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsComments;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVo;
import com.imooc.service.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/** @author afu */
@Service
public class ItemServiceImpl implements ItemService {

  @Autowired private ItemsMapper itemsMapper;
  @Autowired private ItemsImgMapper itemsImgMapper;
  @Autowired private ItemsSpecMapper itemsSpecMapper;
  @Autowired private ItemsParamMapper itemsParamMapper;
  @Autowired private ItemsCommentsMapper itemsCommentsMapper;

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  @Override
  public Items queryItemById(String itemId) {
    return itemsMapper.selectByPrimaryKey(itemId);
  }

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  @Override
  public List<ItemsImg> queryItemImgList(String itemId) {
    Example example = new Example(ItemsImg.class);
    Criteria criteria = example.createCriteria();
    criteria.andEqualTo("itemId", itemId);
    return itemsImgMapper.selectByExample(example);
  }

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  @Override
  public List<ItemsSpec> queryItemSpecList(String itemId) {
    Example example = new Example(ItemsSpec.class);
    Criteria criteria = example.createCriteria();
    criteria.andEqualTo("itemId", itemId);
    return itemsSpecMapper.selectByExample(example);
  }

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  @Override
  public ItemsParam queryItemParam(String itemId) {
    Example example = new Example(ItemsParam.class);
    Criteria criteria = example.createCriteria();
    criteria.andEqualTo("itemId", itemId);
    return itemsParamMapper.selectOneByExample(example);
  }

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  @Override
  public CommentLevelCountsVo queryCommentCounts(String itemId) {
    Integer goodCounts = getCommentsCounts(itemId, CommentLevel.GOOD.type);
    Integer normalCounts = getCommentsCounts(itemId, CommentLevel.NORMAL.type);
    Integer badCounts = getCommentsCounts(itemId, CommentLevel.BAD.type);
    Integer totalCounts = goodCounts + badCounts + normalCounts;

    CommentLevelCountsVo commentLevelCountVo = new CommentLevelCountsVo();
    commentLevelCountVo.setGoodCounts(goodCounts);
    commentLevelCountVo.setNormalCounts(normalCounts);
    commentLevelCountVo.setBadCounts(badCounts);
    commentLevelCountVo.setTotalCounts(totalCounts);
    return commentLevelCountVo;
  }

  /**
   * 分类获取评价数量
   *
   * @param itemId 商品 id
   * @param commentLevel 评价级别
   * @return 评价数量
   */
  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  private Integer getCommentsCounts(String itemId, Integer commentLevel) {
    ItemsComments condition = new ItemsComments();
    condition.setItemId(itemId);
    if (commentLevel != null) {
      condition.setCommentLevel(commentLevel);
    }
    return itemsCommentsMapper.selectCount(condition);
  }
}
