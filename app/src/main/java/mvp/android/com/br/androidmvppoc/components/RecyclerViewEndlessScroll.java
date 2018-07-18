package mvp.android.com.br.androidmvppoc.components;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by rafael.alves on 16/01/2018.
 */

public class RecyclerViewEndlessScroll extends RecyclerView {

    public boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private Context context;
    private boolean noMoreItems;
    private LinearLayoutManager mLayoutManager;
    private EndlessScrollListener endlessScrollListener;

    public RecyclerViewEndlessScroll(Context context) {
        super(context);
        this.context = context;
    }

    public RecyclerViewEndlessScroll(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public RecyclerViewEndlessScroll(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public void addEndlessScroll(final LinearLayoutManager mLayoutManager,
                                 final EndlessScrollListener listener) {
        this.mLayoutManager = mLayoutManager;
        this.endlessScrollListener = listener;

        setLayoutManager(mLayoutManager);

        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading && !noMoreItems) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            if (listener != null) {
                                listener.onEndlessScroll();
                            }
                        }
                    }
                }
            }
        });
    }

    public void scrollToPosition(final int position, Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mLayoutManager != null) {
                    mLayoutManager.scrollToPosition(position);
                }
            }
        });
    }

    public EndlessScrollListener getEndlessScrollListener() {
        return endlessScrollListener;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public interface EndlessScrollListener {
        void onEndlessScroll();
    }

    public void setNoMoreItems(boolean noMoreItems) {
        this.noMoreItems = noMoreItems;
    }
}
