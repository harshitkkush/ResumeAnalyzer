package com.ResumeAnalyzer.backend.Controller;

import com.ResumeAnalyzer.backend.Service.PdfService;
import com.ResumeAnalyzer.backend.Service.ResumeAnalyzerService;
import com.ResumeAnalyzer.backend.dto.ResumeAnalysisResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final PdfService pdfService;
    private final ResumeAnalyzerService analyzerService;

    public ResumeController(
            PdfService pdfService,
            ResumeAnalyzerService analyzerService) {

        this.pdfService = pdfService;
        this.analyzerService = analyzerService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<ResumeAnalysisResponse> analyzeResume(
            @RequestParam("file") MultipartFile file,

            @RequestParam("jobDescription") String jobDescription)
            throws Exception {

        String resumeText = pdfService.extractText(file);

        ResumeAnalysisResponse response = analyzerService.analyzeResume(
                resumeText,
                jobDescription);

        return ResponseEntity.ok(response);
    }
}