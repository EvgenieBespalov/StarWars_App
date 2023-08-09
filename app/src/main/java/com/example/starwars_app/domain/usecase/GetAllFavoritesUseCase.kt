package com.example.starwars_app.domain.usecase

import com.example.starwars_app.domain.entity.DatabaseEntity
import com.example.starwars_app.domain.repository.DatabaseRepository

class GetAllFavoritesUseCase(
    private val repository: DatabaseRepository
) {
    suspend operator fun invoke(): List<DatabaseEntity> = repository.getAll()
}