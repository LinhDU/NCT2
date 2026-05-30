package com.example.nct;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineSongsFragment extends Fragment {

    RecyclerView recyclerView;
    MusicAdapter musicAdapter;
    ArrayList<MusicFiles> onlineFiles = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_songs, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        fetchOnlineSongs();

        return view;
    }

    private void fetchOnlineSongs() {

        RetrofitClient.getApiService().getOnlineSongs()
                .enqueue(new Callback<List<MusicFiles>>() {

                    @Override
                    public void onResponse(Call<List<MusicFiles>> call,
                                           Response<List<MusicFiles>> response) {

                        if (response.isSuccessful() && response.body() != null) {

                            MainActivity.onlineMusicFiles.clear();
                            MainActivity.onlineMusicFiles.addAll(response.body());

                            onlineFiles = new ArrayList<>(response.body());

                            // Đánh dấu nhạc online
                            for (MusicFiles f : MainActivity.onlineMusicFiles) {
                                f.setOnline(true);
                            }

                            musicAdapter = new MusicAdapter(
                                    getContext(),
                                    MainActivity.onlineMusicFiles,
                                    "onlineSongs"
                            );

                            recyclerView.setLayoutManager(
                                    new LinearLayoutManager(
                                            getContext(),
                                            RecyclerView.VERTICAL,
                                            false
                                    )
                            );

                            recyclerView.setAdapter(musicAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MusicFiles>> call, Throwable t) {

                        Toast.makeText(
                                getContext(),
                                "Không thể kết nối Server Online",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchOnlineSongs();
    }
}