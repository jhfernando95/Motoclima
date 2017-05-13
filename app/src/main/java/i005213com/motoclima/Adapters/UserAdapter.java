package i005213com.motoclima.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import i005213com.motoclima.Models.User;
import i005213com.motoclima.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<User> myUser = new ArrayList<>();
    Context context;

    public UserAdapter(Context context, List<User> myUser) {
        this.myUser = myUser;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // holder.myText.setText(myPost.get(position).getTitle());

        holder.myNameUser.setText(myUser.get(position).getUsername());
        holder.myPhone.setText(myUser.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return myUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView myLogo;
        TextView myNameUser;
        TextView myPhone;

        // Constructor
        public ViewHolder(View item)  {
            super(item);

            myLogo = (ImageView) item.findViewById(R.id.logo);
            myNameUser = (TextView) item.findViewById(R.id.txt_name_user);
            myPhone = (TextView) item.findViewById(R.id.txt_email_user);

        }
    }
}
