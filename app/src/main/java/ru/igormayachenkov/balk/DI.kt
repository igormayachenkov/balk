package ru.igormayachenkov.balk

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.igormayachenkov.balk.data.BalkRepository
import ru.igormayachenkov.balk.data.InMemoryBalkRepository
import javax.inject.Singleton

// PROVIDE IMPLEMENTATION INSTANCES
// Which can be used by @Inject constructor()
@Module
@InstallIn(SingletonComponent::class)
object RepoImpl{
    @Singleton
    @Provides
    fun provideInMemoryBalkRepository()  : InMemoryBalkRepository =
        InMemoryBalkRepository()
}

// BIND REPO INTERFACES TO IMPLEMENTATIONS
@Module
@InstallIn(SingletonComponent::class)
abstract class RepoBindingModule {
    @Singleton
    @Binds
    abstract fun bindBalkRepo(impl : InMemoryBalkRepository) : BalkRepository
}