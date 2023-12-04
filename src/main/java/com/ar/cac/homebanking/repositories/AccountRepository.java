package com.ar.cac.homebanking.repositories;

        import com.ar.cac.homebanking.models.Account;
        import com.ar.cac.homebanking.models.enums.AccountType;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
        Optional<Account> findByType(AccountType type);

}