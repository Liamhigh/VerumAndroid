# Verum Omnis – Android Forensic Engine Package

## Overview

This is the complete **Verum Omnis Android Forensic Engine Package**, a production-ready Kotlin application designed for forensic analysis of statements, contradiction detection, and tamper-proof PDF report generation.

### Key Features
- ✅ Complete contradiction engine with nine-brain doctrine implementation
- ✅ Full PDF Sealing Engine with SHA-512 + Watermark + Footer
- ✅ Production-ready Kotlin structure
- ✅ Drop-in ready for Android Studio
- ✅ Fully offline / stateless / forensics-grade
- ✅ No Firebase dependencies

## Package Structure

```
com.verumomnis.forensic/
├── MainActivity.kt                    # Main UI entry point
├── VerumOmnisEngine.kt               # Core orchestrator (Brains 1-9)
├── contradiction/                     # Contradiction Detection Module
│   ├── Claim.kt                      # Data model for statements
│   ├── ClaimExtractor.kt             # Extracts claims from text
│   ├── ContradictionDetector.kt      # Detects contradictions
│   ├── TimelineBrain.kt              # Timeline analysis (future: GPS metadata)
│   ├── BehaviourBrain.kt             # Behavioral stress markers
│   └── NLPUtil.kt                    # NLP utilities
├── pdf/                              # PDF Sealing Engine
│   ├── HashUtil.kt                   # SHA-512 cryptographic hashing
│   ├── PdfWatermark.kt               # Watermark application
│   ├── PdfFooter.kt                  # Footer with hash verification
│   └── PdfSealEngine.kt              # Complete sealed PDF generation
├── model/                            # Data Models
│   └── ForensicResult.kt             # Analysis result container
└── ui/                               # UI Components
    └── Theme.kt                      # Material3 theme
```

## Module Descriptions

### Contradiction Engine
The contradiction engine implements the nine-brain doctrine for forensic analysis:

- **ClaimExtractor**: Processes raw text input and extracts structured claims with entities, time references, and classification
- **ContradictionDetector**: Compares new claims against historical data to identify contradictions
- **BehaviourBrain**: Analyzes text for stress markers and defensive language patterns
- **TimelineBrain**: Foundation for timeline-based impossibility detection (ready for GPS metadata integration)
- **NLPUtil**: Natural language processing utilities for entity extraction and claim classification

### PDF Sealing Engine
Generates tamper-proof, cryptographically sealed PDF reports:

- **HashUtil**: SHA-512 cryptographic hashing for document integrity
- **PdfWatermark**: Applies "VERUM OMNIS" watermark to PDFs
- **PdfFooter**: Adds footer with truncated SHA-512 hash for verification
- **PdfSealEngine**: Orchestrates complete sealed PDF creation with all forensic data

### Core Engine
**VerumOmnisEngine**: Main orchestrator that:
- Maintains claim history across sessions
- Coordinates all analysis modules
- Generates comprehensive forensic reports
- Returns structured results with contradictions and behavioral flags

## Dependencies

The application uses the following key dependencies (configured in `app/build.gradle`):

```gradle
// PDFBox for Android (native + stable)
implementation "com.tom-roush:pdfbox-android:2.0.27.0"

// QR generation
implementation "com.google.zxing:core:3.5.1"

// SHA-512 + crypto
implementation "org.bouncycastle:bcprov-jdk15to18:1.78"

// JSON
implementation "com.google.code.gson:gson:2.11.0"

// Jetpack Compose for UI
implementation "androidx.compose.ui:ui:1.6.0"
implementation "androidx.compose.material3:material3:1.2.0"
```

## Usage

### Basic Usage

```kotlin
// In your activity or composable
val result = VerumOmnisEngine.process(context, inputText)

// Access results
println("Contradictions: ${result.contradictions}")
println("Behavioral Flags: ${result.behaviouralFlags}")
println("PDF Report: ${result.pdfFile.path}")
```

### Example Input/Output

**Input:**
```
I never went to that location.
I was at that place on Tuesday.
```

**Output:**
```
Contradictions: ["Direct contradiction with previous claim: 'I never went to that location.'"]
Behaviour Flags: []
PDF Saved: /data/user/0/com.verumomnis.forensic/files/VO_Sealed_Report.pdf
```

## Building the Application

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24+ (minimum)
- Android SDK 33 (target)
- Kotlin 1.9+

### Build Commands

```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease
```

## Security Features

1. **Cryptographic Integrity**: All PDFs are sealed with SHA-512 hashes
2. **Watermarking**: Visual "VERUM OMNIS" watermark on all reports
3. **Tamper Detection**: Footer includes hash verification
4. **Offline Processing**: No external data transmission
5. **Stateless Design**: No persistent storage of sensitive data

## Future Enhancements

The codebase is structured to support future enhancements:

- **TimelineBrain**: GPS metadata integration for physical impossibility detection
- **ContradictionDetector**: Advanced semantic analysis
- **BehaviourBrain**: Expanded stress marker detection patterns
- **Tax Returns Module**: Financial analysis capabilities
- **Legal Advice Module**: Legal framework integration

## License

Patent Pending - Verum Omnis

## Technical Notes

- Minimum SDK: 24 (Android 7.0)
- Target SDK: 33 (Android 13)
- Kotlin Version: Compatible with 1.5.14
- Compose Version: 1.6.0
- Build System: Gradle 8.2.2

## Architecture

The application follows clean architecture principles:
- **Presentation Layer**: Jetpack Compose UI
- **Domain Layer**: Business logic in Engine and Brain modules
- **Data Layer**: Models and utilities

All modules are designed to be:
- **Testable**: Clear separation of concerns
- **Maintainable**: Single responsibility principle
- **Extensible**: Open for enhancement, closed for modification
