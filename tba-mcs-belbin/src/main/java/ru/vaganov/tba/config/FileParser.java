package ru.vaganov.tba.config;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class FileParser<Target> {
    private String source;

    public FileParser(String source) {
        this.source = source;
    }


    /**
     * @param parseLine (str) -> object. Как преобразовать строку в целевой объект
     * @param action (obj) -> void. Действие с объектом после его получение в результате парсинга строки
     */
    public void exec(lineParse<Target> parseLine, onEachAction<Target> action){
        try(InputStream inputStream = getClass().getResourceAsStream(source);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ){
            reader.readLine();
            String line;
            while((line = reader.readLine()) != null){
                Target obj = parseLine.lineToObj(line);
                action.action(obj);
            }

        }catch (IOException e){
            log.error(e.getMessage());
        }
    }

    public interface onEachAction<T>{
        void action(T obj);
    }

    public interface lineParse<T>{
        T lineToObj(String line);
    }
}
