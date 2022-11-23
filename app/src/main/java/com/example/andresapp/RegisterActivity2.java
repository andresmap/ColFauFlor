package com.example.andresapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity2 extends AppCompatActivity {

    CircleImageView mcircleImageView;
    TextInputEditText mTextInputUsername;
    TextInputEditText mTextInputEmailR;
    TextInputEditText mTextInputPaswordR;
    TextInputEditText mTextInputConfirPaswordR;
    Button mButtonRegister;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        mcircleImageView =findViewById(R.id.circleimageback);
        mTextInputUsername =findViewById(R.id.textInputUsername);
        mTextInputEmailR =findViewById(R.id.textInputEmailR);
        mTextInputPaswordR =findViewById(R.id.textinputPaswordR);
        mTextInputConfirPaswordR =findViewById(R.id.textinputConfirPaswordR);
        mButtonRegister =findViewById(R.id.buttonRegister);
        mAuth =FirebaseAuth.getInstance();

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        mcircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void register() {

        String username =mTextInputUsername.getText().toString();
        String email =mTextInputEmailR.getText().toString();
        String pasword =mTextInputPaswordR.getText().toString();
        String confirmpasword =mTextInputConfirPaswordR.getText().toString();

        if(!username.isEmpty() && !email.isEmpty() && !pasword.isEmpty() && !confirmpasword.isEmpty()) {
            if (isEmailValid(email)){
                if (pasword.equals(confirmpasword)){
                    if (pasword.length()>=6){
                        createUser(email,pasword);
                    } else {
                        Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Las contraseñas no coinceden", Toast.LENGTH_SHORT).show();

                }

            }
            else {
                Toast.makeText(this, "insertó todos los campos pero el email no es valido", Toast.LENGTH_SHORT).show();
            }

        }
        else {
            Toast.makeText(this, "Para continuar complete toda la información", Toast.LENGTH_SHORT).show();
        }

    }

    private void createUser(String email, String pasword) {
        mAuth.createUserWithEmailAndPassword(email, pasword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity2.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity2.this, "El usuario no se puedo registrar", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}