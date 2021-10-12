package com.pucmm.examenandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.pucmm.examenandroid.placeholder.PlaceholderContent;
import com.pucmm.examenandroid.placeholder.PlaceholderContentExamen;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private PlaceholderContentExamen.PlaceholderVersion element;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ItemDetailFragment() {
        // Required empty public constructor
    }

    public static ItemDetailFragment newInstance(PlaceholderContentExamen.PlaceholderVersion element) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("element", element);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            element = (PlaceholderContentExamen.PlaceholderVersion) getArguments().getSerializable("element");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        TextView details = view.findViewById(R.id.detailsFragment);
        TextView internalCode = view.findViewById(R.id.internalCode);
        TextView version = view.findViewById(R.id.versionText);
        TextView releaseDate = view.findViewById(R.id.releaseDateText);
        CheckBox supportedCheckBox = view.findViewById(R.id.supportedCheckBox);
        Button link = view.findViewById(R.id.linkButton);

        details.setText(element.getDetails());
        internalCode.setText(element.getInternalCodeName());
        version.setText(element.getVersionNumber());
        releaseDate.setText(element.getReleaseDate().toString());
        supportedCheckBox.setChecked(element.isSupported());
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = element.getLink();

                Intent openBrowser = new Intent(Intent.ACTION_VIEW);
                openBrowser.setData(Uri.parse(url));
                startActivity(openBrowser);
            }
        });
        return view;
    }
}