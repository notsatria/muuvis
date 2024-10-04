package com.notsatria.search

import android.content.Context
import com.notsatria.muuvis.di.SearchModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [SearchModuleDependencies::class])
interface SearchComponent {

    fun inject(activity: SearchActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(searchModuleDependencies: SearchModuleDependencies): Builder
        fun build(): SearchComponent
    }

}