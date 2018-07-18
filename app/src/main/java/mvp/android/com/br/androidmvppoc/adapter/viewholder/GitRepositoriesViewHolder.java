package mvp.android.com.br.androidmvppoc.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import mvp.android.com.br.androidmvppoc.R;
import mvp.android.com.br.androidmvppoc.domain.Item;
import mvp.android.com.br.androidmvppoc.mvp.presenter.RepositoriesPresenter;
import mvp.android.com.br.androidmvppoc.mvp.base.BasePresenterImpl;


/**
 * Created by rafael.alves on 16/07/2018.
 */

public class GitRepositoriesViewHolder extends AbstractViewHolder<Item> {

    @BindView(R.id.text_view_repository_name)
    TextView textViewRepositoryName;
    @BindView(R.id.text_view_repository_description)
    TextView textViewRepositoryDescription;
    @BindView(R.id.text_view_user_name)
    TextView textViewUserName;
    @BindView(R.id.text_view_number_of_forks)
    TextView textViewNumberOfForks;
    @BindView(R.id.text_view_number_of_stars)
    TextView textViewNumberOfStars;
    @BindView(R.id.text_view_first_name_and_last_name)
    TextView textViewFirstNameAndLastName;
    @BindView(R.id.profile_image)
    ImageView circleImageView;
    ImageLoader imageLoader = ImageLoader.getInstance();

    public GitRepositoriesViewHolder(View itemView, BasePresenterImpl presenter) {
        super(itemView, presenter);
    }

    @Override
    public void bind(final Item item) {

        textViewRepositoryName.setText(item.getName());
        textViewRepositoryDescription.setText(item.getDescription());
        textViewNumberOfForks.setText(item.forksCount.toString());
        textViewNumberOfStars.setText(item.stargazersCount.toString());
        textViewUserName.setText(item.getOwner().getLogin());
        textViewFirstNameAndLastName.setText(item.getFullName());
        imageLoader.displayImage(item.getOwner().getAvatarUrl(), circleImageView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RepositoriesPresenter) getPresenter()).onItemAdapterClick(item);
            }
        });
    }
}
