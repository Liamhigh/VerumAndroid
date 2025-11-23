package com.verumomnis.forensic.pdf

import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font

object PdfFooter {
    fun add(document: PDDocument, footer: String) {
        if (document.pages.count == 0) return
        
        val page = document.pages[0]
        val cs = PDPageContentStream(
            document,
            page,
            PDPageContentStream.AppendMode.APPEND,
            true
        )
        cs.beginText()
        cs.setFont(PDType1Font.HELVETICA, 12f)
        cs.newLineAtOffset(30f, 30f)
        cs.showText(footer)
        cs.endText()
        cs.close()
    }
}
