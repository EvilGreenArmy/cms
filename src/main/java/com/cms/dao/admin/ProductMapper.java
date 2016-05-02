package com.cms.dao.admin;

import com.cms.entities.admin.ProductInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by Reason on 2016/3/26.
 */
@Repository
public interface ProductMapper {

    List<ProductInfo> productQueryPage(Map map);

    void insertProduct(ProductInfo product);

    void updateProduct(ProductInfo product);

    ProductInfo getProductById(Integer id);

    void updateStatus(Map map);

    List<ProductInfo> frontQuery(String status);
}
