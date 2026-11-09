// 1) اسم الحزمة
package com.example.douanetemporelle;

// 2) الاستيرادات
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// 3) الشاشة الثانية: فحص المسافر (ماسح المفارقة)
public class VerificationActivity extends AppCompatActivity {

    // 4) اسم المسافر نحتفظ به لنرسله للشاشة الثالثة
    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 5) ربط الملف بواجهة XML
        setContentView(R.layout.activity_verification);

        // 6) ربط العناصر بالـ ids
        TextView tvNom = findViewById(R.id.tv_nom_aa);
        TextView tvAge = findViewById(R.id.tv_age_aa);
        TextView tvMotif = findViewById(R.id.tv_motif_aa);
        Button btnApprouver = findViewById(R.id.btn_approuver_aa);
        Button btnRefuser = findViewById(R.id.btn_refuser_aa);

        // 7) استقبال البيانات بنفس المفاتيح (من Keys وليست مكتوبة مباشرة)
        Intent recu = getIntent();
        nom = recu.getStringExtra(Keys.nom());
        int naissance = recu.getIntExtra(Keys.naissance(), 0);
        int destination = recu.getIntExtra(Keys.destination(), 0);
        String motif = recu.getStringExtra(Keys.motif());

        // 8) حساب العمر الزمني = سنة الوجهة - سنة الميلاد
        int age = destination - naissance;

        // 9) عرض الملخص على التذكرة
        tvNom.setText("Nom : " + nom);
        tvAge.setText("Âge temporel : " + age + " ans");
        tvMotif.setText("Motif : " + motif);

        // 10) تعطيل زر الموافقة منذ التحميل إذا:
        //     السبب "Modification du passé" والفرق عدد أولي
        if (motif != null && motif.equals("Modification du passé") && estPremier(age)) {
            btnApprouver.setEnabled(false);
        }

        // 11) زر الموافقة: الحكم = true
        btnApprouver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirVerdict(true);
            }
        });

        // 12) زر الرفض: الحكم = false
        btnRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirVerdict(false);
            }
        });
    }

    // 13) فتح الشاشة الثالثة بـ Intent صريح مع الحكم (boolean) والاسم
    private void ouvrirVerdict(boolean verdict) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Keys.verdict(), verdict);
        intent.putExtra(Keys.nom(), nom);
        startActivity(intent);
    }

    // 14) اختبار العدد الأولي (الأعداد الأصغر من 2 ليست أولية)
    private boolean estPremier(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}