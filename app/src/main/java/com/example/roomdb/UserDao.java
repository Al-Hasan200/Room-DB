package com.example.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao{
    @Insert
    void addData(User user);

    @Query("update User set name = :name, email = :email where id = :id")
    void updateData(int id, String name, String email);
    //@Update
    //void updateData(User user);

    @Query("delete from user where id=:id")
    void deleteData(int id);

    @Query("select * from user")
    List<User> getAllData();
}
