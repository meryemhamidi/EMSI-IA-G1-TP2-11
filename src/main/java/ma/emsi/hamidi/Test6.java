package ma.emsi.hamidi;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import ma.emsi.hamidi.tools.meteo.MeteoTool;

import java.util.Scanner;

public class Test6 {
    interface AssistantMeteo {
        String chat(String userMessage);
    }

    public static void main(String[] args) {
        String cle = System.getenv("GEMINI_API_KEY");

        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(cle)
                .modelName("gemini-2.5-flash")
                .temperature(0.1)
                .logRequestsAndResponses(true)
                .build();

        AssistantMeteo assistant =
                AiServices.builder(AssistantMeteo.class)
                        .chatModel(model)
                        .tools(new MeteoTool())
                        .build();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Posez votre question : ");
                String question = scanner.nextLine();
                if (question.isBlank()) {
                    continue;
                }
                if ("fin".equalsIgnoreCase(question)) {
                    break;
                }
                String reponse = assistant.chat(question);
                System.out.println("Assistant : " + reponse);
            }
        }
    }
}