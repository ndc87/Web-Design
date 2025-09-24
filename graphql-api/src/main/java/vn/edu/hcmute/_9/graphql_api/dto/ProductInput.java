package vn.edu.hcmute._9.graphql_api.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProductInput {
    private String title;
    private int quantity;
    private String desc;
    private double price;
    private Long userId;
    private List<Long> categoryIds;
}
