package com.example.pos.connection1.service;

import com.example.pos.connection1.util.exception.customeException.JavaNotFoundByIdGiven;
import com.example.pos.connection1.DTO.categoryDto.CategoryEditResponse;
import com.example.pos.connection1.DTO.categoryDto.CategoryRequest;
import com.example.pos.connection1.DTO.categoryDto.CategoryResponse;
import com.example.pos.connection1.constant.JavaValidation;
import com.example.pos.connection1.entity.Category;
import com.example.pos.connection1.projections.GetCategoryByCode;
import com.example.pos.connection1.repository.CategoryRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;
    @Autowired
    private HttpSession httpSession;

    public List<CategoryResponse> getCategoryByCode(String code) {
        return repo.getCategoryByCode(code).stream()
                .map(c -> CategoryResponse
                        .builder()
                        .catNameEn(c.getCatNameEn())
                        .catNameKh(c.getCatNameKh())
                        .movePosition(c.getMovePosition())
                        .id(c.getId())
                        .parentId(c.getParentId())
                        .build())
                .toList();
    }

    public Category saveCategory(CategoryRequest c) {

        if (c.parentId() != 0) {
            repo.findById(c.parentId())
                    .orElseThrow(
                            () -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "Parent Id has not been found."));
        }

        // boolean catNameKh = repo.existsByCatNameKh(c.getCatNameKh());
        boolean catNameEn = repo.existsByCatNameEnIgnoreCaseAndStatusTrueAndIsDeletedFalse(c.catNameEn());
        boolean catNameKh = repo.existsByCatNameKhIgnoreCaseAndStatusTrueAndIsDeletedFalse(c.catNameEn());
        // JavaValidation.checkDataAlreadyExists(catNameKh); // check catName already
        // exists or not
        System.out.println("api/category + " + catNameEn);
        JavaValidation.checkDataAlreadyExists(catNameEn); // check catName already exists or not
        JavaValidation.checkDataAlreadyExists(catNameKh); // check catNameKh already exists or not

        int count = repo.countLengthRow();
        count++;

        Category obj = new Category();
        obj.setCatNameKh(c.catNameKh());
        obj.setCatNameEn(c.catNameEn());
        obj.setCreateBy(c.createBy());
        obj.setParentId(c.parentId() == null ? 0 : c.parentId());
        obj.setMovePosition(count);
        obj.setCode(c.code().toLowerCase());
        repo.save(obj);
        return obj;
    }

    public List<CategoryResponse> getCategory(int parentId) {
        return repo.getCategory(parentId).stream()
                .map(c -> CategoryResponse
                        .builder()
                        .catNameEn(c.getCatNameEn())
                        .catNameKh(c.getCatNameKh())
                        .movePosition(c.getMovePosition())
                        .id(c.getId())
                        .parentId(c.getParentId())
                        .build())
                .toList();
    }

    public Category updateCategory(int id, Category c) {
        Optional<Category> data = repo.findById(id);
        Category obj = data.get();
        System.out.println("dddddddddddddddd");
        if (!Objects.equals(obj.getCatNameKh(), c.getCatNameKh())) {
            System.out.println("11111111111111 = " + c.getCatNameKh());


            boolean isExist = repo.existsByCatNameKhIgnoreCaseAndStatusTrueAndIsDeletedFalse(c.getCatNameKh());
            System.out.println("isExist : " + isExist);
            JavaValidation.checkDataAlreadyExists(isExist);
        }

        if (!Objects.equals(obj.getCatNameEn(), c.getCatNameEn())) {
            System.out.println("nnnnnnnnnnnnn");

            boolean isExist = repo.existsByCatNameEnIgnoreCaseAndStatusTrueAndIsDeletedFalse(c.getCatNameEn());
            JavaValidation.checkDataAlreadyExists(isExist);
        }

        obj.setCatNameKh(c.getCatNameKh().isEmpty() ? null : c.getCatNameKh());
        obj.setCatNameEn(c.getCatNameEn());
        obj.setParentId(c.getParentId());
        obj.setMovePosition(c.getMovePosition());
        repo.save(obj);
        return obj;
    }

    public CategoryResponse getCategoryById(int id) {
        Category c = repo.getCategoryById(id);
        if (c == null)
            throw new JavaNotFoundByIdGiven();

        return CategoryResponse.builder()
                .catNameEn(c.getCatNameEn())
                .catNameKh(c.getCatNameKh())
                .id(c.getId())
                .parentId(c.getParentId())
                .movePosition(c.getMovePosition())
                .build();

    }

    public void deleteCategory(int id) {
        Category data = repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category id has not been found."));

        Optional<Category> c = repo.findByParentIdAndStatusTrueAndIsDeletedFalse(id);

        if (!c.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Can not delete category has sub category.");

        data.setDeleted(true);
        data.setStatus(false);
        repo.save(data);
    }


    public List<CategoryResponse> search(String code, String searchValue) {

        List<CategoryResponse> search = repo.search(code,searchValue).stream()
                                        .map(c->CategoryResponse.builder()
                                        .id(c.getId())
                                        .catNameEn(c.getCat_name_en())
                                        .catNameKh(c.getCat_name_kh())
                                        .parentId(c.getParent_id())
                                        .movePosition(c.getMove_position())
                                        .build()).toList();

        return search;
    }

}
