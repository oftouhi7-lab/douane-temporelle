// 1) اسم الحزمة
package com.example.douanetemporelle;

// 2) الاستيرادات
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// 3) الشاشة الثالثة: الحكم النهائي
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 4) ربط الملف بواجهة XML
        setContentView(R.layout.activity_result);

        // 5) ربط العناصر بالـ ids
        LinearLayout racine = findViewById(R.id.ll_racine_aa);
        TextView tvMessage = findViewById(R.id.tv_message_aa);
        Button btnNouveau = findViewById(R.id.btn_nouveau_aa);

        // 6) استقبال الحكم (boolean) والاسم بنفس المفاتيح من Keys
        boolean verdict = getIntent().getBooleanExtra(Keys.verdict(), false);
        String nom = getIntent().getStringExtra(Keys.nom());

        // 7) مقبول: خلفية خضراء ورسالة "Bon voyage"
        //    مرفوض: خلفية حمراء ورسالة الاعتقال الزمني
        if (verdict) {
            racine.setBackgroundColor(Color.parseColor("#2E7D32"));
            // إضافة منا: رمز ✔ مع رسالة الموافقة
            tvMessage.setText("✔\nBon voyage " + nom);
        } else {
            racine.setBackgroundColor(Color.parseColor("#C62828"));
            // إضافة منا: رمز ✖ مع رسالة الاعتقال
            tvMessage.setText("✖\n" + nom + " est en état d'arrestation temporelle");
        }
        // 8) زر "Nouveau voyageur": نرجع إلى MainActivity ونغلق الشاشات السابقة
        btnNouveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                // 9) CLEAR_TOP + NEW_TASK: يمسح الـ Activities السابقة من المكدّس
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                // 10) نغلق الشاشة الحالية
                finish();
            }
        });
    }
}