package com.sasha.CRUDwithHbn.service;




import com.sasha.CRUDwithHbn.model.Post;
import com.sasha.CRUDwithHbn.model.PostStatus;
import com.sasha.CRUDwithHbn.repository.PostRepository;
import com.sasha.CRUDwithHbn.repository.hbn.HBNPostRepositoryImpl;

import java.util.List;

public class PostService {

    private PostRepository postRepository;

    public PostService() {
        this.postRepository = new HBNPostRepositoryImpl();
    }

    public PostService(PostRepository postRepositoryConstr) {
        this.postRepository = postRepositoryConstr;
    }

    public Post getById(Integer id) {
        return postRepository.getById(id);
    }

    public Post update(Post post) {
        return postRepository.update(post);
    }

    public void deleteById(Integer id) {
        postRepository.deleteById(id);
    }

    public List<Post> getAll() {
        return postRepository.getAll();
    }

    public Post save(Post post) { return postRepository.save(post);
    }


}
