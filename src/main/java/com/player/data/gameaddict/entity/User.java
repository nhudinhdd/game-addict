package com.player.data.gameaddict.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_mst")
@Data
public class User extends BaseEntity{
    @Id
    @Column(name = "user_id")
    private String userID;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "role")
    private String role;
}
