package com.Work.work.repository;

import com.Work.work.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,Long> {

}
