package com.zeroillusion.minesweeperlite.di

import android.app.Application
import com.zeroillusion.minesweeperlite.data.repository.MinesweeperLiteRepositoryImpl
import com.zeroillusion.minesweeperlite.domain.repository.MinesweeperLiteRepository
import com.zeroillusion.minesweeperlite.domain.use_case.GetGameMode
import com.zeroillusion.minesweeperlite.domain.use_case.GetGameState
import com.zeroillusion.minesweeperlite.domain.use_case.GetRecords
import com.zeroillusion.minesweeperlite.domain.use_case.SaveRecords
import com.zeroillusion.minesweeperlite.domain.use_case.SetGameMode
import com.zeroillusion.minesweeperlite.domain.use_case.UpdateBoard
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MinesweeperLiteModule {

    @Provides
    @Singleton
    fun provideGetGameMode(repository: MinesweeperLiteRepository): GetGameMode {
        return GetGameMode(repository)
    }

    @Provides
    @Singleton
    fun provideGetGameState(repository: MinesweeperLiteRepository): GetGameState {
        return GetGameState(repository)
    }

    @Provides
    @Singleton
    fun provideGetRecords(repository: MinesweeperLiteRepository): GetRecords {
        return GetRecords(repository)
    }

    @Provides
    @Singleton
    fun provideSaveRecords(repository: MinesweeperLiteRepository): SaveRecords {
        return SaveRecords(repository)
    }

    @Provides
    @Singleton
    fun provideSetGameMode(repository: MinesweeperLiteRepository): SetGameMode {
        return SetGameMode(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateBoard(repository: MinesweeperLiteRepository): UpdateBoard {
        return UpdateBoard(repository)
    }

    @Provides
    @Singleton
    fun provideMinesweeperLiteRepository(app: Application): MinesweeperLiteRepository {
        return MinesweeperLiteRepositoryImpl(app)
    }
}