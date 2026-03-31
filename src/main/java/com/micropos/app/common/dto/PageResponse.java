package com.micropos.app.common.dto;


import org.springframework.data.domain.Page;

import java.util.List;
public record PageResponse<T>(
        List<T> content,
        Meta meta
) {

    public record Meta(
            int page,
            int size,
            long totalElements,
            long totalPages,
            boolean first,
            boolean last,
            boolean hasNext,
            boolean hasPrevious
    ) {}

    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                new Meta(
                        page.getNumber(),
                        page.getSize(),
                        page.getTotalElements(),
                        page.getTotalPages(),
                        page.isFirst(),
                        page.isLast(),
                        page.hasNext(),
                        page.hasPrevious()
                )
        );
    }
}