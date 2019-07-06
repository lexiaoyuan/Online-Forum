package beans;

public class Reply {
    private String reply_id;
    private String guestbook_id;
    private String host_user_name;
    private String guest_user_name;
    private String reply_content;
    private String reply_date;

    public String getReply_id() {
        return reply_id;
    }

    public void setReply_id(String reply_id) {
        this.reply_id = reply_id;
    }

    public String getGuestbook_id() {
        return guestbook_id;
    }

    public void setGuestbook_id(String guestbook_id) {
        this.guestbook_id = guestbook_id;
    }

    public String getHost_user_name() {
        return host_user_name;
    }

    public void setHost_user_name(String host_user_name) {
        this.host_user_name = host_user_name;
    }

    public String getGuest_user_name() {
        return guest_user_name;
    }

    public void setGuest_user_name(String guest_user_name) {
        this.guest_user_name = guest_user_name;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public String getReply_date() {
        return reply_date;
    }

    public void setReply_date(String reply_date) {
        this.reply_date = reply_date;
    }
}
