package com.woo.coupon.service.impl;

import com.woo.coupon.dao.CouponTemplateDao;
import com.woo.coupon.entity.CouponTemplate;
import com.woo.coupon.exception.CouponException;
import com.woo.coupon.service.TemplateBaseService;
import com.woo.coupon.vo.CouponTemplateSDK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description 优惠劵模版基础服务接口实现
 * @Author WooLeo
 * @Date 2020/2/29 8:25 下午
 * @Version 1.0
 */
@Slf4j
@Service
public class TemplateBaseServiceImpl implements TemplateBaseService {

    private final CouponTemplateDao templateDao;

    @Autowired
    public TemplateBaseServiceImpl(CouponTemplateDao templateDao) {
        this.templateDao = templateDao;
    }

    /**
     * @param id
     * @throws
     * @title
     * @description 根据优惠劵 ID 获取优惠劵模版信息
     * @author WooLeo
     * @updateTime 2020/2/29 2:51 下午
     */
    @Override
    public CouponTemplate buildTemplateInfo(Integer id) throws CouponException {

        Optional<CouponTemplate> template = templateDao.findById(id);

        if(!template.isPresent()){
            throw new CouponException("Template Is Not Exist: " + id);
        }

        return template.get();
    }

    /**
     * @throws
     * @title
     * @description 查找所有可用的优惠劵模版
     * @author WooLeo
     * @updateTime 2020/2/29 2:52 下午
     */
    @Override
    public List<CouponTemplateSDK> findAllUsableTemplate() {

        List<CouponTemplate> couponTemplates =
                templateDao.findAllByAvailableAndExpired(true, false);

        return couponTemplates.stream().map(this :: template2TemplateSDK).collect(Collectors.toList());
    }

    /**
     * 获取模版的 ids 到 CouponTemplateSDK 的映射
     *
     * @param ids 模版 ids
     * @return Map<key: id, value: CouponTemplateSDK>
     */
    @Override
    public Map<Integer, CouponTemplateSDK> findIds2TemplateSDK(Collection<Integer> ids) {

        List<CouponTemplate> templates = templateDao.findAllById(ids);

        return templates.stream().map(this::template2TemplateSDK).collect(Collectors.toMap(
                CouponTemplateSDK::getId, Function.identity()
        ));
    }

    private CouponTemplateSDK template2TemplateSDK(CouponTemplate template){
        return new CouponTemplateSDK(
                template.getId(),
                template.getName(),
                template.getLogo(),
                template.getDesc(),
                template.getCategory().getCode(),
                template.getProductLine().getProductID(),
                template.getKey(),
                template.getRule(),
                template.getTarget().getCode()
        );
    }
}
