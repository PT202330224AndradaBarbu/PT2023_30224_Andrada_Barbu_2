import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Scheduler {

    private List<Server> servere;
    private int nrMaxServere;
    private int nrMaxClientiPerServer;

    private List<Thread> threaduriServere;

    private LoggerWrapper loggerWrapper;

    public Scheduler(int nrMaxServere, int nrMaxClientiPerServer) {
        this.loggerWrapper = LoggerWrapper.getInstance();
        this.nrMaxServere=nrMaxServere;
        this.nrMaxClientiPerServer=nrMaxClientiPerServer;
        this.servere=new ArrayList<>(nrMaxServere);
        this.threaduriServere = new ArrayList<>(nrMaxServere);
        for(int i=0 ;i < nrMaxServere; i++) {
            Server s=new Server(i, nrMaxClientiPerServer);
            servere.add(s);
            Thread thread=new Thread(s);
            threaduriServere.add(thread);
            thread.start();
        }
    }

    public synchronized  void oprireServere() {
        for(Thread t: threaduriServere) {
            t.interrupt();
        }
        for(Server s:servere) {
            if(s.getNrClientiProcesatiDeServer() != 0) {
                LoggerWrapper.myLogger.info("Coada " + s.getId() + " are timpul mediu de asteptare de " + s.getTimpMediuAsteptare());
                GUI.Logg.addString("Coada " + s.getId() + " are timpul mediu de asteptare de " + s.getTimpMediuAsteptare());
                System.out.println("Coada " + s.getId() + " are timpul mediu de asteptare de " + s.getTimpMediuAsteptare());

                LoggerWrapper.myLogger.info("Coada " + s.getId() + " are timpul mediu de servire de " + s.getTimpMediuServire());
                GUI.Logg.addString("Coada " + s.getId() + " are timpul mediu de servire de " + s.getTimpMediuServire());
                System.out.println("Coada " + s.getId() + " are timpul mediu de servire de " + s.getTimpMediuServire());

                LoggerWrapper.myLogger.info("Coada " + s.getId() + " are ora de varf " + s.getOraVarf());
                GUI.Logg.addString("Coada " + s.getId() + " are ora de varf " + s.getOraVarf());
                System.out.println("Coada " + s.getId() + " are ora de varf " + s.getOraVarf());
            } else {
                LoggerWrapper.myLogger.info("Pe coada nu a fost niciodata vreun client, prin urmare timpul mediu de asteptare=timpul mediu de servire=ora de varf=0");
                GUI.Logg.addString("Pe coada nu a fost niciodata vreun client, prin urmare timpul mediu de asteptare=timpul mediu de servire=ora de varf=0");
                System.out.println("Pe coada nu a fost niciodata vreun client, prin urmare timpul mediu de asteptare=timpul mediu de servire=ora de varf=0");
            }
        }
    }
    public synchronized void asociereClient(Client client) {
        Server s=servere.get(0);
        int aux2 = s.getTimpAsteptarePanaLaServire().get();
        for(Server i:servere) {
            int aux1 = i.getTimpAsteptarePanaLaServire().get();
            if(aux1 < aux2) {
                s=i;
            }
        }
        try {
            System.out.println("Clientul "+client.getId()+" a ajuns la "+client.getTimpInceput() +
                    ". S-a asezat la coada "+s.getId()+"  si va astepta un timp de "+s.getTimpAsteptarePanaLaServire()+
                    ". Servirea clientului va dura "+client.getTimpServire()+". Acesta va iesi din coada la "+ s.getTimpIesireClient(client));
            LoggerWrapper.myLogger.info("Clientul "+client.getId()+" a ajuns la "+client.getTimpInceput() +
                    ". S-a asezat la coada "+s.getId()+"  si va astepta un timp de "+s.getTimpAsteptarePanaLaServire()+
                    ". Servirea clientului va dura "+client.getTimpServire()+". Acesta va iesi din coada la "+ s.getTimpIesireClient(client));
            GUI.Logg.addString("Clientul "+client.getId()+" a ajuns la "+client.getTimpInceput() +
                            ". S-a asezat la coada "+s.getId()+"  si va astepta un timp de "+s.getTimpAsteptarePanaLaServire()+
                            ". Servirea clientului va dura "+client.getTimpServire()+". Acesta va iesi din coada la "+ s.getTimpIesireClient(client));
            s.adaugaClient(client);
        } catch (InterruptedException  e) {
            System.out.println("Coada " + s.getId() + "cu timpul cel mai mic de asteptare este plina! Se asteapta pana la eliberarea ei pentru a putea sa se alature clientul " + client.getId());
            LoggerWrapper.myLogger.info("Coada " + s.getId() + "cu timpul cel mai mic de asteptare este plina! Se asteapta pana la eliberarea ei pentru a putea sa se alature clientul " + client.getId());
            GUI.Logg.addString("Coada " + s.getId() + "cu timpul cel mai mic de asteptare este plina! Se asteapta pana la eliberarea ei pentru a putea sa se alature clientul " + client.getId());
        }

    }

    public List<Server> getServere() {
        return servere;
    }

    public void setServere(List<Server> servere) {
        this.servere = servere;
    }

    public int getNrMaxServere() {
        return nrMaxServere;
    }

    public void setNrMaxServere(int nrMaxServere) {
        this.nrMaxServere = nrMaxServere;
    }

    public int getNrMaxClientiPerServer() {
        return nrMaxClientiPerServer;
    }

    public void setNrMaxClientiPerServer(int nrMaxClientiPerServer) {
        this.nrMaxClientiPerServer = nrMaxClientiPerServer;
    }

}
