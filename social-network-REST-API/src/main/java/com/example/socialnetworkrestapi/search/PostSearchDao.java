//package com.example.socialnetworkrestapi.search;
//
//import com.example.socialnetworkrestapi.DTO.PostDTO;
//import com.example.socialnetworkrestapi.entitys.PostEntity;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//import javax.swing.text.html.parser.Entity;
//import java.util.ArrayList;
//import java.util.List;
//
//@RequiredArgsConstructor
//public class PostSearchDao {
//
//    private final EntityManager em;
//
//    public List<PostDTO> findAllPostsByCriteria(SearchRequest request){
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<PostEntity> criteriaQuery = criteriaBuilder.createQuery(PostEntity.class);
//        List<Predicate> predicates = new ArrayList<>();
//
//        Root<PostEntity> root = criteriaQuery.from(PostEntity.class);
//        if(request.getUserID() != null){
//            Predicate userIdPredicate = criteriaBuilder.like(root.get("user_id"), "%" + request.getUserID() + "%");
//        }
//    }
//}
