import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.speech.v1p1.SpeechClient;
import com.google.cloud.speech.v1p1.RecognizeResponse;
import com.google.protobuf.ByteString;
import com.google.cloud.speech.v1p1.RecognitionConfig;
import com.google.cloud.speech.v1p1.RecognitionAudio;

public class SpeechToTextExample {
    public static void main(String[] args) throws Exception {
        // Caminho para o arquivo de credenciais JSON que você baixou
        String credenciaisArquivo = "caminho-para-credenciais.json";

        // Configurar as credenciais
        CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(
                ServiceAccountCredentials.fromStream(new FileInputStream(credenciaisArquivo)));

        // Criar um cliente de reconhecimento de fala
        try (SpeechClient speechClient = SpeechClient
                .create(SpeechClient.SpeechContext.create().withCredentialsProvider(credentialsProvider))) {
            // Configurar a solicitação de reconhecimento
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                    .setSampleRateHertz(16000)
                    .setLanguageCode("pt-BR") // Idioma (Português do Brasil)
                    .build();

            // Ler o áudio do arquivo (substitua pelo seu próprio áudio)
            ByteString audioBytes = ByteString.readFrom(new FileInputStream("caminho-para-arquivo-de-audio.wav"));

            // Criar uma solicitação de reconhecimento de áudio
            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(audioBytes)
                    .build();

            // Fazer a solicitação de reconhecimento
            RecognizeResponse response = speechClient.recognize(config, audio);

            // Exibir os resultados
            for (SpeechRecognitionAlternative alternative : response.getResultsList().get(0).getAlternativesList()) {
                System.out.println("Transcrição: " + alternative.getTranscript());
            }
        }
    }
}
