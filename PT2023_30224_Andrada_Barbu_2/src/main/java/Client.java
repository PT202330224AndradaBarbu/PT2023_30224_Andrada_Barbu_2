public class Client{
    private int id;
    private int timpServire;
    private int timpInceput;

    public Client(int id,int timpInceput,int timpServire) {
        this.id=id;
        this.timpInceput=timpInceput;
        this.timpServire=timpServire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimpInceput() {
        return timpInceput;
    }

    public void setTimpInceput(int timpInceput) {
        this.timpInceput = timpInceput;
    }

    public int getTimpServire() {
        return timpServire;
    }

    public void setTimpServire(int timpServire) {
        this.timpServire = timpServire;
    }

}
