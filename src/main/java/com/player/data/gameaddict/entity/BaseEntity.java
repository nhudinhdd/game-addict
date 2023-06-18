package com.player.data.gameaddict.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity  implements Persistable<String> {

    @Column(name = "sys_create_dt")
    private LocalDateTime sysCreateDate = LocalDateTime.now();
    @Column(name = "sys_create_id")
    private String sysCreateID = "admin";
    @Column(name = "sys_update_dt")
    private LocalDateTime sysUpdateDate = LocalDateTime.now();
    @Column(name = "sys_update_id")
    private String sysUpdateID = "admin";
    @Transient
    private boolean isNew = false;
    @Override
    public boolean isNew() {
        return isNew;
    }
}
