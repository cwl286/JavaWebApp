package com.demo.model;

import org.springframework.data.repository.CrudRepository;
import com.demo.model.Holidays;


public interface HolidaysRepository extends CrudRepository<Holidays, Integer> {

}