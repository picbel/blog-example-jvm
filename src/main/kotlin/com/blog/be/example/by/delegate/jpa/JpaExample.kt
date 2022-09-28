package com.blog.be.example.by.delegate.jpa


import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.ManyToOne

interface AuthorJpaDao : JpaRepository<AuthorEntity, Long>

class AuthorDao(
    private val authorJpaDao: AuthorJpaDao
) : AuthorJpaDao by authorJpaDao



@Entity
class AuthorEntity(
    @Id
    val id:Long?,
    val name: String
)

@Entity
class BookEntity(
    @Id
    val id:Long?,
    val title: String,
    @ManyToOne(fetch = FetchType.LAZY)
    val author: AuthorEntity
)