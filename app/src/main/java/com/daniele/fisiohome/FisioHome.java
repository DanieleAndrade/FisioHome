package com.daniele.fisiohome;

import android.content.Context;
import android.app.Application;

import com.daniele.fisiohome.model.Disponibilidade;
import com.daniele.fisiohome.model.Fisioterapeuta;
import com.daniele.fisiohome.model.Paciente;

public class FisioHome extends Application {

    private static Context context;
    private static Fisioterapeuta fisioterapeutaAtual;
    private static Disponibilidade disponibilidadeAtual;
    private static String dataAtual;

    private static Paciente paciente;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        FisioHome.context = context;
    }

    public static Fisioterapeuta getFisioterapeutaAtual() {
        return fisioterapeutaAtual;
    }

    public static void setFisioterapeutaAtual(Fisioterapeuta fisioterapeutaAtual) {
        FisioHome.fisioterapeutaAtual = fisioterapeutaAtual;
    }

    public static Disponibilidade getDisponibilidadeAtual() {
        return disponibilidadeAtual;
    }

    public static void setDisponibilidadeAtual(Disponibilidade disponibilidadeAtual) {
        FisioHome.disponibilidadeAtual = disponibilidadeAtual;
    }

    public static Paciente getPaciente() {
        return paciente;
    }

    public static void setPaciente(Paciente paciente) {
        FisioHome.paciente = paciente;
    }

    public static String getDataAtual() {
        return dataAtual;
    }

    public static void setDataAtual(String dataAtual) {
        FisioHome.dataAtual = dataAtual;
    }
}
