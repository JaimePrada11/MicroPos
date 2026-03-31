package com.micropos.app.products.domain.exception;

import com.micropos.app.common.exception.BaseException;
import com.micropos.app.common.exception.ErrorCode;

public class ProductNotFoundException extends BaseException {

    public ProductNotFoundException(Long id){
        super(ErrorCode.RESOURCE_NOT_FOUND,
                "Product with id: " + id + " not found");
    }

    public ProductNotFoundException(String sku){
        super(ErrorCode.RESOURCE_NOT_FOUND,
                "Product with sku: " + sku + " not found");
    }
}
