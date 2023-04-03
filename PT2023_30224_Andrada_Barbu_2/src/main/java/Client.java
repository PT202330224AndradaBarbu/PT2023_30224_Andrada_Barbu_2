public class Client{
    private int id;
    private int timpInceput;
    private int timpFianl;

    public Client(int id,int timpInceput,int timpFianl) {
        this.id=id;
        this.timpInceput=timpInceput;
        this.timpFianl=timpFianl;
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

    public int getTimpFianl() {
        return timpFianl;
    }

    public void setTimpFianl(int timpFianl) {
        this.timpFianl = timpFianl;
    }

}
