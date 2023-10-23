package sistemaCaptura;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;
import sistemaCaptura.conexao.Conexao;
//import sistemaCaptura.telasSistema.TelaMonitorDeRecursos;


import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class HistConsmRecurso {

    private Integer id;
    private String sistemaOperacional;
    private Double consumoCpu;
    private Double consumoDisco;
    private Double consumoRam;
    private Double totalCpu;
    private Double totalDisco;
    private Double totalRam;
    private Integer totalJanelasAbertas;
    private LocalDateTime dataHora = LocalDateTime.now();

    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoDoBanco();
    Looca looca = new Looca();
    Timer timer = new Timer();
    private Integer idVolatil;
//    TelaMonitorDeRecursos telaMonitorDeRecursos = new TelaMonitorDeRecursos();

    public HistConsmRecurso() {
    }

    public HistConsmRecurso(Integer id, Integer idVolatil,String sistemaOperacional , Double consumoCpu, Double consumoDisco, Double consumoRam, Double totalCpu,
                            Double totalDisco, Double totalRam, Integer totalJanelasAbertas, LocalDateTime dataHora) {
        this.id = id;
        this.idVolatil = idVolatil;
        this.sistemaOperacional= sistemaOperacional;
        this.consumoCpu = consumoCpu;
        this.consumoDisco = consumoDisco;
        this.consumoRam = consumoRam;
        this.totalCpu = totalCpu;
        this.totalDisco = totalDisco;
        this.totalRam = totalRam;
        this.totalJanelasAbertas = totalJanelasAbertas;
        this.dataHora = dataHora;
    }

    public void mostrarHistorico() {

//        telaMonitorDeRecursos.criarTela();
        insertHistorico();
    }

    public void insertHistorico() {

        String sistemasOperacional = (looca.getSistema()).getSistemaOperacional();
        Double totalCpu = (looca.getProcessador().getFrequencia()).doubleValue();
        Double totalDisco = (looca.getGrupoDeDiscos().getTamanhoTotal()).doubleValue();
        Double totalRam = (looca.getMemoria().getTotal()).doubleValue();

        con.update("Insert into hardwares (id, sistemaOperacional, totalCpu, totalDisco, totalRam) values (?,?,?,?,?)", id, sistemasOperacional,totalCpu,totalDisco,totalRam);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Double consumoCpu = (looca.getProcessador().getUso()).doubleValue();
//                Double porcentagemUsoCpu = (consumoCpu) * 100.0;

                Double consumoDisco = (double)((looca.getGrupoDeDiscos().getTamanhoTotal()) / looca.getGrupoDeDiscos().getQuantidadeDeDiscos());
                Double porcentagemUsoDisco =  (consumoDisco / totalDisco) * 100 ;

                Double consumoRam = (looca.getMemoria().getEmUso()).doubleValue();
                Double porcentagemUsoRam = (consumoRam / totalRam) *100;

                Integer janelasAbertas = (looca.getGrupoDeJanelas().getTotalJanelas());





                con.update("INSERT INTO volateis (id ,consumoCpu, consumoDisco, consumoRam, totalJanelas, dataHora, fkHardware) VALUES ( ?, ?, ?, ?, ?, ?, ?)",
                        idVolatil,consumoCpu, consumoDisco, consumoRam, janelasAbertas, dataHora, 1);
                String resultado = "Sistema Operacional | " + sistemasOperacional + "      \n" +
                        "-----------------------------------|\n" +
                        " Consumo ram(%%)     | " + String.format("%.2f", porcentagemUsoRam) + "         \n" +
                        " Consumo cpu(%%)     | " + String.format("%.2f", consumoCpu) + "         \n" +
                        " Consumo disco(%%)   | " + String.format("%.2f", porcentagemUsoDisco) + "         \n" +
                        " Janelas Abertas     | " + janelasAbertas + "              \n" +
                        " Data e hora         | " + dataHora + "\n \n";
                System.out.println(resultado);
            }
        }, 10000, 10000);
    }

    public void insertHistoricoCpu() {

        String sistemasOperacional = (looca.getSistema()).getSistemaOperacional();
        Double totalCpu = (looca.getProcessador().getFrequencia()).doubleValue();
        Double totalDisco = (looca.getGrupoDeDiscos().getTamanhoTotal()).doubleValue();
        Double totalRam = (looca.getMemoria().getTotal()).doubleValue();

        con.update("Insert into hardwares (id, sistemaOperacional, totalCpu, totalDisco, totalRam) values (?,?,?,?,?)", id, sistemasOperacional,totalCpu,totalDisco,totalRam);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Double consumoCpu = (looca.getProcessador().getUso()).doubleValue();
//                Double porcentagemUsoCpu = (consumoCpu) * 100.0;

                Double consumoDisco = (double)((looca.getGrupoDeDiscos().getTamanhoTotal()) / looca.getGrupoDeDiscos().getQuantidadeDeDiscos());
                Double porcentagemUsoDisco =  (consumoDisco / totalDisco) * 100 ;

                Double consumoRam = (looca.getMemoria().getEmUso()).doubleValue();
                Double porcentagemUsoRam = (consumoRam / totalRam) *100;

                Integer janelasAbertas = (looca.getGrupoDeJanelas().getTotalJanelas());





                con.update("INSERT INTO volateis (id ,consumoCpu, consumoDisco, consumoRam, totalJanelas, dataHora, fkHardware) VALUES ( ?, ?, ?, ?, ?, ?, ?)",
                        idVolatil,consumoCpu, consumoDisco, consumoRam, janelasAbertas, dataHora, 1);
                String resultado = "Sistema Operacional | " + sistemasOperacional + "      \n" +
                        "-----------------------------------|\n" +
                        " Consumo cpu(%%)     | " + String.format("%.2f", consumoCpu) +
                        " Data e hora         | " + dataHora + "\n \n";
                System.out.println(resultado);
            }
        }, 10000, 10000);
    }

    public void insertHistoricoRam() {


        String sistemasOperacional = (looca.getSistema()).getSistemaOperacional();
        Double totalCpu = (looca.getProcessador().getFrequencia()).doubleValue();
        Double totalDisco = (looca.getGrupoDeDiscos().getTamanhoTotal()).doubleValue();
        Double totalRam = (looca.getMemoria().getTotal()).doubleValue();

        con.update("Insert into hardwares (id, sistemaOperacional, totalCpu, totalDisco, totalRam) values (?,?,?,?,?)", id, sistemasOperacional,totalCpu,totalDisco,totalRam);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Double consumoCpu = (looca.getProcessador().getUso()).doubleValue();
//                Double porcentagemUsoCpu = (consumoCpu) * 100.0;

                Double consumoDisco = (double)((looca.getGrupoDeDiscos().getTamanhoTotal()) / looca.getGrupoDeDiscos().getQuantidadeDeDiscos());
                Double porcentagemUsoDisco =  (consumoDisco / totalDisco) * 100 ;

                Double consumoRam = (looca.getMemoria().getEmUso()).doubleValue();
                Double porcentagemUsoRam = (consumoRam / totalRam) *100;

                Integer janelasAbertas = (looca.getGrupoDeJanelas().getTotalJanelas());





                con.update("INSERT INTO volateis (id ,consumoCpu, consumoDisco, consumoRam, totalJanelas, dataHora, fkHardware) VALUES ( ?, ?, ?, ?, ?, ?, ?)",
                        idVolatil,consumoCpu, consumoDisco, consumoRam, janelasAbertas, dataHora, 1);
                String resultado = "Sistema Operacional | " + sistemasOperacional + "      \n" +
                        "-----------------------------------|\n" +
                        " Consumo ram(%%)     | " + String.format("%.2f", porcentagemUsoRam) +
                        " Data e hora         | " + dataHora + "\n \n";
                System.out.println(resultado);
            }
        }, 10000, 10000);
    }

    public void insertHistoricoJanelas() {


        String sistemasOperacional = (looca.getSistema()).getSistemaOperacional();
        Double totalCpu = (looca.getProcessador().getFrequencia()).doubleValue();
        Double totalDisco = (looca.getGrupoDeDiscos().getTamanhoTotal()).doubleValue();
        Double totalRam = (looca.getMemoria().getTotal()).doubleValue();

        con.update("Insert into hardwares (id, sistemaOperacional, totalCpu, totalDisco, totalRam) values (?,?,?,?,?)", id, sistemasOperacional,totalCpu,totalDisco,totalRam);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Double consumoCpu = (looca.getProcessador().getUso()).doubleValue();
//                Double porcentagemUsoCpu = (consumoCpu) * 100.0;

                Double consumoDisco = (double)((looca.getGrupoDeDiscos().getTamanhoTotal()) / looca.getGrupoDeDiscos().getQuantidadeDeDiscos());
                Double porcentagemUsoDisco =  (consumoDisco / totalDisco) * 100 ;

                Double consumoRam = (looca.getMemoria().getEmUso()).doubleValue();
                Double porcentagemUsoRam = (consumoRam / totalRam) *100;

                Integer janelasAbertas = (looca.getGrupoDeJanelas().getTotalJanelas());





                con.update("INSERT INTO volateis (id ,consumoCpu, consumoDisco, consumoRam, totalJanelas, dataHora, fkHardware) VALUES ( ?, ?, ?, ?, ?, ?, ?)",
                        idVolatil,consumoCpu, consumoDisco, consumoRam, janelasAbertas, dataHora, 1);
                String resultado = "Sistema Operacional | " + sistemasOperacional + "      \n" +
                        "-----------------------------------|\n" +
                        " Janelas Abertas     | " + janelasAbertas +
                        " Data e hora         | " + dataHora + "\n \n";
                System.out.println(resultado);
            }
        }, 10000, 10000);
    }


    // Outros métodos getter e setter...

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public Double getConsumoCpu() {
        return consumoCpu;
    }

    public void setConsumoCpu(Double consumoCpu) {
        this.consumoCpu = consumoCpu;
    }

    public Double getConsumoDisco() {
        return consumoDisco;
    }

    public void setConsumoDisco(Double consumoDisco) {
        this.consumoDisco = consumoDisco;
    }

    public Double getConsumoRam() {
        return consumoRam;
    }

    public void setConsumoRam(Double consumoRam) {
        this.consumoRam = consumoRam;
    }

    public Double getTotalCpu() {
        return totalCpu;
    }

    public void setTotalCpu(Double totalCpu) {
        this.totalCpu = totalCpu;
    }

    public Double getTotalDisco() {
        return totalDisco;
    }

    public void setTotalDisco(Double totalDisco) {
        this.totalDisco = totalDisco;
    }

    public Double getTotalRam() {
        return totalRam;
    }

    public void setTotalRam(Double totalRam) {
        this.totalRam = totalRam;
    }

    public Integer getTotalJanelasAbertas() {
        return totalJanelasAbertas;
    }

    public void setTotalJanelasAbertas(Integer totalUsbConectado) {
        this.totalJanelasAbertas = totalUsbConectado;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Integer getIdVolatil() {
        return idVolatil;
    }

    public void setIdVolatil(Integer idVolatil) {
        this.idVolatil = idVolatil;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }


    @Override
    public String toString() {

        return "HistConsmRecurso{" +
                "id=" + id +
                "Sistema Operacional=" + sistemaOperacional +
                ", totalCpu=" + totalCpu +
                ", totalDisco=" + totalDisco +
                ", totalRam=" + totalRam +

                ", consumoCpu=" + consumoCpu +
                ", consumoDisco=" + consumoDisco +
                ", consumoRam=" + consumoRam +
                ", dataHora=" + dataHora +
                '}';
    }
}