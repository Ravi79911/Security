package com.Abdul.Security.Repo;
import com.Abdul.Security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer>
{
      public Optional<User> findByEmail(String email);
      public  Optional<User> findByToken(String token);
      boolean existsByEmail(String email);

}
