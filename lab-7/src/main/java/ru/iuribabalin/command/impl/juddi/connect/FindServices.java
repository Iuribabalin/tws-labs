package ru.iuribabalin.command.impl.juddi.connect;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;
import ru.iuribabalin.Main;

import java.util.List;

@Getter
@Setter
public class FindServices {

    private List<Pair<String, String>> services;

    public void selectService(int index) {
        Main.connectUrl = services.get(index).getRight();
        System.out.println("Selected service: " + Main.connectUrl);
    }

    public void showServices() {
        int cnt = 0;
        for (Pair<String, String> service : services) {
            System.out.println(cnt + ") " + service.getLeft() + " -> url: " + service.getRight());
            cnt += 1;
        }
    }
}
