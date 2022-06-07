package com.example.rsstestapplication.di

import com.example.rsstestapplication.domain.repository.RssesRepository
import org.koin.dsl.module

val repositoryModule = module {

  single {
     RssesRepository(database = get())
   }
}