package com.example.pos.connection1.projections;

public interface GetCategoryByCode {
    int getId();
    String getCat_name_en();
    String getCat_name_kh();
    int getParent_id();
    int getMove_position();
}
