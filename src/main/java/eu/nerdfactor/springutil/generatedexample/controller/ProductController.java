package eu.nerdfactor.springutil.generatedexample.controller;

import eu.nerdfactor.springutil.generatedexample.dto.ProductDto;
import eu.nerdfactor.springutil.generatedexample.entity.ProductEntity;
import eu.nerdfactor.springutil.generatedrest.annotation.GeneratedRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Base controller for Products.<br>
 * Uses GeneratedRestController to configure a generated controller based on the controller.
 * Provides informationen about the entity and id to use in the generated controller.
 */
@GeneratedRestController(value = "/api/products", entity = ProductEntity.class, id = Integer.class, dto = ProductDto.class)
public class ProductController {
}
