package arkaa.health.user.arkaahealthcare.FirebaseChat;

public class FriendlyMessage {

    private String text;
    private String name;
    private String photoUrl;
    private String sender_id ;

    public FriendlyMessage() {
    }

    public FriendlyMessage(String text, String name, String photoUrl,String sender_id) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.sender_id = sender_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }
}
