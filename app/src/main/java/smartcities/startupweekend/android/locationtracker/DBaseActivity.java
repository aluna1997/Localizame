package smartcities.startupweekend.android.locationtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by felipe on 12/11/16.
 */
public class DBaseActivity extends AppCompatActivity {

    @BindView(R.id.mensajeTextV)
    TextView _mensajeTextV;
    @BindView(R.id.mensajeEditT)
    EditText _mensajeEditT;
    @BindView(R.id.buttonube)
    Button _buttonube;


    //Obtener referencia a la raíz
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    //Obtener referencia a una rama
    DatabaseReference mensajeRef = reference.child("mensaje");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ButterKnife.bind(this);


        _buttonube.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                modificar(v);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        mensajeRef.addValueEventListener(new ValueEventListener() {
            //Saber cuando se modifican nuestros datos en la nube
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                _mensajeTextV.setText(value);
            }
            //Saber cuando no pudieron modificarse nuestros datos en la nube
            @Override
            public void onCancelled(DatabaseError databaseError) {

                }
            });

        }


    public void modificar(View view) {
        //Escribir en la base de datos
        /* El método "setValue()" funciona con:
           String,Long,Double,Boolean,Map<String,Objecto>,List<Object> */
        mensajeRef.setValue("Hola Mundo :D!");

        _mensajeEditT.setText("");
    }
}
