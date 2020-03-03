package com.woo.coupon.converter;

import com.woo.coupon.constant.ProductLine;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @Description 优惠劵产品线枚举转换器
 * @Author WooLeo
 * @Date 2020/2/26 9:14 上午
 * @Version 1.0
 */
@Converter
public class ProductLineConverter implements AttributeConverter<ProductLine, Integer> {
    /**
     * Converts the value stored in the entity attribute into the
     * data representation to be stored in the database.
     *
     * @param productLine the entity attribute value to be converted
     * @return the converted data to be stored in the database
     * column
     */
    @Override
    public Integer convertToDatabaseColumn(ProductLine productLine) {
        return productLine.getProductID();
    }

    /**
     * Converts the data stored in the database column into the
     * value to be stored in the entity attribute.
     * Note that it is the responsibility of the converter writer to
     * specify the correct <code>dbData</code> type for the corresponding
     * column for use by the JDBC driver: i.e., persistence providers are
     * not expected to do such type conversion.
     *
     * @param productId the data from the database column to be
     *               converted
     * @return the converted value to be stored in the entity
     * attribute
     */
    @Override
    public ProductLine convertToEntityAttribute(Integer productId) {
        return ProductLine.of(productId);
    }
}
