package com.teamsparta.jangtrello.infra

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
class BaseEntity {

    // column name = 자동으로 mapping 됨
    @CreatedDate
    @Column(updatable = false)
    var createdAt : LocalDateTime = LocalDateTime.now()

    @CreatedBy
    @Column(updatable = false)
    var createdBy : Long? = null

    @LastModifiedDate
    var modifiedAt : LocalDateTime? = LocalDateTime.now()

    @LastModifiedBy
    var modifiedBy : Long? = null
}

