import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

    private BlockingQueue<Client> clienti;

    private AtomicInteger timpAsteptare;

    private int id;

    private int nrClientiProcesatiDeServer; //nr tuturor clientilor

    private double timpMediuAsteptare;

    private double timpMediuServire;

    private long oraVarf;

    private int ultimaActiune;

    public Server(int id, int nrMaxClienti) {
        this.id=id;
        this.timpAsteptare = new AtomicInteger(0);
        this.timpMediuAsteptare=0.0;
        this.timpMediuServire=0.0;
        this.oraVarf=0;
        this.clienti = new LinkedBlockingQueue<>(nrMaxClienti);
        this.nrClientiProcesatiDeServer=0;
        this.ultimaActiune = SimulationManager.timpCurent; //0
    }
    public synchronized void adaugaClient(Client clientNou) throws InterruptedException  {
        clienti.add(clientNou);
        nrClientiProcesatiDeServer++;
        timpMediuServire+=clientNou.getTimpServire();
        timpMediuAsteptare+= getTimpAsteptarePanaLaServire().get();
        int timpAsteptareNou = timpAsteptare.get() + clientNou.getTimpServire();
        timpAsteptare.set(timpAsteptareNou);
        ultimaActiune = SimulationManager.timpCurent;
    }

    @Override
    public void run() {
        int oraVarfInitiala = SimulationManager.timpCurent;
        int oraVarfNoua = SimulationManager.timpCurent;
        int nrMaxClientiPeCoada = clienti.size();
        while(true){
            try {
                if (nrMaxClientiPeCoada < clienti.size()) {
                    nrMaxClientiPeCoada = clienti.size();
                    oraVarfNoua = SimulationManager.timpCurent;
                }
                oraVarf = oraVarfNoua - oraVarfInitiala;
                Client client=clienti.take();
                Thread.sleep(client.getTimpServire()*1000);
                System.out.println("La timpul "+SimulationManager.timpCurent + " o sa iasa clientul "+client.getId()+ " din coada " + id);
                LoggerWrapper.myLogger.info("La timpul "+SimulationManager.timpCurent + " o sa iasa clientul "+client.getId()+ " din coada " + id);
                GUI.Logg.addString("La timpul "+SimulationManager.timpCurent + " o sa iasa clientul "+client.getId()+ " din coada " + id);
                synchronized (this) {
                    int timpAsteptareNou = timpAsteptare.get() - client.getTimpServire();
                    timpAsteptare.set(timpAsteptareNou);
                    ultimaActiune = SimulationManager.timpCurent;
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public BlockingQueue<Client> getClienti() {
        return clienti;
    }

    public void setClienti(BlockingQueue<Client> clienti) {
        this.clienti = clienti;
    }

    public synchronized AtomicInteger getTimpAsteptarePanaLaServire() {
        int timpAsteptareIntreg = timpAsteptare.get();
        if (timpAsteptareIntreg == 0) {
            return timpAsteptare;
        }
        int timpAsteptareLaCoadaCalculat = timpAsteptareIntreg - (SimulationManager.timpCurent - ultimaActiune);
        AtomicInteger timpAsteptareLaCoada = new AtomicInteger(timpAsteptareLaCoadaCalculat);
        return timpAsteptareLaCoada;
    }

    public synchronized int getTimpIesireClient(Client client)
    {
        return(client.getTimpInceput()+client.getTimpServire()+getTimpAsteptarePanaLaServire().get());
    }

    public void setTimpAsteptare(AtomicInteger timpAsteptare) {
        this.timpAsteptare = timpAsteptare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTimpMediuAsteptare() {
        return timpMediuAsteptare/nrClientiProcesatiDeServer;
    }

    public void setTimpMediuAsteptare(double timpMediuAsteptare) {
        this.timpMediuAsteptare = timpMediuAsteptare;
    }

    public double getTimpMediuServire() {
        return timpMediuServire/nrClientiProcesatiDeServer;
    }

    public void setTimpMediuServire(double timpMediuServire) {
        this.timpMediuServire = timpMediuServire;
    }

    public long getOraVarf() {
        return oraVarf;
    }

    public void setOraVarf(long oraVarf) {
        this.oraVarf = oraVarf;
    }

    public int getNrClientiProcesatiDeServer() {
        return nrClientiProcesatiDeServer;
    }

    public void setNrClientiProcesatiDeServer(int nrClientiProcesatiDeServer) {
        this.nrClientiProcesatiDeServer = nrClientiProcesatiDeServer;
    }
}
