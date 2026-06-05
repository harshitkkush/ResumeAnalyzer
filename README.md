# AI Resume Analyzer

An AI-powered Resume Analyzer that evaluates a candidate's resume against a Job Description (JD) and provides detailed insights including skill matching, missing skills, strengths, weaknesses, and improvement suggestions.

## Features

- Upload resume in PDF format
- Paste Job Description (JD)
- Extracts text from resumes automatically
- AI-based resume analysis using Large Language Models
- Skill matching percentage
- Identifies missing skills
- Highlights strengths and weaknesses
- Provides actionable improvement suggestions
- Supports both local LLMs via Ollama and cloud models via Gemini API
- Modern React-based user interface

## Tech Stack

### Frontend

- React.js
- Axios
- HTML/CSS

### Backend

- Spring Boot
- Spring AI
- Java 21
- Maven

### AI & LLM Integration

- Ollama (Local LLMs)
- Google Gemini API
- Prompt Engineering

### PDF Processing

- Apache PDFBox

## Project Structure

```text
ResumeAnalyzer/
├── backend/
│   ├── Controller/
│   ├── Service/
│   ├── dto/
│   └── Config/
│
├── frontend/
│   ├── src/
│   ├── public/
│   └── package.json
│
└── README.md
```

## How It Works

1. User uploads a resume PDF.
2. User enters a Job Description.
3. Backend extracts text from the PDF.
4. A structured prompt is generated.
5. Spring AI sends the prompt to Ollama or Gemini.
6. The AI analyzes the resume against the JD.
7. Results are displayed on the frontend.

## API Endpoint

### Analyze Resume

```http
POST /api/resume/analyze
```

#### Request

- Resume PDF file
- Job Description text

#### Response

```json
{
  "matchPercentage": 85,
  "matchingSkills": ["Java", "Spring Boot", "REST API"],
  "missingSkills": ["Docker", "AWS"],
  "suggestions": ["Add cloud-related projects", "Include Docker experience"]
}
```

## Setup Instructions

### Backend

```bash
cd backend
mvn spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
npm start
```

## Future Enhancements

- Resume scoring dashboard
- ATS compatibility analysis
- Resume keyword optimization
- Multiple resume comparison
- Interview question generation
- Cover letter generation
- Resume improvement recommendations using RAG
- User authentication and analysis history

## Author

Harshit Kushwaha

Software Developer | Java | Spring Boot | React.js | AI Applications
