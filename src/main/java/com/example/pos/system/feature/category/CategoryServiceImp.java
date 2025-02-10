package com.example.pos.system.feature.category;

import com.example.pos.system.layer.DTO.categoryDto.CategoryResponse;
import com.example.pos.system.domain.settings.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.pos.system.layer.repository.CategoryRepository;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import lombok.RequiredArgsConstructor;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String code, String value) {
        List<CategoryResponse> data = null;

        if (pageNumber == null && pageSize == null) {
            data = categoryRepository.searchByCatNameEnOrCatNameKh(null,value,code
                            ).stream()
                    .map(p -> CategoryResponse.builder()
                            .id(p.getId())
                            .catNameEn(p.getCatNameEn())
                            .catNameKh(p.getCatNameKh())
                            .movePosition(p.getMovePosition())
                            .parentId(p.getParentId())
                            .build())
                    .toList();

            return JavaCollectionResponse.builder()
                    .count(data.size())
                    .data(data)
                    .build();
        } else {
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
            Page<Category> pages = categoryRepository
                    .searchByCatNameEnOrCatNameKh(pageRequest,value, code);

            List<CategoryResponse> content = pages.getContent().stream()
                    .map(p -> CategoryResponse.builder()
                            .id(p.getId())
                            .catNameEn(p.getCatNameEn())
                            .catNameKh(p.getCatNameKh())
                            .movePosition(p.getMovePosition())
                            .parentId(p.getParentId())
                            .build())
                    .toList();

            return JavaCollectionResponse.builder()
                    .data(content)
                    .count(pages.getTotalElements())
                    .build();
        }
    }

    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize, String code) {
        List<CategoryResponse> data = null;

        if (pageNumber == null && pageSize == null) {
            data = categoryRepository.findByCodeAndStatusTrueAndIsDeletedFalseWithSorting(code).stream()
                    .map(p -> CategoryResponse.builder()
                            .id(p.getId())
                            .catNameEn(p.getCatNameEn())
                            .catNameKh(p.getCatNameKh())
                            .movePosition(p.getMovePosition())
                            .parentId(p.getParentId())
                            .build())
                    .toList();

            return JavaCollectionResponse.builder()
                    .count(data.size())
                    .data(data)
                    .build();
        } else {

            Sort sortById = Sort.by(Sort.Direction.ASC, "id");
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
            Page<Category> pages = categoryRepository.findByCodeAndStatusTrueAndIsDeletedFalseWithSorting(code, pageRequest);

            List<CategoryResponse> content = pages.getContent().stream()
                    .map(p -> CategoryResponse.builder()
                            .id(p.getId())
                            .catNameEn(p.getCatNameEn())
                            .catNameKh(p.getCatNameKh())
                            .movePosition(p.getMovePosition())
                            .parentId(p.getParentId())
                            .build())
                    .toList();

            return JavaCollectionResponse.builder()
                    .data(content)
                    .count(pages.getTotalElements())
                    .build();
        }
    }

}
