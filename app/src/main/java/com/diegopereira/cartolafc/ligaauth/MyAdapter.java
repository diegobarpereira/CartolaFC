package com.diegopereira.cartolafc.ligaauth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.R;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List<Ligas> ligas;

    public MyAdapter(Context context, List<Ligas> ligas) {
        this.context = context;
        this.ligas = ligas;
    }


    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;

        } else if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position > ligas.size();
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ligaauth_item, viewGroup, false);
            return new ItemViewHolder(view);

        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ligaauth_header, viewGroup, false);
            return new HeaderViewHolder(view);

        } else if (viewType == TYPE_FOOTER) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ligaauth_footer,
                    viewGroup, false);
            return new FooterViewHolder(view);

        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof HeaderViewHolder) {

            //set the Value from List to corresponding UI component as shown below.
            String nomeliga = null;
            if(ligas.get(position).getTime_dono_id() == null) {
                nomeliga = "LIGAS PÚBLICAS";
            }
            else {
                nomeliga = "LIGAS PRIVADAS";
            }
            ((HeaderViewHolder) holder).nameliga.setText(nomeliga);

            //similarly bind other UI components or perform operations

        }else if (holder instanceof ItemViewHolder) {
            String liga = ligas.get(position).getNome();
            ((ItemViewHolder) holder).tv_liga.setText(liga);

            // Your code here

        }else if (holder instanceof FooterViewHolder) {

            //your code here
        }


    }


    @Override
    public int getItemCount() {
        // Add two more counts to accomodate header and footer
        return this.ligas.size();
    }


    // The ViewHolders for Header, Item and Footer
    class HeaderViewHolder extends RecyclerView.ViewHolder {
        public View View;
        private final TextView nameliga;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            View = itemView;

            // add your ui components here like this below
            nameliga = (TextView) View.findViewById(R.id.nameliga);

        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public View View;
        public TextView tv_liga;
        public ItemViewHolder(View v) {
            super(v);
            View = v;
            // Add your UI Components here
            tv_liga = (AppCompatTextView)itemView.findViewById(R.id.tv_liga);

        }

    }


    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public View View;
        public FooterViewHolder(View v) {
            super(v);
            View = v;
            // Add your UI Components here
        }

    }
}
