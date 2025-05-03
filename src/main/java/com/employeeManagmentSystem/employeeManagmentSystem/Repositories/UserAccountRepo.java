package com.employeeManagmentSystem.employeeManagmentSystem.Repositories;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public interface UserAccountRepo extends JpaRepository <UserAccount,UUID> {


}
