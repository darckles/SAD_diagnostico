package br.com.sad.fragment;

import java.util.ArrayList;
import java.util.List;

import br.com.sad.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.app.ListFragment;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class Lista_Sintomas extends Fragment {

	private List<String> mDataSourceList = new ArrayList<String>();  
    private List<FragmentTransaction> mBackStackList = new ArrayList<FragmentTransaction>();  
   
   
    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        return inflater.inflate(R.layout.activity_sintomas, container, false);  
    }  
       
       
    @Override 
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
           
        //add data to ListView  
        for(int i=0, count=20; i<count; i++){  
            mDataSourceList.add("" + i);  
        }  
           
         
        ListView listView = (ListView) getActivity().findViewById(R.id.ListaSintomas);  
        listView.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mDataSourceList));  
           
        listView.setOnItemClickListener(new OnItemClickListener() {  
   
            @Override 
            public void onItemClick(AdapterView<?> parent, View view,  
                    int position, long id) {  
                //create a Fragment  
                Fragment detailFragment = new Fragment();  
                   
               
                Bundle mBundle = new Bundle();  
                mBundle.putString("arg", mDataSourceList.get(position));  
                detailFragment.setArguments(mBundle);  
                   
                final FragmentManager fragmentManager = getActivity().getFragmentManager();  
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();  
                   
                //check if the device is landscape or portrait 
                Configuration configuration = getActivity().getResources().getConfiguration();  
                int ori = configuration.orientation;  
                   
                fragmentTransaction.replace(R.id.content_frame, detailFragment);  
                   
                if(ori == configuration.ORIENTATION_PORTRAIT){  
                    fragmentTransaction.addToBackStack(null);  
                }  
                   
                fragmentTransaction.commit();  
                   
                   
            }  
        });  
           
    }  
       
    /** 
     *  
     * @param msg 
     */ 
    private void showTost(String msg){  
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();  
    }  
   
}  