import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable {
    public int limitaTimp = 20;
    public int timpMaxProcesat = 7;
    public int timpMinProcesat = 2;
    public int numarServere = 2;
    public int numarClienti = 6;

    public int numarMaxClientiPerServer = 20;

    public int timpSosireMin = 1;
    public int timpSosireMax = 5;

    private Scheduler scheduler;
//    private SimulationFrame frame;
    private List<Client> listaClientiGenerati = new ArrayList<>();

    public static int timpCurent = 0;


    public SimulationManager() {
        this.scheduler=new Scheduler(numarServere,numarMaxClientiPerServer);
        generateNRandomClients();
    }

    public SimulationManager(int limitaTimp, int timpMaxProcesat, int timpMinProcesat, int numarServere,
                             int numarClienti, int numarMaxClientiPerServer, int timpSosireMin, int timpSosireMax) {
        this.limitaTimp = limitaTimp;
        this.timpMaxProcesat = timpMaxProcesat;
        this.timpMinProcesat = timpMinProcesat;
        this.numarServere = numarServere;
        this.numarClienti = numarClienti;
        this.numarMaxClientiPerServer = numarMaxClientiPerServer;
        this.timpSosireMin = timpSosireMin;
        this.timpSosireMax = timpSosireMax;
        this.scheduler = new Scheduler(numarServere, numarMaxClientiPerServer);
        generateNRandomClients();
        Thread newThread = new Thread(this);
        newThread.start();
    }

    private void generateNRandomClients() {
        Random r = new Random();
        int clientiGenerati = 0;
        while(clientiGenerati < numarClienti) {
            int timpServire = r.nextInt((timpMaxProcesat - timpMinProcesat)) + timpMinProcesat;
            int timpSosire = r.nextInt((timpSosireMax - timpSosireMin)) + timpSosireMin;
            Client clientNou = new Client(clientiGenerati,timpSosire,timpServire);
            listaClientiGenerati.add(clientNou);
            clientiGenerati++;
        }
    }

    public void run() {
        List<Client> clientiDeSters = new ArrayList<>();
        while( timpCurent<limitaTimp) {
            System.out.println();
            System.out.println("Time: "+timpCurent);
            LoggerWrapper.myLogger.info("Time: "+timpCurent);
            GUI.Logg.addString("Time: "+timpCurent);
            clientiDeSters.clear();
            for(Client c: listaClientiGenerati) {
                if(c.getTimpInceput() == timpCurent) {
                    scheduler.asociereClient(c);
                    clientiDeSters.add(c);
                }
            }
            listaClientiGenerati.removeAll(clientiDeSters);
            System.out.println("Waiting clients: ");
            LoggerWrapper.myLogger.info("Waiting clients: ");
            GUI.Logg.addString("Waiting clients: ");
            for (Client client : listaClientiGenerati) {
                System.out.println("("+client.getId()+","+client.getTimpInceput()+","+client.getTimpServire()+");");
                LoggerWrapper.myLogger.info("("+client.getId()+","+client.getTimpInceput()+","+client.getTimpServire()+");");
                GUI.Logg.addString("("+client.getId()+","+client.getTimpInceput()+","+client.getTimpServire()+");");
            }
            timpCurent++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        scheduler.oprireServere();
    }

    public static void main(String[] args) {
        SimulationManager gen = new SimulationManager();
        Thread t=new Thread(gen);
        t.start();
    }
}
