
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {
    private static CommonResources comRes = new CommonResources();
    public static void main(String[] args) throws InterruptedException {
        List<Path> resList = new ArrayList<>();
        resList.add(Paths.get("src\\main\\resources\\ресурс 1.txt"));
        resList.add(Paths.get("src\\main\\resources\\ресурс 2.txt"));
        resList.add(Paths.get("src\\main\\resources\\ресурс 3.txt"));

        List<Callable<Path>> taskList = resList.stream()
                .map(res -> (Callable<Path>)() -> {
                    System.out.println(Thread.currentThread().getName() + " working with " + res);
                    Scanner scanner = new Scanner(res, StandardCharsets.UTF_16.name());
                    while (scanner.hasNext() && !comRes.getStop()){
                        comRes.append(scanner.next());
                    }
                    return res;
                })
                .collect(Collectors.toList());

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.invokeAll(taskList);
        executor.shutdown();

        System.out.println();
        if (comRes.getStop()) {
            if (comRes.getDuplicateWord() != null) {
                System.out.println("Ошибка! Слово \"" + comRes.getDuplicateWord() + "\" уже встречается в словаре. Процесс остановлен");
            }
            if (comRes.getIncorrectWord() != null){
                System.out.println("Ошибка! Некорректное \"" + comRes.getIncorrectWord() + "\" слово. Процесс остановлен");
            }
        }else{
            System.out.println("Работа завершена успешно!");
        }
    }
}
