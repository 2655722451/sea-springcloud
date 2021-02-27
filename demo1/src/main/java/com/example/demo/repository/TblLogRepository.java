package com.example.demo.repository;

import com.example.demo.entity.TblDemo;
import com.example.demo.entity.TblLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Classname TblLogRepository
 * @Description TODO
 * @Date 2021/2/23 14:00
 * @Created by Administrator
 */
public interface TblLogRepository extends JpaRepository<TblLog, Integer> {
}
