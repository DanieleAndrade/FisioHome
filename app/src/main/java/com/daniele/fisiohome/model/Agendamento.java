package com.daniele.fisiohome.model;

public class Agendamento {

    private Fisioterapeuta fisioterapeuta;
    private Paciente paciente;
    private Pagamento pagamento;
    private Disponibilidade dataHora;
    private Boolean ativa;

    public Fisioterapeuta getFisioterapeuta() {
        return fisioterapeuta;
    }

    public void setFisioterapeuta(Fisioterapeuta fisioterapeuta) {
        this.fisioterapeuta = fisioterapeuta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Disponibilidade getDataHora() {
        return dataHora;
    }

    public void setDataHora(Disponibilidade dataHora) {
        this.dataHora = dataHora;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }
}
