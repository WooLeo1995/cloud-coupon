package com.woo.coupon.converter;

import com.woo.coupon.constant.CouponCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/** AttributeConverter<x, y> x: 实体属性的类型  y: 数据库对应字段的类型
 * @Description 优惠劵分类枚举转换器
 * @Author WooLeo
 * @Date 2020/2/26 9:04 上午
 * @Version 1.0
 */
@Converter
public class CouponCategoryConverter implements AttributeConverter<CouponCategory, String> {
    /**
     * Converts the value stored in the entity attribute into the
     * data representation to be stored in the database.
     *
     * @param attribute the entity attribute value to be converted
     * @return the converted data to be stored in the database
     * column
     */
    @Override
    public String convertToDatabaseColumn(CouponCategory attribute) {
        return attribute.getCode();
    }

    /**
     * Converts the data stored in the database column into the
     * value to be stored in the entity attribute.
     * Note that it is the responsibility of the converter writer to
     * specify the correct <code>dbData</code> type for the corresponding
     * column for use by the JDBC driver: i.e., persistence providers are
     * not expected to do such type conversion.
     *
     * @param dbData the data from the database column to be
     *               converted
     * @return the converted value to be stored in the entity
     * attribute
     */
    @Override
    public CouponCategory convertToEntityAttribute(String dbData) {
        return CouponCategory.of(dbData);
    }
}
