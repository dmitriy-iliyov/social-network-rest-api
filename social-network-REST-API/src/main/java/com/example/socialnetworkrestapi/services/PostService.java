package com.example.socialnetworkrestapi.services;

import com.example.socialnetworkrestapi.DTO.PostDTO;
import com.example.socialnetworkrestapi.entitys.PostEntity;
import com.example.socialnetworkrestapi.repositorys.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void save(PostEntity postEntity){
        postRepository.save(postEntity);
    }

    @Transactional
    public Optional<PostDTO> findById(Long id){
        return postRepository.findById(id).map(PostDTO::toDTO);
    }

    @Transactional
    public List<PostDTO> findAllByUserIdOrUserNameOrCategoryIdOrCategoryName(Long userId, String userName, Long categoryId, String categoryName){
        List<PostDTO> postDTOS = new ArrayList<>();
        postRepository.findAllByUserIdOrUserNameOrCategoryIdOrCategoryName(userId, userName, categoryId, categoryName).forEach(postEntity -> postDTOS.add(PostDTO.toDTO(postEntity)));
        return postDTOS;
    }

    @Transactional
    public List<PostDTO> findAll(){
        List<PostDTO> postDTOS = new ArrayList<>();
        postRepository.findAll().forEach(postEntity -> postDTOS.add(PostDTO.toDTO(postEntity)));
        return postDTOS;
    }

    @Transactional
    public void deleteById(Long id){
        postRepository.deleteById(id);
    }
}
