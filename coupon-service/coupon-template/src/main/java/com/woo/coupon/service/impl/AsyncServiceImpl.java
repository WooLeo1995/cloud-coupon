package com.woo.coupon.service.impl;

import com.google.common.base.Stopwatch;
import com.woo.coupon.constant.Constant;
import com.woo.coupon.dao.CouponTemplateDao;
import com.woo.coupon.entity.CouponTemplate;
import com.woo.coupon.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

/**
 * @Description 异步服务接口实现
 * @Author WooLeo
 * @Date 2020/2/29 2:58 下午
 * @Version 1.0
 */
@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {

    private final CouponTemplateDao templateDao;

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public AsyncServiceImpl(CouponTemplateDao templateDao,
                            StringRedisTemplate redisTemplate) {
        this.templateDao = templateDao;
        this.redisTemplate = redisTemplate;
    }


    /**
     * @param template {@link CouponTemplate} 优惠劵码实体
     * @throws
     * @title
     * @description 依据模版异步创建优惠劵码
     * @author WooLeo
     * @updateTime 2020/2/29 2:40 下午
     */
    @Async("getAsyncExecutor")
    @Override
    public void asyncConstructCouponByTemplate(CouponTemplate template) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        Set<String> couponCodes = buildCouponCode(template);

        String redisKey = String.format("%s%s",
                Constant.RedisPrefix.COUPON_TEMPLATE,
                template.getId().toString());

        log.info("Push to redis: {}",
                redisTemplate.opsForList().rightPushAll(redisKey, couponCodes));

        template.setAvailable(true);
        templateDao.save(template);

        stopwatch.stop();

        log.info("Construct CouponCode By Template Cost: {}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));

        // todo send email ... 优惠劵模版已经生成
        log.info("CouponTemplate({}) is success... ", template.getId());
    }

    /**
     * 构造优惠劵码
     * 18位
     * 前4位 ：产品线 + 类型
     * 中间6位 ：日期随机数 （20200229）
     * 后8位 ：0～9 随机数构成
     * @param template  {@link CouponTemplate}
     * @return Set<String> 与 template.count 相同个数的优惠劵码
     */
    private Set<String> buildCouponCode(CouponTemplate template) {
        // 创建计时器
        Stopwatch stopwatch = Stopwatch.createStarted();

        Set<String> result = new HashSet<>(template.getCount());

        // 前4位
        String prefix4 = template.getProductLine().getProductID().toString()
                + template.getCategory().getCode();

        // 中间6位
        String date = new SimpleDateFormat("yyMMdd").format(template.getCreateTime());

        for(int i = 0; i != template.getCount(); ++i) {
            result.add(prefix4 + buildCouponCodeSuffix14(date));
        }

        while (result.size() < template.getCount()) {
            result.add(prefix4 + buildCouponCodeSuffix14(date));
        }

        assert result.size() == template.getCount();

        stopwatch.stop();

        log.info("Build Coupon Code Cost: {}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));

        return result;
    }


    /**
     * @param date
     * @return
     */
    private String buildCouponCodeSuffix14(String date){

        char[] bases = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        // 中间6位
        List<Character> chars = date.chars()
                .mapToObj(e -> (char) e).collect(Collectors.toList());

        Collections.shuffle(chars);

        String mid6 = chars.stream().map(Objects::toString).collect(Collectors.joining());

        String suffix8 = RandomStringUtils.random(1, bases) + RandomStringUtils.randomNumeric(7);

        return mid6 + suffix8;
    }
}
