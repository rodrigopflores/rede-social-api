package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * UserRepository interface.
 * This interface extends JpaRepository and provides methods for performing CRUD operations on User objects.
 * JpaRepository is a JPA specific extension of Repository which provides some additional methods to retrieve entities using the pagination and sorting abstraction.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * Custom query method to find a user by email.
   * @param email The email of the user.
   * @return An Optional User object.
   */
  Optional<User> findByEmail(String email);

  /**
   * Custom query method to find users by friend's id and order them by first name in ascending order.
   * @param id The id of the friend.
   * @param pageable The pagination information.
   * @return A page of User objects.
   */
  Page<User> findByFriendOfIdOrderByFirstNameAsc(Integer id, Pageable pageable);

  /**
   * Custom query method to find users by friend request sent id.
   * @param id The id of the user who sent the friend request.
   * @param pageable The pagination information.
   * @return A page of User objects.
   */
  Page<User> findByFriendRequestsSentId(Integer id, Pageable pageable);

  /**
   * Custom query method to search users by a search term and exclude a specific user id.
   * The search term is used to match against the first name, last name, email, and nickname fields of the User entity.
   * The search is case-insensitive.
   * @param searchTerm The search term.
   * @param id The id of the user to exclude from the search results.
   * @param pageable The pagination information.
   * @return A page of User objects.
   */
  @Query(
          value =
                  "SELECT tu.* "
                          + "FROM tcc_user tu  "
                          + "WHERE (LOWER(tu.first_name) LIKE LOWER(CONCAT('%', ?1, '%')) OR  "
                          + "LOWER(tu.last_name) LIKE LOWER(CONCAT('%', ?1, '%')) OR "
                          + "LOWER(tu.email) LIKE LOWER(CONCAT('%', ?1, '%')) OR "
                          + "LOWER(tu.nick_name) LIKE LOWER(CONCAT('%', ?1, '%'))) AND "
                          + "tu.id != ?2",
          nativeQuery = true)
  Page<User> searchUserByString(String searchTerm, Integer id, Pageable pageable);
}