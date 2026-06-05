package com.ResumeAnalyzer.backend.Service;

import org.springframework.stereotype.Service;

@Service
public class PromptBuilderService {

  public String buildPrompt(
      String resumeText,
      String jobDescription) {

    return """
        Analyze the following resume against the job description.

        RESUME:
        %s

        JOB DESCRIPTION:
        %s

        Return ONLY valid JSON.

        Rules:
        1. Do not use markdown.
        2. Do not use ```json.
        3. suggestions must be an array of strings.
        4. Output must start with { and end with }.

        Example:

        {
          "matchScore": xx,
          "skillsFound": ["xxx", "xxx"],
          "missingSkills": ["xxx", "xxx"],
          "suggestions": [
            "Add xy projects",
            "Mention xy experience"
          ]
        }
        """.formatted(resumeText, jobDescription);
  }
}