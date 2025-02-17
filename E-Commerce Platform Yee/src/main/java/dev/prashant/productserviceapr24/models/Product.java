package dev.prashant.productserviceapr24.models;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Product extends BaseModel {
    private String title;
    private  String description;
    private Double price;
    private String imageUrl;
    private int quantity;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
}
