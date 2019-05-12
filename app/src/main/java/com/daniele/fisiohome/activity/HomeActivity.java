package com.daniele.fisiohome.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.daniele.fisiohome.FisioHome;
import com.daniele.fisiohome.R;
import com.daniele.fisiohome.adapters.FisioterapeutasAdapter;
import com.daniele.fisiohome.helper.ConfiguracaoFirebase;
import com.daniele.fisiohome.model.Fisioterapeuta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ListView listViewFisioterapeutas;
    ProgressDialog progressDialog;
    FisioterapeutasAdapter fisioterapeutasAdapter;
    List<Fisioterapeuta> fisioterapeutasLista;
    DatabaseReference fisioterapeutasRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listViewFisioterapeutas = findViewById(R.id.listview_fisioterapeutas);

        fisioterapeutasRef = FirebaseDatabase.getInstance().getReference("fisioterapeutas");

        fisioterapeutasLista = new ArrayList<>();

       // buscarFisioterapeutas();
    }

    @Override
    protected void onStart() {

        super.onStart();

        fisioterapeutasRef.addValueEventListener( new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                fisioterapeutasLista.clear();

                for(DataSnapshot fisioterapeutaSnapshot : dataSnapshot.getChildren()) {

                    Fisioterapeuta fisioterapeuta = fisioterapeutaSnapshot.getValue(Fisioterapeuta.class);
                    fisioterapeutasLista.add(fisioterapeuta);
                }

                FisioterapeutasAdapter adapter = new FisioterapeutasAdapter(HomeActivity.this, fisioterapeutasLista);

                listViewFisioterapeutas.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        listViewFisioterapeutas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToDetalhesFisio(fisioterapeutasLista.get(position));
            }
        });
    }

    public void goToDetalhesFisio(Fisioterapeuta fisioterapeuta) {
        FisioHome.setFisioterapeutaAtual(fisioterapeuta);
        Intent intent = new Intent(this, DetalheFisioterapeutaActivity.class);
        startActivity(intent);
    }

//    private void buscarFisioterapeutas() {
//
//        class SendPostReqAsyncTask extends AsyncTask<String, Void, List<Fisioterapeuta>> {
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                progressDialog = new ProgressDialog(HomeActivity.this);
//                progressDialog.setMessage("Recuperando fisioterapeutas");
//                progressDialog.show();
//            }
//
//            @Override
//            protected List<Fisioterapeuta> doInBackground(String... params) {
//
//                try {
//                    return Fisioterapeuta.getFisioterapeutas();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                return null;
//            }
//
//            protected void onPostExecute(List<Fisioterapeuta> fisioterapeutas) {
//                super.onPostExecute(fisioterapeutas);
//
//                if (fisioterapeutas != null && fisioterapeutas.size() > 0) {
//                    progressDialog.dismiss();
//                    fisioterapeutasAdapter = new FisioterapeutasAdapter(HomeActivity.this, fisioterapeutas);
//                    listViewFisioterapeutas.setAdapter(fisioterapeutasAdapter);
//
//                    fisioterapeutasLista = new ArrayList<Fisioterapeuta>();
//                    fisioterapeutasLista = fisioterapeutas;
//                } else {
//                    progressDialog.dismiss();
//                    Toast.makeText(HomeActivity.this, "Não foi possivel recuperar os fisioterapeutas. " +
//                            "Verifique sua conexão com a internet.", Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//
//        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
//        sendPostReqAsyncTask.execute();
//    }
}
