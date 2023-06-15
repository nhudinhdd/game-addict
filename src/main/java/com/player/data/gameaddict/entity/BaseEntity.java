package com.player.data.gameaddict.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Column(name = "sys_create_dt")
    private LocalDateTime sysCreateDate = LocalDateTime.now();
    @Column(name = "sys_create_id")
    private String sysCreateID = "admin";
    @Column(name = "sys_update_dt")
    private LocalDateTime sysUpdateDate = LocalDateTime.now();
    @Column(name = "sys_update_id")
    private String sysUpdateID = "admin";
}
