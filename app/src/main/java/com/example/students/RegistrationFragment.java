package com.example.students;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.students.databinding.FragmentRegistrationBinding;
import com.example.students.model.User;
import com.example.students.remote_data.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrationFragment extends Fragment {
    FragmentRegistrationBinding binding;

    User newUser;
    private boolean isPasswordVisible = false;
    NavController navController;

//    public RegisterFragment() {
//        // Required empty public constructor
//    }

//    public static RegisterFragment newInstance(String param1, String param2) {
//        RegisterFragment fragment = new RegisterFragment();
//
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentRegistrationBinding
                .inflate(inflater,container,false);
        View root=binding.getRoot();
        binding.imageViewShowPassword.setOnClickListener(v -> togglePasswordVisibility());

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstancesState){
        super.onViewCreated(view,savedInstancesState);
        binding.registerButton.setOnClickListener(v -> {
//            binding.progressBar.setVisibility(View.VISIBLE);
            if (!IsEmptyEditTextRegistration()){
                registerToTable();
            }
        });
        binding.toLogin.setOnClickListener(v -> {
            navController= Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_registrationFragment_to_loginFragment);
        });
    }

    private void registerToTable() {
        newUser=new User(binding.nameUser.getText().toString(),
                binding.email.getText().toString(),
                binding.password.getText().toString());
        Call<User> callCreateUser = RetrofitBuilder.getInstance().getApi().registrationNewUser(newUser);
        callCreateUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        Toast.makeText(requireActivity(),"Registration was successful",Toast.LENGTH_LONG).show();
                        SharedPreferences preferences= getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor prefLoginEdit=preferences.edit();
                        prefLoginEdit.putBoolean("registration",true);
                        prefLoginEdit.commit();
                        navController = Navigation.findNavController(requireActivity(),
                                    R.id.nav_host_fragment_activity_main);
                        navController.navigate(R.id.action_registrationFragment_to_loginFragment);
                    }
                }else {
                    Log.e("fail","fail");
                    Toast.makeText(requireActivity(),"Something is fiilled wrong!!!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                Log.e("failure","failure");
                Toast.makeText(requireActivity(),"Registration was failed",Toast.LENGTH_LONG).show();
            }
        });
    }
    private boolean IsEmptyEditTextRegistration(){
        if(binding.nameUser.getText().toString().isEmpty() ||
                binding.email.getText().toString().isEmpty() ||
                binding.password.getText().toString().isEmpty()){
            Toast toast=Toast.makeText(getActivity(),
                    "Заполните поля для регистрации",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            return true;
        }else {
            return false;
        }
    }

    private void togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible;
        int drawable = isPasswordVisible ? R.drawable.hidden : R.drawable.hidden;
        binding.imageViewShowPassword.setImageResource(drawable);
        binding.password.setTransformationMethod(isPasswordVisible ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
        binding.password.setSelection(binding.password.getText().length());
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }
}