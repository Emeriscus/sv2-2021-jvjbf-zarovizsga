package videos;

public class Channel {

    private String channelName;
    private int subscribers;
    private int countOfVideos;

    public Channel(String channelName, int subscribers, int countOfVideos) {
        this.channelName = channelName;
        this.subscribers = subscribers;
        this.countOfVideos = countOfVideos;
    }

    public String getChannelName() {
        return channelName;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public int getCountOfVideos() {
        return countOfVideos;
    }
}
