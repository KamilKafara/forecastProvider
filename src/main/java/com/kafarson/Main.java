package com.kafarson;

/**
 * Bardzo dziękujemy za zgłoszenie. Zanim umówimy się na rozmowę, chcielibyśmy poprosić o wykonanie krótkiego zadania (powinno zająć ok. 3 godz.)
 * sprawdzającego praktyczną wiedzę, która będzie potrzebna w tej pracy.
 * Kiedy możemy się go spodziewać?
 * <p>
 * Oto szczegóły zadania:
 * Stworzenie aplikacji serwerowej z wykorzystaniem frameworka Spring (Spring Boot)
 * serwującej użytkownikowi historyczne (miniony tydzień) dane meteorologiczne z publicznego
 * API (https://open-meteo.com).
 * Serwer powinien wystawiać endpoint akceptujący długość i szerokość geograficzną.
 * Zwracane informacje muszą zawierać w sobie średnią ilość opadów danego dnia oraz czas wschodu/zachodu słońca.
 * Aplikacja powinna każdorazowo zapisywać w relacyjnej bazie danych czas oraz parametry wywołania endpointu.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * zwraca
 * https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&past_days=10&hourly=temperature_2m,relativehumidity_2m,windspeed_10m
 */
@SpringBootApplication
@EnableScheduling
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
