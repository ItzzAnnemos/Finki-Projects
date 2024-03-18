package mk.ukim.finki.NP.ZadaciZaVezbanje.LogSystem;

public interface ILog {
    public String getMessage();

    public String getType();

    public long getTimestamp();

    public void setMessage(String newMessage);
}
