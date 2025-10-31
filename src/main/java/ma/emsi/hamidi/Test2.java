package ma.emsi.hamidi;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import java.util.Map;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        String cle = System.getenv("GEMINI_API_KEY");

        ChatModel model = GoogleAiGeminiChatModel
                .builder()
                .apiKey(cle)
                .modelName("gemini-2.5-flash")
                .temperature(0.7)
                .build();

        // Le texte de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez saisir le texte à traduire en anglais: ");
        String texte = scanner.nextLine();

        // Création du PromptTemplate
        PromptTemplate template = PromptTemplate.from("Traduis le texte suivant en {{langue}} : {{texte}}");

        // Application du template avec la valeur saisie
        Prompt prompt = template.apply(Map.of(
                        "langue","anglais",
                        "texte" ,texte
                )
        );

        // Envoi du prompt au modèle
        String reponse = model.chat(prompt.text());

        System.out.println("Traduction : " + reponse);

    }
}