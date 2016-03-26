package jescobar.geolocalitzacio.network;

/**
 * Created by Pekee on 14/03/2016.
 */
public class Response {

    private boolean status;
    private String message;

    public Response() {

    }

    public Response(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
