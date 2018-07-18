package mvp.android.com.br.androidmvppoc.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.android.com.br.androidmvppoc.R;
import mvp.android.com.br.androidmvppoc.domain.GitPullRequests;
import mvp.android.com.br.androidmvppoc.mvp.presenter.PullRequestPresenterImpl;


/**
 * Created by rafael.alves on 16/01/2018.
 */

public class GitPullRequestsViewHolder extends AbstractViewHolder<GitPullRequests> {

    @BindView(R.id.text_view_repository_name)
    TextView textViewRepositoryName;
    @BindView(R.id.text_view_repository_description)
    TextView textViewRepositoryDescription;
    @BindView(R.id.text_view_date_pr)
    TextView textViewDatePR;
    @BindView(R.id.text_view_user_name)
    TextView textViewUserName;
    @BindView(R.id.profile_image)
    ImageView circleImageView;

    private ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance*/

    public GitPullRequestsViewHolder(View itemView, PullRequestPresenterImpl presenter) {
        super(itemView, presenter);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(final GitPullRequests item) {
        textViewRepositoryName.setText(item.getTitle());
        textViewRepositoryDescription.setText(item.getBody());
        textViewUserName.setText(item.getUser().getLogin());
        imageLoader.displayImage(item.getUser().getAvatarUrl(), circleImageView);

        Date date = item.getCreatedAt();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormated = format.format(date);
        textViewDatePR.setText(dateFormated);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PullRequestPresenterImpl) getPresenter()).onItemAdapterClick(item);
            }
        });
    }
}
