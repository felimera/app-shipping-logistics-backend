package com.project.appshippinglogistics.repository.implementation;

import com.project.appshippinglogistics.model.Product;
import com.project.appshippinglogistics.model.ProductType;
import com.project.appshippinglogistics.model.ProductType_;
import com.project.appshippinglogistics.model.Product_;
import com.project.appshippinglogistics.model.search.ProductSeeker;
import com.project.appshippinglogistics.repository.ProductCriteriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {
    private EntityManager em;

    @Autowired
    public ProductCriteriaRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Product> getConsultProductForVariousParameters(ProductSeeker seeker) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> productRoot = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        Join<ProductType, Product> productTypeProductJoin = productRoot.join(Product_.PRODUCT_TYPE, JoinType.INNER);

        if (Objects.nonNull(seeker.getName()) && !seeker.getName().isEmpty()) {
            Expression<String> nameExpression = cb.upper(productRoot.get(Product_.NAME));
            String nameValue = "%".concat(seeker.getName().toUpperCase()).concat("%");
            predicates.add(cb.like(nameExpression, nameValue));
        }

        if (Objects.nonNull(seeker.getPrice())) {
            predicates.add(cb.equal(productRoot.get(Product_.PRICE), seeker.getPrice()));
        }

        if (Objects.nonNull(seeker.getStartAmount()) && Objects.nonNull(seeker.getFinalAmount())) {
            predicates.add(cb.between(productRoot.get(Product_.AMOUNT), seeker.getStartAmount(), seeker.getFinalAmount()));
        }

        if (Objects.nonNull(seeker.getStartDate()) && Objects.nonNull(seeker.getFinalDate())) {
            predicates.add(cb.between(productRoot.get(Product_.REGISTRATION_DATE), seeker.getStartDate(), seeker.getFinalDate()));
        }

        if (Objects.nonNull(seeker.getIdProductType()))
            predicates.add(cb.equal(productTypeProductJoin.get(ProductType_.ID), seeker.getIdProductType()));

        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
            cq.orderBy(cb.asc(productRoot.get(Product_.name)));
            return em.createQuery(cq).getResultList();
        }
        return new ArrayList<>();
    }
}
