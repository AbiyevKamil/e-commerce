package com.kamilabiyev.ecommerce.service.data.impl;

import com.kamilabiyev.ecommerce.domain.entity.ProductFile;
import com.kamilabiyev.ecommerce.domain.exception.DataNotFoundException;
import com.kamilabiyev.ecommerce.domain.model.dto.product.ProductDto;
import com.kamilabiyev.ecommerce.domain.model.request.product.CreateProductRequest;
import com.kamilabiyev.ecommerce.domain.model.request.product.ProductFilterRequest;
import com.kamilabiyev.ecommerce.domain.model.request.product.UpdateProductRequest;
import com.kamilabiyev.ecommerce.repository.ProductRepository;
import com.kamilabiyev.ecommerce.repository.SellerRepository;
import com.kamilabiyev.ecommerce.repository.UserRepository;
import com.kamilabiyev.ecommerce.service.data.ProductService;
import com.kamilabiyev.ecommerce.service.utils.file.FileUtil;
import com.kamilabiyev.ecommerce.service.utils.mapper.ProductMapper;
import com.kamilabiyev.ecommerce.service.utils.security.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    SellerRepository sellerRepository;
    UserRepository userRepository;
    ProductMapper productMapper;
    FileUtil fileUtil;

    @SneakyThrows
    @Override
    public Long create(CreateProductRequest createProductRequest) {
        var userDetails = SecurityUtil.getPrincipal().get();
        var userCheck = userRepository.findByUsernameAndIsDeleted(userDetails.getUsername(), false);
        if (userCheck.isEmpty()) throw new DataNotFoundException("User not found.");
        var user = userCheck.get();
        var sellerCheck = sellerRepository.findByUserIdAndIsDeleted(user.getUserId(), false);
        if (sellerCheck.isEmpty()) throw new DataNotFoundException("User not found.");
        var seller = sellerCheck.get();
        var product = productMapper.toProduct(createProductRequest);
        product.setSeller(seller);
        Set<ProductFile> productFiles = new LinkedHashSet<>();
        for (var file : createProductRequest.getFiles()) {
            productFiles.add(ProductFile.builder().filePath(fileUtil.upload(file)).build());
        }
        product.setProductFiles(productFiles);
        var saved = productRepository.save(product);
        return saved.getId();
    }

    @Override
    public Long update(UpdateProductRequest updateProductRequest) {
        return null;
    }

    @Override
    public List<ProductDto> getFiltered(ProductFilterRequest productFilterRequest) {
        return null;
    }

    @Override
    public ProductDto getById(Long id) {
        return null;
    }
}
