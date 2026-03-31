package com.micropos.app.products.interfaces.rest;

import com.micropos.app.common.dto.ApiResponse;
import com.micropos.app.common.dto.PageResponse;
import com.micropos.app.products.application.dto.request.CreateProductRequest;
import com.micropos.app.products.application.dto.request.UpdateProductRequest;
import com.micropos.app.products.application.dto.response.ProductResponse;
import com.micropos.app.products.application.service.ProductServiceImp;
import com.micropos.app.products.domain.model.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImp productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> create(
            @Valid @RequestBody CreateProductRequest request){

        Product created = productService.create(request);
        ProductResponse response = ProductResponse.from(created);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> findById(@PathVariable Long id){
        Product product = productService.findById(id);
        return  ResponseEntity.ok(ApiResponse.success(ProductResponse.from(product)));
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<ApiResponse<ProductResponse>> findBySku(@PathVariable String sku){
        Product product = productService.findBySku(sku);
        return  ResponseEntity.ok(ApiResponse.success(ProductResponse.from(product)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> findAll
            (
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "10") int size
            ) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProductResponse> products = productService.findAll(pageable)
                .map(ProductResponse::from);

        return ResponseEntity.ok(ApiResponse.success(PageResponse.from(products)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request
            )
    {
        Product product = productService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(
                "Product updated successfully",
                ProductResponse.from(product)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id)
    {
        productService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Product deleted successfully"));
    }
}
