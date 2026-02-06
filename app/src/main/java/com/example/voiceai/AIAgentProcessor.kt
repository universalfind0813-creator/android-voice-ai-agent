import com.example.voiceai.GeminiAI;
import com.example.voiceai.VoiceCommand;

class AIAgentProcessor {
    private val geminiAI = GeminiAI()

    fun processVoiceCommand(command: VoiceCommand): String {
        // Process the voice command using Gemini AI integration
        val response = geminiAI.generateResponse(command)
        return response
    }
}