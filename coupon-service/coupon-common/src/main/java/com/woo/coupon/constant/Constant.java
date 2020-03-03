package com.woo.coupon.constant;

/**
 * @Description 通用常量定义
 * @Author WooLeo
 * @Date 2020/2/29 2:59 下午
 * @Version 1.0
 */
public class Constant {
    // kafka 消息 topic
    public static final String TOPIC = "user_coupon_op";

    //
    public static class RedisPrefix{

        public static final String COUPON_TEMPLATE = "coupon_template_code_";

        // 用户当前所有可用的优惠劵 key 前缀
        public static final String USER_COUPON_USABLE = "user_coupon_usable_";

        // 用户当前所有已使用的优惠劵 key 前缀
        public static final String USER_COUPON_USED = "user_coupon_used_";

        // 用户当前所有已过期的优惠劵 key 前缀
        public static final String USER_COUPON_EXPIRED = "user_coupon_expired_";
    }
}
