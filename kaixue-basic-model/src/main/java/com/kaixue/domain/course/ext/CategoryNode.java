package com.kaixue.domain.course.ext;

import com.kaixue.domain.course.Category;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CategoryNode extends Category
{
    private List<CategoryNode> children;
}
