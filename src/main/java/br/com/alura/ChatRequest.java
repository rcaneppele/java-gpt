package br.com.alura;

import java.util.List;

public record ChatRequest(String model, List<ChatMessage> messages) {
}
