package com.example.apipokemondata.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apipokemondata.Common.Common;
import com.example.apipokemondata.Interface.ItemClickListener;
import com.example.apipokemondata.Model.Pokemon;
import com.example.apipokemondata.R;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {

    Context context;
    List<Pokemon> pokemonList;

    public PokemonListAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        // se cargan las imagenes de los pokemones
        Glide.with(context).load(pokemonList
                .get(position).getImg())
                .into(holder.pokemon_image);
        // se le pone el nombre
        holder.pokemon_name.setText(pokemonList.get(position).getName());
        //evento
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Toast.makeText(context,"Click at Pokemon: " +pokemonList.get(position).getName(),Toast.LENGTH_LONG).show();

                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(new Intent(Common.KEY_ENABLE_HOME).putExtra("position",position));


            }
        });

    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView pokemon_image;
        TextView pokemon_name;

        ItemClickListener itemClickListener;

        public  void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener= itemClickListener;
        }


        public MyViewHolder(View itemView) {
            super(itemView);

            pokemon_image = itemView.findViewById(R.id.pokemon_image);
            pokemon_name = itemView.findViewById(R.id.txt_pokemon_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }
}
