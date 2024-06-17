package com.sivalabs.myapp.models;

public record PagedResult<T>(
        Iterable<T> data,
        long totalElements,
        int totalPages,
        int pageNumber
) {
}
