package hello;

import java.io.Serializable;

public class MessageWIthId implements Serializable {

    private String message;
    private int id;

    public MessageWIthId(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MessageWIthId{" +
                "message='" + message + '\'' +
                ", id=" + id +
                '}';
    }



}
