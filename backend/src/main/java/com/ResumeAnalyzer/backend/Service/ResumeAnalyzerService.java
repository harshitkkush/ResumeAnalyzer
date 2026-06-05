package com.ResumeAnalyzer.backend.Service;

import com.ResumeAnalyzer.backend.dto.ResumeAnalysisResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ResumeAnalyzerService {

        private final ChatClient chatClient;
        private final PromptBuilderService promptBuilder;
        private final ObjectMapper objectMapper;

        public ResumeAnalyzerService(
                        ChatClient.Builder builder,
                        PromptBuilderService promptBuilder,
                        ObjectMapper objectMapper) {

                this.chatClient = builder.build();
                this.promptBuilder = promptBuilder;
                this.objectMapper = objectMapper;
        }

        public ResumeAnalysisResponse analyzeResume(
                        String resumeText,
                        String jobDescription)
                        throws Exception {

                String prompt = promptBuilder.buildPrompt(
                                resumeText,
                                jobDescription);

                String response = chatClient.prompt()
                                .user(prompt)
                                .call()
                                .content();
                response = response
                                .replace("```json", "")
                                .replace("```", "")
                                .trim();

                System.out.println("CLEANED RESPONSE:");
                System.out.println(response);

                return objectMapper.readValue(
                                response,
                                ResumeAnalysisResponse.class);
        }
}