package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findByUserIdOrderByTimeDesc(Integer id, Pageable pageable);

    Page<Post> findByUserIdAndPrivatePostFalseOrderByTimeDesc(Integer id, Pageable pageable);

    Page<Post> findDistinctByUserFriendsIdOrUserIdOrderByTimeDesc(Integer id, Integer id2, Pageable pageable);
}
