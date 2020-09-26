package com.roots.app.di.module;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.roots.app.mvp.contract.SortContract;
import com.roots.app.mvp.model.SortModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author : bird
 * @Classname : SortModule
 * @Description : TODO
 * @Date : 2020/8/27 2:49
 */
@Module
public class SortModule {

    private SortContract.View view;

    public SortModule(SortContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    public SortContract.Model provideSortModel(IRepositoryManager repositoryManager) {
        return new SortModel(repositoryManager);
    }

    @FragmentScope
    @Provides
    public SortContract.View provideSortView() {
        return view;
    }

//    @FragmentScope
//    @Provides
//    public SortLeftAdapter provideSortLeftAdapter() {
//        return new SortLeftAdapter(new ArrayList<>());
//    }
}
