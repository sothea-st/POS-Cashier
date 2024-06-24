package com.example.pos.connection1.service;

import com.example.pos.connection1.util.exception.customeException.JavaNotFoundByIdGiven;
import com.example.pos.connection1.DTO.categoryDto.CategoryRequest;
import com.example.pos.connection1.DTO.categoryDto.CategoryResponse;
import com.example.pos.connection1.constant.JavaValidation;
import com.example.pos.connection1.entity.Category;
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
        // boolean catNameKh = repo.existsByCatNameKh(c.getCatNameKh());
        boolean catNameEn = repo.existsByCatNameEn(c.catNameEn());
        // JavaValidation.checkDataAlreadyExists(catNameKh); // check catName already
        // exists or not
        JavaValidation.checkDataAlreadyExists(catNameEn); // check catName already exists or not

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

        if (!Objects.equals(obj.getCatNameKh(), c.getCatNameKh())) {
            boolean isExist = repo.existsByCatNameKh(c.getCatNameKh());
            JavaValidation.checkDataAlreadyExists(isExist);
        }

        if (!Objects.equals(obj.getCatNameEn(), c.getCatNameEn())) {
            boolean isExist = repo.existsByCatNameEn(c.getCatNameEn());
            JavaValidation.checkDataAlreadyExists(isExist);
        }

        obj.setCatNameKh(c.getCatNameKh());
        obj.setCatNameEn(c.getCatNameEn());
        obj.setParentId(c.getParentId());
        obj.setMovePosition(c.getMovePosition());
        repo.save(obj);
        return obj;
    }

    public Category getCategoryById(int id) {
        Category c = repo.getCategoryById(id);
        if (c == null)
            throw new JavaNotFoundByIdGiven();
        return c;
    }

    public void deleteCategory(int id) {
        Optional<Category> data = repo.findById(id);
        Category obj = data.get();
        obj.setDeleted(true);
        obj.setStatus(false);
        repo.save(obj);
    }

}
