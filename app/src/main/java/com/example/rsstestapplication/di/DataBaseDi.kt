package com.example.rsstestapplication.di

import com.example.rsstestapplication.domain.database.getDatabase
import org.koin.dsl.module

val databaseModule = module {

   single { getDatabase(context = get()) }
}