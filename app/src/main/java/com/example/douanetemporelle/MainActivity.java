// 1) اسم الحزمة
package com.example.douanetemporelle;

// 2) الاستيرادات
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// 3) الشاشة الأولى: استمارة التسجيل
public class MainActivity extends AppCompatActivity {

    // 4) تعريف عناصر الواجهة
    private EditText etNom, etNaissance, etDestination;
    private RadioGroup rgMotif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 5) ربط الملف بواجهة XML
        setContentView(R.layout.activity_main);

        // 6) ربط كل متغير بالعنصر الذي يحمل نفس الـ id
        etNom = findViewById(R.id.et_nom_aa);
        etNaissance = findViewById(R.id.et_naissance_aa);
        etDestination = findViewById(R.id.et_destination_aa);
        rgMotif = findViewById(R.id.rg_motif_aa);
        btnInspecter = findViewById(R.id.btn_inspecter_aa);

        // 7) ماذا يحدث عند الضغط على الزر
        btnInspecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inspecter();
            }
        });
    }

    private void inspecter() {
        // 8) قراءة النصوص من الخانات
        String nom = etNom.getText().toString().trim();
        String naissance = etNaissance.getText().toString().trim();
        String destination = etDestination.getText().toString().trim();

        // 9) التحقق أن كل الخانات معمّرة
        if (nom.isEmpty() || naissance.isEmpty() || destination.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // 10) معرفة سبب السفر المختار
        RadioButton choisi = findViewById(rgMotif.getCheckedRadioButtonId());
        String motif = choisi.getText().toString();

        // 11) إنشاء Intent صريح نحو الشاشة الثانية
        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

        // 12) إرسال البيانات بمفاتيح مبنية من Keys (وليس مكتوبة مباشرة)
        intent.putExtra(Keys.nom(), nom);
        intent.putExtra(Keys.naissance(), Integer.parseInt(naissance));
        intent.putExtra(Keys.destination(), Integer.parseInt(destination));
        intent.putExtra(Keys.motif(), motif);

        // 13) فتح الشاشة الثانية
        startActivity(intent);
    }
}