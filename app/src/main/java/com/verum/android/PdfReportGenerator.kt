package com.verum.android

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.pdmodel.PDPage
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle
import com.tom_roush.pdfbox.pdmodel.graphics.image.LosslessFactory
import java.io.File
import java.security.MessageDigest

object PdfReportGenerator {
    fun generateReport(
        context: Context,
        report: ContradictionReport,
        logoResId: Int,
        outputFile: File
    ): File {

        PDFBoxResourceLoader.init(context)

        val doc = PDDocument()
        val page = PDPage(PDRectangle.A4)
        doc.addPage(page)

        val cs = PDPageContentStream(doc, page)

        // draw logo
        val logo = BitmapFactory.decodeResource(context.resources, logoResId)
        val pdLogo = LosslessFactory.createFromImage(doc, logo)
        cs.drawImage(pdLogo, 200f, 730f, 150f, 60f)

        var y = 700f
        fun text(t: String, size: Float = 12f) {
            cs.beginText()
            cs.setFont(com.tom_roush.pdfbox.pdmodel.font.PDType1Font.HELVETICA, size)
            cs.newLineAtOffset(40f, y)
            cs.showText(t)
            cs.endText()
            y -= 18f
        }

        text("VERUM OMNIS — CONTRADICTION REPORT", 14f)
        text("Summary: ${report.summary}")

        text("WHO: ${report.answers.who}")
        text("WHAT: ${report.answers.what}")
        text("WHEN: ${report.answers.whenText}")
        text("WHERE: ${report.answers.where}")
        text("HOW: ${report.answers.how}")
        text("WHY: ${report.answers.why}")

        text("Contradictions:")
        report.contradictions.forEach {
            text("- ${it.description}")
        }

        cs.close()
        doc.save(outputFile)
        doc.close()

        val fileBytes = outputFile.readBytes()
        val hash = MessageDigest.getInstance("SHA-512").digest(fileBytes)
            .joinToString("") { "%02x".format(it) }

        // stamp QR + footer
        val doc2 = PDDocument.load(outputFile)
        val pg = doc2.getPage(0)
        val cs2 = PDPageContentStream(doc2, pg,
            PDPageContentStream.AppendMode.APPEND, true)

        val qr = createQr(hash)
        val pdQr = LosslessFactory.createFromImage(doc2, qr)
        cs2.drawImage(pdQr, 450f, 40f, 90f, 90f)

        cs2.beginText()
        cs2.setFont(com.tom_roush.pdfbox.pdmodel.font.PDType1Font.HELVETICA_OBLIQUE, 9f)
        cs2.newLineAtOffset(350f, 140f)
        cs2.showText("✔ Patent Pending Verum Omnis")
        cs2.endText()

        cs2.beginText()
        cs2.setFont(com.tom_roush.pdfbox.pdmodel.font.PDType1Font.HELVETICA, 8f)
        cs2.newLineAtOffset(350f, 125f)
        cs2.showText("SHA-512: ${hash.take(24)}...")
        cs2.endText()

        cs2.close()
        doc2.save(outputFile)
        doc2.close()

        return outputFile
    }

    private fun createQr(text: String): Bitmap {
        val size = 256
        val bits = QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, size, size)
        val bmp = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)

        for (x in 0 until size)
            for (y in 0 until size)
                bmp.setPixel(x, y, if (bits[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt())

        return bmp
    }
}
