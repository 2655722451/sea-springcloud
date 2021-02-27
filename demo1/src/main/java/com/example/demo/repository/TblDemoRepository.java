package com.example.demo.repository;

import com.example.demo.entity.TblDemo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Classname TblDemoRepository
 * @Description TODO
 * @Date 2021/2/23 9:56
 * @Created by Administrator
 */
public interface TblDemoRepository extends JpaRepository<TblDemo, Integer> {

}
