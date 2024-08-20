package com.example.pos.connection1.feature.category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.pos.connection1.DTO.categoryDto.CategoryResponse;
import com.example.pos.connection1.entity.Category;
import com.example.pos.connection1.repository.CategoryRepository;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize, String code) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
        Page<Category> pages = categoryRepository.findByCodeAndStatusTrueAndIsDeletedFalse(code, pageRequest);
        List<CategoryResponse> data = pages.getContent().stream()
                .map((var p) -> CategoryResponse.builder()
                        .id(p.getId())
                        .catNameEn(p.getCatNameEn())
                        .catNameKh(p.getCatNameKh())
                        .movePosition(p.getMovePosition())
                        .parentId(p.getParentId())
                        .build())
                .toList();

        return JavaCollectionResponse.builder()
                .data(data)
                .count(pages.getTotalElements())
                .build();
    }

    @Override
    public JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String code, String value) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
        Page<Category> pages = categoryRepository.findByCodeAndCatNameEnContainingIgnoreCaseAndStatusTrueAndIsDeletedFalse(code, value, pageRequest);

        List<CategoryResponse> data = pages.getContent().stream()
                .map(p -> CategoryResponse.builder()
                        .id(p.getId())
                        .catNameEn(p.getCatNameEn())
                        .catNameKh(p.getCatNameKh())
                        .movePosition(p.getMovePosition())
                        .parentId(p.getParentId())
                        .build())
                .toList();

        return JavaCollectionResponse.builder()
                .data(data)
                .count(pages.getTotalElements())
                .build();
    }

}
