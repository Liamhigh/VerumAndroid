package com.verumomnis.forensic.pdf

import android.content.Context
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.pdmodel.PDPage
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font
import java.io.ByteArrayOutputStream
import java.io.File

object PdfSealEngine {
    private const val HASH_DISPLAY_LENGTH = 32
    
    fun createSealedPdf(context: Context, text: String): File {
        PDFBoxResourceLoader.init(context)
        
        val outputFile = File(context.filesDir, "VO_Sealed_Report.pdf")
        val document = PDDocument()
        val page = PDPage()
        document.addPage(page)

        val cs = PDPageContentStream(document, page)
        cs.beginText()
        cs.setFont(PDType1Font.HELVETICA, 14f)
        cs.newLineAtOffset(50f, 750f)
        cs.showText(text)
        cs.endText()
        cs.close()

        // Hash
        val baos = ByteArrayOutputStream()
        document.save(baos)
        val hash = HashUtil.sha512(baos.toByteArray())

        PdfWatermark.add(document, "VERUM OMNIS")
        PdfFooter.add(document, "SHA-512: ${hash.take(HASH_DISPLAY_LENGTH)}…")

        document.save(outputFile)
        document.close()

        return outputFile
    }
}
