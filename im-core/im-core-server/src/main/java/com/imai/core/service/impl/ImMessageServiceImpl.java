package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImMessage;
import com.imai.core.domain.bo.ImMessageBo;
import com.imai.core.domain.vo.ImMessageVo;
import com.imai.core.mapper.ImMessageMapper;
import com.imai.core.service.IImMessageService;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 消息存储Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImMessageServiceImpl implements IImMessageService {

    private final ImMessageMapper baseMapper;

    /**
     * 查询消息存储
     *
     * @param id 主键
     * @return 消息存储
     */
    @Override
    public ImMessageVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询消息存储列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息存储分页列表
     */
    @Override
    public TableDataInfo<ImMessageVo> queryPageList(ImMessageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImMessage> lqw = buildQueryWrapper(bo);
        Page<ImMessageVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的消息存储列表
     *
     * @param bo 查询条件
     * @return 消息存储列表
     */
    @Override
    public List<ImMessageVo> queryList(ImMessageBo bo) {
        LambdaQueryWrapper<ImMessage> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImMessage> buildQueryWrapper(ImMessageBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImMessage> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkConversationId() != null, ImMessage::getFkConversationId, bo.getFkConversationId());
        lqw.eq(bo.getFkFromUserId() != null, ImMessage::getFkFromUserId, bo.getFkFromUserId());
        lqw.eq(bo.getConversationSeq() != null, ImMessage::getConversationSeq, bo.getConversationSeq());
        lqw.eq(StringUtils.isNotBlank(bo.getLocalMsgId()), ImMessage::getLocalMsgId, bo.getLocalMsgId());
        lqw.eq(bo.getMsgType() != null, ImMessage::getMsgType, bo.getMsgType());
        lqw.eq(StringUtils.isNotBlank(bo.getPayload()), ImMessage::getPayload, bo.getPayload());
        lqw.eq(StringUtils.isNotBlank(bo.getMediaUrl()), ImMessage::getMediaUrl, bo.getMediaUrl());
        lqw.eq(StringUtils.isNotBlank(bo.getMsgText()), ImMessage::getMsgText, bo.getMsgText());
        lqw.eq(StringUtils.isNotBlank(bo.getAtUsers()), ImMessage::getAtUsers, bo.getAtUsers());
        lqw.eq(bo.getMsgStatus() != null, ImMessage::getMsgStatus, bo.getMsgStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getReceiverOnly()), ImMessage::getReceiverOnly, bo.getReceiverOnly());
        lqw.eq(bo.getReceiverCount() != null, ImMessage::getReceiverCount, bo.getReceiverCount());
        lqw.eq(bo.getRefCount() != null, ImMessage::getRefCount, bo.getRefCount());
        lqw.eq(bo.getRefType() != null, ImMessage::getRefType, bo.getRefType());
        lqw.eq(bo.getRootMsgId() != null, ImMessage::getRootMsgId, bo.getRootMsgId());
        lqw.eq(bo.getParentMsgId() != null, ImMessage::getParentMsgId, bo.getParentMsgId());
        lqw.eq(bo.getDeleted() != null, ImMessage::getDeleted, bo.getDeleted());
        lqw.eq(bo.getAtAll() != null, ImMessage::getAtAll, bo.getAtAll());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImMessage::getExtras, bo.getExtras());
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), ImMessage::getAppId, bo.getAppId());
        lqw.eq(bo.getConversationType() != null, ImMessage::getConversationType, bo.getConversationType());
        lqw.eq(bo.getToUid() != null, ImMessage::getToUid, bo.getToUid());
        lqw.eq(bo.getCmd() != null, ImMessage::getCmd, bo.getCmd());
        lqw.eq(bo.getPersistent() != null, ImMessage::getPersistent, bo.getPersistent());
        lqw.eq(bo.getPriority() != null, ImMessage::getPriority, bo.getPriority());
        lqw.eq(bo.getNeedReceipt() != null, ImMessage::getNeedReceipt, bo.getNeedReceipt());
        return lqw;
    }

    /**
     * 新增消息存储
     *
     * @param bo 消息存储
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImMessageBo bo) {
        ImMessage add = MapstructUtils.convert(bo, ImMessage.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改消息存储
     *
     * @param bo 消息存储
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImMessageBo bo) {
        ImMessage update = MapstructUtils.convert(bo, ImMessage.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImMessage entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除消息存储信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
