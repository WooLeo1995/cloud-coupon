package com.woo.coupon.service.impl;

import com.woo.coupon.dao.CouponTemplateDao;
import com.woo.coupon.entity.CouponTemplate;
import com.woo.coupon.exception.CouponException;
import com.woo.coupon.service.AsyncService;
import com.woo.coupon.service.BuildTemplateService;
import com.woo.coupon.vo.TemplateResquest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 构造优惠劵模版接口实现
 * @Author WooLeo
 * @Date 2020/2/29 3:46 下午
 * @Version 1.0
 */
@Slf4j
@Service
public class BuildTemplateServiceImpl implements BuildTemplateService {

    // 异步服务
    private final AsyncService asyncService;

    private final CouponTemplateDao templateDao;

    @Autowired
    public BuildTemplateServiceImpl(AsyncService asyncService,
                                    CouponTemplateDao templateDao) {
        this.asyncService = asyncService;
        this.templateDao = templateDao;
    }

    /**
     * @param templateResquest
     * @throws
     * @title
     * @description
     * @author WooLeo
     * @updateTime 2020/2/29 10:03 上午
     */
    @Override
    public CouponTemplate buildTemplate(TemplateResquest templateResquest)
            throws CouponException {
        if (!templateResquest.validate()){
            throw new CouponException("BuildTemplate Param Is Not Valid!");
        }

        if (null != templateDao.findAllByName(templateResquest.getName())){
            throw new CouponException("Exist Same Name Template!");
        }

        // 构造 CouponTemplate 并保存到数据库中
        CouponTemplate template = request2Template(templateResquest);
        template = templateDao.save(template);

        // 根据优惠劵模版异步生成优惠劵码
        asyncService.asyncConstructCouponByTemplate(template);
        return template;
    }


    /**
     * 将 TemplateRequest 转换为 CouponTemplate
     * @param resquest
     * @return
     */
    private CouponTemplate request2Template(TemplateResquest resquest){
        return new CouponTemplate(resquest.getName(),
                resquest.getLogo(),
                resquest.getDesc(),
                resquest.getCategory(),
                resquest.getProductLine(),
                resquest.getCount(),
                resquest.getUserId(),
                resquest.getTarget(),
                resquest.getRule());
    }
}
