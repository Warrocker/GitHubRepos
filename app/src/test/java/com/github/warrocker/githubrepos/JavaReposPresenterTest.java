package com.github.warrocker.githubrepos;

import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem;
import com.github.warrocker.githubrepos.ui.reposScreen.IReposModel;
import com.github.warrocker.githubrepos.ui.reposScreen.IReposView;
import com.github.warrocker.githubrepos.ui.reposScreen.ReposPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Warrocker on 11.12.2017.
 */
// this test here cause kotlin test don`t work
@RunWith(MockitoJUnitRunner.class)
public class JavaReposPresenterTest {
    @Mock
    private IReposView view;
    @Mock
    private IReposModel model;
    private ReposPresenterImpl presenter;

    @Before
    public void setUp() {
        presenter = new ReposPresenterImpl(model, view);
    }

    @Test
    public void progressBarShowTest() {
        presenter.onSearchCalled("test");
        verify(view, times(1)).startProgressBar();
    }

    @Test
    public void progressBarStopTestOnFinished() {
        presenter.onLoadFinished(new ArrayList<RepoItem>());
        verify(view, times(1)).stopProgressBar();
    }

    @Test
    public void progressBarStopTestOnCancelled() {
        presenter.onSearchCancelled();
        verify(view, times(1)).stopProgressBar();
    }

    @Test
    public void progressBarStopTestOnInterrupted() {
        presenter.onLoadInterrupted();
        verify(view, times(1)).stopProgressBar();
    }
    @Test
    public void checkDataUpdating(){
        List<RepoItem> items = new ArrayList<RepoItem>();
        presenter.onLoadFinished(items);
        verify(view, times(1)).updateViewWithData(items);
        verify(view, times(1)).stopProgressBar();
    }
}
