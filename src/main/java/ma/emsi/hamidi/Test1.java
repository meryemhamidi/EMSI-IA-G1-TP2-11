package ma.emsi.hamidi;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        String cle = System.getenv("GEMINI_API_KEY");

        ChatModel model = GoogleAiGeminiChatModel
                .builder()
                .apiKey(cle)
                .modelName("gemini-2.5-flash")
                .temperature(0.7)
                .build();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez saisir votre qst: ");
        String question = scanner.nextLine();

        String reponse = model.chat(question);

        System.out.println("Réponse du modèle : " + reponse);
    }
}