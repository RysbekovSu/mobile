package com.example.students;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.students.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding binding;
    private int currentAnimationIndex = 0;
    private final int[] animations = {
            R.raw.mainlottie
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        startAnimations();
    }

    private void startAnimations() {
        if (currentAnimationIndex < animations.length) {
            binding.lottieAnimationView.setAnimation(animations[currentAnimationIndex]);
            binding.lottieAnimationView.setRepeatCount(0);  // Ensure it doesn't loop
            binding.lottieAnimationView.playAnimation();
            binding.lottieAnimationView.setSpeed(4.0f);  // Set the speed to 2x
            binding.lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    currentAnimationIndex++;
                    startAnimations();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
        } else {
            navigateToMainActivity();
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}