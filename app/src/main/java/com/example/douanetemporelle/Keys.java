package com.example.douanetemporelle;

public class Keys {

    // آخر 4 أرقام من بطاقة الطالب
    public static final String MATRICULE = "3437";

    // دالة static تبني المفتاح: الرقم + "_" + الاسم
    public static String buildKey(String matricule, String name) {
        return matricule + "_" + name;
    }

    // مفاتيح جاهزة للـ 3 Activities
    public static String nom()         { return buildKey(MATRICULE, "NOM_VOYAGEUR"); }
    public static String naissance()   { return buildKey(MATRICULE, "ANNEE_NAISSANCE"); }
    public static String destination() { return buildKey(MATRICULE, "ANNEE_DESTINATION"); }
    public static String motif()       { return buildKey(MATRICULE, "MOTIF"); }
    public static String verdict()     { return buildKey(MATRICULE, "VERDICT"); }
}
