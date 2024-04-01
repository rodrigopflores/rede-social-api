package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Page<User> findByFriendOfIdOrderByFirstNameAsc(Integer id, Pageable pageable);

    Page<User> findByFriendRequestsSentId(Integer id, Pageable pageable);

    @Query(
            value = "SELECT tu.* " +
                    "FROM tcc_user tu  " +
                    "WHERE (LOWER(tu.first_name) LIKE LOWER(CONCAT('%', ?1, '%')) OR  " +
                    "LOWER(tu.last_name) LIKE LOWER(CONCAT('%', ?1, '%')) OR " +
                    "LOWER(tu.email) LIKE LOWER(CONCAT('%', ?1, '%')) OR " +
                    "LOWER(tu.nick_name) LIKE LOWER(CONCAT('%', ?1, '%'))) AND " +
                    "tu.id != ?2",
            nativeQuery = true
    )
    Page<User> searchUserByString(String searchTerm, Integer id, Pageable pageable);


}