package com.example.pos.system.layer.service;

import com.example.pos.system.constant.util.exception.customeException.JavaNotFoundByIdGiven;
import com.example.pos.system.layer.DTO.categoryDto.CategoryRequest;
import com.example.pos.system.layer.DTO.categoryDto.CategoryResponse;
import com.example.pos.system.constant.JavaValidation;
import com.example.pos.system.domain.Category;
import com.example.pos.system.layer.repository.CategoryRepository;

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


        System.out.println("nameEn : " + c.catNameEn() + "  code : " + c.code());

        JavaValidation.checkDataAlreadyExists(
                repo.existsCatNameEnAndCode(c.catNameEn(),c.code()),
                "The field catNameEn already exits."
        ); // check catName already exists or not


        JavaValidation.checkDataAlreadyExists(
                repo.existsCatNameKhAndCode(c.catNameKh(), c.code()),
                "The field catNameKh already exists."
        ); // check catNameKh already exists or not


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
        String catNameKh = c.getCatNameKh();


        if (catNameKh != null) {
            boolean isExist = false;

            // Use a null-safe check to compare catNameKh values
            if (!Objects.equals(obj.getCatNameKh(), catNameKh) && repo.existsCatNameKhAndCode(catNameKh, c.getCode())) {
                isExist = true;
            }

            // Check if data already exists and throw an error or handle it
            JavaValidation.checkDataAlreadyExists(isExist, "The field catNameKh already exists.");
        }



        // Check if category name en is already existed?
        if(!obj.getCatNameEn().toLowerCase().trim().equals(c.getCatNameEn().toLowerCase().trim()) &&
                repo.existsCatNameEnAndCode(c.getCatNameEn(),c.getCode())){
            // Validate if the new catNameEn already exists in another record
            JavaValidation.checkDataAlreadyExists(
                    true,
                    "The field catNameEn already exists."
            );
        }


        obj.setCatNameKh(c.getCatNameKh());
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
