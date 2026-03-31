package com.micropos.app.products.domain.exception;

import com.micropos.app.common.exception.BaseException;
import com.micropos.app.common.exception.ErrorCode;

public class ProductAlreadyExistsException extends BaseException {

    public ProductAlreadyExistsException(String sku) {
        super(ErrorCode.CONFLICT,
                "Product with sku: " + sku + " already exists");
    }
}
