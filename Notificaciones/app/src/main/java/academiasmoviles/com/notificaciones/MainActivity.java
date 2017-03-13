package academiasmoviles.com.notificaciones;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnToastDefault;
    private Button btnToastGravity;
    private Button btnToastPersonalized;
    private Button btnStatusBar;
    private Button btnSnackbar1;
    private Button btnSnackbar2;
    private Button btnDialogAlert;
    private Button btnDialogConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToastDefault = (Button) findViewById(R.id.btnToastDefault);
        btnToastDefault.setOnClickListener(this);
        btnToastGravity = (Button) findViewById(R.id.btnToastGravity);
        btnToastGravity.setOnClickListener(this);
        btnToastPersonalized = (Button) findViewById(R.id.btnToastPersonalized);
        btnToastPersonalized.setOnClickListener(this);
        btnStatusBar = (Button) findViewById(R.id.btnStatusBar);
        btnStatusBar.setOnClickListener(this);
        btnSnackbar1 = (Button) findViewById(R.id.btnSnackbar1);
        btnSnackbar1.setOnClickListener(this);
        btnSnackbar2 = (Button) findViewById(R.id.btnSnackbar2);
        btnSnackbar2.setOnClickListener(this);
        btnDialogAlert = (Button) findViewById(R.id.btnDialogAlert);
        btnDialogAlert.setOnClickListener(this);
        btnDialogConfirm = (Button) findViewById(R.id.btnDialogConfirm);
        btnDialogConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnToastDefault:

                Toast.makeText(getApplicationContext(), "Toast por defecto.", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnToastGravity:

                Toast toastGravity = Toast.makeText(getApplicationContext(), "Toast con gravedad." , Toast.LENGTH_SHORT);
                toastGravity.setGravity(Gravity.CENTER | Gravity.RIGHT, 0, 0);
                toastGravity.show();

                break;
            case R.id.btnToastPersonalized:

                Toast toastPersonalized = new Toast(getApplicationContext());
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.layout_toast_personalized, (ViewGroup) findViewById(R.id.llContent));
                TextView txtMsg = (TextView)layout.findViewById(R.id.tvMessage);
                txtMsg.setText("Toast Personalizado");
                toastPersonalized.setDuration(Toast.LENGTH_SHORT);
                toastPersonalized.setView(layout);
                toastPersonalized.show();

                break;
            case R.id.btnStatusBar:

                NotificationCompat.Builder mBuilder =
                        (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(android.R.drawable.stat_sys_warning)
                                .setLargeIcon((((BitmapDrawable)getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap()))
                                .setContentTitle("Academias Móviles")
                                .setContentText("Ejemplo de notificación.")
                                .setContentInfo("1")
                                .setTicker("Alerta!");

                Intent notIntent = new Intent(MainActivity.this, MainActivity.class);

                PendingIntent contIntent = PendingIntent.getActivity(MainActivity.this, 0, notIntent, 0);

                mBuilder.setContentIntent(contIntent);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify((new Random()).nextInt(), mBuilder.build());

                break;
            case R.id.btnSnackbar1:

                Snackbar.make(v, "Snackbar 1", Snackbar.LENGTH_SHORT).show();

                break;
            case R.id.btnSnackbar2:

                Snackbar.make(v, "Snackbar 2", Snackbar.LENGTH_SHORT)
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .setAction("Acción", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.i("Snackbar", "Acción ejecutada.");
                            }
                        }).show();

                break;
            case R.id.btnDialogAlert:

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Mensaje de alerta").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

                break;
            case R.id.btnDialogConfirm:

                AlertDialog.Builder builderConfirm = new AlertDialog.Builder(MainActivity.this);

                builderConfirm.setMessage("¿Desea cerrar sesión?")
                        .setTitle("Confirmación")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()  {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("Dialogos", "Confirmacion Aceptada.");
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("Dialogos", "Confirmacion Cancelada.");
                                dialog.cancel();
                            }
                        });
                builderConfirm.show();

                break;
        }
    }

}