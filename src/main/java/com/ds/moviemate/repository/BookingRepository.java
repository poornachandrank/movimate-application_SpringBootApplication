package com.ds.moviemate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ds.moviemate.model.BookingEntity;
import com.ds.moviemate.model.UserEntity;
@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Long> {

	List<BookingEntity> findByUserId(UserEntity userId);

	

}
