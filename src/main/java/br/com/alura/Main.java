package br.com.alura;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final MediaType JSON = MediaType.get("application/json");

    public static void main(String[] args) throws Exception {
        var apiKey = System.getenv("GPT_API_KEY");
        var client = new OkHttpClient();

        var scanner = new Scanner(System.in);
        System.out.println("Digite sua pergunta:");
        var pergunta = scanner.nextLine();

        var mensagem = new ChatMessage("user", pergunta);
        var chatRequest = new ChatRequest("gpt-3.5-turbo", List.of(mensagem));

        var json = new ObjectMapper().writeValueAsString(chatRequest);
        var body = RequestBody.create(json, JSON);
        var request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " +apiKey)
                .post(body)
                .build();
        try (var response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}