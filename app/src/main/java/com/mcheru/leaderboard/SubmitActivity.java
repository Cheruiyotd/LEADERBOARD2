package com.mcheru.leaderboard;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mcheru.leaderboard.retrofitutyls.Project;
import com.mcheru.leaderboard.retrofitutyls.RetroApi;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubmitActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    EditText edtName, edtAddress, edtLink, edtMail;
    //RequestQueue queue;
    Button submitBtn;
    Project signUpResponsesData;
    EditText emailId, password, name;
    Button signUp, submitprojbtn, cancelSubmitting;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_submit2);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_FULLSCREEN);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        dialog = new Dialog(this);
        edtName = findViewById(R.id.name);
        edtMail = findViewById(R.id.editTextTextEmailAddress);
        edtLink = findViewById(R.id.link);
        edtAddress = findViewById(R.id.address);
        submitBtn = findViewById(R.id.submit_form);

        submitBtn.setText(R.string.submt);
        submitBtn.setOnClickListener(view -> {
            // validate the fields and call sign method to implement the api
            if (validate(edtAddress) && validateEmail() && validate(edtLink) && validate(edtName)) {
                openDialog();
            }
        });
    }

    private boolean validateEmail() {
        String email = edtMail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            emailId.setError("Email is not valid.");
            emailId.requestFocus();
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validate(EditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {
            return true; // returns true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }

    private void openDialog() {
        dialog.setContentView(R.layout.confirm_submission);
        //dialog.create();
        submitprojbtn = dialog.findViewById(R.id.yes_button);
        cancelSubmitting = findViewById(R.id.button_cancel_submit);
        try {
            submitprojbtn.setOnClickListener(view -> {
                if (validate(edtAddress) && validateEmail() && validate(edtLink) && validate(edtName)) {
                    submitProject();
                    dialog.create();
                    dialog.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cancelSubmitting.setOnClickListener(view -> {
                //not working
                dialog.dismiss();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog.getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    private void submitProject() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(SubmitActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog


        (RetroApi.getClient().projectEntry(edtName.getText().toString().trim(),
                edtMail.getText().toString().trim(),
                edtAddress.getText().toString().trim(),
                edtLink.getText().toString().trim())).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                signUpResponsesData = response.body();
                if(response.isSuccessful()){
                    dialog.setContentView(R.layout.success_layout);
                    dialog.create();
                    dialog.show();
                    progressDialog.dismiss();
                }
                else {
                    dialog.setContentView(R.layout.not_success_layout);
                    dialog.create();
                    dialog.show();
                }


            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                dialog.setContentView(R.layout.not_success_layout);
                dialog.create();
                dialog.show();
                progressDialog.dismiss();

            }
        });
    }
}

