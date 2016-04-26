package griffin.wilson.jobmarket;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JobMarketViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public LinearLayout languageLayout;

        public JobMarketViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            languageLayout = (LinearLayout) itemView.findViewById(R.id.language_layout);
        }
    }