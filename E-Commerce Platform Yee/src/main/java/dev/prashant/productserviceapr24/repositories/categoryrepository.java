package dev.prashant.productserviceapr24.repositories;
import dev.prashant.productserviceapr24.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface categoryrepository extends JpaRepository<Category, Long>{
    Category findByTitle(String title);

    Category save(Category category);

    Optional<Category> findById(Long id);
}
