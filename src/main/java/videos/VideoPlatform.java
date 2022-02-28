package videos;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VideoPlatform {

    private List<Channel> channels = new ArrayList<>();

    public List<Channel> getChannels() {
        return channels;
    }

    public void readDataFromFile(Path path) {
        try {
            Scanner scanner = new Scanner(path);
            addChannelsToList(scanner);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot open file for read!", ioe);
        }
    }

    private void addChannelsToList(Scanner scanner) {
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String[] temp = scanner.nextLine().split(";");
            channels.add(new Channel(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
        }
    }

    public int calculateSumOfVideos() {
        return channels.stream()
                .mapToInt(Channel::getCountOfVideos)
                .sum();
    }
}
