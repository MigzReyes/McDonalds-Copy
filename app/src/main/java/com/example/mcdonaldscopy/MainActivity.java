package com.example.mcdonaldscopy;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ViewPager2 viewPager = findViewById(R.id.viewPager);

        // Add images to the list
        List<Integer> images = Arrays.asList(
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3
        );

        SlideShow adapter = new SlideShow(this, images);
        viewPager.setAdapter(adapter);

        autoSlide(viewPager, images.size());
    }

    private void autoSlide(ViewPager2 viewPager, int itemCount) {
        final int delay = 3000; // 3 seconds
        viewPager.postDelayed(new Runnable() {
            int currentItem = 0;

            @Override
            public void run() {
                currentItem = (currentItem + 1) % itemCount;
                viewPager.setCurrentItem(currentItem, true);
                viewPager.postDelayed(this, delay);
            }
        }, delay);
    }
}