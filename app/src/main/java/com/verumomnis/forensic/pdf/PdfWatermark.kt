package com.verumomnis.forensic.pdf

import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font

object PdfWatermark {
    fun add(document: PDDocument, text: String) {
        val page = document.pages[0]
        val cs = PDPageContentStream(
            document,
            page,
            PDPageContentStream.AppendMode.APPEND,
            true
        )
        cs.beginText()
        cs.setFont(PDType1Font.HELVETICA_BOLD, 70f)
        cs.setNonStrokingColor(180, 180, 180)
        cs.setTextMatrix(1f, 0f, -0.3f, 1f, 150f, 400f)
        cs.showText(text)
        cs.endText()
        cs.close()
    }
}
